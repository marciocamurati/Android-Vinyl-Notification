package com.android.notification;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Android_Vinyl_NotificationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
