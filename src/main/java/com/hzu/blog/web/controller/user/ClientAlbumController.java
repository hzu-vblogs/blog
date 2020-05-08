package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/albums")
public class ClientAlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/index/{userId}")
    public String index(@PathVariable("userId") Long userId){

        return null;
    }

    /**
     * 根据用户id查询所有的文章
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/{userId}")
    public BaseResult articles(@PathVariable("userId") Long userId){
        return BaseResult.success("success",albumService.selectByUserId(userId));
    }

    /**
     * 查看相册信息，可能不会用到
     * @param albumId
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail/{albumId}")
    public BaseResult lookDetail(@PathVariable("albumId") Long albumId){
        return BaseResult.success("success",albumService.lookDetail(albumId));
    }

}
