package com.hzu.blog.web.controller.user;

import com.hzu.blog.domain.AlbumPic;
import com.hzu.blog.service.AlbumPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "album/pics")
public class ClientAlbumPicController {
    @Autowired
    private AlbumPicService albumPicService;


    @RequestMapping("/{albumId}")
    public String articles(@PathVariable("albumId") Long albumId, Model model){
        List<AlbumPic> albumPics = albumPicService.selectByAlbumId(albumId);
        if (albumPics == null||albumPics.isEmpty()){
            model.addAttribute("msg","相册暂无图片");
            return "user/404";
        }
        model.addAttribute("albumPics",albumPics);
        return "user/my_picture";
    }
}
