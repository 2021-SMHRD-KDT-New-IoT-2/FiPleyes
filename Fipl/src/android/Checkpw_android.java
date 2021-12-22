package android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.EmployeeDAO;
import model.EmployeeVO;


@WebServlet("/Checkpw_android")
public class Checkpw_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		
		System.out.println("넘어온 값 : "+emp_no+"/ "+emp_pw);

		EmployeeDAO dao = new EmployeeDAO();

		boolean check = dao.checkPw(emp_no, emp_pw);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(check==true){
			System.out.println("비밀번호 일치");
			out.print("0");
			
		} else {
			System.out.println("비밀번호 불일치");
			out.print("1");
		}

		
	}

}
