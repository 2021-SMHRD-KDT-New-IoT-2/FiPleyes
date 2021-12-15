package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/update")
public class Update extends HttpServlet {
	
	EmployeeVO vo = null;
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		// ���� ��������
		HttpSession session = request.getSession();
		
		//���� �α����� ������� ����
		vo = (EmployeeVO)session.getAttribute("employee");
		String emp_no = vo.getEmp_no(); // ����� ���� ���
		String emp_pw = vo.getEmp_pw(); // ����� ���� ��й�ȣ 
		
		
		// vo �缳���ϱ� ���� ������ ���� �ҷ����� ��
		String emp_name = vo.getEmp_name(); 
		String emp_phone = vo.getEmp_phone(); 
		String emp_email = vo.getEmp_email(); 
		String dept_no = vo.getDept_no(); 
		String emp_status = vo.getEmp_status(); 
		
		
		// input �±� ���Ӱ� (��й�ȣ Ȯ���� ���� �Է��� ���)
		String check_emp_pw = request.getParameter(""); // ��input�±� ���Ӱ� �Է��ʿ�
				
		EmployeeDAO dao = new EmployeeDAO();
		
		if(emp_pw.equals(check_emp_pw)) { // ������ ��й�ȣ��  �Է��� ��й�ȣ�� ��ġ�ϴٸ� -> ������Ʈ�� �����Ѵ�
			
			System.out.println("���� ��й�ȣ�� �Է��� ��й�ȣ ��ġ");
			
			// ������ ��й�ȣ �Է�
			String new_emp_pw1 = request.getParameter(""); // ��input�±� ���Ӱ� �Է��ʿ�
			String new_emp_pw2 = request.getParameter(""); // ��input�±� ���Ӱ� �Է��ʿ�
			
			if(new_emp_pw1.equals(new_emp_pw2)) { // �����Է��� �� ��й�ȣ�� ������
				
				System.out.println("���� �Է��� �� ��й�ȣ ����");
				int cnt = dao.update(emp_no, new_emp_pw1);
				
				if(cnt > 0) {
					System.out.println("��й�ȣ ��������");
					
					vo = new EmployeeVO(emp_no, new_emp_pw1, emp_name, emp_phone, emp_email, dept_no, emp_status);
					session.setAttribute("Employee",vo);
					
					// �ڼ��� ������ ������ �̵�
					//response.sendRedirect("main.jsp");
					
				}else {
					System.out.println("��й�ȣ ��������");
					// �ڼ��� ���н� ������ �̵�
					//response.sendRedirect("main.jsp");
				}
				
			}else { // �Է��� �� ��й�ȣ�� �ٸ���
				System.out.println("���� �Է��� �� ��й�ȣ�� �ٸ�");
				// �ڻ��� �Է��� �� ��й�ȣ�� �ٸ��� ������ �̵�
				//response.sendRedirect("main.jsp");
			}			
		
		} else { // ���� ��й�ȣ�� ��й�ȣ�� ��ġ���� �ʴٸ�
			System.out.println("���� ���̵�� ��й�ȣ �ٸ�");
			// �ڼ��� ��й�ȣ�� ��й�ȣ�� ��ġ���� �ʴٸ� �� ������
			//response.sendRedirect(".html");
		}
	}
}
