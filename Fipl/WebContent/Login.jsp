<%@page import="model.EmployeeVO"%>
<%@page import="model.EmployeeDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%!
public String getCookieValue(Cookie[] cookies, String cookieName){
	for (Cookie cookie : cookies) {
		if(cookie.getName().equals(cookieName)){
			return cookie.getValue();
		}
	}
	return "";
}
%>


<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FiPl.net</title>
<link rel="stylesheet" href="CSS/Login.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/script.js" defer></script>
<link rel="stylesheet" href="CSS/Font_l.css">
<link rel="shortcut icon" href="img/favicon.png">
</head>
<body class="layout">


<%

session.removeAttribute("employee");

// 자동로그인을 위한 쿠키 가져오기
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie tempCookie : cookies){
		if(tempCookie.getName().equals("emp_no")){
			// 쿠기에 emp_no가 있으면 세션을 생성하기
			
			String emp_no = getCookieValue(cookies, "emp_no");
			String emp_pw = getCookieValue(cookies, "emp_pw");
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeVO vo = dao.login(emp_no, emp_pw);

			response.setCharacterEncoding("euc-kr");	

			if (vo != null) {
				System.out.println("쿠키로 로그인 성공");
				session.setAttribute("employee", vo);
				System.out.println(vo);
				response.sendRedirect("Main.jsp");

			} else {
				System.out.println("쿠키로 로그인 실패");
				response.sendRedirect("Login.jsp");
			}
		}
	}
}

%>

	<form action="Login" method="post" class="loginForm">
		<div class="fullS">

			<img class="logo" src="img/logo.png"> <br> <br>
			<div class="white">
				<input name="emp_no" class="log_t" type="text" placeholder=  "  사번"><br>
				<hr>
				<br> <input name="emp_pw" class="log_t" type="password" placeholder="  PW"><br>
				<hr>
				<br> <input type="checkbox" name="logincheck" value="true"><a>&nbsp; 로그인유지</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <a id = "modal_btn">비밀번호 찾기</a>
				<br> <br> <input type="submit" value="로그인" class="login_s">
			</div>
		</div>
	</form>
	
	<!-- 비밀번호 수정 모달 -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
	<div class="pw_h"><h3>비밀번호 찾기</h3></div>
	<div class="pw_d">
			<form action="SendPwEmail" method="post">
				<input type="text" name="emp_name" placeholder="이름" class="log_t" required="required"><br>
				<hr class="modal_hr">
				<br> 
				<input type="text" name="emp_phone" placeholder="전화번호" class="log_t" required="required" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" title="###-####-#### 형식으로 입력"><br>
				<hr class="modal_hr">
				<br> 
				<input type="text" name="emp_email" placeholder="이메일" class="log_t" required="required" title="이메일 형식으로 입력해주세요"><br>
				<hr class="modal_hr">
				<br>
				<br> <input type="submit" value="임시 비밀번호 발급" class="login_m">
			</form>
		</div>
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div></div>
	</div>
	

</body>

</html>