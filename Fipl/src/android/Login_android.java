package android;

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

@WebServlet("/Login_android")
public class Login_android extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		String emp_no = request.getParameter("emp_no");
		String emp_pw = request.getParameter("emp_pw");
		
		// üũ�ڽ��� üũ�� ������ True, ������ null�� �ȴ�
		//String logincheck = request.getParameter("logincheck");
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(emp_no, emp_pw);

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		if (vo != null) {
			System.out.println("�α��� ����");

			// �ȵ���̵� �α���
			Gson gson = new Gson();
			String result = gson.toJson(vo);
			out.print(result);

		} else {
			System.out.println("�α��� ����");
			out.print("fail");
		}
	}

}
