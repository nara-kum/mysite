<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<title>MySite</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gallery.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-3.7.1.js"></script>
</head>

<body>
	<div class="wrap">

		<!-- 해더 + 네비 ------------------------------------>
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- 해더 + 네비 ----->

		<div class="content2 clearfix">
			
			<!-- 해더 + 네비 ------------------------------------>
			<c:import url="/WEB-INF/views/include/asideAttach.jsp"></c:import>
			<!-- 해더 + 네비 ----->

			<main>
				<div class="main-head clearfix">
					<h3>일반갤러리</h3>
					<ol class="clearfix">
						<li>홈</li>
						<li>갤러리</li>
						<li>일반갤러리</li>
					</ol>
				</div>

				<div id="gallery-list">
					<c:if test="${sessionScope.authUser!=null}">
						<div id="img-upload" class="btn-box">
							<button class="btn btn-blue btn-md" type="submit">이미지올리기</button>
						</div>
					</c:if>

					<ul class="clearfix">

						<!-- 이미지반복영역 -->
						 
					<c:forEach items="${requestScope.galleryVO}" var="galleryVO">
						<li>
							<div id="no-${galleryVO.no}" class="card" data-no="${galleryVO.no}" data-user="${galleryVO.userNo}">
								<img src="${pageContext.request.contextPath}/galleryupload/${galleryVO.saveName}">
								<div class="writer" data-content="${galleryVO.content}">
									작성자: <strong>${galleryVO.writeName}</strong>
								</div>
							</div>
						</li>
						<!-- 이미지반복영역 -->
					</c:forEach>
					</ul>
				</div>

			</main>
		</div>

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	
			
	<!-- 모달창 -->
	<!-- 업로드 모달창 -->
	<div id="modal-upload" class="modal-bg">

		<div class="modal-content">

			<div id="modal-close" class="clearfix">
				<button class="btn-close">X</button>
			</div>

			<p class="title">이미지등록 모달창</p>

			<form id="imgupload-form" action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
				<div class="info-row">
					<label for="txt-content">글작성</label> <input id="txt-content" type="text" name="content" value="">
				</div>

				<div class="info-row">
					<label for="txt-file">이미지선택</label> <input type="file" name="file" value="">
				</div>
				<div class="btn-box">
					<button type="submit" class="btn-del btn btn-blue btn-md">등록</button>
				</div>
			</form>

		</div>

	</div>

	<!-- 이미지보기 모달창 active -->
	<div id="modal-view" class="modal-bg">

		<div class="modal-content">

			<div id="img-close" class="clearfix">
				<button class="btn-close">X</button>
			</div>

			<p class="title">이미지보기 모달창</p>

			<div id="img-view">
				<img src="">

				<div class="img-content"></div>

				<div class="btn-box">
					<button type="submit" class="btn-del btn btn-blue btn-md">삭제</button>
				</div>

			</div>


		</div>

	</div>
	
<script>
	$(document).ready(function(){
		console.log('돔트리');

		//업로드 모달창 띄우기
		$('#img-upload').on('click',function(){
			console.log('업로드모달');

			$('#modal-upload.modal-bg').addClass('active');

		});

		//업로드 모달창 닫기
		$('#modal-close').on('click',function(){
			console.log('업로드모달닫기');

			$('#modal-upload.modal-bg').removeClass('active')

		});
		
		//이미지 모달창 띄우기
		$('.card').on('click',function(){
			console.log('이미지 모달창');
			
			$('#modal-view.modal-bg').addClass('active');

			//모달창으로 img src값 가져와서 img src자리에 넣어주기
			let imgSrc = $(this).children('img').attr('src');
			$("#img-view img").attr('src', imgSrc);

			//내용 가져오기
			let imgContent =  $(this).children('.writer').data('content');
			$("#img-view .img-content").text(imgContent);
			
			//글번호 가져오기
			let imgNo =  $(this).data('no');
			console.log(imgNo);
			
			//유저번호 가져오기
			let imgUser =  $(this).data('user');
			console.log(imgUser);

			// 모달에 글번호 저장
			$('#img-view').attr('data-no', imgNo);

			// 선택된 카드 DOM 저장
			selectedCard = $(this);

		});
		
		//업로드 모달창 닫기
		$('#img-close').on('click',function(){
			console.log('업로드모달닫기');

			$('#modal-view.modal-bg').removeClass('active')

		});

		//이미지 삭제
		$('#img-view .btn-box').on('click',function(){
			console.log('삭제버튼');

			event.preventDefault();

			//글번호 가져오기
			let imgNo =  $('.card').data('no');
			console.log(imgNo);
			
			$.ajax({
			
				//보낼때 옵션
				url : "${pageContext.request.contextPath}/gallery/remove/"+imgNo,
				type : "delete",
				// contentType : "application/json",
				data : imgNo,

				//받을때 옵션
				dataType : "json",
				success : function(jsonResult){
					
					if(jsonResult.result == 'success'){
						if (selectedCard) {
							selectedCard.remove();
						}

						$('#modal-view.modal-bg').removeClass('active');
					}else{
						$('#modal-view.modal-bg').removeClass('active');
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});

		});

	});

</script>

</body>
</html>