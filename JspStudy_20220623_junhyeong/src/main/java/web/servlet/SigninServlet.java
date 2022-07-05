package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.entity.User;
import service.UserService;
import service.UserServiceImpl;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserService userService;
   
	
    public SigninServlet() {
    	userService = new UserServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//로그인 페이지로 보내주는 용
		request.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//로그인 요청시
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		response.setContentType("text/palin;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			user = userService.loadUser(username, password); //여기서 exception이 뜨면 아래 if가 작동하지않는다. finally에 쓰기위해 user = null이라고 따로 빼준다.(지역변수에 걸림)
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(user == null) {
				out.print(false);
			}else {
				out.print(true);
			}
		}
				
	}

}
