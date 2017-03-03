package com.duxact.pm.realm;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.duxact.pm.biz.system.service.UserInfoService;
import com.duxact.pm.entity.custom.UserVo;

/**
 * <p>User: tanhaican
 * <p>Date: 2017-3-01
 * <p>Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
    private UserInfoService userService;

    @Override
    /**
     * 授权信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String umCode = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserVo userVo = null;
		try {
			userVo = userService.findByUm(umCode);
		} catch (IllegalAccessException e) {
			throw new UnknownAccountException("未知错误：IllegalAccess");
		} catch (InvocationTargetException e) {
			throw new UnknownAccountException("未知错误：InvocationTarget");
		}
		
		if(userVo == null) {
            throw new AccountException("账户不存在");//没找到帐号
        }
		
        Set<String> userRoles = new HashSet<String>();
        userRoles.add(userVo.getRoleName());
        authorizationInfo.setRoles(userRoles);
        authorizationInfo.setStringPermissions(null);//TODO 增加Permission
        return authorizationInfo;
    }

    @Override
    /**
     * 认证信息
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String umCode = (String)token.getPrincipal();

        UserVo user = null;
		try {
			user = userService.findByUm(umCode);
		} catch (IllegalAccessException e) {
			throw new ShiroException("未知错误：IllegalAccess");
		} catch (InvocationTargetException e) {
			throw new ShiroException("未知错误：InvocationTarget");
		} catch(Exception e) {
			throw new ShiroException("未知错误：Other");
		}

        if(user == null) {
            throw new UnknownAccountException("账号不存在");//没找到帐号
        }
        
        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUmCode(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=umCode + salt
                getName()  //realm name
        );
        return authenticationInfo;
    }


}