package com.duxact.pm.biz.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duxact.pm.entity.UserRole;
import com.duxact.pm.biz.system.dao.UserRoleDao;
import com.duxact.pm.biz.system.service.UserRoleService;

/**
 * <p>User: tanhaican
 * <p>Date: 2017-3-2
 * <p>Version: 1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDao roleDao;

	@Override
	public void createUserRole(UserRole role) {
		roleDao.createUserRole(role);
	}

	@Override
	public void updateUserRole(UserRole role) {
		roleDao.updateUserRole(role);
	}

	@Override
	public void deleteUserRole(Integer roleId) {
		roleDao.deleteUserRole(roleId);
	}

	@Override
	public UserRole findOne(Long roleId) {
		return roleDao.findOne(roleId);
	}

	@Override
	public List<UserRole> findByRoleName(String roleName) {
		return roleDao.findByRoleName(roleName);
	}

	@Override
	public List<UserRole> findSome(UserRole role) {
		return roleDao.findSome(role);
	}

	@Override
	public List<UserRole> findAll() {
		return roleDao.findAll();
	}

}
