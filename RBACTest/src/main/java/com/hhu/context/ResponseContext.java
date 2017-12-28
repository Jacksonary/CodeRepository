package com.hhu.context;

import javax.servlet.http.HttpServletResponse;

public class ResponseContext {
	
	private static ThreadLocal<HttpServletResponse> tl = new ThreadLocal<>();
	
	public static void setCurrent(HttpServletResponse response) {
		tl.set(response);
	}
	
	public static HttpServletResponse getCurrent() {
		return tl.get();
	}

}
