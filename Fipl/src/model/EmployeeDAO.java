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

	// ��� ����
	private void Connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "fipl";
			String dbpw = "fipl";

			conn = DriverManager.getConnection(url, dbid, dbpw);

			System.out.println("DAO Ŀ���� ����");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO Ŀ���� ����");

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
		// vo�� ������ ��� ���
		// EmployeeVO info = null;

		try {
			Connection();
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, emp_no);
			psmt.setString(2, emp_pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("DAO �α��� �� ����");

//				��񿡼� ������ �ҷ����� ( �ҷ��ð� ���ϱ� )
//	            String rs_emp_id = rs.getString(1);
//	            String rs_emp_pw = rs.getString(2);
//	            String rs_emp_name = rs.getString(3);
//	            String rs_emp_phone = rs.getString(4);
//		        String rs_emp_email = rs.getString(5);
//		        String rs_dept_no = rs.getString(6);
//		        String rs_emp_status = rs.getString(7);

//				�ҷ��°� VO�� �ֱ�
//	            info = new EmployeeVO(rs_emp_id, rs_emp_pw, rs_emp_name, rs_emp_phone, rs_emp_email, rs_dept_no, rs_emp_status);

				
			} else {
				System.out.println("DAO �α��ΰ� ����");
			}

		} catch (Exception e) {
			System.out.println("�α��� dao����");
			e.printStackTrace();
		} finally {
			close();
		}
		// ������ �� �����ϱ� ( �̸��̸� �̸�, vo�� vo)
		return null;
	}

	// ��й�ȣ Ȯ��
	public boolean updateCheck(String emp_no, String check_emp_pw) {
		// emp_no�� ������ ���̵�  check_emp_pw�� ��й�ȣ Ȯ���� ���� �Է��� pw
		try {
			Connection();
			
			String sql = "select * from Employees where emp_no = ? and emp_pw =?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, emp_no);
			psmt.setString(2, check_emp_pw);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("DAO ���̵� ��й�ȣ ��ġ");
				check = true;
			} else {
				System.out.println("DAO ���̵� ��й�ȣ ����ġ");
			}
			
		} catch (Exception e) {
			System.out.println("��й�ȣ Ȯ�� DAO ����");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return check;
	}

	public int update(String emp_no, String new_emp_no1) {
		// ������ ���̵�, ���� �Է��� ��й�ȣ �� �ϳ�
		
		try {
			Connection();
			
			String sql = "UPDATE employees SET emp_pw = ? WHERE emp_no = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,new_emp_no1);
			psmt.setString(2,emp_no);
			
			cnt = psmt.executeUpdate();
			
			System.out.println("DAO ��й�ȣ ���� ����");
			
		} catch (Exception e) {
			System.out.println("��й�ȣ ���� DAO ����");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return cnt;
	}

	

	
}
