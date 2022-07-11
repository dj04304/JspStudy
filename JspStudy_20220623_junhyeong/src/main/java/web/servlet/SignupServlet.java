package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import service.UserService;
import web.dto.SignupReqDto;


/*
 * 데이터 요청 방법
 * 
 * 1. form submit 요청(Get 요청 -> params(파라미터), Post 요청 -> body, JSON): Get요청은 사용하지 않음. Get요청은 무조건 href로 한다.
 * 2. AJAX 요청 (동기, 비동기 요청 Get, Post, Put, Delete 모두 가능)
 * 3. 주소창, href 요청, repalce() 요청
 * 
 */

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	
	public SignupServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
	}

	@Override //생성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입 요청");
		
		SignupReqDto signupReqDto = SignupReqDto.builder()
				.name(request.getParameter("name"))
				.email(request.getParameter("email"))
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.build();
	
		
		try {
			PrintWriter out = response.getWriter(); //회원가입을 성공했을 때, alert를 띄운다음 넘어가야한다.
			
			if(userService.createUser(signupReqDto)) {
				System.out.println("회원가입 성공");
				out.print(true);
			}else {
				System.out.println("회원가입 실패");
				out.print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
