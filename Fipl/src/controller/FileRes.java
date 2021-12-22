package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/FileRes")
public class FileRes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("접속!");
	      int maxSize = 100*1024*1024;   
	      String save_path = getServletContext().getRealPath("upload");
	      System.out.println("전송중!");
	      try {
	         MultipartRequest multi = new MultipartRequest(request, save_path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println("전송완료!");
	}

}
