package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// ������ �ҷ�����
		HttpSession session = request.getSession();
		
		// ������ �����
		session.removeAttribute("employee");
		
		// �α��� ���� ��Ű�� �����Ѵ�
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie tempCookie : cookies) {
				if (tempCookie.getName().equals("emp_no")) {
					tempCookie.setMaxAge(0);
					tempCookie.setPath("/");
					response.addCookie(tempCookie);
				}
			}
		}
		
		// �������� �̵��Ѵ�
		response.sendRedirect("Login.jsp");
	}

}
