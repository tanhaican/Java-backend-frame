package com.duxact.pm.constant;

public class Constants {
	public static enum ResponseCode {
		FAILED(0),
		SUCCESS(1),
		EXCEPTION(-1);
		
		private int code;
		
		private ResponseCode(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return this.code;
		}
	}
}
