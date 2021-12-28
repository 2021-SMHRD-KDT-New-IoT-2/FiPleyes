package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeviceDAO;
import model.DeviceVO;
import model.EmployeeVO;

@WebServlet("/DeviceStatusUp")
public class DeviceStatusUp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		// ��ġ���� ������ ������ -> ��� �Է�
		// ���������� ������ ������ ���� �����ð� �̻� �����? false -> true�� ��
		boolean status = false;
		
	while(true) {
		String url = "172.30.1.9:5000";
		
//		��if("��ġ�� ���°� ���� �ð� �����") {
//			status = true;
//		}
		
		String device_no = "��fasle�� ���� ��ġ�� ��ȣ";

		if (status) {
			
			DeviceDAO dao = new DeviceDAO();
			int cnt = dao.statusUpdate(device_no);
			
			if(cnt > 0) {
				System.out.println("��ġ ���� ���� ����");
				
			}else {
				System.out.println("��ġ ���� ���� ����");

			}
			
		} else {
			System.out.println();
		}

		}
	}

}
