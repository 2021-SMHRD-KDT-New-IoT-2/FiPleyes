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
		<input type="text" placeholder="���� ��й�ȣ�� �Է����ּ���"><br>
		<button>Ȯ���ϱ�</button><br>
		<input type="text" placeholder="������ ��й�ȣ�� �Է����ּ���"><br>
		<input type="text" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���"><br>
		<button>�����ϱ�</button>				
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
						<tr>
							<td class="id"></td>
							<td class="loca"></td>
							<td class="date"></td>
							<td class="detail"><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><a class="btn js-click-modal">�󼼺���</a></td>
						</tr>
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
							<button>����</button>
							<button>�Ű�</button>
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
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a class="btn js-click-modal-1">�󼼺���</a></td>
				</tr>

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


	<footer> </footer>
	