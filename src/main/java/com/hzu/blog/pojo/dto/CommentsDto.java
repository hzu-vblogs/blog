package com.hzu.blog.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsDto {
    /**
     * 评论内容
     */
    private String content;

    private Long articleId;

    private String name;

    private String email;

    //回复评论的id
    private Long parentId;
}
