package com.hzu.blog.abstracts;

import com.github.pagehelper.PageInfo;
import com.hzu.blog.common.dto.PaginationDto;

import java.util.List;

public interface BaseService<T> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> selectAll();


    /**
     * 分页查询返回的数据
     * @param entity     查询的条件
     * @return
     */
    PageInfo<T> page(PaginationDto paginationDto, T entity);

    Integer count(T entity);

}
