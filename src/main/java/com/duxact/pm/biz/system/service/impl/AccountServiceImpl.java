package com.duxact.pm.biz.system.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.duxact.pm.biz.system.dao.UserInfoDao;
import com.duxact.pm.biz.system.service.AccountService;
import com.duxact.pm.entity.UserInfo;
import com.duxact.pm.entity.custom.UserVo;
import com.duxact.pm.util.PasswordHelper;

/**
 * Created by tanhaican on 16-2-14.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    UserInfoDao userDao;
    
    @Override
    public UserVo findByUmAndPassword(String umCode, String password) throws IllegalAccessException, InvocationTargetException {
    	UserInfo userInfo = userDao.findByUm(umCode);
    	
    	String enPassword = PasswordHelper.encryptPassword(umCode, password, userInfo.getSalt());
    	if(!userInfo.getPassword().equals(enPassword)) {
    		return null;
    	}
    	UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userVo, userInfo);
    	
        return userVo;
    }

}
