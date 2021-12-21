<%@page import="model.DeviceVO"%>
<%@page import="model.DeviceDAO"%>
<%@page import="model.ReportVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ReportDAO"%>
<%@page import="model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
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
// 자동로그인을 위한 쿠키 가져오기
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie tempCookie : cookies){
		if(tempCookie.getName().equals("emp_no")){
			session.setAttribute("emp_no", tempCookie.getValue());
		}
	}
}
 
// 그냥 로그인 했을때 세션 가져오기
EmployeeVO vo = (EmployeeVO) session.getAttribute("employee");
String name = vo.getEmp_name(); //가져올 값 지정하기 
String dept = vo.getDept_no();

//신고 리스트
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
		<div> <h1> <%= name %>님 환영합니다.</h1></div>
		<div class="container_h">
			<nav>
				<ul>
					<li><a href="#page2">미처리신고</a></li>
					<li><a href="#page3">보류신고</a></li>
					<li><a href="#page4">이상기기</a></li>
					<li><a href="#page5">MY기기순찰</a></li>
					<li><a id="modal_btn">비밀번호수정</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- 메인 화면 -->
	<section id="page1">
		<div class="fullD">
			<div class=d1 onclick="window.location.href='#page2'">
				<a class ="box_text">미처리신고</a>
			</div>
			<div class=d2 onclick="window.location.href='#page3'">
				<a class ="box_text">보류 신고</a>
			</div>
			<div class=d3 onclick="window.location.href='#page4'">
				<a class ="box_text">이상 기기 관리</a>
			</div>
			<div class=d4 onclick="window.location.href='#page5'">
				<a class ="box_text">MY 기기 관리</a>
			</div>
		</div>

	</section>
	
	<!-- 비밀번호 수정 모달 -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
	<div class="pw_h"><h3>비밀번호 수정</h3></div>
	<div class="pw_d">
		<input type="text" name = "check_emp_pw" id = "check_emp_pw" placeholder="기존 비밀번호를 입력해주세요"><br>
		<em id = "pw_check"></em><br>
		<button onclick = "pwcheck()">확인하기</button><br>
		<input type="text" name ="new_emp_pw1" id = "new_emp_pw1" placeholder="변경할 비밀번호를 입력해주세요"><br>
		<input type="text" name ="new_emp_pw2" id = "new_emp_pw2" placeholder="비밀번호를 한번 더 입력해주세요"><br>
		<button onclick = "pwchange()">수정하기</button>
	</div>
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div></div>
	</div>
	
	<!-- 미처리 신고 관리 페이지 / page2  -->

	<section id="page2">
		<div id="ti_mi">
			<h3>미처리 신고</h3>
		</div>
		<main>
			<table class="scrolltable">
					<thead>
						<tr>
							<th class="id"><h3>ID</h3></th>
							<th class="loca"><h3>위치</h3></th>
							<th class="date"><h3>날짜/시간</h3></th>
							<th class="detail"><h3>상세보기</h3></th>
						</tr>
					</thead>
				<tbody>
					
					<%for (int i = 0; i < report_yet.size(); i++) {%>
					<tr>
						<td id="rep_no" class="id"><%=report_yet.get(i).getRep_no()%></td>
						<td class="loca"><%=reportDao.reportLoc(report_yet.get(i).getDevice_no())%></td>
						<td class="date"><%=report_yet.get(i).getRep_time()%></td>
						<td class="detail"><a class="btn js-click-modal" onclick="repDetail(<%=report_yet.get(i).getRep_no()%>)">상세보기</a></td>
					</tr>
					<%}%>
				</tbody>

			</table>
			
			<!--상세보기 페이지 -->
			<div class="container" id = "detail_rep">
				<div class="black_bg"></div>
				<div class="modal">
				
					<div class="modal_header">미처리 신고</div>
					<div class="modal_main">
						<img id = "detail_rep_img" src="" class="numberpad">
						<table border="1px" class="detail_t">
							<tr>
								<td class="id_d"><h3>ID</h3></td>
								<td id = "detail_rep_no"></td>
							</tr>
							<tr>
								<td class="loca_d"><h3>주소</h3></td>
								<td id = "detail_rep_loc"></td>
							</tr>
							<tr>
								<td class="date_d"><h3>날짜</h3></td>
								<td id = "detail_rep_data"></td>
							</tr>
							<tr>
								<td class="number"><h3>번호</h3></td>
								<td id = "detail_car_no"></td>
							</tr>
							<tr>
								<td class="accu"><h3>누적</h3></td>
								<td id = "detail_rep_add"></td>

							</tr>
						</table>

						<div class="btn_p">
							<a id = "hold_rep" href="">보류</a>
							<a id = "fine_rep" href="">신고</a>
						</div>
						<div class="btn_c_p">
							<a class="btn js-close-modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</main>
	</section>
	
	<!-- 보류신고 관리 페이지 / page3-->

	<section id="page3">
		<div id="ti_mi">
			<h3>보류 신고</h3>
		</div>
		<main>
			<table border="1px">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>위치</h3></td>
					<td class="date"><h3>날짜/시간</h3></td>
					<td class="detail"><h3>상세보기</h3></td>
				</tr>
			<%
				for(int i = 0; i< report_hold.size(); i++){ %>
				<tr>
					<td><%=report_hold.get(i).getRep_no()%></td>
					<td><%=reportDao.reportLoc(report_hold.get(i).getDevice_no()) %></td>
					<td><%=report_hold.get(i).getRep_time() %></td>
					<td><a class="btn js-click-modal-1" onclick="holdDetail(<%= report_hold.get(i).getRep_no()%>)">상세보기</a></td>
				</tr>
				<%} %>

			</table>
		</main>
		<div class="container-1">

			<div class="modal-1">
				<div class="modal_header">보류 신고</div>
				<div class="modal_main">
					<img id = "hold_rep_img" src="" class="numberpad">
					<table border="1px" class="detail_t">
						<tr>
							<td class="id_d"><h3>ID</h3></td>
							<td id = "detail_hold_no"></td>
						</tr>
						<tr>
							<td class="loca_d"><h3>주소</h3></td>
							<td id = "detail_hold_loc"></td>
						</tr>
						<tr>
							<td class="date_d"><h3>날짜</h3></td>
							<td  id = "detail_hold_date"></td>
						</tr>
						<tr>
							<td class="number"><h3>번호</h3></td>
							<td  id = "detail_hold_car_no"></td>
						</tr>
						<tr>
							<td class="accu"><h3>누적</h3></td>
							<td>0</td>
						</tr>
					</table>

					<div class="btn_p">
						<a id = "fine_hold_rep" href="">신고</a>
						<a id = "delet_rep" href="">삭제</a>
					</div>
					<div class="btn_c_p">
						<a class="btn js-close-modal-1">Close</a>
					</div>
				</div>
			</div>
		</div>

	</section>
	
	<!-- 이상 기기 관리 페이지  / page4 -->

	<section id="page4">
		<div id="ti_mi">
			<h3>이상 기기 관리</h3>
		</div>
		<main>
			<table border="1px" class="page4_table">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>위치</h3></td>
					<td class="id"><h3>현재 상태</h3></td>
				</tr>
				<%
				for(int i = 0; i< errorDevice.size(); i++){ %>
				<tr>
					<td><%=errorDevice.get(i).getDevice_no()%></td>
					<td><%=errorDevice.get(i).getDevice_loc()%></td>
					<td>🔴</td>
				</tr>
				<%} %>

			</table>
		</main>
		

	</section>
	
	<!-- My 기기 관리 페이지  / page5 -->

	<section id="page5">
		<div id="ti_mi">
			<h3>MY 기기 순찰</h3>
		</div>
		<main>
			<table border="1px" class="page4_table">
				<tr>
					<td class="id"><h3>ID</h3></td>
					<td class="loca"><h3>위치</h3></td>
					<td class="id"><h3>현재 상태</h3></td>
				</tr>
				<%
				for(int i = 0; i< allDevice.size(); i++){ %>
				<tr>
					<td><%=allDevice.get(i).getDevice_no()%></td>
					<td><%=allDevice.get(i).getDevice_loc()%></td>
					
					<% if(allDevice.get(i).getDevice_status().equals("0")){%>
						<td>🟢</td>
					<%} else if (allDevice.get(i).getDevice_status().equals("1")) {%>
						<td>🔴</td>
					<%} %>
				</tr>
				<%} %>
				

			</table>
			<div class="add_p"><button type="button" id="add_btn"><img class = "add_d" src = "img/add.png">기기추가</button></div>
		</main>
		<div class="add_bg"></div>
		<div class="add_wrap">
			<div class="add_close">
				<a href="">close</a>
			</div>
			
		<article id= "add_device">
			<div>
				<div class="pw_h">
					<h3>새로운 기기 등록</h3>
				</div>
				<form action = "DeviceRegister" method = "post">
				<div class="pw_d">
					<h5>기기 번호</h5>
					<br> <input type="text" placeholder="기기 번호를 입력해주세요" class="add_input" name = "device_no"><br>
					<hr class="hr_one">
					<br>
					<h5>기기 위치</h5>
					<br> <input type="text" placeholder="기기의 위치를 입력해주세요" class="add_input" name = "device_loc"><br>
					<hr class="hr_one">
					<br>
					<br>
					<input id="adding_btn" type = "submit" value = "등록하기 " >
				</div>
				</form>
			</div>
		</article>
		</div>



	</section>


 <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/jquery.min.js"></script>
   <script src="js/browser.min.js"></script>
   <script src="js/breakpoints.min.js"></script>
   <script src="js/util.js"></script>
   <script src="js/main.js"></script>
   
