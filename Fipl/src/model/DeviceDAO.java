package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

	// 장치 중복
	public boolean duplicateCheck(String device_no) {
		boolean check = false;

		try {
			Connection();

			String sql = "select * from DEVICES where DEVICE_NO=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			rs = psmt.executeQuery();

			check = rs.next();

		} catch (Exception e) {
			System.out.println("장치목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return check;
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
				
				System.out.println(vo);
				al.add(vo);

			}

		} catch (Exception e) {
			System.out.println("장치목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		
		
		Collections.sort(al);
		
		
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

		String get_device_loc = "";

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
	
	// 장치삭제
		public int deleteDevice(String device_no) {
			try {

				Connection();

				String sql = "delete from DEVICES where device_no =?";
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, device_no);

				cnt = psmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("장치등록 DAO 실패");
				e.printStackTrace();

			} finally {
				close();
			}
			return cnt;
		}

	
	// 장치 중복 확인 
	public boolean deviceCheck(String device_no) {
		boolean check = false;
		try {
			Connection();
			
			String sql = "select device_no from devices where device_no=?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, device_no);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				check=true;
			}else {
				check=false;
			}	
		} catch (Exception e) {
			System.out.println("중복확인 실패");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return check;
	}
	
	// 장치를 통한 부서 번호 가지고 오기
	public String getDept_no(String device_no) {
		String getDept_no = "";

		try {
			Connection();

			String sql = "select * from DEVICES where DEVICE_no=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				getDept_no = rs.getString("device_dept");
			}

		} catch (Exception e) {
			System.out.println("장치를 통한 부서번호 가져오는 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}

		return getDept_no;
	}
	
	// 전체 갯수 세기
	public String countDevice(String dept_no) {
		String count = "0";
		try {
			Connection();

			String sql = "select count(*) from devices where DEVICE_DEPT = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dept_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("장치 전체 갯수 가져오는 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
	
	// 이상기기 갯수 세기
	public String countErrorDevice(String dept_no) {
		String count = "0";
		try {
			Connection();

			String sql = "select count(*) from devices where DEVICE_DEPT = ? and DEVICE_STATUS = '1'";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dept_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("이상기기 갯수 가져오는 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
	
	// 디바이스 번호 찾기
	public String findDeviceId(String device_url) {
		String device_no = null;
		System.out.println(device_url);
		try {
			Connection();

			String sql = "select device_no from devices where device_url = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, device_url);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				device_no = rs.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("Url을 통해, id를 찾지 못함");
			e.printStackTrace();
		} finally {
			close();
		}
		return device_no;
	}
	// 디바이스  url 가져오기
	public ArrayList<String> DeviceUrl(){
		
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection();
			String sql = "select device_url from DEVICES";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String get_device_url = rs.getString(1);
				System.out.println(get_device_url);
				al.add(get_device_url);
			}
		} catch (Exception e) {
			System.out.println("장치 URL 목록 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}
	
	
}
