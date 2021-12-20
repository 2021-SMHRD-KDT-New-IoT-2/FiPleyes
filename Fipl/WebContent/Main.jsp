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
<body class="layout">
	<header>
		<div class="head">
			<h1>
				<a href="#"><img class = "logo" src = "img/logo_1.png"> </a>
			</h1>
		</div>
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
		<input type="text" placeholder="기존 비밀번호를 입력해주세요"><br>
		<button>확인하기</button><br>
		<input type="text" placeholder="변경할 비밀번호를 입력해주세요"><br>
		<input type="text" placeholder="비밀번호를 한번 더 입력해주세요"><br>
		<button>수정하기</button>				
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
						<tr>
							<td class="id"></td>
							<td class="loca"></td>
							<td class="date"></td>
							<td class="detail"><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">상세보기</a></td>
						</tr>
					</tbody>

				</table>
			
			<!--상세보기 페이지 -->
			<div class="container">
				<div class="black_bg"></div>
				<div class="modal">
				
					<div class="modal_header">미처리 신고</div>
					<div class="modal_main">
						<img src="img/number.png" class="numberpad">
						<table border="1px" class="detail_t">
							<tr>
								<td class="id_d"><h3>ID</h3></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
								<td class="number"><h3>번호</h3></td>
								<td></td>
							</tr>
							<tr>
								<td class="accu"><h3>누적</h3></td>
								<td></td>

							</tr>
						</table>

						<div class="btn_p">
							<button>보류</button>
							<button>신고</button>
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
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">상세보기</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">상세보기</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">상세보기</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">상세보기</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">상세보기</a></td>
				</tr>

			</table>
		</main>
		<div class="container-1">

			<div class="modal-1">
				<div class="modal_header">보류 신고</div>
				<div class="modal_main">
					<img src="img/number.png" class="numberpad">
					<table border="1px" class="detail_t">
						<tr>
							<td class="id_d"><h3>ID</h3></td>
							<td></td>
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
							<td class="number"><h3>번호</h3></td>
							<td></td>
						</tr>
						<tr>
							<td class="accu"><h3>누적</h3></td>
							<td></td>
						</tr>
					</table>

					<div class="btn_p">
						<button>보류</button>
						<button>삭제</button>
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
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>

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
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>

			</table>
			<div class="add_p"><button type="button" id="add_btn"><img class = "add_d" src = "img/add.png">기기추가</button></div>
		</main>
		<div class="add_bg"></div>
		<div class = "add_wrap">
		<div class="add_close">
			<a href="">close</a>		
			
				</div>
				<div><div class="pw_h"><h3>새로운 기기 등록</h3></div>
			<div class="pw_d">
				<h5>기기 번호</h5><br>
				<input type="text" placeholder="기기 번호를 입력해주세요" class="add_input"><br>
				<hr class = "hr_one">
				<br><h5>기기 위치</h5><br>
				<input type="text" placeholder="기기의 위치를 입력해주세요" class="add_input"><br>
				<hr class = "hr_one"><br><br>
				
				<button id="adding_btn">등록하기</button></div>
			</div>
		</div>
		
		

	</section>


	<footer> </footer>
	