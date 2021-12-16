<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Center Modal</title>

        <style>
            #my_modal {
                display: none;
                width: 300px;
                padding: 20px 60px;
                background-color: #fefefe;
                border: 1px solid #888;
                border-radius: 3px;
            }

            #my_modal .modal_close_btn {
                position: absolute;
                top: 10px;
                right: 10px;
            }
        </style>
    </head>

    <body>
        <div id="my_modal">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita dolore eveniet laborum repellat sit distinctio, ipsa rem dicta alias velit? Repellat doloribus mollitia dolorem
            voluptatum ex reiciendis aut in incidunt?
            <a class="modal_close_btn">�ݱ�</a>
        </div>

        <button id="popup_open_btn">�˾�â ��� �࿰</button>

        <script>
            function modal(id) {
                var zIndex = 9999;
                var modal = document.getElementById(id);

                // ��� div �ڿ� ��������� ���̾�
                var bg = document.createElement('div');
                bg.setStyle({
                    position: 'fixed',
                    zIndex: zIndex,
                    left: '0px',
                    top: '0px',
                    width: '100%',
                    height: '100%',
                    overflow: 'auto',
                    // ���̾� ������ ���⼭ �ٲٸ� ��
                    backgroundColor: 'rgba(0,0,0,0.4)'
                });
                document.body.append(bg);

                // �ݱ� ��ư ó��, �ò��� ���̾�� ��� div �����
                modal.querySelector('.modal_close_btn').addEventListener('click', function() {
                    bg.remove();
                    modal.style.display = 'none';
                });

                modal.setStyle({
                    position: 'fixed',
                    display: 'block',
                    boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

                    // �ò��� ���̾� ���� ��ĭ ���� ���̱�
                    zIndex: zIndex + 1,

                    // div center ����
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    msTransform: 'translate(-50%, -50%)',
                    webkitTransform: 'translate(-50%, -50%)'
                });
            }

            // Element �� style �ѹ��� ������Ʈ�� �����ϴ� �Լ� �߰�
            Element.prototype.setStyle = function(styles) {
                for (var k in styles) this.style[k] = styles[k];
                return this;
            };

            document.getElementById('popup_open_btn').addEventListener('click', function() {
                // ���â ����
                modal('my_modal');
            });
        </script>
    </body>
</html>