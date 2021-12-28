package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeviceDAO;
@WebServlet("/DeleteDevice_android")
public class DeleteDevice_android extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String device_no = request.getParameter("device_no");
		
		DeviceDAO dao = new DeviceDAO();
	
		int cnt = dao.deleteDevice(device_no);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("디바이스 삭제 동작 cnt : "+cnt);
		
		if(cnt>0) {
			out.print("기기 삭제 완료");
		}else {
			out.print("기기 삭제 실패");
		}
	
	}

}
