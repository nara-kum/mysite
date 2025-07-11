<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>MySite</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/guestbook.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-3.7.1.js"></script>
</head>


<body>
	<div class="wrap">
		<!-- 헤더 -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- /헤더 -->

		<div class="content2 clearfix">
			<aside>
				<h2>방명록</h2>
				<ul>
					<li><a href="">일반방명록</a></li>
					<li><a href="">ajax방명록</a></li>
				</ul>
			</aside>

			<main>

				<div class="main-head clearfix">
					<h3>일반방명록</h3>
					<ol class="clearfix">
						<li>홈</li>
						<li>방명록</li>
						<li>일반방명록</li>
					</ol>
				</div>

				<div id="guestbook-addlist">
					<form id="formAdd" class="form-box" action="" method="get">
						<table>
							<colgroup>
								<col style="width: 70px;">
								<col style="width: 340px;">
								<col style="width: 70px;">
								<col style="width: 340px;">
							</colgroup>
							<tbody>
								<tr>
									<th><label for="txt-name">이름</label>
									</th>
									<td><input id="txt-name" type="text" name="name" value=""></td>
									<th><label for="txt-password">패스워드</label></th>
									<td><input id="txt-password" type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea id="text-content" name="content"></textarea></td>
								</tr>
								<tr>
									<td colspan="4" class="btn-box">
										<button class="btn btn-blue btn-lg" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
					</form>
					
					<div id="gbListArea">
					
					</div>

					<c:forEach items="${requestScope.gList}" var="guestVO">
						<table class="guestbook-item">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tbody>
								<tr>
									<td>${guestVO.no}</td>
									<td>${guestVO.name}</td>
									<td>${guestVO.regDate}</td>
									<td class="txt-center"><a class="btn btn-gray btn-sm" href="${pageContext.request.contextPath}/guestbook/removeform?no=${guestVO.no}">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${guestVO.content}</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</div>

			</main>
		</div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
<!-------------------------------------------------------------------------->
<!-- 모달창 -->
<div class="modal-bg">

	<div class="modal-content">
		<p>비밀번호를 입력해 주세요</p>

		<form id="modalForm" action="" method="">
			<div>
				<input id="text-password" type="password" name="password" value="">
				<input id="txt-no" type="text" name="no">
			</div>
			<button type="submit" class="btn-del btn btn-blue btn-md">삭제</button>
			<button class="btn-close btn btn-gray btn-md">닫기</button>
		</form>
	</div>

</div>

<!-------------------------------------------------------------------------->
<script>
	$(document).ready(function(){
		console.log('돔');

		//리스트 데이터 요청해서 그리는 함수
		fetchList();
		
		//전송버튼 클릭할때
		$('#formAdd').on('submit',function(event){
			console.log('등록버튼');
			event.preventDefault();

			let name = $('#txt-name').val();
			let password = $('#txt-password').val();
			let content = $('#text-content').val();

			let guestVO = {
				name: name,
				password: password,
				content: content
			};

			console.log(guestVO);

			$.ajax({
			
				//보낼때 옵션
				url : "${pageContext.request.contextPath}/api/guestbook/add",
				type : "post",
				// contentType : "application/json",
				data : guestVO,

				//받을때 옵션
				dataType : "json",
				success : function(guestVO){
					//화면 그리기
					rander(guestVO, 'up');
					//폼 비우기
					$('#txt-name').val('');
					$('#txt-password').val('');
					$('#text-content').val('');
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		});

		//삭제버튼 클릭했을때 -->모달창 띄우기
		$('#gbListArea').on('click','.btn-modal',function(){
			console.log('모달창');

			$('.modal-bg').addClass('active');

			//내가 가지고 있는 no값을 모달창 no값 박스에 넣는다
			let $this = $(this);
			let no = $this.data('no');
			console.log(no);

			//번호 추가
			$('#txt-no').val(no);
			
			//비밀번호 값 지우기
			$('#text-password').val('');
		});

		//모달창의 닫기 버튼을 클릭했을때 -->모달창 닫기
		$('.btn-close').on('click',function(){
			console.log('모달창닫기');
			
			$('#txt-password').val('');

			$('.modal-bg').removeClass('active')
		});

		//모달창의 삭제 진짜 삭제
		$('#modalForm').on('submit',function(event){
			console.log('모달창삭제');

			event.preventDefault();

			//데이터 수집

			let password = $('#text-password').val();
			let no = $('#txt-no').val();

			let guestVO = {
				password: password,
				no: no
			};
		
			$.ajax({
			
				//보낼때 옵션
				url : "${pageContext.request.contextPath}/api/guestbook/remove",
				type : "post",
				// contentType : "application/json",
				data : guestVO,

				//받을때 옵션
				dataType : "json",
				success : function(result){
					/*성공시 처리해야될 코드 작성*/
					console.log(result);

					if(result==1){
						//화면에서 지우기
						$('#t'+no).remove();
					}
					
					$('.modal-bg').removeClass('active');
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});

		});

	});

	function fetchList(){
		$.ajax({
			
			//보낼때 옵션
			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			// contentType : "application/json",
			// data : {id: id},

			//받을때 옵션
			dataType : "json",
			success : function(guestVO){
				/*성공시 처리해야될 코드 작성*/
				// console.log(guestVO);

				//화면에 그린다
				for(let i=0; i<guestVO.length; i++){
					rander(guestVO[i], 'down');
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};

	function rander(guestVO, updown){
		console.log(guestVO);
		console.log('그린다');

		let str = '';
		str += '<table id=t' + guestVO.no + '>';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tbody>';
		str += '		<tr>';
		str += '			<td>' + guestVO.no + '</td>';
		str += '			<td>' + guestVO.name + '</td>';
		str += '			<td>' + guestVO.regDate + '</td>';
		str += '			<td class="txt-center">';
		str += '				<button class="btn-modal btn btn-gray btn-sm" data-no="' + guestVO.no + '">삭제</button>';
		str += '			</td>'
		str += '		</tr>';
		str += '		<tr>';
		str += '			<td colspan=4>' + guestVO.content + '</td>';
		str += '		</tr>';
		str += '	</tbody>';
		str += '</table>';
		
		if(updown == 'up'){
			$('#gbListArea').prepend(str);
		}else if(updown == 'down'){
			$('#gbListArea').append(str);
		}
	};
</script>
</body>
</html>