package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeVO;
import model.ReportDAO;

@WebServlet("/ReportStatusUp")
public class ReportStatusUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ڰ� �Ű� ���� ������ �������� ��

		request.setCharacterEncoding("euc-kr");

		// ���� ������ �ϰ��� �ϴ� �Ű��� ��ȣ
		String rep_no = request.getParameter("rep_no");

		// ����� ���� ���
		EmployeeVO vo = new EmployeeVO();
		HttpSession session = request.getSession();
		vo = (EmployeeVO) session.getAttribute("employee");
		String emp_no = vo.getEmp_no();

		ReportDAO dao = new ReportDAO();

		// �ڻ���ڰ� � ���·� ������ �����ߴ��� ��������
		String change_rep_status = "��"; // �ڻ���ڰ� ������ ��

		int cnt = dao.statusUpdate(rep_no, change_rep_status, emp_no);

		if (cnt > 0) {
			System.out.println("�Ű� ���� �ٲٱ� ����");
			// �ڽŰ� ���� �ٲٱ� ������ ������ �̵�
			// response.sendRedirect("main.jsp");
		} else {
			System.out.println("�Ű� ���� �ٲٱ� ����");
			// �ڽŰ� ���� �ٲٱ� ���н� ������ �̵�
			// response.sendRedirect("main.jsp");
		}

	}

}
