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

@WebServlet("/Login_android")
public class Login_android extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("euc-kr");
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		// 체크박스에 체크가 있으면 True, 없으면 null이 된다
		//String logincheck = request.getParameter("logincheck");
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(emp_no, emp_pw);

		response.setCharacterEncoding("euc-kr");
		PrintWriter out = response.getWriter();

		if (vo != null) {
			System.out.println("로그인 성공");

			// 안드로이드 로그인
			Gson gson = new Gson();
			String result = gson.toJson(vo);
			out.print(result);

		} else {
			System.out.println("로그인 실패");
			out.print("fail");
		}
	}

}
