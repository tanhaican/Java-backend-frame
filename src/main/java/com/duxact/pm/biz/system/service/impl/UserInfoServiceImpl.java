package com.duxact.pm.biz.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duxact.pm.entity.UserInfo;
import com.duxact.pm.entity.UserRole;
import com.duxact.pm.entity.custom.UserVo;
import com.duxact.pm.biz.system.dao.UserInfoDao;
import com.duxact.pm.biz.system.dao.UserRoleDao;
import com.duxact.pm.biz.system.service.UserInfoService;
import com.duxact.pm.util.PasswordHelper;

/**
 * Created by tanhaican on 16-2-9.
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userDao;

    @Resource
    UserRoleDao UserRoleDao;

    public void add(UserInfo user) {
    	PasswordHelper.encryptPassword(user.getUmCode(), user.getPassword(), user.getSalt());
        userDao.add(user);
    }
    
    @Transactional
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void deleteByUm(String umCode) {
        userDao.deleteByUm(umCode);
    }
    
    @Override
    public void update(UserInfo user) {
        userDao.update(user);
    }

    @Override
    public void changePassword(String umCode, String password) {
    	UserInfo user = userDao.findByUm(umCode);
    	user.setPassword(password);
    	PasswordHelper.encryptNewPassword(user);
        userDao.updatePassword(user);
    }
    
    @Override
    public UserVo findById(Integer id) throws IllegalAccessException, InvocationTargetException {
        UserInfo userInfo = userDao.findById(id);

        if(null == userInfo) {
        	return null;
        }

        long roleId = userInfo.getRoleId();

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userVo, userInfo);

        UserRole role = UserRoleDao.findOne(roleId);

        userVo.setRoleId(role.getRoleId());
        userVo.setRoleName(role.getRoleName());

        return userVo;
    }
    
    @Override
    public UserVo findByUm(String umCode) throws IllegalAccessException, InvocationTargetException {
    	UserInfo userInfo = null;
    	try {
    		userInfo = userDao.findByUm(umCode);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

        if(null == userInfo) {
        	return null;
        }

        long roleId = userInfo.getRoleId();

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userVo, userInfo);

        UserRole role = UserRoleDao.findOne(roleId);

        userVo.setRoleId(role.getRoleId());
        userVo.setRoleName(role.getRoleName());

        return userVo;
    }


    @Override
    public List<UserVo> findByUserName(String userName) throws IllegalAccessException, InvocationTargetException {
        List<UserVo> userVoList = null;
        List<UserInfo> userList = userDao.findByUserName(userName);

        if(null == userList) {
        	return null;
        }
        userVoList = new ArrayList<>();
        Iterator<UserInfo> iterator = userList.iterator();

        while (iterator.hasNext()) {
            UserInfo user = (UserInfo) iterator.next();
            long roleId = user.getRoleId();

            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userVo, user);

            UserRole role = UserRoleDao.findOne(roleId);

            userVo.setRoleId(role.getRoleId());
            userVo.setRoleName(role.getRoleName());

            userVoList.add(userVo);
        }

        return userVoList;
    }
    
    @Override
    public List<UserVo> findAll() throws IllegalAccessException, InvocationTargetException {
        List<UserVo> userVoList = null;
        List<UserInfo> userList = userDao.findAll();

        if(null == userList) {
        	return null;
        }
        userVoList = new ArrayList<>();
        Iterator<UserInfo> iterator = userList.iterator();

        while (iterator.hasNext()) {
            UserInfo user = (UserInfo) iterator.next();
            long roleId = user.getRoleId();

            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userVo, user);

            UserRole role = UserRoleDao.findOne(roleId);

            userVo.setRoleId(role.getRoleId());
            userVo.setRoleName(role.getRoleName());

            userVoList.add(userVo);
        }

        return userVoList;
    }
    
    @Override
    public UserRole findUserRoleByUm(String umCode) throws IllegalAccessException, InvocationTargetException {
    	UserInfo userInfo = userDao.findByUm(umCode);

        if(null == userInfo) {
        	return null;
        }

        long roleId = userInfo.getRoleId();

        UserRole role = UserRoleDao.findOne(roleId);

        return role;
    }

}

