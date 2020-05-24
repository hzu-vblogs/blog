package com.hzu.blog.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "`user`")
public class User implements Serializable {
    /**
     * 用户 id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "`password`")
    private String password;

    /**
     * 个人介绍
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 头像
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 1表示用户被锁定
     */
    @Column(name = "lock_status")
    private Boolean lockStatus;

    private static final long serialVersionUID = 1L;
}