<!-- 비밀번호 중복체크 -->
     <script>
		function pwcheck() {
			var input = $('#check_emp_pw').val();
			
			$.ajax({
				type : "post", // 데이터 전송 방식
				data : {
					"check_emp_pw" : input
				}, // 전송하는 데이터
				url :  "PwCheck", // 데이터를 전송하는 페이지
				dataType : "json", // 응답데이터의 형식
				success : function(data) {
					if (data == true) {
						$("#pw_check").html("비밀번호가 일치합니다.");
		               } else {
		            	$("#pw_check").html("비밀번호가 일치하지 않습니다.");
		               }
				},
				 error : function() { // 실패
		               alert("잠시후 다시 시도해주세요");
		            }
			})
			
		}
	  </script>
	
	<!-- 입력한 두 비밀번호가 일치하는지 확인하고 같다면 변경-->
	  <script>
		function pwchange() {
			var input1 = $('#new_emp_pw1').val();
			var input2 = $('#new_emp_pw2').val();
			
			$.ajax({
				type : "post", // 데이터 전송 방식
				data : {
					"new_emp_pw1" : input1,
					"new_emp_pw2" : input2
				}, // 전송하는 데이터
				url :  "Update", // 데이터를 전송하는 페이지
				dataType : "json", // 응답데이터의 형식
				success : function(data) {
					if (data == true) {
						alert("비밀번호를 변경하였습니다.");
						location.href = "Login.jsp";
					}else {
				 		alert("비밀번호 변경에 실패하였습니다.");
					}
				},
				 error : function() { // 실패
		               alert("잠시후 다시 시도해주세요");
		            }
			})
			}
		</script>


		<!-- 선택한 미처리 신고 상세보기  -->
		 <script>
		function repDetail(rep_no) {
			$.ajax({
				type : "post", // 데이터 전송 방식
				data : {"rep_no" : rep_no }, // 전송하는 데이터
				url :  "ReportGet", // 데이터를 전송하는 페이지
				dataType : "json", // 응답데이터의 형식
				success : function(res) {
					$("#detail_rep_no").html(res.rep_no);
					$("#detail_rep_loc").html(res.device_loc);
					$("#detail_rep_data").html(res.rep_time);
					$("#detail_car_no").html(res.car_no);
					$("#hold_rep").attr("href", "ReportStatusUp?rep_no=" + res.rep_no + "&status=1");
					$("#fine_rep").attr("href", "ReportStatusUp?rep_no=" + res.rep_no + "&status=2");
					$("#detail_rep_img").attr("src", res.rep_file);
				},
				 error : function() { // 실패
		               alert("잠시후 다시 시도해주세요");
		            }
			})
			}
		</script>
		
		
		<!-- 선택한 보류 신고 상세보기  -->
		 <script>
		function holdDetail(rep_no) {
			$.ajax({
				type : "post", // 데이터 전송 방식
				data : {"rep_no" : rep_no }, // 전송하는 데이터
				url :  "ReportGet", // 데이터를 전송하는 페이지
				dataType : "json", // 응답데이터의 형식
				success : function(res) {
					$("#detail_hold_no").html(res.rep_no);
					$("#detail_hold_loc").html(res.device_loc);
					$("#detail_hold_date").html(res.rep_time);
					$("#detail_hold_car_no").html(res.car_no);
					$("#fine_hold_rep").attr("href", "ReportStatusUp?rep_no=" + res.rep_no + "&status=2");
					$("#delet_rep").attr("href", "ReportStatusUp?rep_no=" + res.rep_no + "&status=3");
					$("#hold_rep_img").attr("src", res.rep_file);
				},
				 error : function() { // 실패
		               alert("잠시후 다시 시도해주세요");
		            }
			})
			}
		</script>
	<footer> </footer>
	