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

import model.ReportDAO;

@WebServlet("/FileRes")
public class FileRes extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
//    response.setContentType("text/html;charset=UTF-8");
//    response.setHeader("Cache-Control", "no-cache");
      String CarName = ""; // �ڵ��� ��ȣ�� ����� ����
      
      System.out.println("����Ȯ��...."); // ���� Ȯ��
      int maxSize = 100 * 1024 * 1024; // �̹��� ũ��
      String save_path = getServletContext().getRealPath("upload"); // ���� ���
      
      MultipartRequest multi = new MultipartRequest(request, save_path, maxSize, "utf-8", 
            new DefaultFileRenamePolicy()); // ���� ���
      
      Enumeration files = multi.getFileNames(); // ���� �ּ� ã��
      System.out.println(files); 
      while (files.hasMoreElements()) { // �ִ� ���� ��� ã��
          //logger.error("����6");
         String name = (String)files.nextElement(); // �ڷᰡ ���� ��쿣 while ���� ��� : ã�� ���� ��������
         
           String filename = multi.getFilesystemName(name); // ���Ͼ��ε� �� �̸� ���
           // System.out.println("���ε��� �̸�"+filename);
           String data = filename.replace(".jpg", ""); // ���� ��ó�� .jpg ����
         // System.out.println(data); ��ó�� ���� Ȯ��
           // base64 ���� ���ڵ�
         Decoder decoder = Base64.getDecoder(); 
         byte[] decodedBytes = decoder.decode(data);
         try {
            CarName = new String(decodedBytes, "UTF-8"); // ���ڵ��� ���� CarName�� �����ϱ�   
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
         System.out.println("��¥ ���� ��ȣ: " + CarName); // ������ȣ Ȯ��
         // String originalFilename = multi.getOriginalFileName(name); // ���� ��¥ �̸�
         // System.out.println("������¥ �̸�"+originalFilename);
           // String type = multi.getContentType(name); // ���۵� ������ ���� Ÿ��
         
         // ���� �̸� �ٲٱ� (��� �����ϱ�)
         String url = "C:\\Users\\smhrd\\Desktop\\2��������Ʈ\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Fipl\\upload\\";
         File prefile = new File(url+data+".jpg"); // ���� ����  ��θ� ���� ��ü ����
         System.out.println(prefile);
         File Carfile = new File(url+CarName+".jpg"); // ���� �� ���� ��θ� ���� ��ü ����, ���� ���ϰ� ���� ��ġ 
         // "210.223.239.165:8088\\Fipl\\upload\\"
         System.out.println(Carfile);
         prefile.renameTo(Carfile);
         String carPath = Carfile.toString();
         System.out.println(carPath);        		 
      }
      
      String rep_time = multi.getParameter("time");
      String device_no = multi.getParameter("device");
      String rep_file = "210.223.239.165:8088\\Fipl\\upload\\"+ CarName;
      String car_no = CarName;
      
      System.out.println(rep_time);
      System.out.println(device_no);
      
      
      ReportDAO dao = new ReportDAO();
      int cnt = dao.register(device_no, rep_file, car_no, rep_time);
      
      if (cnt > 0) {
			System.out.println("�Ű� ��� ����!");
		} else {
			System.out.println("�Ű� ��� ����!");
		}
      
   }
}