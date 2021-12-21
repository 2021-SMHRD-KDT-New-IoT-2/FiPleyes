package controller;

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

@WebServlet("/ReportGet")
public class ReportGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		String rep_no =request.getParameter("rep_no");
		
		ReportDAO dao = new ReportDAO();
		ReportVO vo = dao.getReport(rep_no);
		
		if(vo != null) {
			Gson gson = new Gson();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			String result = gson.toJson(vo);
			
			out.print(result);
		}
		
		
	}

}
