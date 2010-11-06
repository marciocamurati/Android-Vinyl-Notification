package com.android.notification.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.android.notification.service.ParserService;

/**
 * Servlet used at cron
 * @author marcio.camurati
 *
 */
public class AndroidServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3440439138908647092L;
	
	private ApplicationContext context;
	
	public void init(ServletConfig config) throws ServletException {
		context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, java.io.IOException {
		ParserService parserService = (ParserService) context.getBean("parserService");
		
		parserService.validate();
	}

	public void destroy() {
		context = null;
	}
}
