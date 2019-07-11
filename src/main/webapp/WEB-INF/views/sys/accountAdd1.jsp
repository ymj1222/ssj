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
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="accountAdd1" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">编辑账号信息</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										名称: 
										<input type="text" id="name" name="name"required="required"/>
										账号: 
										<input type="text" id="account" onchange = "vaild();"  onkeyup="this.value=this.value.replace(/[^\d]/g,'') " name="account"required="required"/>
										密码: 
										<input type="password" id="password" name="password"required="required"/>
										确认密码: 
										<input type="password" id="rPassword"onchange="fuck();" name="rPassword"required="required"/>
									</div>
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
<script type="text/javascript">
	function fuck(){
		var password=$("#password").val();
		var rPassword=$("#rPassword").val();
		if(password!=rPassword){
			alert("请确认前后密码一致");
			$("#rPassword").val("");
		}
	}
	
	
    function vaild(){
		$.ajax({
			type : "POST",
			url : "/ssj/accountGet",
			dataType : 'JSON',
			async : false,
			data : {
				account:$("#account").val()
			},
			success : function(datas) {
				if(datas.account !=null && datas.account !=""){
					$("#account").val("");
					 alert("该账号已被注册"); 
					 return false;
				}
			}
		});
	}
</script>
</html>
