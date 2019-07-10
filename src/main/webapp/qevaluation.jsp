<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>評價</title>
<%@ include file="qcommon.jsp"%>
<script type="text/javascript">

	$(function() {

		var filterList = {

			init : function() {

				// MixItUp plugin
				// http://mixitup.io
				$('#portfoliolist').mixitup({
					targetSelector : '.portfolio',
					filterSelector : '.filter',
					effects : [ 'fade' ],
					easing : 'snap',
					// call the hover effect
					onMixEnd : filterList.hoverEffect()
				});
			},
			hoverEffect : function() {
				$('#portfoliolist .portfolio').hover(function() {
					$(this).find('.label').stop().animate({
						bottom : 0
					}, 200, 'easeOutQuad');
					$(this).find('img').stop().animate({
						top : -30
					}, 500, 'easeOutQuad');
				}, function() {
					$(this).find('.label').stop().animate({
						bottom : -40
					}, 200, 'easeInQuad');
					$(this).find('img').stop().animate({
						top : 0
					}, 300, 'easeOutQuad');
				});

			}

		};
</script>
</head>
<body>
	<!-- start header -->
	<!-- 头 -->
	<%@ include file="qheader.jsp"%>
	<!-- 菜单 -->
	<%@ include file="qmune.jsp"%>
	<!-- start slider -->



	<!-- start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<form action="/ssj/ProductEvaluationInsert" method="post">
					<p align="center">
					<input type="hidden"  name="productCode" value="${productCode} "></input>
						<textarea name="context" placeholder="留言" rows="8" cols="100"></textarea><br /> <select
							name=type required="required">
							<option value="2">服务一般</option>
							<option value="1">服务态度差</option>
							<option value="3">满意</option>
							<option value="4">非常满意</option>
						</select> <br /> <input type="submit">
					</p>

				</form>
				<!-- start gallery_sale  -->
				<div class="gallery1">
					<div class="container" id="a">
						<div id="port"></div>
					</div>
					<!-- container -->
					<script type="text/javascript" src="js/fliplightbox.min.js"></script>
					<script type="text/javascript">
						$('body').flipLightBox();
					</script>
					<div class="clear"></div>
				</div>
				<!---End-gallery----->
			</div>
		</div>
	</div>
	<!-- 以下函数代码用于右下角箭头上滑 -->
	<%@ include file="qtop.jsp"%>
</body>
</html>