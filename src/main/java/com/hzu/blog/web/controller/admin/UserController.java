package com.hzu.blog.web.controller.admin;

import com.hzu.blog.abstracts.AbstractController;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.User;
import com.hzu.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends AbstractController<User,UserService> {


    /**
     * 跳转列表页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list")
    public String list() {
        return "admin/system-form";
    }

    @RequestMapping(value = "/message")
    @Override
    public String form(){
        return "admin/user-form";
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
    public BaseResult save(User entity) {
        return null;
    }

    /**
     * 保存信息
     *
     * @param entity
     * @return
     */

    @RequestMapping(value = "/save")
    @ResponseBody
    public BaseResult save(User entity, HttpSession session) {
        if (entity == null)
            return BaseResult.fail("请求参数错误");
        if (entity.getId() == null){
            service.insert(entity);
        }else{
            if (StringUtils.isEmpty(entity.getPassword()))
                entity.setPassword(null);
            service.update(entity);
            if (session.getAttribute("user")!=null){
                session.setAttribute("user",service.getById(entity.getId()));
            }
        }

        return BaseResult.success("保存成功");
    }

    /**
     * 删除单条数据
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult delete(Long id) {
        return super.delete(id);
    }

    /**
     * 分页查询
     *
     * @param paginationDto
     * @param entity
     * @return
     */
    @Override
    @RequestMapping(value = "/page")
    @ResponseBody
    public BaseResult page(PaginationDto paginationDto, User entity) {
        return super.page(paginationDto, entity);
    }

    @RequestMapping(value = "/unlock")
    @ResponseBody
    public BaseResult unlock(Long id) {
        service.unLock(service.getById(id));
        return BaseResult.success();
    }


}
