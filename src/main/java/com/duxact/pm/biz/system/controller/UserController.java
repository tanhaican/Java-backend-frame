package com.duxact.pm.biz.system.controller;

import java.lang.reflect.InvocationTargetException;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duxact.pm.entity.UserInfo;
import com.duxact.pm.biz.system.service.UserInfoService;
import com.duxact.pm.biz.system.service.UserRoleService;

/**
 * Created by tanhaican on 16-2-9.
 */

@Controller
@RequiresRoles("admin")
@RequestMapping("user.do")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRoleService userRoleService;

    @RequiresRoles("admin")
    @RequestMapping("user.view")
    public String userView(Model m) throws InvocationTargetException, IllegalAccessException {
        m.addAttribute("userList", userInfoService.findAll());
        return "/admin/system/user/user";
    }

    @RequiresRoles("admin")
    @RequestMapping("user_add.view")
    public String userAddView(Model m) {
        m.addAttribute("roleList", userRoleService.findAll());
        return "/admin/system/user/user_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("findById")
    public String findById(Integer id, Model m) {
        try {
			m.addAttribute("user", userInfoService.findById(id));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        return "/admin/system/user/user_update";
    }

    @RequiresRoles("admin")
    @RequestMapping("update")
    public String update(UserInfo user) {
    	userInfoService.update(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(UserInfo user) {

    	userInfoService.add(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(Integer id) {
    	userInfoService.delete(id);
        return "redirect:/user.do/user.view";
    }

}
