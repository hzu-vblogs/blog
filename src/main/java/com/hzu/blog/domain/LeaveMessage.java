package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "leave_message")
public class LeaveMessage implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "content")
    private String content;

    @Column(name = "create_day")
    private Date createDay;

    /**
     * 0
     */
    @Column(name = "is_parent")
    private Boolean isParent;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "is_root")
    private Boolean isRoot;

    /**
     * 留言给哪一个用户
     */
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_response")
    private Boolean isResponse;

    private static final long serialVersionUID = 1L;
}