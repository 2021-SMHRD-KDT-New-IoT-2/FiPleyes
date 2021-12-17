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
    <link rel = "stylesheet" href="CSS/main.css">
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
        <div class="head"><h1><a href="">FiPl.net</a></h1></div>
        <div><h1> <%=name %>�� �α��εǾ����ϴ�.</h1></div>
        <div class="container_h">
        <nav>
            <ul>
                <li><a href = "#page2">��ó���Ű�</a></li>
                <li><a href = "#page3">�����Ű�</a></li>
                <li><a href = "#page4">�̻���</a></li>
                <li><a href = "#page5">MY������</a></li>
                <li><a href = "btn js-click-modal-pw">��й�ȣ����</a></li>
            </ul>
        </nav>
        </div>
    </header>
    <!-- ���� ȭ�� -->
    <section id="page1">
    <div class="fullD">
        <div class=d1><a href="#page2">��ó���Ű�</a></div>
        <div class=d2><a href="#page3">���� �Ű�</a></div>
        <div class=d3><a href="#page4">�̻� ��� ����</a></div>
        <div class=d4><a href="#page5">MY ��� ����</a></div>
        <div class=d5><a href="btn js-click-modal-pw">��й�ȣ����</a></div>
    </div>
    			<div class="container-pw">
				  
				 <div class="modal-pw">
				    <div class="modal_header">���� �Ű�</div>
					    <div class="modal_main">
				               <div class="btn_p"><button>����</button></div>
					    <div class="btn_c_p"><a class="btn js-close-modal-pw">Close</a></div>
					 	</div>
					</div>
				</div>
   

    </section>    
        
        <section id="page2">
            <div id="ti_mi"><h3>��ó�� �Ű�</h3></div>
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
                    <td> <a class="btn js-click-modal">�󼼺���</a></td>
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
               <div class="container">
				  
				  <div class="modal">
				    <div class="modal_header">��ó�� �Ű�
				    </div>
				    <div class="modal_main">
				    <img src="img/number.png" class="numberpad">
			               <table border="1px" class = "detail_t">
			                   <tr>
			                    <td class="id_d"><h3>ID</h3></td>
			                    <td ></td>
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
			                    <td class = "number"><h3>��ȣ</h3></td>
			                    <td></td>
			                </tr>
			                <tr>
			                    <td class = "accu"><h3>����</h3></td>
			                    <td></td>
			  
			                </tr>          
			               </table>
			               
			               <div class="btn_p"><button>����</button><button>�Ű�</button></div>
				      <div class="btn_c_p"><a class="btn js-close-modal">Close</a></div>
				    </div>
				  </div>
				</div>               
            </main>
        </section>

        <section id="page3">
            <div id="ti_mi"><h3>���� �Ű�</h3></div>
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
				    <div class="modal_header">���� �Ű�
				    </div>
				    <div class="modal_main">
				         <img src="img/number.png" class="numberpad">
			               <table border="1px" class = "detail_t">
			                  <tr>
			                    <td class="id_d"><h3>ID</h3></td>
			                    <td ></td>
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
			                    <td class = "number"><h3>��ȣ</h3></td>
			                    <td></td>
			                </tr>
			                <tr>
			                    <td class = "accu"><h3>����</h3></td>
			                    <td></td>
			                </tr>          
			               </table>
			               
			               <div class="btn_p"><button>����</button><button>����</button></div>
				      <div class="btn_c_p"><a class="btn js-close-modal-1">Close</a></div>
				  </div>
				</div>
				</div>
               
        </section>

        <section id="page4">
            <div id="ti_mi"><h3>�̻� ��� ����</h3></div>
            <main>
                <table border="1px" class = "page4_table">
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
        
        <section id="page5">
	        <div id="ti_mi"><h3>MY ��� ����</h3></div>
	            <main>
	                <table border="1px" class = "page4_table">
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
 
    
        <footer>
 
        </footer>

	<script> 
		/*��й�ȣ���� �˾�  */
		$('.js-click-modal-pw').click(function(){
		$('.container-pw').addClass('modal-open-pw');
		});

		$('.js-close-modal-pw').click(function(){
		  $('.container-pw').removeClass('modal-open-pw');
		});
    </script>
	<script> 
		/*page2 �˾�  */
		$('.js-click-modal').click(function(){
		$('.container').addClass('modal-open');
		});

		$('.js-close-modal').click(function(){
		  $('.container').removeClass('modal-open');
		});

    </script>
    <script>
   		/*page3 �˾�  */
	    $('.js-click-modal-1').click(function(){
		$('.container-1').addClass('modal-open-1');
		});

		$('.js-close-modal-1').click(function(){
		  $('.container-1').removeClass('modal-open-1');
		});
    </script>
    
  
    
</body>
</html>
