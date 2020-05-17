package com.hzu.blog.web.controller.user;

import com.github.pagehelper.PageInfo;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.LeaveMessage;
import com.hzu.blog.mapper.LeaveMessageMapper;
import com.hzu.blog.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/leave/message")
public class ClientLeaveMessageController {
    @Autowired
    private LeaveMessageService leaveMessageService;
    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    /**
     * 根据用户id查询所有的文章
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/{userId}")
    public BaseResult articles(@PathVariable("userId") Long userId, PaginationDto paginationDto){
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setUserId(userId);
        PageInfo<LeaveMessage> page = leaveMessageService.page(paginationDto, leaveMessage);
        List<LeaveMessage> list = page.getList();

        return BaseResult.success("success",sortComments(list));
    }


    private List<LeaveMessage> sortComments(List<LeaveMessage> source){
        if (source == null||source.isEmpty())
            return source;
        List<LeaveMessage> target = new ArrayList<>();
        for (LeaveMessage comments:source){
            //找到根节点
            if (comments.getIsRoot()!=null&&comments.getIsRoot()){
                target.add(comments);
                //往根节点添加回复子节点
                addChildren(comments,target);
            }
        }
        return target;
    }

    private void addChildren(LeaveMessage root,List<LeaveMessage> target){
        List<LeaveMessage> comments = leaveMessageMapper.selectByParentId(root.getId());
        if (comments!=null&&!comments.isEmpty()){
            for (int i=0;i<comments.size();i++){
                target.add(comments.get(i));
                addChildren(comments.get(i),target);
            }
        }
    }
}
