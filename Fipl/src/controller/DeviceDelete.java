package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeviceDAO;
import model.EmployeeVO;

@WebServlet("/DeviceDelete")
public class DeviceDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
	
		String device_no = request.getParameter("device_no");
		
		DeviceDAO dao = new DeviceDAO();
		int cnt = dao.deleteDevice(device_no);
		
		if (cnt > 0) {
			System.out.println("장치 삭제 성공!");
			response.sendRedirect("Main.jsp#page5");
			
		} else {
			System.out.println("장치 삭제 실패!");
			response.sendRedirect("Main.jsp#page5");
		}
		
	}

}
