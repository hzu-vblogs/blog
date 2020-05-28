package com.hzu.blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 名字
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 是否可见，1为可见
     */
    @Column(name = "is_visible")
    private Boolean isVisible;

    @Column(name = "create_day")
    private Date createDay;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    private String firstPic = "/blogs/static/asserts/images/logo.jpg";

    private static final long serialVersionUID = 1L;
}