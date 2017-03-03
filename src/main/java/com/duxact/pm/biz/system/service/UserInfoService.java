package com.duxact.pm.biz.system.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.duxact.pm.entity.UserInfo;
import com.duxact.pm.entity.UserRole;
import com.duxact.pm.entity.custom.UserVo;

/**
 * Created by tanhaican on 16-2-9.
 */
public interface UserInfoService {

    public void add(UserInfo user);
    
    public void delete(Integer id);
    
    public void deleteByUm(String umCode);

    public void update(UserInfo user);

    public void changePassword(String umCode, String password);

    public UserVo findById(Integer id) throws IllegalAccessException, InvocationTargetException;

    public UserVo findByUm(String umCode) throws IllegalAccessException, InvocationTargetException;
    
    public List<UserVo> findByUserName(String userName) throws IllegalAccessException, InvocationTargetException;

    public List<UserVo> findAll() throws IllegalAccessException, InvocationTargetException;
    
    public UserRole findUserRoleByUm(String umCode) throws IllegalAccessException, InvocationTargetException ;
}
