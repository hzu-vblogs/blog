package com.hzu.blog.web.controller.admin;

import cn.hutool.core.io.FileUtil;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.Article;
import com.hzu.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Validated
@CrossOrigin("*")
public class UploadController {
    public static String UPLOAD_PIC_PATH = "D://temp-rainy/";
//    public static String UPLOAD_PIC_PATH = "/usr/temp-rainy/";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("uploadPic")
    public BaseResult uploadPic(@NotNull MultipartFile file, HttpServletRequest request){

        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));  // 后缀名
        //确定图片存放的位置，并且文件名用uuid替代避免重复
        String fiePath = UPLOAD_PIC_PATH+ UUID.randomUUID().toString().replaceAll("-","")+suffixName;

        //判断目录是否存在不存在则创建
        File directory = new File(UPLOAD_PIC_PATH);
        if (!directory.exists()){
            directory.mkdir();
        }
        System.out.println(fiePath);
        //确定文件的位置
        File dest = new File(fiePath);
        //利用springmvc的api将文件转换到dest中

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/blogs/temp-rainy/" + dest.getName();
        return BaseResult.success("上传成功",filename);
    }

    /**
     * {
     *     success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     *     message : "提示的信息，上传成功或上传失败及错误信息等。",
     *     url     : "图片地址"        // 上传成功时才返回
     * }
     * //返回的参数success的值不是字符串“0”和“1”，而是数字0和1，后台返回的时候一定要注意
     * @param file
     * @param
     * @return
     */
    @PostMapping(value = "/uploadfile")
    public @ResponseBody Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletResponse response) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //保存
        response.setContentType("application/json; charset=UTF-8");
        try {

            //获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));  // 后缀名
            //确定图片存放的位置，并且文件名用uuid替代避免重复
            String fiePath = UPLOAD_PIC_PATH+ UUID.randomUUID().toString().replaceAll("-","")+suffixName;

            //判断目录是否存在不存在则创建
            File directory = new File(UPLOAD_PIC_PATH);
            if (!directory.exists()){
                directory.mkdir();
            }
            System.out.println(fiePath);
            //确定文件的位置
            File dest = new File(fiePath);
            //利用springmvc的api将文件转换到dest中

            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filename = "/blogs/temp-rainy/" + dest.getName();
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url",filename);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap.get("success"));
        return resultMap;
    }

    @RequestMapping("download")
    public void downloadMd(HttpServletResponse response,Long id){
        Article article = articleMapper.selectByPrimaryKey(id);
        String fileName = article.getTitle()+".md";
        String content = article.getContent();
        response.setContentType("text/plain");

        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ServletOutputStream outputStream = null;
        BufferedOutputStream buffer = null;

        try {
            outputStream = response.getOutputStream();
            buffer = new BufferedOutputStream(outputStream);
            buffer.write(content.getBytes("UTF-8"));
            buffer.flush();
            buffer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("uploadArticle")
    public BaseResult uploadPic(@NotNull MultipartFile file){
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));  // 后缀名
        //确定图片存放的位置，并且文件名用uuid替代避免重复
        String fiePath = UPLOAD_PIC_PATH+ UUID.randomUUID().toString().replaceAll("-","")+suffixName;

        //判断目录是否存在不存在则创建
        File directory = new File(UPLOAD_PIC_PATH);
        if (!directory.exists()){
            directory.mkdir();
        }
        System.out.println(fiePath);
        //确定文件的位置
        File dest = new File(fiePath);
        //利用springmvc的api将文件转换到dest中
        String string = null;
        try {
            file.transferTo(dest);
             string = FileUtil.readString(dest, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return BaseResult.fail("上传失败");
        }
        String filename =  dest.getName();
        redisTemplate.opsForValue().set(filename,string);
        return BaseResult.success("上传成功",filename);
    }




}
