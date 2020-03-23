package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 1L;
}