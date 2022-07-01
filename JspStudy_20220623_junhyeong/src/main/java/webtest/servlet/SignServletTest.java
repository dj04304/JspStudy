package webtest.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import servicetest.UserServiceTest;
import servicetest.UserServiceTestImpl;
import webtest.dto.SignupReqTestDto;


@WebServlet("/signtest")
public class SignServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserServiceTest userServiceTest;
	
	public SignServletTest() {
		userServiceTest = new UserServiceTestImpl();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signuptest.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입 정보");
		
		SignupReqTestDto signupReqTestDto = SignupReqTestDto.builder()
					.name(request.getParameter("name"))
					.email(request.getParameter("email"))
					.username(request.getParameter("username"))
					.password(request.getParameter("password"))
					.build();
		
		try {
			userServiceTest.createUser(signupReqTestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
