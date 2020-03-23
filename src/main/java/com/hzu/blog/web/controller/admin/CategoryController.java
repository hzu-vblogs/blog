package com.hzu.blog.web.controller.admin;

import com.hzu.blog.abstracts.AbstractController;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.Category;
import com.hzu.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends AbstractController<Category, CategoryService> {
    /**
     * 跳转列表页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "/list")
    public String list() {
        return "admin/category-table";
    }

    /**
     * 跳转到表单页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "/form")
    public String form() {
        return "admin/category-form";
    }

    /**
     * 跳转详情页
     *
     * @return
     */
    @Override
    public String detail() {
        return null;
    }

    /**
     * 保存信息
     *
     * @param entity
     * @return
     */
    @Override
    public BaseResult save(Category entity) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param paginationDto
     * @param entity
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/page")
    public BaseResult page(PaginationDto paginationDto, Category entity) {
        return super.page(paginationDto, entity);
    }

    /**
     * 删除单条数据
     *
     * @param id
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/delete")
    public BaseResult delete(Long id) {
        return super.delete(id);
    }
}
