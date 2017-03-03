package com.duxact.pm.biz.system.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duxact.pm.entity.UserRole;
import com.duxact.pm.biz.system.service.UserRoleService;

/**
 * Created by tanhaican on 16-2-21.
 */
@Controller

@RequestMapping("/role.do")
public class RoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequiresRoles("admin")
    @RequestMapping("role.view")
    public String roleView(Model model) {
        model.addAttribute("roleList", userRoleService.findAll());
        return "/admin/system/role/role";
    }

    @RequiresRoles("admin")
    @RequestMapping("role_add.view")
    public String roleAddView(Model model) {
        model.addAttribute("roleList", userRoleService.findAll());
        return "/admin/system/role/role_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(UserRole role, RedirectAttributes redirectAttributes) {
    	userRoleService.createUserRole(role);
        return "redirect:/role.do/role.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(Integer id, RedirectAttributes redirectAttributes) {
    	userRoleService.deleteUserRole(id);
        return "redirect:/role.do/role.view";
    }

}
