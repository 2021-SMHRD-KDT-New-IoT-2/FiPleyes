package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		// üũ�ڽ��� üũ�� ������ True, ������ null�� �ȴ�
		String logincheck = request.getParameter("logincheck");
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(emp_no, emp_pw);

		response.setCharacterEncoding("euc-kr");	

		if (vo != null) {
			System.out.println("�α��� ����");
			// ���� ��ü ����
			HttpSession session = request.getSession();
			// ���� �� ����
			session.setAttribute("employee", vo);
			
			if (logincheck != null) {
				// �α��� ���� üũ�� �Ǿ� �ִٸ� -> �α��� �����ϱ�
				Cookie cookie = new Cookie("emp_no", emp_no);
				response.addCookie(new Cookie("emp_pw", vo.getEmp_pw()));
				response.addCookie(new Cookie("emp_phone", vo.getEmp_phone()));
				response.addCookie(new Cookie("emp_email", vo.getEmp_email()));
				response.addCookie(new Cookie("emp_name", vo.getEmp_name()));
				response.addCookie(new Cookie("dept_no", vo.getDept_no()));
				System.out.println(cookie);
				cookie.setMaxAge(60); // �����Ǵ� �ð�
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			response.sendRedirect("Main.jsp");

		} else {
			System.out.println("�α��� ����");
			response.sendRedirect("Login.jsp");
		}
	}

}