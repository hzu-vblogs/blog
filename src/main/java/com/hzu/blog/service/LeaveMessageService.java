package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.LeaveMessage;
import com.hzu.blog.domain.User;

import javax.mail.MessagingException;

public interface LeaveMessageService extends BaseService<LeaveMessage> {

    boolean reply(Long id, User user, String content) throws MessagingException;

}
