package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 1L;
}