package com.hzu.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 分类的名字
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 分类的排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 分类的创建日期
     */
    @Column(name = "create_day")
    private Date createDay;

    @Column(name = "user_id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}