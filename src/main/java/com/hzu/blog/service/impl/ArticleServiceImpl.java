package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.common.utils.MarkdownUtils;
import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.Comments;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.mapper.CommentsMapper;
import com.hzu.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends AbstractBaseServiceImpl<Article, ArticleMapper> implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentsMapper commentsMapper;
    @Override
    public List<Article> selectByUserId(Long userId) {
        return articleMapper.selectByUserId(userId);
    }

    @Override
    public void good(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        article.setGoodNums(article.getGoodNums()+1);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public Article lookDetail(Long articleId) {
        Article article = articleMapper.selectOneWithComments(articleId);
        List<Comments> target = sortComments(article.getComments());
        article.setReadNums(article.getReadNums()+1);
        articleMapper.updateByPrimaryKeySelective(article);
        article.setComments(target);
        article.setContent(MarkdownUtils.markdownToHtmlExtensions(article.getContent()));
        return article;
    }

    private List<Comments> sortComments(List<Comments> source){
        if (source == null||source.isEmpty())
            return source;
        List<Comments> target = new ArrayList<>();
        for (Comments comments:source){
            //找到根节点
            if (comments.getIsRoot()!=null&&comments.getIsRoot()){
                target.add(comments);
                //往根节点添加回复子节点
                addChildren(comments,target);
            }
        }
        return target;
    }

    private void addChildren(Comments root,List<Comments> target){
        List<Comments> comments = commentsMapper.selectByParentId(root.getId());
        if (comments!=null&&!comments.isEmpty()){
            for (int i=0;i<comments.size();i++){
                target.add(comments.get(i));
                addChildren(comments.get(i),target);
            }
        }
    }

}
