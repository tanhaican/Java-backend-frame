package com.duxact.pm.biz.workbench.service;


import java.util.List;

import com.duxact.pm.biz.workbench.entity.MenuVo;

/**
 * Created by tanhaican on 16-2-9.
 */
public interface MenuService {

	public List<MenuVo> getMyMenus(String umCode);
}
