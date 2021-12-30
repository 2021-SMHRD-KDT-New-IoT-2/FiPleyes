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

		String device_no = request.getParameter("device_no");
		System.out.println(device_no);
		DeviceDAO dao = new DeviceDAO();
		int cnt = dao.statusUpdate(device_no);

		if (cnt > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}

}
