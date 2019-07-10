<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>所有热卖</title>
<%@ include file="qcommon.jsp"%>
<script type="text/javascript">
	$(function() {
		var code = getUrlParam("code");
		list(code);
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
		filterList.init();
	});
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if (r != null)
			return unescape(r[2]);
		return null; //返回参数值
	}
	function list(code) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$
				.ajax({
					type : "POST",//请求方式为post
					url : "../ssj/selecttoproject",//请求url
					dataType : 'JSON',//数据格式为json
					async : false,//是否同步，false表示为异步 
					data : {
						code:code
					},
					success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
						var cont = '';
						$
								.each(
										datas,
										function(i, obj) {
											cont = cont
													+ '<h3>'
													+ obj.name
													+ '</h3><h3>'
													+ obj.induction
													+ '</h3><div class="portfolio logo1" data-cat="'+obj.typeName+'"><div class="portfolio-wrapper"><a href="Productdetail?code='
													+ obj.code
													+ '"> <img src="'+obj.brandName+'" alt="Image 2" width="400" height="400" /></a><div class="label"><div class="label-text"><a class="text-title">ethnic fashion guide: sarees</a> <span class="text-category">'
													+ obj.induction
													+ '</span></div><div class="label-bg"></div></div></div></div>';
										});
						$("#port").html(cont);
					}
				});
	}
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
				<!-- start gallery_sale  -->
				<div class="gallery1">
					<div class="container" id="a">
						<div id="port"></div>
					</div>
					<!-- container -->
					<script type="text/javascript" src="js/fliplightbox.min.js"></script>
					<script type="text/javascript">
						$('body').flipLightBox()
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