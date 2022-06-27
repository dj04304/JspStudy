package web.servlet.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/username")
public class GetUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException { // init은 최초 생성시에 1회 실행된다.
		System.out.println("최초 1회만 실행");
	}


	public void destroy() {
		System.out.println("서블릿 객체가 소멸될 때 1회만 실행");
	}

	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("서비스 호출");
//	}

	
	//요청마다 어떤 작업을 할지 나눠야 하기 때문에 CRUD로 나눠준다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("test");
		String name = request.getParameter("name");
		System.out.println("Get요청 들어옴");
		System.out.println("Read");
		
		System.out.println("test: " + t);
		System.out.println("name: " + name);
		
		request.setAttribute("name", name);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user.jsp"); //연결할 대상을 RequestDispatcher가 선택을 한다. 즉, 중간다리 역할을 해주는 것이다.
		dispatcher.forward(request, response);//forward 는 그대로 전달해주는 역할을 한다.
		
		
		
		
//		response.setContentType("text/html; charset=UTF-8"); //응답내용의 타입을 정해주는 것이 (text)이다.
//		response.setCharacterEncoding("UTF-8"); //UTF-8 로 encodeing후 응답.
//		response.getWriter().println("<html><head></head><body>"); //실행하면 내용을 읽어옮
//		response.getWriter().println("<h1>안녕하세요</h1><p>" + name + "</p></body></html>"); //실행하면 내용을 읽어옮
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post요청 들어옴");
		System.out.println("Create");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Put요청 들어옴");
		System.out.println("Update");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Delete요청 들어옴");
		System.out.println("Delete");
	}
	
}
