package com.example.demo.controller;


import com.example.demo.entity.Pageh;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.until.JsonUtils;
import com.example.demo.until.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired

    @RequestMapping("/finduserAdd")
    public String findadd() {
        return "forward:/WEB-INF/views/user/userAdd.jsp";
    }

    @RequestMapping("/finduserList")
    public String findselect() {
        return "forward:/WEB-INF/views/user/userList.jsp";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String useradd(User user, Model model){
        userService.add(user);
        return "forward:/WEB-INF/views/user/userList.jsp";
    }

    @ResponseBody
    @RequestMapping("/userselect")
    public void select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Page page = new Page();
        Pageh pageh = new Pageh();
        String pageNow = request.getParameter("pageNow");
        String pageSize = request.getParameter("pageSize");

        if (null == pageNow || "".equals(pageNow.trim())) {
            pageNow = "1";
        }
        if (null == pageSize || "".equals(pageNow.trim())) {
            pageSize = page.getPageSize() + "";
        }
        int pageCount = 0;
        String name = request.getParameter("accm");

        pageh.setPageNow((Integer.parseInt(pageNow)-1)*Integer.parseInt(pageSize));
        pageh.setPageSize(Integer.parseInt(pageSize));
        name=name.replaceAll("_","\\\\_");
        pageh.setObject1(name);
        List<User> list = userService.query();
        response.setCharacterEncoding("utf-8");
        page.setList(list);
        page.setPageCount(pageCount);
        page.setPageNow(Integer.parseInt(pageNow));
        String parseJSON = JsonUtils.beanToJson(page);
        response.getWriter().write(parseJSON);
    }

    /**
     * 根据name删除数据
     *
     * @param name
     */
    @RequestMapping(value = "/userDelete")
    public String delete(String name) {
        userService.delete(name);
        return "forward:/WEB-INF/views/user/userList.jsp";
    }

    /**
     * 得到要修改的数据
     *
     */
    @RequestMapping(value = "/userUpdate")
    public String update(User user, Model model) {
        User ter = userService.getById(user.getId());
        model.addAttribute("user", ter);
        return "forward:/WEB-INF/views/user/userupdateSave.jsp";
    }
    /**
     * 修改数据
     *
     */
    @RequestMapping(value = "/userUpdateSave")
    public String updateSave(User user) {
        userService.update(user.getName(),user.getId());
        return "forward:/WEB-INF/views/user/userList.jsp";
    }
}
