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

	// 장치등록
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
			System.out.println("장치등록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}

	// 전체 장치목록
	public ArrayList<DeviceVO> allList(String device_dept) {
		// 세션에서 가져온 사용자의 부서를 입력받아서 해당 부서의 기기만 보여주게 한다

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
			System.out.println("장치목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}

	// 이상기기 장치목록
	public ArrayList<DeviceVO> errorDevice(String device_dept) {
		al = new ArrayList<DeviceVO>();

		try {
			Connection();

			String sql = "select * from DEVICES where DEVICE_STATUS='1' and DEVICE_DEPT=?";
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
			System.out.println("장치목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}

	// 장치 상태 업데이트
	public int statusUpdate(String device_no) {
		// 기기 이상 데이터가 들어온 기기 번호
		try {
			Connection();

			String sql = "UPDATE DEVICES SET DEVICE_STATUS = '1' WHERE DEVICE_NO = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("장치 상태 변경 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}

	// 디바이스 위치 검색
	public String deviceLocation(String device_no) {
		
		String get_device_loc="";

		try {
			Connection();

			String sql = "select * from DEVICES where DEVICE_no=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				get_device_loc = rs.getString("device_loc");
			}

		} catch (Exception e) {
			System.out.println("장치목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		
		return get_device_loc;
	}

}
