package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "music")
public class Music implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 歌曲名字
     */
    @Column(name = "`name`")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 对应文件的url
     */
    @Column(name = "url")
    private String url;

    /**
     * 创建日期
     */
    @Column(name = "create_day")
    private Date createDay;

    /**
     * 歌手名字
     */
    @Column(name = "singer")
    private String singer;

    private static final long serialVersionUID = 1L;
}