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
            <img class="logo" src="img/logo.png"><br><br>	
            <input id = "emp_no" name = "emp_no" class="log_t" type="text" placeholder="사번"><br>
            <input id = "emp_pw" name = "emp_pw" class="log_t" type="password" placeholder="PW"><br>
            <input id = "logincheck" type="checkbox" name = "logincheck" value = "true">로그인 유지 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="로그인">
        </div>
    </form>
</body>

</html>