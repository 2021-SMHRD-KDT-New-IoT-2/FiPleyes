package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {

	EmployeeVO vo = null;
	int cnt = 0;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 디비 연결
	private void Connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "fipl";
			String dbpw = "fipl";

			conn = DriverManager.getConnection(url, dbid, dbpw);


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("커낵션 DAO 실패");

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

		try {
			Connection();
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_no);
			psmt.setString(2, emp_pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("디비에 로그인 값 있음");

				//디비에서 데이터 불러오기
	            String get_emp_id = rs.getString(1);
	            String get_emp_pw = rs.getString(2);
	            String get_emp_name = rs.getString(3);
	            String get_emp_phone = rs.getString(4);
		        String get_emp_email = rs.getString(5);
		        String get_dept_no = rs.getString(6);
		        String get_emp_status = rs.getString(7);

				// 불러온거 VO에 넣기
	            vo = new EmployeeVO(get_emp_id, get_emp_pw, get_emp_name, get_emp_phone, get_emp_email, get_dept_no, get_emp_status);

			} else {
				System.out.println("디비에 로그인값 없음");
			}

		} catch (Exception e) {
			System.out.println("로그인 DAO 실패");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	// 비밀번호 업데이트
	public int update(String emp_no, String new_emp_no1) {
		// 세션의 아이디, 새로 입력한 비밀번호 중 하나

		try {
			Connection();

			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_no = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, new_emp_no1);
			psmt.setString(2, emp_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("비밀번호 변경 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}
	
	public EmployeeVO findPw(String name, String phone, String email) {

		try {
			Connection();
			String sql = "select * from Employees where emp_name = ? and emp_phone =? and emp_email=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, email);

			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("디비에 로그인 값 있음");

				//디비에서 데이터 불러오기
	            String get_emp_id = rs.getString(1);
	            String get_emp_pw = rs.getString(2);
	            String get_emp_name = rs.getString(3);
	            String get_emp_phone = rs.getString(4);
		        String get_emp_email = rs.getString(5);
		        String get_dept_no = rs.getString(6);
		        String get_emp_status = rs.getString(7);

				// 불러온거 VO에 넣기
	            vo = new EmployeeVO(get_emp_id, get_emp_pw, get_emp_name, get_emp_phone, get_emp_email, get_dept_no, get_emp_status);

			} else {
				System.out.println("디비에 로그인값 없음");
			}

		} catch (Exception e) {
			System.out.println("비밀번호 찾기 DAO 실패");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	public boolean checkPw(String emp_no, String emp_pw) {
		
		boolean check = false;
		
		try {
			Connection();
			String sql = "select * from Employees where emp_no = ? and emp_pw=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_no);
			psmt.setString(2, emp_pw);

			rs = psmt.executeQuery();
			
			check = rs.next();
	
		} catch (Exception e) {
			System.out.println("비밀번호 확인 DAO 실패");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return check;
	}

	
	// 본인 인증
	public boolean checkEmp(String emp_name, String emp_phone, String emp_email) {
		boolean check = false;
		try {
		
			Connection();

			String sql = "Select * from employees where emp_name = ? and emp_phone = ? and emp_email = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_name);
			psmt.setString(2, emp_phone);
			psmt.setString(3, emp_email);

			cnt = psmt.executeUpdate();
			
			if(cnt == 1) {
				check = true;
			}
			
		} catch (Exception e) {
			System.out.println("본인인증 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return check;
	}
	
	// 임시 비밀번호 생성
	public String createTempPW() { 

        String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String newCode = new String();
        for (int x = 0; x < 8; x++) {
            int random = (int) (Math.random() * str.length);
            newCode += str[random];
        }
        return newCode;
    }
	
	// 비밀번호 업데이트
	public int tempPwUpdate(String emp_email, String temp_pw) {
		try {
			Connection();

			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_email = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, temp_pw);
			psmt.setString(2, emp_email);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("비밀번호 찾고 나서 비밀번호 변경 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}
	
	
}
