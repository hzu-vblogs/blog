package com.hzu.blog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据接收bean
 */
@Data
public class PaginationDto implements Serializable {

    private Integer limit; //数据量

    private Integer page; //当前页面

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
