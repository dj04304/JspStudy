package web.servlet.api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.UserServiceImpl;

@WebServlet("/check/username")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	
	public CheckUsernameServlet() {
		userService = new UserServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // request안에 세션이 들어있다. 세션에 user정보를 넣어두면 아무리 탭을 열고 꺼도, 로그인상태가 따라다닌다.
		
		String username = request.getParameter("username"); //파라미터를 username으로 받는다. 아이디 중복체크이기 때문에 username만 필요하다.
		
		response.setContentType("text/plain;charset=UTF-8"); //필터역할, 응답을 받을 때, 한글텍스트가 깨지지 않기 위함
		
		try {
			response.getWriter().print(userService.checkUsername(username));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
