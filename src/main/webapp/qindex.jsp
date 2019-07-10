<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>客户端首页 </title>
<%@ include file="qcommon.jsp"%>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.cslider.js"></script><!-- 广告-->
<link href="css/owl.carousel.css" rel="stylesheet">
<script type="text/javascript" src="js/modernizr.custom.28468.js"></script><!-- 7件商品展示-->
<!-- start slider -->		

	
	
	<script type="text/javascript">
		$(function() {
			$('#da-slider').cslider();
			list();
		});
		function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
			$.ajax({
						type : "POST",//请求方式为post
						url : "../ssj/advertisementqueryl",//请求url
						dataType : 'JSON',//数据格式为json
						async : false,//是否同步，false表示为异步 
						data : {
						},
						success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
							$.each(datas,function(i, obj) {
								var src=document.getElementById(i);
								var href=document.getElementById("h"+i);
								
										src.src=obj.photo;
								href.href="advertisementClickUpdate?photo="+obj.photo;
								queryName(obj.photo,i);
									});
						}
					});
		}
		function queryName(url,i) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
			$.ajax({
						type : "POST",//请求方式为post
						url : "../ssj/advertisementqueryll",//请求url
						dataType : 'JSON',//数据格式为json
						async : false,//是否同步，false表示为异步 
						data : {
							url:url
						},
						success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
							$("#n"+i).html(datas.name);
						}
					});
		}
	</script>
		<!-- 展示样式 -->
		     <!-- Owl Carousel Assets -->
		    <!-- Prettify -->
			<!--用于1-7商品展示件数 -->
		    <script src="js/owl.carousel.js"></script>
		        <script>
		    $(document).ready(function() {
		
		      $("#owl-demo").owlCarousel({
		        items : 5,
		        lazyLoad : true,
		        autoPlay : true,
		        navigation : true,
			    navigationText : ["",""],
			    rewindNav : false,
			    scrollPerPage : false,
			    pagination : false,
    			paginationNumbers: false,
		      });
		
		    });
		   
		    </script>
		   <!-- //Owl Carousel Assets -->
		<!-- start top_js_button -->
</head>
<body>
<!-- start header -->
<!-- 头 -->
	<%@ include file="qheader.jsp"%>
	<!-- 菜单 -->
	<%@ include file="qmune.jsp"%>
<!-- start slider -->
			


<!-- start main -->
<div id="da-slider" class="da-slider">
				<div class="da-slide">	
					<h2>春季热销</h2>
					<p id="n0"></p>
					<a id="h0" href="" class="da-link">shop now</a>
					<div class="da-img"><img src="" id="0"alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>夏季热销</h2>
					<p id="n1"></p>
					<a id="h1"href="" class="da-link">shop now</a>
					<div class="da-img"><img src=""id="1" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>秋季热销</h2>
					<p id="n2"></p>
					<a id="h2"href="" class="da-link">shop now</a>
					<div class="da-img"><img src="" id="2"alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>冬季热销</h2>
					<p id="n3"></p>
					<a id="h3" href="" class="da-link">shop now</a>
					<div class="da-img"><img src="" id="3"alt="image01" /></div>
				</div>
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span>
					<span class="da-arrows-next"></span>
				</nav>
				<!-- 广告左右切换-->
			</div>
<!----start-cursual---->
<div class="wrap">
<!----start-img-cursual---->
	<div id="owl-demo" class="owl-carousel">
	
		<div class="item" id="d0" onClick="">
			<div class="cau_left">
				<img class="lazyOwl" src="" id="4"alt="Lazy Owl Image">
			</div>	
			<div class="cau_left">
				<h4 id="n4"></h4>
				<a id="h4" href="" class="btn">shop</a>
			</div>
		</div>	
		
		<div class="item" id="d1" onClick="">
			<div class="cau_left">
				<img class="lazyOwl" src="" id="5"alt="Lazy Owl Image">
			</div>
			<div class="cau_left">
				<h4 id="n5"></h4>
				<a id="h5" href="" class="btn">shop</a>
			</div>
		</div>
		<div class="item" id="d2" onClick="">
			<div class="cau_left">
				<img class="lazyOwl" id="6"src="" alt="Lazy Owl Image">
			</div>
			<div class="cau_left">	
				<h4 id="n6"></h4>
				<a id="h6"href="" class="btn">shop</a>
			</div>
		</div>
	</div>
</div>
<!-- start main1 -->
<!-- 以下函数代码用于右下角箭头上滑 -->
<%@ include file="qtop.jsp"%>
</body>
</html>