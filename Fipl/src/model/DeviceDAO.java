package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceDAO {

	DeviceVO vo = null;
	int cnt = 0;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ArrayList<DeviceVO> al = null;
	
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

	// ��ġ���
	public int register(String device_no, String device_loc, String device_dept) {
		try {

			Connection();

			String sql = "insert into DEVICES values (?, sysdate, ?, '0', ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);
			psmt.setString(2, device_loc);
			psmt.setString(3, device_dept);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}
	
	
	// ��ġ���
	public ArrayList<DeviceVO> allDevice(String device_dept) {
		al = new ArrayList<DeviceVO>();

		try {
			Connection();

			String sql = "select * from DEVICES where DEVICE_DEPT=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_dept);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String get_device_no = rs.getString(1);
				String get_device_date = rs.getString(2);
				String get_device_loc = rs.getString(3);
				String get_device_status = rs.getString(4);
				String get_device_dept = rs.getString(5);

				vo = new DeviceVO(get_device_no, get_device_date, get_device_loc, get_device_status, get_device_dept);

				al.add(vo);

			}

		} catch (Exception e) {
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}
	
	// ��ġ ���� ������Ʈ
	public int statusUpdate() {
		return 0;
	}
	
}
