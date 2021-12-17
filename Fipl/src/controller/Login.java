package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		// 체크박스에 체크가 있으면 True, 없으면 null이 된다
		String logincheck = request.getParameter("logincheck");
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(emp_no, emp_pw);

		response.setCharacterEncoding("euc-kr");
		PrintWriter out = response.getWriter();

		if (vo != null) {
			System.out.println("로그인 성공");

			// 세션 객체 생성
			HttpSession session = request.getSession();
			// 세션 값 설정
			session.setAttribute("employee", vo);
			
			if (logincheck != null) {
				// 로그인 유지 체크가 되어 있다면 -> 로그인 유지하기
				Cookie cookie = new Cookie("emp_no", emp_no);
				cookie.setMaxAge(60); // 유지되는 시간
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			// 로그인 성공시 이동할 페이지
			response.sendRedirect("Main.jsp");

			// 안드로이드 로그인
			Gson gson = new Gson();
			String result = gson.toJson(vo);
			out.print(result);

		} else {
			System.out.println("로그인 실패");

			// 로그인 실패시 이동할 페이지
			response.sendRedirect("Login.html");

			// 안드로이드 로그인 실패때 뜨는 문구
			out.print("fail");
		}
	}

}