package com.duxact.pm.biz.system.dao;

import java.util.List;

import com.duxact.pm.entity.UserInfo;

/**
 * Created by tanhaican on 16-2-9.
 */
public interface UserInfoDao {
    public void add(UserInfo user);
    
    public void delete(Integer id);
    
    public void deleteByUm(String umCode);

    public void update(UserInfo user);

    public void updatePassword(UserInfo userInfo);

    public UserInfo findById(Integer id);

    public UserInfo findByUm(String umCode);
    
    public UserInfo findByUmAndPassword(UserInfo userInfo);
    
    public List<UserInfo> findByUserName(String userName);

    public List<UserInfo> findAll();
    
}