package domain;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LoginRequest loginBean = (LoginRequest) ((HttpServletRequest)request).getSession().getAttribute("loginRequest");
        if(loginBean == null || !loginBean.isLoggedIn() ){
        	System.out.println("user holder is" + loginBean);
        	System.out.println("login bean is" + loginBean.isLoggedIn());
        	String contextPath = ((HttpServletRequest)request).getContextPath();
        	((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
        }     
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
