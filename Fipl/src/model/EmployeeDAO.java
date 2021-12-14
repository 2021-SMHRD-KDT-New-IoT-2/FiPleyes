package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {

	EmployeeVO vo = new EmployeeVO();
	int cnt = 0;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	boolean check = false;

	// 디비 연결
	private void Connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "fipl";
			String dbpw = "fipl";

			conn = DriverManager.getConnection(url, dbid, dbpw);

			System.out.println("DAO 커낵션 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO 커낵션 실패");

		}
	}

	// 디비 연결 끊기
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// 로그인
	public EmployeeVO login(String emp_no, String emp_pw) {
		// vo를 돌려줄 경우 사용
		// EmployeeVO info = null;

		try {
			Connection();
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_no);
			psmt.setString(2, emp_pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("DAO 로그인 값 있음");

//				디비에서 데이터 불러오기 ( 불러올것 정하기 )
//	            String rs_emp_id = rs.getString(1);
//	            String rs_emp_pw = rs.getString(2);
//	            String rs_emp_name = rs.getString(3);
//	            String rs_emp_phone = rs.getString(4);
//		        String rs_emp_email = rs.getString(5);
//		        String rs_dept_no = rs.getString(6);
//		        String rs_emp_status = rs.getString(7);

//				불러온거 VO에 넣기
//	            info = new EmployeeVO(rs_emp_id, rs_emp_pw, rs_emp_name, rs_emp_phone, rs_emp_email, rs_dept_no, rs_emp_status);

				
			} else {
				System.out.println("DAO 로그인값 없음");
			}

		} catch (Exception e) {
			System.out.println("로그인 dao실패");
			e.printStackTrace();
		} finally {
			close();
		}
		// 돌려줄 값 지정하기 ( 이름이면 이름, vo면 vo)
		return null;
	}

	// 비밀번호 확인
	public boolean updateCheck(String emp_no, String check_emp_pw) {
		// emp_no은 세션의 아이디  check_emp_pw는 비밀번호 확인을 위해 입력한 pw
		try {
			Connection();
			
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, emp_no);
			psmt.setString(2, check_emp_pw);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("DAO 아이디 비밀번호 일치");
				check = true;
			} else {
				System.out.println("DAO 아이디 비밀번호 불일치");
			}
			
		} catch (Exception e) {
			System.out.println("비밀번호 확인 DAO 실패");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return check;
	}

	public int update(String emp_no, String new_emp_no1) {
		// 세션의 아이디, 새로 입력한 비밀번호 중 하나
		
		try {
			Connection();
			
			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_no = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,new_emp_no1);
			psmt.setString(2,emp_no);
			
			cnt = psmt.executeUpdate();
			
			System.out.println("DAO 비밀번호 변경 성공");
			
		} catch (Exception e) {
			System.out.println("비밀번호 변경 DAO 실패");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return cnt;
	}

	

	
}
