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

	// ��� ����
	private void Connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "fipl";
			String dbpw = "fipl";

			conn = DriverManager.getConnection(url, dbid, dbpw);


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ŀ���� DAO ����");

		}
	}

	// ��� ���� ����
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

	// �α���
	public EmployeeVO login(String emp_no, String emp_pw) {

		try {
			Connection();
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_no);
			psmt.setString(2, emp_pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("��� �α��� �� ����");

				//��񿡼� ������ �ҷ�����
	            String get_emp_id = rs.getString(1);
	            String get_emp_pw = rs.getString(2);
	            String get_emp_name = rs.getString(3);
	            String get_emp_phone = rs.getString(4);
		        String get_emp_email = rs.getString(5);
		        String get_dept_no = rs.getString(6);
		        String get_emp_status = rs.getString(7);

				// �ҷ��°� VO�� �ֱ�
	            vo = new EmployeeVO(get_emp_id, get_emp_pw, get_emp_name, get_emp_phone, get_emp_email, get_dept_no, get_emp_status);

			} else {
				System.out.println("��� �α��ΰ� ����");
			}

		} catch (Exception e) {
			System.out.println("�α��� DAO ����");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	// ��й�ȣ ������Ʈ
	public int update(String emp_no, String new_emp_no1) {
		// ������ ���̵�, ���� �Է��� ��й�ȣ �� �ϳ�

		try {
			Connection();

			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_no = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, new_emp_no1);
			psmt.setString(2, emp_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("��й�ȣ ���� DAO ����");
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
				System.out.println("��� �α��� �� ����");

				//��񿡼� ������ �ҷ�����
	            String get_emp_id = rs.getString(1);
	            String get_emp_pw = rs.getString(2);
	            String get_emp_name = rs.getString(3);
	            String get_emp_phone = rs.getString(4);
		        String get_emp_email = rs.getString(5);
		        String get_dept_no = rs.getString(6);
		        String get_emp_status = rs.getString(7);

				// �ҷ��°� VO�� �ֱ�
	            vo = new EmployeeVO(get_emp_id, get_emp_pw, get_emp_name, get_emp_phone, get_emp_email, get_dept_no, get_emp_status);

			} else {
				System.out.println("��� �α��ΰ� ����");
			}

		} catch (Exception e) {
			System.out.println("��й�ȣ ã�� DAO ����");
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
			System.out.println("��й�ȣ Ȯ�� DAO ����");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return check;
	}

	
	// ���� ����
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
			System.out.println("�������� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return check;
	}
	
	// �ӽ� ��й�ȣ ����
	public String createTempPW() { 

        String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String newCode = new String();
        for (int x = 0; x < 8; x++) {
            int random = (int) (Math.random() * str.length);
            newCode += str[random];
        }
        return newCode;
    }
	
	// ��й�ȣ ������Ʈ
	public int tempPwUpdate(String emp_email, String temp_pw) {
		try {
			Connection();

			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_email = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, temp_pw);
			psmt.setString(2, emp_email);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("��й�ȣ ã�� ���� ��й�ȣ ���� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}
	
	
}
