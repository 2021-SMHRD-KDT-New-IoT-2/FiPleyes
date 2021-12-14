package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(emp_no, emp_pw);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if(vo != null) {
			System.out.println("로그인 성공");
			
			// 세션 객체 생성
			HttpSession session = request.getSession();
			// 세션 값 설정
			session.setAttribute("employee", vo);
			
			//로그인 성공시 이동할 페이지
			//response.sendRedirect(".html");
			
			// 안드로이드 로그인 
			Gson gson = new Gson();
			String result = gson.toJson(vo);
			out.print(result);
			
		}else {
			System.out.println("로그인 실패");
			
			//로그인 실패시 이동할 페이지
			//response.sendRedirect(".html");
			
			// 안드로이드 로그인 실패때 뜨는 문구
			out.print("fail");
		}
	}

}