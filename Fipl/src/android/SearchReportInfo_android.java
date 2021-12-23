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

@WebServlet("/SearchReportInfo_android")
public class SearchReportInfo_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String status = request.getParameter("status");
		String emp_dept = request.getParameter("emp_dept");

		System.out.println("받아온값 -------------- " + status + "/" + emp_dept);

		ReportDAO dao = new ReportDAO();
		DeviceDAO dao2 = new DeviceDAO();
		ArrayList<ReportVO> reports = dao.reportList(emp_dept, status);

		for(int i=0;i<reports.size();i++) {
			String device_loc = dao2.deviceLocation(reports.get(i).getDevice_no());
			reports.get(i).setDevice_loc(device_loc);
		}
		
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		System.out.println(reports.size());
		
		Gson gson = new Gson();
		String result = gson.toJson(reports);
		out.print(result);

	}

}
