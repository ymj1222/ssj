<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>登录页面</title>
<%@ include file="common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center">登录</div>
								<p class="lead">Login to your account</p>
							</div>
							<form class="form-auth-small" action="logging" method="POST">
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">Email</label>
									<input type="hidden"value="fuck" name="fuckYou"id="fuckYou">
									<input type="text" class="form-control" id="account" name="account"  placeholder="Email">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<input type="password" class="form-control" id="password" name="password"  placeholder="Password">
								</div>
								 <div align="center" style="display: none;" id="code">
		<input type="hidden" name="hiddenCode" id="hiddenCodeId" value="${hiddenCode}">
          <img id="vimg" title="点击更换" onclick="changeCode();" src="/image"><br /> <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" name="randomCode" style="width:150px;">
		</div>${errorMark}
								<button type="submit" class="btn btn-primary btn-lg btn-block">LOGIN</button>
								<div class="bottom">
									<span class="helper-text"> <a href="toAccountAdd?fuck=fuck">注册  </a></span>
								</div>
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">泽俊公司</h1>
							<p>by十三</p>
						</div>
					</div>
			
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	err();
});
	function err()
	{
		var hiddenCodeId = $("#hiddenCodeId").val();
		if(hiddenCodeId =="")
		{
			document.getElementById("code").style.display = "none";//将验证码输入框隐藏起来
		}
		else if(hiddenCodeId == "1")
		{
			document.getElementById("code").style.display = "block";//将验证码输入框隐藏起来
		}
	}
	
	function changeCode()
	{
		var imgNode = document.getElementById("vimg");
		//Math.random(); 随机数，只是为了每次都请求sevlet，如果每次请求的都是同一个值，那么只会请求一次servlet
		imgNode.src = "/ssj/image";
	}
</script>
</html>
