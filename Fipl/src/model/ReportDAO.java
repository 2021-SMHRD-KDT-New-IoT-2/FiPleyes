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

	// 신고 등록
	public int register(String device_no, String rep_file, String car_no, String rep_time) {
		
		DeviceDAO dao = new DeviceDAO();
		String dept_no = dao.getDept_no(device_no);
		
		try {

			Connection();

			String sql = "insert into reports (device_no, rep_time, rep_file, car_no, rep_dept) values (?, sysdate, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			// 신고번호 : 자동생성, 실내일경우 차량번호가 null로 들어감
			// 처리 상태는 0(정상)이 디폴트 값, 처리 담당자는 추후에 지정됨
			psmt.setString(1, device_no);
//			psmt.setString(2, rep_time);
			psmt.setString(2, rep_file);
			psmt.setString(3, car_no);
			psmt.setString(4, dept_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("신고등록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}

	// 신고 목록
	public ArrayList<ReportVO> reportList(String rep_dept, String rep_status) {
		// 세션에서 가져온 사용자의 부서를 입력받고,
		// 미처리 신고 목록페이지에서는 rep_status = 0,
		// 보류 신고 목록페이지에서는 rep_status = 1, 벌금 rep_status = 2, 삭제 rep_status = 3를 가지고 와서
		// 목록 보여주기

		al = new ArrayList<ReportVO>();

		try {
			Connection();

			System.out.println("DAO 매개변수 report_dept : " + rep_dept + "/ rep_status : " + rep_status);

			String sql = "select * from REPORTS where REP_STATUS= ? and REP_DEPT = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rep_status);
			psmt.setString(2, rep_dept);

			rs = psmt.executeQuery();

			while (rs.next()) {

				// 실내용이면 get_car_no은 null, get_emp_no 처리하기 전엔 null
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
			System.out.println("신고 목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}

	// 신고 상태 변경
	public int statusUpdate(String rep_no, String rep_status, String emp_no) {
		// 가져온 신고 번호의 신고 상태를 변경

		try {
			Connection();

			String sql = "UPDATE REPORTS SET REP_STATUS = ?, EMP_NO = ? WHERE REP_NO = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, rep_status);
			psmt.setString(2, emp_no);
			psmt.setString(3, rep_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("신고 상태 변경 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;

	}

	// 디바이스 번호를 통해 신고 위치 파악
	public String reportLoc(String device_no) {
		String get_device_loc = null;
		try {
			Connection();
			String sql = "select device_loc from devices where device_no= ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				get_device_loc = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("신고 상태 변경 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return get_device_loc;
	}

	public ReportVO getReport(String rep_no) {
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
				String device_loc = reportLoc(get_device_no);

				vo = new ReportVO(get_rep_no, get_device_no, get_rep_time, get_rep_file, get_car_no, get_rep_status,
						get_rep_dept, get_emp_no, device_loc);
			}

		} catch (Exception e) {
			System.out.println("신고 상세내역 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return vo;
	}


public ReportVO getReport2(String rep_no) {
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
			String device_loc = reportLoc(get_device_no);
			int total_report = totalReport(get_car_no);
			
			vo = new ReportVO(get_rep_no, get_device_no, get_rep_time, get_rep_file, get_car_no, 
					get_rep_status, get_rep_dept, get_emp_no, device_loc, total_report);
		}

	} catch (Exception e) {
		System.out.println("신고 상세내역 DAO 실패");
		e.printStackTrace();

	} finally {
		close();
	}
	return vo;
}

	
	// 누적 건수 계산
	public int totalReport(String car_no) {
		
		try {
			Connection();

			String sql = "SELECT COUNT(car_no) FROM reports where car_no=?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, car_no);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("누적건수 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
		
	}

	
	// 차량 번호당 이전까지 누적 신고 횟수 조회
	public int accumulateCounter(String car_no) {
		al = new ArrayList<ReportVO>();
		try {
			Connection();

			System.out.println("DAO 매개변수 car_not : " + car_no);

			String sql = "select * from REPORTS where CAR_NO= ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, car_no);

			rs = psmt.executeQuery();

			while (rs.next()) {

				// 실내용이면 get_car_no은 null, get_emp_no 처리하기 전엔 null
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
			System.out.println("신고 목록 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}
		return al.size() - 1;
	}

	// 미처리 신고 전체 갯수 세기
	public String countReport(String dept_no) {
		String count = "0";
		try {
			Connection();

			String sql = "select count(*) from reports where rep_dept = ? and rep_status = '0' ";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dept_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("보류 신고 전체 갯수 가져오는 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
	
	// 미처리 신고 전체 갯수 세기
	public String countHoldReport(String dept_no) {
		String count = "0";
		try {
			Connection();

			String sql = "select count(*) from reports where rep_dept = ? and rep_status = '1' ";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dept_no);

			rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("미처리 신고 전체 갯수 가져오는 DAO 실패");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
}
