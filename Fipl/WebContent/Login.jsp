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

// �ڵ��α����� ���� ��Ű ��������
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie tempCookie : cookies){
		if(tempCookie.getName().equals("emp_no")){
			// ��⿡ emp_no�� ������ Ư�� �������� �̵�
			response.sendRedirect("Main.jsp");
		}
	}
}

%>

	<form action="Login" method="post" class="loginForm">
		<div class="fullS">

			<img class="logo" src="img/logo.png"> <br> <br>
			<input name="emp_no" class="log_t" type="text" placeholder=  "  ���"><br>
			<hr>
			<br> <input name="emp_pw" class="log_t" type="password" placeholder="  PW"><br>
			<hr>
			<br> <input type="checkbox" name="logincheck" value="true"><a>&nbsp; �α�������</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <a id = "modal_btn">��й�ȣ ã��</a>
			<br> <br> <input type="submit" value="�α���" class="login_s">
		</div>
	</form>
	<!-- ��й�ȣ ���� ��� -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
	<div class="pw_h"><h3>��й�ȣ ã��</h3></div>
	<div class="pw_d">
		<input type="text" placeholder="�̸�" class="log_t"><br>
		<hr class="modal_hr"><br>
		<input type="text" placeholder="��ȭ��ȣ" class="log_t"><br>
		<hr class="modal_hr"><br>
		<input type="text" placeholder="�̸���" class="log_t"><br>
		<hr class="modal_hr"><br><br>
		<input type="submit" value="�ӽ� ��й�ȣ �߱�" class="login_m">
	</div>
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div></div>
	</div>
	

</body>

</html>