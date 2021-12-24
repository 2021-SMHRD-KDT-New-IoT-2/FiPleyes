package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeviceDAO;
import model.DeviceVO;
import model.EmployeeVO;

@WebServlet("/DeviceRegister")
public class DeviceRegister extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// 기기 등록을 위해 입력안 값 가져오기 
		String device_no = request.getParameter("device_no");
		String device_loc = request.getParameter("device_loc");
		
		System.out.println(device_no + ", " + device_loc);
		
		// 세션 가져오기
		HttpSession session = request.getSession();

		// 현재 로그인한 사용자의 정보
		EmployeeVO Evo = (EmployeeVO) session.getAttribute("employee");
		String device_dept = Evo.getDept_no(); // 사용자 세션에 등록된 부서 번호
		
		DeviceDAO dao = new DeviceDAO();
		boolean check = dao.duplicateCheck(device_no);
		int cnt =0;
		if(!check) {
			cnt = dao.register(device_no, device_loc, device_dept);
		}
		
		System.out.println("cnt/ check 값 : "+cnt + "/ "+check);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		out.print(check);
	}

}
