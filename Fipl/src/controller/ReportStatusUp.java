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
		System.out.println("���� �Ű� ��ȣ"+rep_no);

		// ����� ���� ���
		EmployeeVO vo = new EmployeeVO();
		HttpSession session = request.getSession();
		vo = (EmployeeVO) session.getAttribute("employee");
		String emp_no = vo.getEmp_no();

		ReportDAO dao = new ReportDAO();

		//����ڰ� � ���·� ������ �����ߴ��� ��������
		String status = request.getParameter("status");
		System.out.println("���� ���°�"+status);
		String kind = request.getParameter("kind");

		int cnt = dao.statusUpdate(rep_no, status, emp_no);

		if (cnt > 0) {
			System.out.println("�Ű� ���� �ٲٱ� ����");
			if(kind.equals("1")) {
				response.sendRedirect("Main.jsp#page2");
			}else if(kind.equals("2")){
				response.sendRedirect("Main.jsp#page3");
			}
			
		} else {
			System.out.println("�Ű� ���� �ٲٱ� ����");
			if(kind.equals("1")) {
				response.sendRedirect("Main.jsp#page2");
			}else if(kind.equals("2")){
				response.sendRedirect("Main.jsp#page3");
			}
		}

	}

}
