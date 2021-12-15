package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.EmployeeVO;

@WebServlet("/update")
public class Update extends HttpServlet {
	
	EmployeeVO vo = null;
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		//현재 로그인한 사용자의 정보
		vo = (EmployeeVO)session.getAttribute("employee");
		String emp_no = vo.getEmp_no(); // 사용자 세션 사번
		String emp_pw = vo.getEmp_pw(); // 비교를 위한 사용자 사번
		String emp_name = vo.getEmp_name(); // 밑의 세션은 다시 vo 설정하기 위해 세션의 값을 불러오는 것
		String emp_phone = vo.getEmp_phone(); 
		String emp_email = vo.getEmp_email(); 
		String dept_no = vo.getDept_no(); 
		String emp_status = vo.getEmp_status(); 
		
		
		String check_emp_pw = request.getParameter(""); // input 태그 네임값 (비밀번호 확인을 위해 입력한 사번)
		
		
		EmployeeDAO dao = new EmployeeDAO();
		boolean check = dao.updateCheck(emp_no, check_emp_pw);
		
		if(check) { // 세션 아이디와 비밀번호가 일치하다면 -> 업데이트를 진행한다
			
			
			
			System.out.println("세션 아이디와 비밀번호 일치");

			String new_emp_pw1 = request.getParameter(""); // input태그 네임값
			String new_emp_pw2 = request.getParameter(""); // input태그 네임값
			
			if(new_emp_pw1.equals(new_emp_pw2)) { // 새로입력한 두 비밀번호가 같으면
				System.out.println("새로 입력한 두 비밀번호 같음");
				int cnt = dao.update(emp_no, new_emp_pw1);
				
				if(cnt > 0) {
					System.out.println("비밀번호 수정성공");
					vo = new EmployeeVO(emp_no, new_emp_pw1, emp_name, emp_phone, emp_email, dept_no, emp_status);
	
					
					session.setAttribute("Employee",vo);
					
					// 성공시 페이지 이동
					//response.sendRedirect("main.jsp");
				}else {
					System.out.println("비밀번호 수정실패");
					// 실패시 페이지 이동
					//response.sendRedirect("main.jsp");
				}
				
			}else { // 입력한 두 비밀번호가 다르면
				System.out.println("새로 입력한 두 비밀번호가 다름");
				//두 비밀번호가 다를시 페이지 이동
				//response.sendRedirect("main.jsp");
			}			
		
		} else { // 세션 아이디와 비밀번호가 일치하지 않다면
			System.out.println("세션 아이디와 비밀번호 다름");
			// 일치하지 않다면 갈 페이지
			//response.sendRedirect(".html");
		}
	}
}
