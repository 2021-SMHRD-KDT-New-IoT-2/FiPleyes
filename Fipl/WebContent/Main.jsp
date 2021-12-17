<%@page import="model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="CSS/main.css">
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
			<div class=d1>
				<a href="#page2">��ó���Ű�</a>
			</div>
			<div class=d2>
				<a href="#page3">���� �Ű�</a>
			</div>
			<div class=d3>
				<a href="#page4">�̻� ��� ����</a>
			</div>
			<div class=d4>
				<a href="#page5">MY ��� ����</a>
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
		</main>

	</section>


	<footer> </footer>


	<!-- ��й�ȣ ���� ��� -->
	<script>
		window.onload = function() {

			function onClick() {
				document.querySelector('.modal_wrap').style.display = 'block';
				document.querySelector('.black_bg').style.display = 'block';
			}
			function offClick() {
				document.querySelector('.modal_wrap').style.display = 'none';
				document.querySelector('.black_bg').style.display = 'none';
			}

			document.getElementById('modal_btn').addEventListener('click',
					onClick);
			document.querySelector('.modal_close').addEventListener('click',
					offClick);

		};
	</script>


	<script>
		/*page2 �˾�  */
		$('.js-click-modal').click(function() {
			$('.container').addClass('modal-open');
			$('.black_bg').fadeIn(1000);      
	        $('.black_bg').fadeTo("slow");
		});

		$('.js-close-modal').click(function() {
			$('.container').removeClass('modal-open');
			$('.black_bg, .window').hide();
		});
	</script>
	
	<script>
		/*page3 �˾�  */
		$('.js-click-modal-1').click(function() {
			$('.container-1').addClass('modal-open-1');
			$('.black_bg').fadeIn(1000);      
	        $('.black_bg').fadeTo("slow");
		});

		$('.js-close-modal-1').click(function() {
			$('.container-1').removeClass('modal-open-1');
			$('.black_bg, .window').hide();
		});
	</script>



</body>
</html>
