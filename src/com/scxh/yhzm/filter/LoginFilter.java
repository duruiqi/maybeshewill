package com.scxh.yhzm.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scxh.yhzm.util.CookieUtil;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		String uri = request.getRequestURI();

		Object account = request.getSession().getAttribute("account");
		Object user = request.getSession().getAttribute("user");
		
		
		if (null == account && uri.endsWith("main.jsp")) {
			//读取本地书签如果已经记住了登录密码则放行
			Map<String,String> cookieInfo = new HashMap<String, String>();
			CookieUtil.readCookie(request, cookieInfo);
			if(cookieInfo.size() == 2){
				request.getRequestDispatcher("/adminAction.do?method=adminLogin").forward(request,response);
			}
			else{
			response.sendRedirect(request.getContextPath()
					+ "/backPage/login.jsp");
			}
		}
		else if(uri.contains("frontPage/index.jsp")){
			request.getRequestDispatcher("/mainAction.do?method=showMain").forward(request,response);
		}
		else {
			filterchain.doFilter(request, response);
		}
		// filterchain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}

}
