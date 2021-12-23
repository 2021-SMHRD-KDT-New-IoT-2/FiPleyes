package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeviceDAO;

@WebServlet("/DeviceCheck")
public class DeviceCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		
		String device_no =request.getParameter("input_device_no");
		
		DeviceDAO dao = new DeviceDAO();
		boolean check = dao.deviceCheck(device_no);
		
		PrintWriter out = response.getWriter();
		out.print(check);
		
		
	}

}
