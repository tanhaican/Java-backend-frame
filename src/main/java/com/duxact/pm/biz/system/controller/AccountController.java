package com.duxact.pm.biz.system.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duxact.pm.biz.system.service.UserInfoService;

/**
 * Created by tanhaican on 16-2-16.
 */
@Controller
@RequestMapping("account.do")
public class AccountController {
    
    @Autowired
    private UserInfoService userInfoService;

    @RequiresAuthentication
    @RequestMapping("profile.view")
    public String profileView() {
        return "/admin/system/account/profile";

    }

    @RequiresAuthentication
    @RequestMapping("update")
    public String update(HttpSession session, String password) {
        String umCode = (String) session.getAttribute("umCode");
        userInfoService.changePassword(umCode, password);
        return "redirect:/account.do/profile.view";
    }
}
