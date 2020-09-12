package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		if(action==null||"".equals(action)) {
			response.getWriter().write("你提交的是非法数据！");
			return;
		}
		try {
			Method method =  this.getClass().getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
		} catch (Exception e) {
			response.getWriter().write("你提交的是非法数据!!");
			e.printStackTrace();
		}
	}
}
