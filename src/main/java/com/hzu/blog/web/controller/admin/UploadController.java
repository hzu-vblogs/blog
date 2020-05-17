package com.hzu.blog.web.controller.admin;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.pojo.dto.MDDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController("uploadPic")
@Validated
public class UploadController {
    public static String UPLOAD_PIC_PATH = "D://temp-rainy/";
//    public static String UPLOAD_PIC_PATH = "/usr/temp-rainy/";


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
     * @param request
     * @return
     */
    @RequestMapping("mdUploadPic")
    public MDDto mdUploadPic(@NotNull @RequestParam(value = "editormd-image-file") MultipartFile file, HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html; charset=UTF-8");

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

        MDDto mdDto = new MDDto();
        mdDto.setMessage("上传成功");
        mdDto.setSuccess(1);
        mdDto.setUrl(filename);
        return mdDto;
    }


}
