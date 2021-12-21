package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeviceDAO;
import model.DeviceVO;
import model.EmployeeVO;

@WebServlet("/DeviceRegister")
public class DeviceRegister extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		
		// ��� ����� ���� �Է¾� �� �������� 
		String device_no = request.getParameter("device_no");
		String device_loc = request.getParameter("device_loc");
		
		// ���� ��������
		HttpSession session = request.getSession();

		// ���� �α����� ������� ����
		EmployeeVO Evo = (EmployeeVO) session.getAttribute("employee");
		String device_dept = Evo.getDept_no(); // ����� ���ǿ� ��ϵ� �μ� ��ȣ
		
		DeviceDAO dao = new DeviceDAO();
		int cnt = dao.register(device_no, device_loc, device_dept);
		
		if (cnt > 0) {
			System.out.println("��ġ ��� ����!");
			response.sendRedirect("Main.jsp#add_device");
		} else {
			System.out.println("��ġ ��� ����!");
			response.sendRedirect("Main.jsp#add_device");
		}

	}

}
