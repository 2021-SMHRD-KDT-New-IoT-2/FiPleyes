package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeviceDAO;
import model.DeviceVO;
import model.EmployeeVO;

@WebServlet("/DeviceStatusUp")
public class DeviceStatusUp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		// 장치에서 들어오는 데이터 -> 디비에 입력
		// ★장지에서 들어오는 데이터 값이 일정시간 이상 끊기면? false -> true가 됨
		boolean status = false;
		
	while(true) {
		String url = "172.30.1.9:5000";
		
//		★if("장치의 상태가 일정 시간 끊기면") {
//			status = true;
//		}
		
		String device_no = "★fasle로 들어온 장치의 번호";

		if (status) {
			
			DeviceDAO dao = new DeviceDAO();
			int cnt = dao.statusUpdate(device_no);
			
			if(cnt > 0) {
				System.out.println("장치 상태 수정 성공");
				
			}else {
				System.out.println("장치 상태 수정 실패");

			}
			
		} else {
			System.out.println();
		}

		}
	}

}
