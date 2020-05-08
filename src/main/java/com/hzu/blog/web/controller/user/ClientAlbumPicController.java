package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.service.AlbumPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("album/pics")
public class ClientAlbumPicController {
    @Autowired
    private AlbumPicService albumPicService;


    @RequestMapping("/{albumId}")
    public BaseResult articles(@PathVariable("albumId") Long albumId){
        return BaseResult.success("success",albumPicService.selectByUserId(albumId));
    }
}
