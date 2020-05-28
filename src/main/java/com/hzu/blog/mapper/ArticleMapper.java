package com.hzu.blog.mapper;

import com.hzu.blog.domain.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {
    List<Article> selectByUserId(Long userId);

    List<Article> selectByUserIdAndRecommend(Long userId);

    List<Article> selectByUserIdWithCateGory(@Param("userId") Long userId, @Param("title") String title, @Param("categoryId") Long categoryId);

    Article selectOneWithComments(Long articleId);

}