package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 세션을 불러오고
		HttpSession session = request.getSession();
		
		// 세션을 지운다
		session.removeAttribute("employee");
		
		// 페이지를 이동한다
		// response.sendRedirect("main.jsp");
	}

}
