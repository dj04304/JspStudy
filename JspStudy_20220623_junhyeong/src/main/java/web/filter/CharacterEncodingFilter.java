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

//모든 요청에 적용하라 -> /*
@WebFilter("/*")
public class CharacterEncodingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//전 처리
		System.out.println("전 처리");
		HttpServletRequest httpRequest = (HttpServletRequest)request; //ServletRequest는 HttpServletRequest가 업캐스팅 되어 들어온것이다. 그래서 
																		//getMethod()를 가져오기 위해선 다시 HttpServletRequest으로 다운캐스팅 해줘야 한다.
		
		if(!httpRequest.getMethod().equalsIgnoreCase("get")) { //equalsIgnoreCase 는 대 소문자를 구분하지 않는다.
			request.setCharacterEncoding("UTF-8"); //body로 post를 들고오면, 한글이 깨져서 들어온다. 그래서 Parameter를 가져오기 이전에 인코딩을 해주는 역할이다.			
			System.out.println("인코딩 됨!");
		}
		
		chain.doFilter(request, response); //서블릿
		//후 처리
		System.out.println("후 처리");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
