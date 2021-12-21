package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/Update")
public class Update extends HttpServlet {

	EmployeeVO vo = null;

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");

		// 세션 가져오기
		HttpSession session = request.getSession();

		// 여기서 본인 비밀번호 1차 확인가능하면 하고, 아니면 PwCheck 서블릿으로 가야됨!!
		// 현재 로그인한 사용자의 정보
		vo = (EmployeeVO) session.getAttribute("employee");
		String emp_no = vo.getEmp_no(); // 사용자 세션 사번
		String emp_pw = vo.getEmp_pw(); // 사용자 세션 비밀번호

		// vo 재설정하기 위해 세션의 값을 불러오는 것
		String emp_name = vo.getEmp_name();
		String emp_phone = vo.getEmp_phone();
		String emp_email = vo.getEmp_email();
		String dept_no = vo.getDept_no();
		String emp_status = vo.getEmp_status();

		boolean check = false;

		EmployeeDAO dao = new EmployeeDAO();

		// 변경할 비밀번호 입력
		String new_emp_pw1 = request.getParameter("new_emp_pw1");
		String new_emp_pw2 = request.getParameter("new_emp_pw2");
		
		PrintWriter out = response.getWriter();
		
		if(new_emp_pw1.equals(new_emp_pw2)) {
			int cnt = dao.update(emp_no, new_emp_pw1);

			if (cnt > 0) {
				System.out.println("비밀번호 수정성공");
				vo = new EmployeeVO(emp_no, new_emp_pw1, emp_name, emp_phone, emp_email, dept_no, emp_status);
				session.setAttribute("Employee", vo);
				check = true;
			} else {
				System.out.println("비밀번호 수정실패");
			}
			
		}
		out.print(check);
		

	}
}
