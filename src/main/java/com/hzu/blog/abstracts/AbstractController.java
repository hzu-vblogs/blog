package com.hzu.blog.abstracts;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractController<T,S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转列表页
     *
     * @return
     */
    public abstract String list();

    /**
     * 跳转到表单页
     *
     * @return
     */
    public abstract String form();

    /**
     * 跳转详情页
     *
     * @return
     */
    public abstract String detail();


    /**
     * 保存信息
     *
     * @param entity
     * @return
     */
    public abstract BaseResult save(T entity);

    /**
     * 删除单条数据
     * @param id
     * @return
     */
    public BaseResult delete(Long id){
        BaseResult baseResult = BaseResult.success("删除用户成功");
        if (id != null)
            service.deleteById(id);
        else {
            baseResult = BaseResult.fail("传入参数不能为null");
        }
        return baseResult;
    };

    /**
     * 分页查询
     *
     * @return
     */
    public BaseResult page(PaginationDto paginationDto, T entity) {
        List<T> page = service.page(paginationDto,entity).getList();

        return BaseResult.success("加载分页数据成功",page,service.count(entity));
    }
}
