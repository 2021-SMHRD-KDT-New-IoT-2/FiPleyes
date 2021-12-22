package android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DeviceDAO;
import model.ReportDAO;
import model.ReportVO;

@WebServlet("/SearchReportLocation_android")
public class SearchReportLocation_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String device_no = request.getParameter("device_no");

		System.out.println("SearchReportLocation 받아온값 -------------- " + device_no);

		DeviceDAO dao = new DeviceDAO();
		String device_loc = dao.deviceLocation(device_no);

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		System.out.println(device_loc);
		
		out.print(device_loc);
	}


}
