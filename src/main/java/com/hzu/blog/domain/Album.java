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

    private static final long serialVersionUID = 1L;
}