package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import domain.entity.User;
import service.UserService;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserService userService;
   
	
    public SigninServlet() {
    	userService = ServletContextConfig.getInstance().getUserService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//로그인 페이지로 보내주는 용
		List<Cookie> cookies = Arrays.asList(request.getCookies()); //배열을 리스트로 바꿔줌
		
		cookies.forEach(cookie -> {
			if(cookie != null) {
				if(cookie.getName().equals("username")) {
					request.setAttribute("username", cookie.getValue());
				}else if(cookie.getName().equals("password")){
					request.setAttribute("password", cookie.getValue());
				}else if(cookie.getName().equals("maintain")) {
					request.setAttribute("maintain", cookie.getValue());
				}
			}
		});
		
		request.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//로그인 요청시
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String maintainFlag = request.getParameter("maintain");
		
		
		
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
				HttpSession session = request.getSession();
				System.out.println("session id: " + session.getId());
				
				System.out.println("session CreationTime: " 
							+ (new SimpleDateFormat("HH:mm:ss")).format(session.getCreationTime()));
				
				System.out.println("session LastAccessedTime: " 
							+ (new SimpleDateFormat("HH:mm:ss")).format(session.getLastAccessedTime())); //로그인 유지시간을 getLastAccessedTime으로 계산한다.
				
				//세션의 만료시간 설정 60*10 은 10분
				//session.setMaxInactiveInterval(30); //기본적으로 30분이다.(초단위) 세션에 값이 들어온다고 해서 매번 초기화되는것이 아니다.
				
				//세션에 값 저장하는 방법
				session.setAttribute("principal", user); //getAttribute 할 때, principal(key값)을 주면 user값을 가져온다.
				
				//체크박스가 true이면 쿠키에 정보 저장
				if(maintainFlag.equalsIgnoreCase("true")) { 
					List<Cookie> cookies = new ArrayList<Cookie>();
				cookies.add(new Cookie("username", username));	
				cookies.add(new Cookie("password", password));
				cookies.add(new Cookie("maintain", "true"));
				
				cookies.forEach(cookie -> {
					cookie.setMaxAge(60 * 60 * 24 *30); //60초 * 60초 1시간 * 24시간 * 30 => 한달
					response.addCookie(cookie); //response에 차례로 cookie를 넣어줌
					
				});
				
				}
				
				
				out.print(true);
			}
		}
				
	}

}
