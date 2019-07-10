<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>所有热卖</title>
<%@ include file="qcommon.jsp"%>

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
	 	 <div class="contact">				
				  <div class="contact-form">
			 	  	 	<h2>Contact Us</h2>
			 	 	    <form method="post" action="contact-post.html">
					    	<div>
						    	<span><label>Name</label></span>
						    	<span><input name="userName" type="text" class="textbox"></span>
						    </div>
						    
						    <div>
						    	<span><label>E-mail</label></span>
						    	<span><input name="userEmail" type="text" class="textbox"></span>
						    </div>
						    <div> 
						     	<span><label>Mobile</label></span>
						    	<span><input name="userPhone" type="text" class="textbox"></span>
						    </div>
						    <div>
						    	<span><label>Subject</label></span>
						    	<span><textarea name="userMsg"> </textarea></span>
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
<%@ include file="qtop.jsp"%>
</body>
</html>