package com.hzu.blog.web.controller.admin;

import com.hzu.blog.abstracts.AbstractController;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.Category;
import com.hzu.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Controller
@Validated
@RequestMapping(value = "/admin/category")
public class CategoryController extends AbstractController<Category, CategoryService> {


    @ModelAttribute
    public Category category(Long id){
        if (id!=null)
            return service.getById(id);
        return new Category();
    }


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
    @ResponseBody
    @RequestMapping(value = "/save")
    public BaseResult save(Category entity) {
        if (entity.getId()!=null){
            service.update(entity);
        }else {
            entity.setCreateDay(new Date());
            service.insert(entity);
        }

        return BaseResult.success("保存成功");
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
        System.out.println(entity);
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


    @ResponseBody
    @RequestMapping(value = "/{userId}")
    public BaseResult getByUserId(@NotNull(message = "用户id不能为空") @PathVariable(value = "userId") Long userId) {
        System.out.println(userId);
        return BaseResult.success("获取成功",service.getCategoryByUserId(userId));

    }

}
