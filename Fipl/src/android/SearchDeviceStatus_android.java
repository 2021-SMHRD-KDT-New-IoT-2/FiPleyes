package android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DeviceDAO;
import model.DeviceVO;

@WebServlet("/SearchDeviceStatus_android")
public class SearchDeviceStatus_android extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String dept_no = request.getParameter("dept_no");
		String status = request.getParameter("status");

		System.out.println("SearchDeviceStatus 받아온 dept_no/status 값 -------------- " + dept_no+"/"+status);

		DeviceDAO dao = new DeviceDAO();
		
		ArrayList<DeviceVO> list ;
		
		if(status.equals("")) {//모든기기 검색
			list = dao.allList(dept_no);
		}else {//이상기기 검색
			list = dao.errorDevice(dept_no);
		}
		
		
		

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String result = gson.toJson(list);
		out.print(result);
	
	}

}
