<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="CSS/Login.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/script.js" defer></script>
</head>
<body class="layout">


<%

session.removeAttribute("employee");

// 자동로그인을 위한 쿠키 가져오기
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie tempCookie : cookies){
		if(tempCookie.getName().equals("emp_no")){
			// 쿠기에 emp_no가 있으면 특정 페이지로 이동
			response.sendRedirect("Main.jsp");
		}
	}
}

%>

	<form action="Login" method="post" class="loginForm">
		<div class="fullS">

			<img class="logo" src="img/logo.png"> <br> <br>
			<input name="emp_no" class="log_t" type="text" placeholder=  "  사번"><br>
			<hr>
			<br> <input name="emp_pw" class="log_t" type="password" placeholder="  PW"><br>
			<hr>
			<br> <input type="checkbox" name="logincheck" value="true"><a>&nbsp; 로그인유지</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <a id = "modal_btn">비밀번호 찾기</a>
			<br> <br> <input type="submit" value="로그인" class="login_s">
		</div>
	</form>
	<!-- 비밀번호 수정 모달 -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
	<div class="pw_h"><h3>비밀번호 찾기</h3></div>
	<div class="pw_d">
		<input type="text" placeholder="이름" class="log_t"><br>
		<hr class="modal_hr"><br>
		<input type="text" placeholder="전화번호" class="log_t"><br>
		<hr class="modal_hr"><br>
		<input type="text" placeholder="이메일" class="log_t"><br>
		<hr class="modal_hr"><br><br>
		<input type="submit" value="임시 비밀번호 발급" class="login_m">
	</div>
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div></div>
	</div>
	

</body>

</html>