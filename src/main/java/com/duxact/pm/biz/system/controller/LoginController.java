package com.duxact.pm.biz.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duxact.pm.biz.system.service.UserInfoService;
import com.duxact.pm.constant.Constants;
import com.duxact.pm.output.BaseResponseVo;

/**
 * Created by tanhaican on 16-2-14.
 */
@Controller
@RequestMapping ("/action")
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponseVo<Object> login(HttpServletRequest req, HttpSession session) {
    	BaseResponseVo<Object> retVo = new BaseResponseVo<Object>();
		@SuppressWarnings("rawtypes")
		Class authException = (Class) req.getAttribute("shiroLoginFailure");
		Constants.ResponseCode respCode = null;
        if(null != authException) {
        	String exceptionClassName = authException.getName();
            String error = null;
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "账号不存在";
                respCode = Constants.ResponseCode.FAILED; 
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
                respCode = Constants.ResponseCode.FAILED;
            } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                error = "账户被锁";
                respCode = Constants.ResponseCode.FAILED;
            } else if (ShiroException.class.getName().equals(exceptionClassName)) {
                error = "未知错误";
                respCode = Constants.ResponseCode.EXCEPTION;
            } else if (exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
                respCode = Constants.ResponseCode.EXCEPTION;
            } else {
            	error = "未知错误";
                respCode = Constants.ResponseCode.FAILED;
            }
            
            retVo.setRetMsg(error);
        } else {
        	org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            boolean isAuthenticated = subject.isAuthenticated();

            if (isAuthenticated) {
                String principal = (String) subject.getPrincipal();
                respCode = Constants.ResponseCode.SUCCESS;
                session.setAttribute("umCode", principal);
                retVo.setRetMsg("登录成功");
            } else {
            	respCode = Constants.ResponseCode.FAILED;
                retVo.setRetMsg("登录失败");
            }
        }
        retVo.setRetCode(respCode.getCode());

        return retVo;
    }


}