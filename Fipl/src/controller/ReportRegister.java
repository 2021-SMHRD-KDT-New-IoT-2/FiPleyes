package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;

@WebServlet("/ReportRegister")
public class ReportRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		
		// ��ġ���� ������ ������ -> ��� �Է��ϱ�
		
		
		
		String photo_num = "1";
		
		String device_no = "�ڵ����͸� ������ ��ġ ��ȣ";
		String rep_file = 
				"210.223.239.165:8088\\Fipl\\upload\\" + photo_num + ".jpg";
		String car_no = "�ڻ������� ������ ������ȣ";
		
		ReportDAO dao = new ReportDAO();
		int cnt = dao.register(device_no, rep_file, car_no);
		
		if (cnt > 0) {
			System.out.println("�Ű� ��� ����!");
			// �Ű� ��� ���� �� �̵� ��������
			response.sendRedirect("Main.jsp");
			
		} else {
			System.out.println("�Ű� ��� ����!");
			//�� �Ű� ��� ���� �� �̵� �������� // �����͸� �ҷ��� �� ���ٴ� �������� �̵��ϱ�
			//response.sendRedirect("");
		}
	}

}
