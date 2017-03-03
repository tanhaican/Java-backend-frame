package com.duxact.pm.biz.system.service;


import java.util.List;

import com.duxact.pm.entity.UserRole;

/**
 * <p>User: tanhaican
 * <p>Date: 2017-3-2
 * <p>Version: 1.0
 */
public interface UserRoleService {

    public void createUserRole(UserRole role);

    public void updateUserRole(UserRole role);

    public void deleteUserRole(Integer roleId);

    public UserRole findOne(Long roleId);
    
    public List<UserRole> findByRoleName(String roleName);

    public List<UserRole> findSome(UserRole role);
    
    public List<UserRole> findAll();
}
