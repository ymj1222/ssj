<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>举报</title>
<%@ include file="../../../qcommon.jsp"%>

</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
		$(function() {
			var code = getUrlParam("code");
			get(code);
		});
		function get(code){
			$.ajax({
				type : "POST",
				url : "../ssj/articleReport",
				dataType : 'JSON',
				async : false,
				data : {
					code : code
				},
				success : function(datas) {
					$("#articleCode").val(code);
					$("#articleName").val(datas.name);
				}
			});
		}
		function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
	</script>
<body>
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
			 	  	 	<h2>举报文章</h2>
			 	 	    <form method="post" action="myArticleReportAdd">
						    <input id="articleCode" type="hidden" name="articleCode" >
						    <input id="articleName"type="hidden" name="articleName" >
						    <div>
						    	<select name="type">
											<option>请选择举报类型</option>
											<option value="色情/暴力">色情/暴力</option>
											<option value="内容不符">内容不符</option>
											<option value="虚假宣传">虚假宣传</option>
											<option value="不符合社会主义核心价值观">不符合社会主义核心价值观</option>
										</select>
						    </div>
						    <div>
						     	<textarea name="context" class="form-control"
											placeholder="举报内容">
											</textarea>
						    </div>
						   <div>
						   		<span><input type="submit" class="" value="提交举报"></span>
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