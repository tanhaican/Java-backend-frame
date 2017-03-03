package com.duxact.pm.biz.workbench.dao;

import java.util.List;

import com.duxact.pm.biz.workbench.entity.MenuVo;

/**
 * Created by tanhaican on 16-2-9.
 */
public interface MenuDao {

    public List<MenuVo> getMyMenus(String umCode);

}