package com.duxact.pm.biz.workbench.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duxact.pm.biz.workbench.entity.MenuVo;
import com.duxact.pm.biz.workbench.service.MenuService;
import com.duxact.pm.constant.Constants;
import com.duxact.pm.output.BaseResponseVo;
import com.duxact.pm.util.ControllerUtils;

/**
 * Created by tanhaican on 16-2-14.
 */
@Controller
@RequestMapping ("/action/workbench")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/getMenu")
    @ResponseBody
    public BaseResponseVo<Object> login(HttpServletRequest req, HttpSession session) {
    	BaseResponseVo<Object> retVo = new BaseResponseVo<Object>();
		Constants.ResponseCode respCode = null;
		String retMsg = null;
		
		String umCode = ControllerUtils.getUser(session);
		if(null == umCode) {
			respCode = Constants.ResponseCode.FAILED;
			retMsg = "尚未登录或者登录已超时";
		} else {
			try {
				List<MenuVo> menus = menuService.getMyMenus(umCode);
				retVo.setData(menus);
				respCode = Constants.ResponseCode.SUCCESS;
			} catch(Exception e) {
				respCode = Constants.ResponseCode.EXCEPTION;
				retMsg = "获取菜单列表时发生异常";
			}
			
		}
		
        retVo.setRetCode(respCode.getCode());
        retVo.setRetMsg(retMsg);

        return retVo;
    }


}