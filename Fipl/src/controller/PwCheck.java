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
		
		//���� �α����� ������� ����
		HttpSession session = request.getSession();
		vo = (EmployeeVO)session.getAttribute("employee");
		String emp_pw = vo.getEmp_pw(); // ����� ���� ��й�ȣ 

		PrintWriter out = response.getWriter();
		// �������� ����ؼ� ��й�ȣ üũ�ϴ� ���
		if(check_emp_pw.equals(emp_pw)) {
			check = true;
			//��� ��Ʈ��(���)			
		}
		//��θ� ���ؼ� ���䵥���͸� ���
		out.print(check);
	}
}
