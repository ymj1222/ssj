<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>编辑</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="../js/jquery-2.2.4.js"></script>
	<script>
	 function checkPhone(){ 
	        var phone = document.getElementById('phone').value;
	        if(!(/^1[34578]\d{9}$/.test(phone))){ 
	        	$("#phone").val("");
	            alert("手机号码有误，请重填");  
	            return false; 
	        } 
	    }
	 function checkWechat(){ 
	        var phone = document.getElementById('wechat').value;
	        if(!(/^1[34578]\d{9}$/.test(wechat))){ 
	        	$("#wechat").val("");
	            alert("微信号有误，请重填");  
	            return false; 
	        } 
	    }
		</script>	
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="usersAdd" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">添加用户</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										姓名: 
										<input type="text" id="name"name="name"required="required"  />
									</div>
									<div class="panel-body">
										账号: 
										<input type="text" id="account"onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "maxlength="11" name="account"required="required" />
									</div>
									<div class="panel-body">
										密码: 
										<input type="password" id="password" name="password"required="required" />
									</div>
									<div class="panel-body">
										微信: 
										<input type="text" id="wechat"maxlength="11" onchange="checkWechat();"name="wechat" required="required" />
									</div>
									<div class="panel-body">
									电话: <br />
									<input type="text" id="phone"maxlength="11" onchange = "checkPhone();" name="phone"required="required" /><br />
									</div>
									<div class="panel-body">
									所在城市: <br />
									<input type="text" id="city" name="city" required="required"/><br />
									</div>
									性别:
									<label class="fancy-checkbox">
										<input type="radio" id="sex1" name="sex"required="required" value="1">
										<span>男</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio"id="sex2" name="sex"required="required" value="0">
										<span>女</span>
									</label>
									
									<div class="panel-body">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
