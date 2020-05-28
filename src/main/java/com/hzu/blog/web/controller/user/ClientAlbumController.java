package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.Album;
import com.hzu.blog.service.AlbumService;
import com.hzu.blog.service.UserService;
import com.hzu.blog.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("albums")
public class ClientAlbumController {
    @Autowired
    private UserService userService;

    @Autowired
    private AlbumService albumService;
    @Autowired
    private VisitLogService visitLogService;
    @RequestMapping("/{userId}")
    public String index(@PathVariable("userId") Long userId, Model model, HttpSession httpSession, HttpServletRequest request){
        httpSession.setAttribute("myUser",userService.getById(userId));
        List<Album> albums = albumService.selectByUserId(userId);
        visitLogService.saveLog(request, userId);
        if (albums==null||albums.isEmpty()){
            model.addAttribute("msg","博主暂无相册");
            return "user/404";
        }

        model.addAttribute("albums",albums);
        return "user/album";
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
