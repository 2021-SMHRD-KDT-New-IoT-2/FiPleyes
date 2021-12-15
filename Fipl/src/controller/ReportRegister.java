package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;

@WebServlet("/ReportRegister")
public class ReportRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		
		// 장치에서 들어오는 데이터 -> 디비에 입력하기
		// 신고등록을 위한 장치의 데이터 가져오기
		
		String device_no = "★데이터를 보내온 장치 번호";
		String rep_file = "★톰캣의 파일 주소 값";
		String car_no = "★사진에서 추출한 차량번호";
		
		ReportDAO dao = new ReportDAO();
		int cnt = dao.register(device_no, rep_file, car_no);
		
		if (cnt > 0) {
			System.out.println("신고 등록 성공!");
			//★ 신고 등록 성공 후 이동 할페이지
			//response.sendRedirect("");
		} else {
			System.out.println("신고 등록 실패!");
			//★ 신고 등록 실패 후 이동 할페이지
			//response.sendRedirect("");
		}
	}

}
