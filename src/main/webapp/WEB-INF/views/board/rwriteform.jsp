<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<title>MySite</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/board.css">
</head>

<body>
	<div class="wrap">
		<!-- 헤더 -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- /헤더 -->
		<div class="content2 clearfix">
			<aside>
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</aside>

			<main>
				<div class="main-head clearfix">
					<h3>댓글게시판</h3>
					<ol class="clearfix">
						<li>홈</li>
						<li>게시판</li>
						<li>댓글게시판</li>
					</ol>
				</div>

				<div id="board-writeform">

					<form class="form-box" action="${pageContext.request.contextPath}/board/rwrite" method="get">
						<!-- 댓글 -->
						<div class="info-row">
							<label class="info-title" for="txt-title">댓&nbsp;&nbsp;&nbsp;글</label> 
							<input type="text" id="txt-title" name="title" value="" placeholder="댓글 입력">
							<input type="hidden" name="no" value="${param.no}">
						</div>
						
						<div class="btn-box">
							<a class="btn btn-gray btn-md" href="${pageContext.request.contextPath}/board/relist">목록</a>
							<button class="btn btn-blue btn-md" type="submit">등록</button>
						</div>
					</form>
					<!-- //form -->

				</div>


			</main>
		</div>

		<footer>
			<p>Copyright ⓒ 2025 황일영. All right reserved</p>
		</footer>

	</div>

</body>
</html>