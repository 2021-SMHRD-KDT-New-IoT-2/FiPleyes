<%@page import="model.DeviceVO"%>
<%@page import="model.DeviceDAO"%>
<%@page import="model.ReportVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ReportDAO"%>
<%@page import="model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="CSS/main.css">
<link rel="stylesheet" href="CSS/Font.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/script.js" defer></script>

</head>
<body class = "layout">
<%
// �ڵ��α����� ���� ��Ű ��������
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie tempCookie : cookies){
		if(tempCookie.getName().equals("emp_no")){
			// ��⿡ emp_no�� ������ Ư�� �������� �̵�
			session.setAttribute("emp_no", tempCookie.getValue());
		}
	}
}
 
// �׳� �α��� ������ ���� ��������
EmployeeVO vo = (EmployeeVO) session.getAttribute("employee");
String name = vo.getEmp_name(); //������ �� �����ϱ� 
String dept = vo.getDept_no();

//�Ű� ����Ʈ
ReportDAO reportDao = new ReportDAO();
ArrayList<ReportVO> report_yet = reportDao.reportList(dept, "0");
ArrayList<ReportVO> report_hold = reportDao.reportList(dept, "1");
ArrayList<ReportVO> report_delete = reportDao.reportList(dept, "2");

DeviceDAO deviceDao = new DeviceDAO();
ArrayList<DeviceVO> allDevice = deviceDao.allList(dept);
ArrayList<DeviceVO> errorDevice = deviceDao.errorDevice(dept);
%>
	<header>
		<div class="head">
			<h1>
				<a href="#"><img class = "logo" src = "img/logo_1.png"> </a>
			</h1>
		</div>
		<div> <h1> <%= name %>�� ȯ���մϴ�.</h1></div>
		<div class="container_h">
			<nav>
				<ul>
					<li><a href="#page2">��ó���Ű�</a></li>
					<li><a href="#page3">�����Ű�</a></li>
					<li><a href="#page4">�̻���</a></li>
					<li><a href="#page5">MY������</a></li>
					<li><a id="modal_btn">��й�ȣ����</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- ���� ȭ�� -->
	<section id="page1">
		<div class="fullD">
			<div class=d1 onclick="window.location.href='#page2'">
				<a class ="box_text">��ó���Ű�</a>
			</div>
			<div class=d2 onclick="window.location.href='#page3'">
				<a class ="box_text">���� �Ű�</a>
			</div>
			<div class=d3 onclick="window.location.href='#page4'">
				<a class ="box_text">�̻� ��� ����</a>
			</div>
			<div class=d4 onclick="window.location.href='#page5'">
				<a class ="box_text">MY ��� ����</a>
			</div>
		</div>

	</section>
	
	<!-- ��й�ȣ ���� ��� -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
	<div class="pw_h"><h3>��й�ȣ ����</h3></div>
	<div class="pw_d">
		<input type="text" name = "check_emp_pw" id = "check_emp_pw" placeholder="���� ��й�ȣ�� �Է����ּ���"><br>
		<button onclick = "pwcheck()">Ȯ���ϱ�</button><br>
		<input type="text" name ="new_emp_pw1" id = "new_emp_pw1" placeholder="������ ��й�ȣ�� �Է����ּ���"><br>
		<input type="text" name ="new_emp_pw2" id = "new_emp_pw2" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���"><br>
		<button onclick = "pwchange()">�����ϱ�</button>
	</div>
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div></div>
	</div>
	
	<!-- ��ó�� �Ű� ���� ������ / page2  -->

	<section id="page2">
		<div id="ti_mi">
			<h3>��ó�� �Ű�</h3>
		</div>
		<main>
			<table class="scrolltable">
					<thead>
						<tr>
							<th class="id"><h3>ID</h3></th>
							<th class="loca"><h3>��ġ</h3></th>
							<th class="date"><h3>��¥/�ð�</h3></th>
							<th class="detail"><h3>�󼼺���</h3></th>
						</tr>
					</thead>
				<tbody>
					
					<%for (int i = 0; i < report_yet.size(); i++) {%>
					<tr>
						<td id="rep_no" class="id"><%=report_yet.get(i).getRep_no()%></td>
						<td class="loca"><%=reportDao.reportLoc(report_yet.get(i).getDevice_no())%></td>
						<td class="date"><%=report_yet.get(i).getRep_time()%></td>
						<td class="detail"><a class="btn js-click-modal" onclick="repDetail()">�󼼺���</a></td>
					</tr>
					<%}%>
				</tbody>

			</table>
			
			<!--�󼼺��� ������ -->
			<div class="container">
				<div class="black_bg"></div>
				<div class="modal">
				
					<div class="modal_header">��ó�� �Ű�</div>
					<div class="modal_main">
						<img src="img/number.png" class="numberpad">
						<table border="1px" class="detail_t">
							<tr>
								<td class="id_d"><h3>ID</h3></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td class="loca_d"><h3>�ּ�</h3></td>
								<td></td>
							</tr>
							<tr>
								<td class="date_d"><h3>��¥</h3></td>
								<td></td>
							</tr>
							<tr>
								<td class="number"><h3>��ȣ</h3></td>
								<td></td>
							</tr>
							<tr>
								<td class="accu"><h3>����</h3></td>
								<td></td>

							</tr>
						</table>

						<div class="btn_p">
							<a href="ReportStatusUp?rep_no=2&status=1">����</a>
							<a href="ReportStatusUp?rep_no=2&status=2">�Ű�</a>
						</div>
						<div class="btn_c_p">
							<a class="btn js-close-modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</main>
	</section>
	
	<!-- �����Ű� ���� ������ / page3-->

	<section id="page3">
		<div id="ti_mi">
			<h3>���� �Ű�</h3>
		</div>
		<main>
			<table border="1px">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>��ġ</h3></td>
					<td class="date"><h3>��¥/�ð�</h3></td>
					<td class="detail"><h3>�󼼺���</h3></td>
				</tr>
			<%
				for(int i = 0; i< report_hold.size(); i++){ %>
				<tr>
					<td><%=report_hold.get(i).getRep_no()%></td>
					<td><%=reportDao.reportLoc(report_hold.get(i).getDevice_no()) %></td>
					<td><%=report_hold.get(i).getRep_time() %></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>
				<%} %>

			</table>
		</main>
		<div class="container-1">

			<div class="modal-1">
				<div class="modal_header">���� �Ű�</div>
				<div class="modal_main">
					<img src="img/number.png" class="numberpad">
					<table border="1px" class="detail_t">
						<tr>
							<td class="id_d"><h3>ID</h3></td>
							<td></td>
						</tr>
						<tr>
							<td class="loca_d"><h3>�ּ�</h3></td>
							<td></td>
						</tr>
						<tr>
							<td class="date_d"><h3>��¥</h3></td>
							<td></td>
						</tr>
						<tr>
							<td class="number"><h3>��ȣ</h3></td>
							<td></td>
						</tr>
						<tr>
							<td class="accu"><h3>����</h3></td>
							<td></td>
						</tr>
					</table>

					<div class="btn_p">
						<button>����</button>
						<button>����</button>
					</div>
					<div class="btn_c_p">
						<a class="btn js-close-modal-1">Close</a>
					</div>
				</div>
			</div>
		</div>

	</section>
	
	<!-- �̻� ��� ���� ������  / page4 -->

	<section id="page4">
		<div id="ti_mi">
			<h3>�̻� ��� ����</h3>
		</div>
		<main>
			<table border="1px" class="page4_table">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>��ġ</h3></td>
					<td class="id"><h3>���� ����</h3></td>
				</tr>
				<%
				for(int i = 0; i< errorDevice.size(); i++){ %>
				<tr>
					<td><%=errorDevice.get(i).getDevice_no()%></td>
					<td><%=errorDevice.get(i).getDevice_loc()%></td>
					<td><%=errorDevice.get(i).getDevice_status()%></td>
				</tr>
				<%} %>

			</table>
		</main>
		

	</section>
	
	<!-- My ��� ���� ������  / page5 -->

	<section id="page5">
		<div id="ti_mi">
			<h3>MY ��� ����</h3>
		</div>
		<main>
			<table border="1px" class="page4_table">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>��ġ</h3></td>
					<td class="id"><h3>���� ����</h3></td>
				</tr>
				<%
				for(int i = 0; i< allDevice.size(); i++){ %>
				<tr>
					<td><%=allDevice.get(i).getDevice_no()%></td>
					<td><%=allDevice.get(i).getDevice_loc()%></td>
					<td><%=allDevice.get(i).getDevice_status()%></td>
				</tr>
				<%} %>
				

			</table>
			<div class="add_p"><button type="button" id="add_btn"><img class = "add_d" src = "img/add.png">����߰�</button></div>
		</main>
		<div class="add_bg"></div>
		<div class = "add_wrap">
		<div class="add_close">
			<a href="">close</a>		
			
				</div>
				<div><div class="pw_h"><h3>���ο� ��� ���</h3></div>
			<div class="pw_d">
				<h5>��� ��ȣ</h5><br>
				<input type="text" placeholder="��� ��ȣ�� �Է����ּ���" class="add_input"><br>
				<hr class = "hr_one">
				<br><h5>��� ��ġ</h5><br>
				<input type="text" placeholder="����� ��ġ�� �Է����ּ���" class="add_input"><br>
				<hr class = "hr_one"><br><br>
				
				<button id="adding_btn">����ϱ�</button></div>
			</div>
		</div>
		
		

	</section>


 <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/jquery.min.js"></script>
   <script src="js/browser.min.js"></script>
   <script src="js/breakpoints.min.js"></script>
   <script src="js/util.js"></script>
   <script src="js/main.js"></script>
