package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginControllerFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)throws IOException, ServletException { 
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		if(username==null){
			response.sendRedirect(request.getContextPath()+"/login_user.jsp");
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
}
