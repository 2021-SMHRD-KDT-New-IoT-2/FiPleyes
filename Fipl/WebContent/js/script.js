//비밀번호 수정 모달 

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

/* 메뉴 상단바 색깔 변경*/

$(function(){
	
	$('li').click(function(){
		$('li').removeClass()
			$(this).addClass('on')
	})
})

/*page2 팝업  */
$('.js-click-modal').click(function() {
	$('.container').addClass('modal-open');
	$('.black_bg').fadeIn(1000);
	$('.black_bg').fadeTo("slow");
});

$('.modal_close_div').click(function() {
	$('.container').removeClass('modal-open');
	$('.black_bg, .window').hide();
});

/*page3 팝업  */
$('.js-click-modal-1').click(function() {
	$('.container-1').addClass('modal-open-1');
	$('.black_bg').fadeIn(1000);
	$('.black_bg').fadeTo("slow");
});

$('.modal_close_div_1').click(function() {
	$('.container-1').removeClass('modal-open-1');
	$('.black_bg, .window').hide();
});

//기기 추가 모달


window.addEventListener('load',function() {

	function onClick() {
		document.querySelector('.add_wrap').style.display = 'block';
		document.querySelector('.add_bg').style.display = 'block';
	}
	function offClick() {
		document.querySelector('.add_wrap').style.display = 'none';
		document.querySelector('.add_bg').style.display = 'none';
	}

	document.getElementById('add_btn').addEventListener('click',
		onClick);
	document.querySelector('.add_close').addEventListener('click',
		offClick);

});


