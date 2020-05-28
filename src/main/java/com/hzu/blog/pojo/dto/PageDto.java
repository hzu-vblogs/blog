package com.hzu.blog.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PageDto implements Serializable {
    private Integer pageNum;
    private Long total;
    private Integer size;
    private Integer pages;

}
