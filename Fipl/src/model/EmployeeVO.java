package model;

public class EmployeeVO {

	private String emp_no;
	private String emp_pw;
	private String emp_name;
	private String emp_phone;
	private String emp_email;
	private String dept_no;
	private String emp_status;

	public EmployeeVO(String emp_no, String emp_pw, String emp_name, String emp_phone, String emp_email, String dept_no,
			String emp_status) {
		super();
		this.emp_no = emp_no;
		this.emp_pw = emp_pw;
		this.emp_name = emp_name;
		this.emp_phone = emp_phone;
		this.emp_email = emp_email;
		this.dept_no = dept_no;
		this.emp_status = emp_status;
	}

	public EmployeeVO() {

	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_pw() {
		return emp_pw;
	}

	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}

	@Override
	public String toString() {
		return "EmployeeVO [emp_no=" + emp_no + ", emp_pw=" + emp_pw + ", emp_name=" + emp_name + ", emp_phone="
				+ emp_phone + ", emp_email=" + emp_email + ", dept_no=" + dept_no + ", emp_status=" + emp_status + "]";
	}

}
