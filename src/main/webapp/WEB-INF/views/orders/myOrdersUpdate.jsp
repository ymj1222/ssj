<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>订单修改</title>
<%@ include file="../../../qcommon.jsp"%>
</head>
<body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function() {
	var code = getUrlParam("code");
	get(code);
});
function get(code) {
	$.ajax({
		type : "POST",
		url : "../ssj/myOrdersUpdateQuery",
		dataType : 'JSON',
		async : false,
		data : {
			code : code
		},
		success : function(datas) {
			$("#code").val(datas.code);
			$("#amount").val(datas.amount);
			$("#receivingAddress").val(datas.receivingAddress);
			$("#phone").val(datas.phone);
			$("#consignee").val(datas.consignee);
		}
	});
}
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; //返回参数值
}
</script>
<!-- start header -->
<!-- 头 -->
	<%@ include file="../../../qheader.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../qmune.jsp"%>
<!-- start slider -->
			


<!-- start main -->
<div class="main_bg">
<div class="wrap">	
<div class="main">
	 	 <div class="contact">				
				  <div class="contact-form">
			 	  	 	<h2>修改订单</h2>
			 	 	    <form method="post" action="myOrdersUpdateSave?code=${code}">
						    <div>
						    	<span><input name="amount" type="hidden"required="required"value="${amount}" class="textbox"></span>
						    </div>
						    <div>
						     	<span><label>收货地址</label></span>
						    	<span><input name="receivingAddress"required="required" value="${receivingAddress}" type="text" class="textbox"></span>
						    </div>
						    <div>
						    	<span><label>联系电话</label></span>
						    	<span><input name="phone"required="required" value="${phone}"type="text" class="textbox"> </span>
						    </div>
						    <div>
						    	<span><label>姓名</label></span>
						    	<span><input name="consignee"required="required" value="${consignee}"type="text" class="textbox"> </span>
						    </div>
						   <div>
						   		<span><input type="submit" class="" value="提交订单"></span>
						  </div>
					    </form>
				    </div>
  				<div class="clear"></div>		
			  </div>
		</div>
</div>
</div>	
<!-- 以下函数代码用于右下角箭头上滑 -->
<%@ include file="../../../qtop.jsp"%>
</body>
</html>