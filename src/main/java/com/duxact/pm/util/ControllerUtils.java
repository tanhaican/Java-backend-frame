package com.duxact.pm.util;

import javax.servlet.http.HttpSession;

public class ControllerUtils {

	public static String getUser(HttpSession session) {
		String umCode = (String) session.getAttribute("umCode");
		
		return umCode;
	}
}
