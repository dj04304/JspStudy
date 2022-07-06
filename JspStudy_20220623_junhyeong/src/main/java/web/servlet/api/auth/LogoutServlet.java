package web.servlet.api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); //객체를 강제로 만료시키는 함수
		response.sendRedirect("/index"); //만료 후, index의 위치로 보내준다. forword 처럼 요청을 날리는 역할인데, forword는 request response 를 들고간다면,
										//sendRedirect는 url에 그대로 검색한 것 처럼 보인다
	}

}
