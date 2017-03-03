package com.duxact.pm.entity;

/**
 * <p>User: tanhaican
 * <p>Date: 2017-3-1
 * <p>Version: 1.0
 */
public class UserRole extends BaseEntity {
	private static final long serialVersionUID = 5024117630353375036L;
	private Integer roleId; //编号
    private String roleName;
    private String comment;
    private Integer status;
    private Long order;
    private Integer isSysLevel;
    private String description;
    
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public Integer getIsSysLevel() {
		return isSysLevel;
	}
	public void setIsSysLevel(Integer isSysLevel) {
		this.isSysLevel = isSysLevel;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", roleName=" + roleName + ", comment=" + comment + ", status=" + status
				+ ", order=" + order + ", isSysLevel=" + isSysLevel + ", description=" + description + "]";
	}

    
}
