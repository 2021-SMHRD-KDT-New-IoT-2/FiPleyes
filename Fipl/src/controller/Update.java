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
		String emp_pw = vo.getEmp_pw(); // �񱳸� ���� ����� ���
		String emp_name = vo.getEmp_name(); // ���� ������ �ٽ� vo �����ϱ� ���� ������ ���� �ҷ����� ��
		String emp_phone = vo.getEmp_phone(); 
		String emp_email = vo.getEmp_email(); 
		String dept_no = vo.getDept_no(); 
		String emp_status = vo.getEmp_status(); 
		
		
		String check_emp_pw = request.getParameter(""); // input �±� ���Ӱ� (��й�ȣ Ȯ���� ���� �Է��� ���)
		
		
		EmployeeDAO dao = new EmployeeDAO();
		boolean check = dao.updateCheck(emp_no, check_emp_pw);
		
		if(check) { // ���� ���̵�� ��й�ȣ�� ��ġ�ϴٸ� -> ������Ʈ�� �����Ѵ�
			
			
			
			System.out.println("���� ���̵�� ��й�ȣ ��ġ");

			String new_emp_pw1 = request.getParameter(""); // input�±� ���Ӱ�
			String new_emp_pw2 = request.getParameter(""); // input�±� ���Ӱ�
			
			if(new_emp_pw1.equals(new_emp_pw2)) { // �����Է��� �� ��й�ȣ�� ������
				System.out.println("���� �Է��� �� ��й�ȣ ����");
				int cnt = dao.update(emp_no, new_emp_pw1);
				
				if(cnt > 0) {
					System.out.println("��й�ȣ ��������");
					vo = new EmployeeVO(emp_no, new_emp_pw1, emp_name, emp_phone, emp_email, dept_no, emp_status);
	
					
					session.setAttribute("Employee",vo);
					
					// ������ ������ �̵�
					//response.sendRedirect("main.jsp");
				}else {
					System.out.println("��й�ȣ ��������");
					// ���н� ������ �̵�
					//response.sendRedirect("main.jsp");
				}
				
			}else { // �Է��� �� ��й�ȣ�� �ٸ���
				System.out.println("���� �Է��� �� ��й�ȣ�� �ٸ�");
				//�� ��й�ȣ�� �ٸ��� ������ �̵�
				//response.sendRedirect("main.jsp");
			}			
		
		} else { // ���� ���̵�� ��й�ȣ�� ��ġ���� �ʴٸ�
			System.out.println("���� ���̵�� ��й�ȣ �ٸ�");
			// ��ġ���� �ʴٸ� �� ������
			//response.sendRedirect(".html");
		}
	}
}
