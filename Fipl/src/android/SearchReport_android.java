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

import model.ReportDAO;
import model.ReportVO;

@WebServlet("/SearchReport_android")
public class SearchReport_android extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String status = request.getParameter("status");
		String emp_dept = request.getParameter("emp_dept");
		
		System.out.println("받아온값 -------------- "+status+"/"+emp_dept);
		
		ReportDAO dao = new ReportDAO();
		ArrayList<ReportVO> reports = dao.reportList(emp_dept, status);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(reports.size());
		out.print(reports.size());
		
		
	}

}
