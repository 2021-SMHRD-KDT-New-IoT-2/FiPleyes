package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeeDAO;
import model.SendEmail;
@WebServlet("/SendEmail_android")
public class SendEmail_android extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
		
		String emp_name = request.getParameter("emp_name");
		String emp_phone = request.getParameter("emp_phone");
		String emp_email = request.getParameter("emp_email");
	
		EmployeeDAO dao = new EmployeeDAO();
		boolean checkEmp = dao.checkEmp(emp_name, emp_phone, emp_email);
		System.out.println(emp_name+" / "+emp_phone+" / "+emp_email);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(checkEmp) {
			
			String temp_pw = dao.createTempPW();
			SendEmail sendemail = new SendEmail();
			sendemail.sendMail(emp_email, temp_pw);
			
			int cnt = dao.tempPwUpdate(emp_email, temp_pw);
			
			if(cnt >0) {
				out.print("임시 비밀번호 발급 완료");
				System.out.println("발급 완료");
			}else {
				out.print("임시 비밀번호 발급 실패");
				System.out.println("발급 실패");
			}
			 		}

	}

}
