package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO {

	ReportVO vo = new ReportVO();
	int cnt = 0;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<ReportVO> al = null;

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

	// �Ű� ���
	public int register(String device_no, String rep_file, String car_no) {
		try {

			Connection();

			String sql = "insert into reports (device_no, rep_time, rep_file, car_no) values (?, sysdate, ?, ?)";
			psmt = conn.prepareStatement(sql);

			// �Ű��ȣ : �ڵ�����, �ǳ��ϰ�� ������ȣ�� null�� ��
			// ó�� ���´� 0(����)�� ����Ʈ ��, ó�� ����ڴ� ���Ŀ� ������
			psmt.setString(1, device_no);
			psmt.setString(2, rep_file);
			psmt.setString(3, car_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("�Ű��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}

	
	// �Ű� ���
	public ArrayList<ReportVO> reportList(String rep_dept, String rep_status) {
		// ���ǿ��� ������ ������� �μ��� �Է¹ް�,
		// ��ó�� �Ű� ��������������� rep_status = 0, 
		// ���� �Ű� ��������������� rep_status = 1, ���� rep_status = 2, ���� rep_status = 3�� ������ �ͼ� ��� �����ֱ�
		
		al = new ArrayList<ReportVO>();

		try {
			Connection();

			String sql = "select * from REPORTS where REP_STATUS= ? and REP_DEPT = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rep_status);
			psmt.setString(2, rep_dept);

			rs = psmt.executeQuery();

			while (rs.next()) {

				// �ǳ����̸� get_car_no�� null, get_emp_no ó���ϱ� ���� null
				String get_rep_no = rs.getString(1);
				String get_device_no = rs.getString(2);
				String get_rep_time = rs.getString(3);
				String get_rep_file = rs.getString(4);
				String get_car_no = rs.getString(5);
				String get_rep_status = rs.getString(6);
				String get_rep_dept = rs.getString(7);
				String get_emp_no = rs.getString(8);

				vo = new ReportVO(get_rep_no, get_device_no, get_rep_time, get_rep_file, get_car_no, get_rep_status,
						get_rep_dept, get_emp_no);

				al.add(vo);
			}

		} catch (Exception e) {
			System.out.println("�Ű� ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}

	
	// �Ű� ���� ����
	public int statusUpdate(String rep_no, String rep_status, String emp_no) {
		// ������ �Ű� ��ȣ�� �Ű� ���¸� ����

		try {
			Connection();

			String sql = "UPDATE REPORTS SET REP_STATUS = ?, EMP_NO = ? WHERE REP_NO = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rep_status);
			psmt.setString(2, emp_no);
			psmt.setString(3, rep_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("�Ű� ���� ���� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;

	}

	// ����̽� ��ȣ�� ���� �Ű� ��ġ �ľ�
	public String reportLoc(String device_no) {
		String get_device_no = null;
		try {
			Connection();
			String sql = "select device_loc from devices where device_no= ?";
			
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				get_device_no = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("�Ű� ���� ���� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return get_device_no;
	}

	// �Ű� ��ȣ �����ֱ�
	public String rep_no(String rep_no) {
		return rep_no;
	}
	
	// �Ű� �󼼳��� ����
	public ReportVO getReport (String rep_no) {
	 System.out.println(rep_no);
		try {
			Connection();

			String sql = "select * from REPORTS where REP_NO= ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rep_no);
			rs = psmt.executeQuery();

			if (rs.next()) {

				String get_rep_no = rs.getString(1);
				String get_device_no = rs.getString(2);
				String get_rep_time = rs.getString(3);
				String get_rep_file = rs.getString(4);
				String get_car_no = rs.getString(5);
				String get_rep_status = rs.getString(6);
				String get_rep_dept = rs.getString(7);
				String get_emp_no = rs.getString(8);

				vo = new ReportVO(get_rep_no, get_device_no, get_rep_time, get_rep_file, get_car_no, get_rep_status,
						get_rep_dept, get_emp_no);

			}

		} catch (Exception e) {
			System.out.println("�Ű� �󼼳��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return vo;
	}
	
}
