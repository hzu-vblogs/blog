package com.hzu.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "album_pic")
public class AlbumPic implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "create_day")
    private Date createDay;

    /**
     * 所属相册id
     */
    @Column(name = "album_id")
    private Long albumId;

    private static final long serialVersionUID = 1L;
}