package com.hzu.blog.web.controller.admin;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@Validated
public class CodeController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    private MailService mailService;
    @RequestMapping("/code")
    public void defaultKaptcha(HttpSession session, HttpServletResponse response)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute("code", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    @RequestMapping(value = "/register/code")
    public BaseResult registerCode(@NotNull String email, HttpSession httpSession){
        String createText = defaultKaptcha.createText();
        try {
            mailService.sendSimpleTextMailActual("vblogs系统注册验证码",createText,new String[]{email},null,null,null);
        }catch (Exception e){
            return BaseResult.fail("邮箱错误");
        }
        httpSession.setAttribute("registerCode",createText);
        return BaseResult.success("发送成功");
    }
}
