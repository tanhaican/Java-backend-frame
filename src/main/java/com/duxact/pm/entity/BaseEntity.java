package com.duxact.pm.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 6773045764859673207L;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
