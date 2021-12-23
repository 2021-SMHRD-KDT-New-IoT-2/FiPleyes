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

import model.EmployeeDAO;
import model.EmployeeVO;
import model.SendEmail;

@WebServlet("/SendPwEmail")
public class SendPwEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		String emp_name = request.getParameter("emp_name");
		String emp_phone = request.getParameter("emp_phone");
		String emp_email = request.getParameter("emp_email");
	
		EmployeeDAO dao = new EmployeeDAO();
		boolean checkEmp = dao.checkEmp(emp_name, emp_phone, emp_email);
		
		if(checkEmp) {
			String temp_pw = dao.createTempPW();
			SendEmail sendemail = new SendEmail();
			sendemail.sendMail(emp_email, temp_pw);
			int cnt = dao.tempPwUpdate(emp_email, temp_pw);
			if(cnt >0) {
				System.out.println("비밀번호 업데이트 성공");
				response.sendRedirect("Login.jsp");
			}else {
				System.out.println("비밀번호 업데이트 실패 ");
				response.sendRedirect("Login.jsp");
			}
			 		}

		
	}

}