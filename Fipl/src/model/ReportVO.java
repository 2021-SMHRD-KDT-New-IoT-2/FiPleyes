package model;

public class ReportVO {

	private String rep_no;
	private String device_no;
	private String rep_time;
	private String rep_file;
	private String car_no;
	private String rep_status;
	private String emp_no;

	public ReportVO(String rep_no, String device_no, String rep_time, String rep_file, String car_no, String rep_status,
			String emp_no) {
		super();
		this.rep_no = rep_no;
		this.device_no = device_no;
		this.rep_time = rep_time;
		this.rep_file = rep_file;
		this.car_no = car_no;
		this.rep_status = rep_status;
		this.emp_no = emp_no;
	}

	public ReportVO() {

	}

	public String getRep_no() {
		return rep_no;
	}

	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getRep_time() {
		return rep_time;
	}

	public void setRep_time(String rep_time) {
		this.rep_time = rep_time;
	}

	public String getRep_file() {
		return rep_file;
	}

	public void setRep_file(String rep_file) {
		this.rep_file = rep_file;
	}

	public String getCar_no() {
		return car_no;
	}

	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}

	public String getRep_status() {
		return rep_status;
	}

	public void setRep_status(String rep_status) {
		this.rep_status = rep_status;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	@Override
	public String toString() {
		return "ReportVO [rep_no=" + rep_no + ", device_no=" + device_no + ", rep_time=" + rep_time + ", rep_file="
				+ rep_file + ", car_no=" + car_no + ", rep_status=" + rep_status + ", emp_no=" + emp_no + "]";
	}

}
