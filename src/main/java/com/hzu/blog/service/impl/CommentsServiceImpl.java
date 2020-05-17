package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Comments;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.CommentsMapper;
import com.hzu.blog.pojo.dto.CommentsDto;
import com.hzu.blog.service.CommentsService;
import com.hzu.blog.service.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentsServiceImpl extends AbstractBaseServiceImpl<Comments, CommentsMapper> implements CommentsService{
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private MailService mailService;
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

    @Override
    @Transactional
    public boolean reply(Long id, User user, String content) {
        Comments comments = dao.selectByPrimaryKey(id);
        comments.setIsReply(true);
        comments.setResponseName(user.getUsername());

        Comments back = new Comments();
        back.setIsReply(false);
        back.setCreateDay(new Date());
        back.setName(user.getUsername());
        back.setIsRoot(false);
        back.setArticleId(comments.getArticleId());
        back.setContent(content);
        back.setParentId(comments.getId());
        back.setEmail(user.getEmail());
        back.setGoodNums(0L);
        dao.insert(back);
        dao.updateByPrimaryKeySelective(comments);
        mailService.sendSimpleTextMailActual("博主回复您的评论啦",content,new String[]{comments.getEmail()},null,null,null);

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
        List<Comments> comments = dao.selectByParentId(id);

        if (comments!=null&&!comments.isEmpty()){
            for (Comments comment:comments){
                ids.add(comment.getId());
                delete(comment.getId(),ids);
            }

        }

    }


}
