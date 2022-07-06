package web.servlet.api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import domain.entity.User;


@WebServlet("/api/v1/principal")
public class PrincipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("세션에 저장된 데이터 확인");
		HttpSession session = request.getSession();
		
		//로그인 후 실행하면 session에서 user정보를 들고온다. 
		System.out.println("User정보: " + session.getAttribute("principal"));// user객체가 들어있음, user에는 data어노테이션이 있기 때문에 toString 으로 실행됨
		
		//gson이 parsing해줘서 toString을 받을 수 있게끔해줌
		Gson gson = new Gson();
		String userJson = gson.toJson((User) session.getAttribute("principal")); //principal 이 object형태이기 때문에 User로 다운캐스팅 해줘야한다.
		System.out.println(userJson);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(userJson);
	}

}
