package nyla.solutions.blueprint.jee.web.security;

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

import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

public class SecurityFilter implements Filter 
{
	public void init(FilterConfig config) throws ServletException 
	{
	}//---------------------------------------------
	public void destroy() 
	{
		//config = null;
	}//---------------------------------------------
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) 
	throws IOException, ServletException 
	{
		System.out.println("Starting SecurityFilter.doFilter");
		
		if(!(servletRequest instanceof HttpServletRequest)
			|| !(servletResponse instanceof HttpServletResponse))
		{
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
			
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		
		String uri = request.getRequestURI();
		Debugger.println(this,"uri="+uri);
		
		//only protect secure paths
		/*if(!Text.matches(uri,this.securePathRegExp))
		{
			chain.doFilter(request, response);
			return;
		}*/
		
		//authorize request
		HttpSession session = request.getSession(false);	
		
		Debugger.println(this,"Session="+session);
		if(session == null || session.isNew())
		{
			//request.getRequestDispatcher(this.loginPage).forward(request, response);
			Debugger.println(this,"redirecting to the login page");
			response.sendRedirect(request.getContextPath()+this.loginPage);
			return;
		}
		
		try 
		{
			//get user name
			SecurityGuard guard = (SecurityGuard)ServiceFactory.getInstance().create(SecurityGuard.class);		
			if(!guard.authorized(session))
			{
				Debugger.println(this,"SC_FORBIDDEN");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			
			chain.doFilter(request, response);
		} 
		catch (SecurityException e) 
		{			
			Debugger.printWarn(e);
			session.invalidate();
			//users needs to re-login
			response.sendRedirect(request.getContextPath()+this.loginPage);
		}
	}//---------------------------------------------

	
	//private String securePathRegExp = Config.getProperty(getClass(),"securePathRegExp");
	
	private String loginPage = Config.getProperty(getClass(),"loginPage","/login.jsf");
	
}
