package com.duxact.pm.output;

public class BaseResponseVo<T> {
	/**
	 * 0: 失败
	 * 1： 成功
	 * -1： 异常
	 */
	private int retCode;
	private String retMsg;
	private T data;
	
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
