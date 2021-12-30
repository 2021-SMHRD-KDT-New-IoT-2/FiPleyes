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

	// ��ġ �ߺ�
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
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return check;
	}

	// ��ü ��ġ���
	public ArrayList<DeviceVO> allList(String device_dept) {
		// ���ǿ��� ������ ������� �μ��� �Է¹޾Ƽ� �ش� �μ��� ��⸸ �����ְ� �Ѵ�

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
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		
		
		Collections.sort(al);
		
		
		return al;
	}

	// �̻��� ��ġ���
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
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}

	// ��ġ ���� ������Ʈ
	public int statusUpdate(String device_no) {
		// ��� �̻� �����Ͱ� ���� ��� ��ȣ
		try {
			Connection();

			String sql = "UPDATE DEVICES SET DEVICE_STATUS = '1' WHERE DEVICE_NO = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, device_no);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("��ġ ���� ���� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return cnt;
	}

	// ����̽� ��ġ �˻�
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
			System.out.println("��ġ��� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}

		return get_device_loc;
	}
	
	// ��ġ����
		public int deleteDevice(String device_no) {
			try {

				Connection();

				String sql = "delete from DEVICES where device_no =?";
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, device_no);

				cnt = psmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("��ġ��� DAO ����");
				e.printStackTrace();

			} finally {
				close();
			}
			return cnt;
		}

	
	// ��ġ �ߺ� Ȯ�� 
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
			System.out.println("�ߺ�Ȯ�� ����");
			e.printStackTrace();
			
		}finally {
			close();
		}
		return check;
	}
	
	// ��ġ�� ���� �μ� ��ȣ ������ ����
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
			System.out.println("��ġ�� ���� �μ���ȣ �������� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}

		return getDept_no;
	}
	
	// ��ü ���� ����
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
			System.out.println("��ġ ��ü ���� �������� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
	
	// �̻��� ���� ����
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
			System.out.println("�̻��� ���� �������� DAO ����");
			e.printStackTrace();

		} finally {
			close();
		}

		return count;
	}
	
	// ����̽� ��ȣ ã��
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
			System.out.println("Url�� ����, id�� ã�� ����");
			e.printStackTrace();
		} finally {
			close();
		}
		return device_no;
	}
	// ����̽�  url ��������
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
			System.out.println("��ġ URL ��� ����");
			e.printStackTrace();

		} finally {
			close();
		}
		return al;
	}
	
	
}
