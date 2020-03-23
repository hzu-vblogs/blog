package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "visit_log")
public class VisitLog implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 访问ip
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 请求路径
     */
    @Column(name = "request_url")
    private String requestUrl;

    /**
     * 访问时间
     */
    @Column(name = "create_day")
    private Date createDay;

    /**
     * 访问状态，0代表失败，1代表成功
     */
    @Column(name = "visit_status")
    private Boolean visitStatus;

    @Column(name = "user_id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}