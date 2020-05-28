package com.hzu.blog.abstracts;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzu.blog.common.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public abstract class AbstractBaseServiceImpl <T,D extends MyMapper<T>> implements BaseService<T>{

    @Autowired
    protected D dao;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 更新
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        dao.updateByPrimaryKeySelective(entity);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        dao.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param entity
     */
    @Override
    public void insert(T entity) {
        dao.insert(entity);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 分页查询返回的数据
     *
     * @param entity 查询的条件
     * @return
     */
    @Override
    public PageInfo<T> page(PaginationDto paginationDto, T entity) {
        if (paginationDto.getPage() == null)
            paginationDto.setPage(1);
        if (paginationDto.getLimit() == null)
            paginationDto.setLimit(10);
        PageHelper.startPage(paginationDto.getPage(),paginationDto.getLimit());
        return new PageInfo<>(dao.page(entity));
    }

    @Override
    public Integer count(T entity){
        return dao.totalCount(entity);
    }
}
