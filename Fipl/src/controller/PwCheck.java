package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeVO;

@WebServlet("/PwCheck")
public class PwCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
	
		boolean check = false;
		EmployeeVO vo = null;
		String check_emp_pw =request.getParameter("check_emp_pw");
		
		//현재 로그인한 사용자의 정보
		HttpSession session = request.getSession();
		vo = (EmployeeVO)session.getAttribute("employee");
		String emp_pw = vo.getEmp_pw(); // 사용자 세션 비밀번호 

		
		// 에이젝스 사용해서 비밀번호 체크하는 방법
		if(check_emp_pw.equals(emp_pw)) {
			check = true;
			//출력 스트림(통로)
			PrintWriter out = response.getWriter();
			//통로를 통해서 응답데이터를 출력
			out.print(check);
		}
		
	}
}
