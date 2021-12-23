package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeviceDAO;

@WebServlet("/DeviceRegister_android")
public class DeviceRegister_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String device_no = request.getParameter("device_no");
		String device_loc = request.getParameter("device_loc");
		String device_dept = request.getParameter("device_dept");
		
		DeviceDAO dao = new DeviceDAO();
		boolean check = dao.duplicateCheck(device_no);
		int cnt =0;
		if(!check) {
			cnt = dao.register(device_no, device_loc, device_dept);
		}
		
		System.out.println("cnt/ check 값 : "+cnt + "/ "+check);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(cnt>0) {
			out.print("성공");
		}else {
			out.print("실패.......");
		}
		
		
	}

}
