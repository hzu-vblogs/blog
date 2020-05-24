package com.hzu.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "article")
public class Article implements Serializable {
    /**
     * 文章id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章分类id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 文章描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建日期
     */
    @Column(name = "create_day")
    private Date createDay;

    /**
     * 更新日期
     */
    @Column(name = "update_day")
    private Date updateDay;

    /**
     * 点赞数
     */
    @Column(name = "good_nums")
    private Long goodNums;

    /**
     * 阅读量
     */
    @Column(name = "read_nums")
    private Long readNums;

    /**
     * 是否是推荐文章
     */
    @Column(name = "is_recommend")
    private Boolean isRecommend;


    private List<Comments> comments;

    private static final long serialVersionUID = 1L;
}