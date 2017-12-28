package com.hhu.context;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhu.dto.Accordion;
import com.hhu.entity.User;

@WebFilter
public class UserContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest requ = (HttpServletRequest) request;
		ResponseContext.setCurrent(resp);
		
		if(requ.getRequestURI().contains("login")) {
			Cookie cookie = new Cookie("auth",null);
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			chain.doFilter(request, response);
			return;
		}
		
		if(requ.getRequestURI().contains("index.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(requ.getRequestURI().endsWith(".css")||requ.getRequestURI().endsWith(".js")
				||requ.getRequestURI().endsWith(".jpg")||requ.getRequestURI().endsWith(".gif")) {
			chain.doFilter(request, response);
		}
		
		//设置Cookie的值，如果长度为2就是我们需要处理的值，否则不是Cookie被人串改
		String cookieValue= "";
		if(null!=requ.getCookies()) {
			for(Cookie cookie:requ.getCookies()) {
				if(Objects.equals(cookie.getValue(), "auth")) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		
		byte[] bytes = Base64.getDecoder().decode(cookieValue);
		String auth = new String(bytes);
		
		String[] array = auth.split("\\$");
		if(2==array.length) {
			User user = LoginUserCache.get(array[0]);
			if(null==user) {
				LoginUserHelper helper = WebApplicationContext.getBean(LoginUserHelper.class);
				helper.executeLogin(array[0],array[1]);
				//需要登录，可能过了很长世间，缓存并不在这个机器上，此时需要Cookie加载到机器上
				user = LoginUserCache.get(array[0]);
			}
			if(null!=user&&Objects.equals(user.getPwd(), array[1])) {
				List<Accordion> accordions = LoginUserCache.getAccordions(user.getName());
				requ.setAttribute("accordions", accordions);
				UserContext.setCurrent(user);
				LoginUserCache.setCookie(user);
				chain.doFilter(request, response);
				return;
			} else {
				Cookie cookie = new Cookie("auth", null);
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
				resp.sendRedirect("/index.jsp");
			}
		} else {
			Cookie cookie = new Cookie("auth", null);
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
