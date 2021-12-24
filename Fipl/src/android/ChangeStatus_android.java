package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;

@WebServlet("/ChangeStatus_android")
public class ChangeStatus_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String rep_no = request.getParameter("report_no");
		String rep_status = request.getParameter("status");
		String emp_no = request.getParameter("emp_no");
		
		ReportDAO dao = new ReportDAO();
		int cnt = dao.statusUpdate(rep_no, rep_status, emp_no);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(cnt>0) {
			
		}else {
			
		}
		
		
		
	}

}
