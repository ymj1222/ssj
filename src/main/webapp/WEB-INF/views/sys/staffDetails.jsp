<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">
<head>
	<title>Tables</title>
	<%@ include file="../../../common.jsp"%>
</head>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="../js/jquery-2.2.4.js"></script>
	<script>
	$(function() {
		get(); 
	});
	
	function get(){
		$.ajax({
			type : "POST",
			url : "/ssj/staffGet",
			dataType : 'JSON',
			async : false,
			data : {
				code : $("#code").val()
			},
			success : function(datas) {
				$("#account").text(datas.account);
				$("#name").text(datas.name);
				if(datas.state=="0"){
					$("#state").text("试用期");
					}
					if(datas.state=="1"){
						$("#state").text("在职");
						}
					if(datas.state=="2"){
						$("#state").text("离职");
						}
				if(datas.sex=="0"){
				$("#sex").text("男");
				}else if(datas.sex=="1"){
				$("#sex").text("女");	
				}
				$("#birthday").text(datas.birthday);
				$("#tel").text(datas.tel);
				$("#orgCode").text(datas.orgCode);
			}
		});
	}
	
		</script>	
<body>
	<div id="wrapper">
	<!-- 头 -->
	<%@ include file="../../../header.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../mune.jsp"%>
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
									<form class="navbar-form navbar-left">
										<div class="input-group">
											<input type="text" value="" class="form-control" placeholder="Search dashboard...">
											<input type="hidden" id="code" value="${code}" class="form-control" placeholder="Search dashboard...">
											<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
										</div>
									</form>
									<table class="table table-bordered" >
										<thead>
											<tr>
												<th>员工详情</th>
											</tr>
										</thead>
										<tbody id="tbodyId">
											<tr>
												<td>账号</td>
												<td id="account"></td>
												<td>姓名</td>
												<td id="name"></td>
												<td>任职状态</td>
												<td id="state"></td>
												<td>性别</td>
												<td id="sex"></td>
												<td>生日</td>
												<td id="birthday"></td>
												<td>电话</td>
												<td id="tel"></td>
												<td>所在部门</td>
												<td id="orgCode"></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>

</html>