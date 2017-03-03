package com.duxact.pm.biz.workbench.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duxact.pm.biz.workbench.dao.MenuDao;
import com.duxact.pm.biz.workbench.entity.MenuVo;
import com.duxact.pm.biz.workbench.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao ;
	
	public List<MenuVo> getMyMenus(String umCode) {
		return menuDao.getMyMenus(umCode); 
	}
}
