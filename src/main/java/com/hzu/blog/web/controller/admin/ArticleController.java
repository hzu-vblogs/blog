package com.hzu.blog.web.controller.admin;

import com.hzu.blog.abstracts.AbstractController;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.Article;
import com.hzu.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/admin/article")
public class ArticleController extends AbstractController<Article, ArticleService> {


    @ModelAttribute
    public Article getArticle(Long id){
        if (id != null){
            Article article = service.getById(id);
            return article;
        }

        return new Article();
    }

    /**
     * 跳转列表页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list")
    public String list() {
        return "admin/article-list";
    }

    @Override
    @RequestMapping(value = "form")
    public String form(){
        return "admin/article";
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
    @RequestMapping(value = "/save")
    @ResponseBody
    public BaseResult save(Article entity) {
        if (entity.getUserId() == null)
            return BaseResult.fail("用户id不能为空");

        if (entity.getId() == null){
            entity.setCreateDay(new Date());
            entity.setUpdateDay(entity.getCreateDay());
            entity.setGoodNums(0L);
            entity.setReadNums(0L);
            service.insert(entity);
        }else{
            service.update(entity);
        }
        return BaseResult.success();
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
    public BaseResult page(PaginationDto paginationDto, Article entity) {
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
}
