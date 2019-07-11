<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>所有热卖</title>
<%@ include file="../../../qcommon.jsp"%>

</head>
<body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">



function 
checkMobile() {
	var str=document.getElementById("phone").value();
var re = /^1\d{10}$/
if (re.test(str)) {
alert("正确");
} else {
alert("请输入正确的号码...");
alert(str);

}
}</script>

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
			 	  	 	<h2>商品购买</h2>
			 	 	    <form method="post" action="myordersAdd">
			 	 	   		<div>
						    	<span><input name="productCode" type="hidden" class="textbox"value="${productCode}"></span>
						    </div>
						    <div>
						    	<span><input name="productName" type="hidden" class="textbox"value="${productName}"></span>
						    </div>
						    <div>
						    	<span><input name="price" type="hidden" class="textbox"value="${price}"></span>
						    </div>
						    <div>
						    	<span><input name="amount" type="hidden" class="textbox"value="${amount}"></span>
						    </div>
					    	<div>
						    	<span><label>姓名</label></span>
						    	<span><input name="consignee"required="required" type="text" class="textbox"></span>
						    </div>
						    
						    <div>
						    	<span><label>电话</label></span>
						    	<span><input id ="phone"name="phone" required="required"type="text" class="textbox" onchange="checkMobile()" ></span>
						    </div>
						    <div>
						     	<span><label>收货地址</label></span>
						    	<span><input name="receivingAddress"required="required" type="text" class="textbox"></span>
						    </div>
						   <div>
						   		<span><input type="submit" class="" value="提交订单" ></span>
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