<!-- ��й�ȣ �ߺ�üũ -->
     <script>
		function pwcheck() {
			var input = $('#check_emp_pw').val();
			console.log("pwcheck() : " + input);
			
			$.ajax({
				type : "post", // ������ ���� ���
				data : {
					"check_emp_pw" : input
				}, // �����ϴ� ������
				url :  "PwCheck", // �����͸� �����ϴ� ������
				dataType : "json", // ���䵥������ ����
				success : function(data) {
					if (data == true) {
		                  alert("��й�ȣ�� ��ġ�մϴ�.")
		               } else {
		                  alert("��й�ȣ�� �ٸ��ϴ�.")
		               }
				},
				 error : function() { // ����
		               alert("����� �ٽ� �õ����ּ���")
		            }
			})
			
		}
	  </script>
	
	<!-- �Է��� �� ��й�ȣ�� ��ġ�ϴ��� Ȯ���ϰ� ���ٸ� ����-->
	  <script>
		function pwchange() {
			var input1 = $('#new_emp_pw1').val();
			var input2 = $('#new_emp_pw2').val();
	
			$.ajax({
				type : "post", // ������ ���� ���
				data : {
					"new_emp_pw1" : input1,
					"new_emp_pw2" : input2
				}, // �����ϴ� ������
				url :  "Update", // �����͸� �����ϴ� ������
				dataType : "json", // ���䵥������ ����
				success : function(data) {
					
					console.log(data);
					if (data == true) {
						alert("��й�ȣ�� �����Ͽ����ϴ�.")
					}else {
				 		alert("��й�ȣ ���濡 �����Ͽ����ϴ�.")
					}
				},
				 error : function() { // ����
		               alert("����� �ٽ� �õ����ּ���")
		            }
			})
			}
		</script>

		<!-- ������ �Ű� ���̵� �Ѿ�ɴϴ�. -->
		 <script>
		function pwchange() {
			var input1 = $('#new_emp_pw1').val();
			var input2 = $('#new_emp_pw2').val();
	
			$.ajax({
				type : "post", // ������ ���� ���
				data : {
					"new_emp_pw1" : input1,
					"new_emp_pw2" : input2
				}, // �����ϴ� ������
				url :  "Update", // �����͸� �����ϴ� ������
				dataType : "json", // ���䵥������ ����
				success : function(data) {
					
					console.log(data);
					if (data == true) {
						alert("��й�ȣ�� �����Ͽ����ϴ�.")
					}else {
				 		alert("��й�ȣ ���濡 �����Ͽ����ϴ�.")
					}
				},
				 error : function() { // ����
		               alert("����� �ٽ� �õ����ּ���")
		            }
			})
			}
		</script>
	<footer> </footer>
	