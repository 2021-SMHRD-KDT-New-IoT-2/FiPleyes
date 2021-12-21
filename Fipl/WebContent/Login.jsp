<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel = "stylesheet" href="CSS/Login.css">
</head>
<body class = "layout">

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
            <img class="logo" src="img/logo.png"><br><br>	
            <input id = "emp_no" name = "emp_no" class="log_t" type="text" placeholder="���"><br>
            <input id = "emp_pw" name = "emp_pw" class="log_t" type="password" placeholder="PW"><br>
            <input id = "logincheck" type="checkbox" name = "logincheck" value = "true">�α��� ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="�α���">
        </div>
    </form>
</body>

</html>