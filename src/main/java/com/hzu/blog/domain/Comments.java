package com.hzu.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comments implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    @Column(name = "article_id")
    private Long articleId;

    /**
     * 点赞数
     */
    @Column(name = "good_nums")
    private Long goodNums;

    @Column(name = "`name`")
    private String name;

    @Column(name = "email")
    private String email;

    /**
     * 是否是评论的根节点
     */
    @Column(name = "is_root")
    private Boolean isRoot;

    /**
     * 是否被回复
     */
    @Column(name = "is_reply")
    private Boolean isReply;

    @Column(name = "create_day")
    private Date createDay;

    /**
     * 父评论id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 回复评论的人的名称
     */
    @Column(name = "response_name")
    private String responseName;

    //为了方便传参无实际作用
    @Transient
    private Long userId;

    @Transient
    private String title;

    private static final long serialVersionUID = 1L;
}