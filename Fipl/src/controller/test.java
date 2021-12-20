package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;

@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		String rep_no =request.getParameter("rep_no");
		
		System.out.println("값들어오기 성공 " + rep_no);
		if(rep_no != null) {
			
			request.setAttribute("rep_no", rep_no);
			ServletContext result = getServletContext();
			RequestDispatcher dispatcher = result.getRequestDispatcher("/Main.jsp.container");
			dispatcher.forward(request, response);
		}
	}

}
