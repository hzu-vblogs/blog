package com.hzu.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    @JsonIgnore
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

    private static final long serialVersionUID = 1L;
}