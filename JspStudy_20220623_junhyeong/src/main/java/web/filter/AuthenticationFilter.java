package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;


@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


	public AuthenticationFilter() {
       
    }

	
	public void destroy() {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//전처리
		if(req.getRequestURI().contains("signin") || req.getRequestURI().contains("signup")) { //Url은 http부터 끝까지 모두포함, Uri는 뒤에 요청부분("/index")
			HttpSession session =  req.getSession(); //요청이 들어오면 session안을 검사하라
			if(session.getAttribute("principal") != null) { //null이 아니면 로그인 되어있다는 뜻
				resp.sendRedirect("/index");
				return; //메소드를 빠져나가야하기 때문에 return해준다.
			}
		}
		
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		//ServletContextConfig.getInstance();
		fConfig.getServletContext();
	}
	

}
