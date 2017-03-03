package com.duxact.pm.entity.custom;

import com.duxact.pm.entity.UserInfo;

/**
 * Created by tanhaican on 16-2-21.
 */
public class UserVo extends UserInfo {
	private static final long serialVersionUID = 1318815397625225142L;
	private String roleName;
	
	public UserVo(){}
	
	public UserVo(String umCode, String password) {
		super(umCode, password);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
