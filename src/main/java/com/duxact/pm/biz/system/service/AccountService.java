package com.duxact.pm.biz.system.service;

import java.lang.reflect.InvocationTargetException;

import com.duxact.pm.entity.custom.UserVo;

/**
 * Created by tanhaican on 16-2-14.
 */
public interface AccountService {

	public UserVo findByUmAndPassword(String umCode, String password) throws IllegalAccessException, InvocationTargetException;

    // public void updatePassword(String id, String password);
}
