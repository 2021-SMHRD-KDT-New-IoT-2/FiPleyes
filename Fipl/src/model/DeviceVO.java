package model;

public class DeviceVO {

	private String device_no;
	private String device_date;
	private String device_loc;
	private String device_status;
	private String device_dept;

	public DeviceVO(String device_no, String device_date, String device_loc, String device_status, String device_dept) {
		super();
		this.device_no = device_no;
		this.device_date = device_date;
		this.device_loc = device_loc;
		this.device_status = device_status;
		this.device_dept = device_dept;
	}

	public DeviceVO() {

	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getDevice_date() {
		return device_date;
	}

	public void setDevice_date(String device_date) {
		this.device_date = device_date;
	}

	public String getDevice_loc() {
		return device_loc;
	}

	public void setDevice_loc(String device_loc) {
		this.device_loc = device_loc;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

	public String getDevice_dept() {
		return device_dept;
	}

	public void setDevice_dept(String device_dept) {
		this.device_dept = device_dept;
	}

	@Override
	public String toString() {
		return "DeviceVO [device_no=" + device_no + ", device_date=" + device_date + ", device_loc=" + device_loc
				+ ", device_status=" + device_status + ", device_dept=" + device_dept + "]";
	}

}
