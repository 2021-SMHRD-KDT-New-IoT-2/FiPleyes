<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel = "stylesheet" href="CSS/View.css">
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/script.js" defer></script>
</head>
<body class = "layout">
    <header>
        <div class="head"><h1><a href="">FiPl.net</a></h1></div>
           <div class="container">
        <nav>
            <ul>
                <li><a href = "#page2">미처리 신고</a></li>
                <li><a href = "#page3">보류 신고</a></li>
                <li><a href = "#page4">이상 기기 관리</a></li>
                <li><a href = "#page5">MY 기기 관리</a></li>
                <li><a href = "#">MYPAGE</a></li>
            </ul>
        </nav>
        </div>
    </header>
    <!-- 메인 화면 -->
    <section id="page1">
    <div class="container">
   		<div><h3>미처리 신고</h3></div>
            <main>
            <img src="img/number.png" class="numberpad">
               <table border="1px">
                   <tr>
                    <td class="id_d"><h3>ID</h3></td>
                    <td ></td>
                   </tr>
                   <tr>
                    <td class="loca_d"><h3>주소</h3></td>
                    <td></td>
                   </tr>
                   <tr>
                    <td class="date_d"><h3>날짜</h3></td>
                    <td></td>
                </tr>
                <tr>
                    <td class = "number_d"><h3>번호</h3></td>
                    <td></td>
                </tr>
                <tr>
                    <td class = "accu_d"><h3>누적</h3></td>
                    <td></td>
  
                </tr>
          
               </table>
               <button class = "btn_b">보류</button>
               <button class = "btn_s">신고</button>
               <a href="#" class = "check">LOGIN</a>            
            </main>
        </div>
        
        <div class="login" style="transform: translate(-50%) translateY(-50%); opacity :1;">
        <span>로그인</span>	
        <form action="Login" method="post">
            <input type="text" placeholder="ID" name="id">
            <input type="password" placeholder="PASSWORD" name="pw">
            <input class="login_btn" type="submit" value="LOGIN">
        </form>
        <div class="login_close"><img src="img/xx.png" alt=""></div>
    </div>

    </section>
    
    <footer>
       
    </footer>
    
    
    
    
</body>
</html>