<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>${name}</title>
<%@ include file="qcommon.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		list();
		ph('${code}','${amount}');
	});
	function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/selectIs",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$("#articleCode").val(datas.code)
				cont = cont + datas.content;
				$("#tableName").text(datas.name);
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont);
				$("#number").html(datas.number);
			}
		});
	}

	function page(pageNow, pageCount) {
		$("#pageNowSpanId").text(pageNow);
		$("#pageCountSpanId").text(pageCount);

		$("#pageCountId").val(pageCount);
		if (pageNow > 1) {
			$("#upPageId").val(pageNow - 1);
		} else {
			$("#upPageId").val(1);
		}
		if (pageNow == pageCount) {
			$("#downPageId").val(pageCount);
		} else {
			$("#downPageId").val(pageNow + 1);
		}
	}

	function lastPage() {
		list($("#pageCountId").val(), $("#pageSizeid").val());
	}
	function upPage() {
		list($("#upPageId").val(), $("#pageSizeid").val());
	}
	function downPage() {
		list($("#downPageId").val(), $("#pageSizeid").val());
	}
	function changPageSize() {
		var pageSize = $("#pageSizeId").val();
		list(1, pageSize);
	}
	function search() {
		changPageSize();
	}
</script>
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
				<!-- start content -->
				<div class="single">
					<!-- start span1_of_1 -->
					<div class="left_content">
						<div class="span1_of_1">
							<!-- start product_slider -->
							<div class="product-view">
								<div class="product-essential">
									<div class="product-img-box">
										<div class="more-views" style="float: left;">
											<div class="more-views-container">
												<ul>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
													<li><a class="cs-fancybox-thumbs"
														data-fancybox-group="thumb"
														style="width: 64px; height: 85px;" href="" title="" alt="">
															<img src="" src_main="" title="" alt="" />
													</a></li>
												</ul>
											</div>
										</div>
										<div class="product-image">
											<a class="cs-fancybox-thumbs cloud-zoom"
												rel="adjustX:30,adjustY:0,position:'right',tint:'#202020',tintOpacity:0.5,smoothMove:2,showTitle:true,titleOpacity:0.5"
												data-fancybox-group="thumb" href="images/0001-2.jpg"
												title="Women Shorts" alt="Women Shorts"> <img
												src="images/0001-2.jpg" alt="Women Shorts"
												title="Women Shorts" />
											</a>
										</div>
										<script type="text/javascript">
											function ph(code,amount) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
												$
														.ajax({
															type : "POST",//请求方式为post
															url : "../ssj/selectphoto",//请求url
															dataType : 'JSON',//数据格式为json
															async : false,//是否同步，false表示为异步 
															data : {
																code : code,
															},
															success : function(
																	datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
																var sel='<option value="1">1</option>';
																if(amount >= 2){
																	sel=sel+='<option value="2">2</option>';
																}
																if(amount >=3){
																	sel=sel+='<option value="3">3</option>';
																}	if(amount >= 4){
																	sel=sel+='<option value="4">4</option>';
																}	if(amount >= 5){
																	sel=sel+='<option value="5">5</option>';
																}
																$("#amount").html(sel);
																var cont = '';
																var num = 0;
																$
																		.each(
																				datas,
																				function(
																						i,
																						obj) {
																					if (num != 0) {
																						cont += ',';
																					}
																					cont = cont
																							+ '"item_'
																							+ num
																							+ '":{"orig":"'
																							+ obj.url
																							+ '","main":"'
																							+ obj.url
																							+ '","thumb":"'
																							+ obj.url
																							+ '","label":""} ';
																					num += 1
																				});
																var prodGallery = jQblvg
																		.parseJSON('{"prod_1":{"main":{"orig":"${photo1}","main":"${photo1}","thumb":"${photo1}","label":""},"gallery":{'
																				+ cont
																				+ '},"type":"simple","video":false}}'), gallery_elmnt = jQblvg('.product-img-box'), galleryObj = new Object(), gallery_conf = new Object();
																gallery_conf.moreviewitem = '<a class="cs-fancybox-thumbs" data-fancybox-group="thumb" style="width:64px;height:85px;" href=""  title="" alt=""><img src="" src_main="" width="64" height="85" title="" alt="" /></a>';
																gallery_conf.animspeed = 200;
																jQblvg(document)
																		.ready(
																				function() {
																					galleryObj[1] = new prodViewGalleryForm(
																							prodGallery,
																							'prod_1',
																							gallery_elmnt,
																							gallery_conf,
																							'.product-image',
																							'.more-views',
																							'http:');
																					jQblvg(
																							'.product-image .cs-fancybox-thumbs')
																							.absoluteClick();
																					jQblvg(
																							'.cs-fancybox-thumbs')
																							.fancybox(
																									{
																										prevEffect : 'none',
																										nextEffect : 'none',
																										closeBtn : true,
																										arrows : true,
																										nextClick : true,
																										helpers : {
																											thumbs : {
																												width : 64,
																												height : 85,
																												position : 'bottom'
																											}
																										}
																									});
																					jQblvg(
																							'#wrap')
																							.css(
																									'z-index',
																									'100');
																					jQblvg(
																							'.more-views-container')
																							.elScroll(
																									{
																										type : 'vertical',
																										elqty : 4,
																										btn_pos : 'border',
																										scroll_speed : 400
																									});

																				});
															}
														});
											}

											function initGallery(a, b, element) {
												galleryObj[a] = new prodViewGalleryForm(
														prods, b,
														gallery_elmnt,
														gallery_conf,
														'.product-image',
														'.more-views', '');
											};
										</script>
									</div>
								</div>
							</div>
							<!-- end product_slider -->
						</div>
						<!-- start span1_of_1 -->
						<div class="span1_of_1_des">
							<div class="desc1">
								<form action="finMyOrdersAdd">
									<h3>${name}</h3>
									<h5>${sell}</h5>
									<input name="productCode" type="hidden" value="${code}"
										class="textbox"> <input name="productName"
										type="hidden" value="${name}" class="textbox"> <input
										name="price" type="hidden" value="${sell}" class="textbox">
									<div class="available">
										<h4>Available Options :</h4>
										<ul>
											<li>颜色: ${color}</li>
											<li>尺寸: ${size}</li>
											<li>数量: <select id="amount" name="amount">
											</select>
											</li>
										</ul>
										<div class="btn_form">

											<input type="submit" value="点击购买" title="" />
								</form>
							</div>

						</div>

					</div>
				</div>
				<div class="clear"></div>
				<!-- start tabs -->
				<section class="tabs">
					<input id="tab-1" type="radio" name="radio-set"
						class="tab-selector-1" checked="checked"> <label
						for="tab-1" class="tab-label-1">商品介绍</label> <input id="tab-2"
						type="radio" name="radio-set" class="tab-selector-2">

					<div class="clear-shadow"></div>

					<div class="content">
						<div class="content-1">
							<p class="para top">
								<span>${induction}</span>
							</p>
							<div class="clear"></div>
						</div>
					</div>
				</section>
				<!-- end tabs -->
			</div>
			<!-- start sidebar -->
			<form action="finmyArticleReport">
				<div class="left_sidebar">
					<div class="sellers">
						<h4 id="tableName"></h4>
						<textarea id="tbodyId" rows="49" cols="36 " readOnly="readOnly"></textarea>
						<input id="articleCode" name="code" type="hidden">
					</div>
					<input type="submit" class="" value="举报此文章">
				</div>
			</form>
			<!-- end sidebar -->
			<div class="clear"></div>
		</div>
		<!-- end content -->
	</div>
	</div>
	</div>
	<!-- 以下函数代码用于右下角箭头上滑 -->
	<%@ include file="qtop.jsp"%>
</body>
</html>