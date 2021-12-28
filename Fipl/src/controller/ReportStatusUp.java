package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeVO;
import model.ReportDAO;

@WebServlet("/ReportStatusUp")
public class ReportStatusUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자가 신고 상태 변경을 선택했을 시
		
		request.setCharacterEncoding("euc-kr");

		// 상태 변경을 하고자 하는 신고의 번호
		String rep_no = request.getParameter("rep_no");
		System.out.println("서블릿 신고 번호"+rep_no);

		// 사용자 세션 사번
		EmployeeVO vo = new EmployeeVO();
		HttpSession session = request.getSession();
		vo = (EmployeeVO) session.getAttribute("employee");
		String emp_no = vo.getEmp_no();

		ReportDAO dao = new ReportDAO();

		//사용자가 어떤 상태로 변경을 선택했는지 가져오기
		String status = request.getParameter("status");
		System.out.println("서블릿 상태값"+status);
		String kind = request.getParameter("kind");

		int cnt = dao.statusUpdate(rep_no, status, emp_no);

		if (cnt > 0) {
			System.out.println("신고 상태 바꾸기 성공");
			if(kind.equals("1")) {
				response.sendRedirect("Main.jsp#page2");
			}else if(kind.equals("2")){
				response.sendRedirect("Main.jsp#page3");
			}
			
		} else {
			System.out.println("신고 상태 바꾸기 실패");
			if(kind.equals("1")) {
				response.sendRedirect("Main.jsp#page2");
			}else if(kind.equals("2")){
				response.sendRedirect("Main.jsp#page3");
			}
		}

	}

}
