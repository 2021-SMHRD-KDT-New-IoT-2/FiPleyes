package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Enumeration;

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

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
//    response.setContentType("text/html;charset=UTF-8");
//    response.setHeader("Cache-Control", "no-cache");
      String CarName = ""; // 자동차 번호로 사용할 변수
      
      System.out.println("연결확인...."); // 연결 확인
      int maxSize = 100 * 1024 * 1024; // 이미지 크기
      String save_path = getServletContext().getRealPath("upload"); // 저장 장소
      
      MultipartRequest multi = new MultipartRequest(request, save_path, maxSize, "utf-8", 
            new DefaultFileRenamePolicy()); // 저장 기능
      
      Enumeration files = multi.getFileNames(); // 파일 주소 찾기
      System.out.println(files); 
      while (files.hasMoreElements()) { // 있는 파일 모두 찾기
          //logger.error("문제6");
         String name = (String)files.nextElement(); // 자료가 많을 경우엔 while 문을 사용 : 찾은 파일 가져오기
         
           String filename = multi.getFilesystemName(name); // 파일업로드 한 이름 얻기
           // System.out.println("업로드한 이름"+filename);
           String data = filename.replace(".jpg", ""); // 파일 전처리 .jpg 빼기
         // System.out.println(data); 전처리 파일 확인
           // base64 파일 디코딩
         Decoder decoder = Base64.getDecoder(); 
         byte[] decodedBytes = decoder.decode(data);
         try {
            CarName = new String(decodedBytes, "UTF-8"); // 디코딩한 파일 CarName에 저장하기   
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
         System.out.println("진짜 차량 번호: " + CarName); // 차량번호 확인
         // String originalFilename = multi.getOriginalFileName(name); // 파일 진짜 이름
         // System.out.println("파일진짜 이름"+originalFilename);
           // String type = multi.getContentType(name); // 전송된 파일의 내용 타입
         
         // 파일 이름 바꾸기 (경로 수정하기)
         String url = "C:\\Users\\smhrd\\Desktop\\2차프로젝트\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Fipl\\upload\\";
         File prefile = new File(url+data+".jpg"); // 원본 파일  경로를 통해 객체 생성
         System.out.println(prefile);
         File Carfile = new File(url+CarName+".jpg"); // 변경 된 파일 경로를 통해 객체 생성, 원본 파일과 같은 위치 
         // "210.223.239.165:8088\\Fipl\\upload\\"
         System.out.println(Carfile);
         prefile.renameTo(Carfile);
         String carPath = Carfile.toString();
         System.out.println(carPath);        		 
      }
      String time = multi.getParameter("time");
      String device = multi.getParameter("device");
      System.out.println(time);
      System.out.println(device);
   }
}