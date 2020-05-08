package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Comments;
import com.hzu.blog.mapper.CommentsMapper;
import com.hzu.blog.pojo.dto.CommentsDto;
import com.hzu.blog.service.CommentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentsServiceImpl extends AbstractBaseServiceImpl<Comments, CommentsMapper> implements CommentsService{
    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    @Transactional
    public void comments(CommentsDto commentsDto) {
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsDto,comments);
        if (comments.getParentId() ==null){
            comments.setIsRoot(true);
        }else{
            //找到父级评论设置为已回复
            Comments parent = commentsMapper.selectByPrimaryKey(comments.getParentId());
            parent.setIsReply(true);
            commentsMapper.updateByPrimaryKeySelective(parent);
            comments.setIsRoot(false);
        }
        comments.setIsReply(false);
        comments.setCreateDay(new Date());
        commentsMapper.insert(comments);
    }


    @Override
    public void good(Long commentsId) {
        Comments comments = commentsMapper.selectByPrimaryKey(commentsId);
        comments.setGoodNums(comments.getGoodNums()+1);
        commentsMapper.updateByPrimaryKeySelective(comments);
    }


}
