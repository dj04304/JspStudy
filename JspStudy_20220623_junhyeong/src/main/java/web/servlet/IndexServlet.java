package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 요청의 종류
 * 
 * 1.view(HTML) 요청 -> 무조건 get요청(주소창)
 * 2.api(Application Programming Interface) 요청 -> CRUD(post, get, put, delete): JavaScript(AJAX 동기, 비동기요청)
 * api란 기능을 수행하는 하나의 프로그램이며, 필요시에 요청하여 그 기능을 사용하는 것을 말한다.
 * 
 * 
 */

@WebServlet("/index")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}

}
