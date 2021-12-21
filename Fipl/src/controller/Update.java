package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/Update")
public class Update extends HttpServlet {

	EmployeeVO vo = null;

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");

		// ���� ��������
		HttpSession session = request.getSession();

		// ���⼭ ���� ��й�ȣ 1�� Ȯ�ΰ����ϸ� �ϰ�, �ƴϸ� PwCheck �������� ���ߵ�!!
		// ���� �α����� ������� ����
		vo = (EmployeeVO) session.getAttribute("employee");
		String emp_no = vo.getEmp_no(); // ����� ���� ���
		String emp_pw = vo.getEmp_pw(); // ����� ���� ��й�ȣ

		// vo �缳���ϱ� ���� ������ ���� �ҷ����� ��
		String emp_name = vo.getEmp_name();
		String emp_phone = vo.getEmp_phone();
		String emp_email = vo.getEmp_email();
		String dept_no = vo.getDept_no();
		String emp_status = vo.getEmp_status();

		boolean check = false;

		EmployeeDAO dao = new EmployeeDAO();

		// ������ ��й�ȣ �Է�
		String new_emp_pw1 = request.getParameter("new_emp_pw1");
		String new_emp_pw2 = request.getParameter("new_emp_pw2");
		
		PrintWriter out = response.getWriter();
		
		if(new_emp_pw1.equals(new_emp_pw2)) {
			int cnt = dao.update(emp_no, new_emp_pw1);

			if (cnt > 0) {
				System.out.println("��й�ȣ ��������");
				vo = new EmployeeVO(emp_no, new_emp_pw1, emp_name, emp_phone, emp_email, dept_no, emp_status);
				session.setAttribute("Employee", vo);
				check = true;
			} else {
				System.out.println("��й�ȣ ��������");
			}
			
		}
		out.print(check);
		

	}
}
