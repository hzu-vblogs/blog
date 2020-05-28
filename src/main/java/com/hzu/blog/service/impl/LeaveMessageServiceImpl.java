package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.LeaveMessage;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.LeaveMessageMapper;
import com.hzu.blog.service.LeaveMessageService;
import com.hzu.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveMessageServiceImpl extends AbstractBaseServiceImpl<LeaveMessage, LeaveMessageMapper> implements LeaveMessageService{

    @Autowired
    private MailService mailService;

    @Override
    public boolean reply(Long id, User user, String backContent)  {
        LeaveMessage leaveMessage = dao.selectByPrimaryKey(id);
        String email = leaveMessage.getEmail();
        String content = leaveMessage.getContent();
        leaveMessage.setIsParent(true);
        leaveMessage.setIsRoot(false);
        leaveMessage.setIsResponse(true);
        LeaveMessage back = new LeaveMessage();
        back.setIsRoot(false);
        back.setIsParent(false);
        back.setUserId(user.getId());
        back.setCreateDay(new Date());
        back.setEmail(user.getEmail());
        back.setContent(backContent);
        back.setIsResponse(false);
        back.setName(user.getUsername());
        back.setParentId(id);

        dao.insert(back);
        dao.updateByPrimaryKeySelective(leaveMessage);

        mailService.sendSimpleTextMailActual("博主回复您的留言啦",content,new String[]{email},null,null,null);

        return true;
    }

    @Override
    public void deleteById(Long id){
        List<Long> ids = new ArrayList<>();
        delete(id,ids);
        ids.add(id);
        dao.deleteMulti(ids);

    }

    private void delete(Long id, List<Long> ids){
        List<LeaveMessage> leaveMessages = dao.selectByParentId(id);

        if (leaveMessages!=null&&!leaveMessages.isEmpty()){
            for (LeaveMessage leaveMessage:leaveMessages){
                ids.add(leaveMessage.getId());
                delete(leaveMessage.getId(),ids);
            }

        }

    }





}
