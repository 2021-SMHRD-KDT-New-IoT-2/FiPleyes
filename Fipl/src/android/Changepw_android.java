package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeeDAO;


@WebServlet("/Changepw_android")
public class Changepw_android extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String emp_no = request.getParameter("emp_no");
		//String emp_originPw = request.getParameter("emp_originPw");
		String emp_newPw = request.getParameter("emp_newPw");
		
		EmployeeDAO dao = new EmployeeDAO();
		
		int cnt = dao.update(emp_no, emp_newPw);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(cnt>0) {
			System.out.println("��й�ȣ ���� �Ϸ�");
			out.print("1");
		}else {
			System.out.println("��й�ȣ ���� ����");
			out.print("0");
		}
		
		
		
	}

}
