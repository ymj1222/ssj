<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>

<head>
<title>专题修改</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
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
		url : "../ssj/specialUpdateQuery",
		dataType : 'JSON',
		async : false,
		data : {
			code : code
		},
		success : function(datas) {
			$("#code").val(datas.code);
			$("#name").val(datas.name);
			$("#type").val(datas.type);
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
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="specialUpdateSave?code=${code}">
		
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">修改专题</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
								<div class="panel-body">
										<input type="hidden" name="code" value="${code}"
											class="form-control" placeholder="编号">
									</div>
									<div class="panel-body">
										<input type="hidden" name="type" value="${type}"
											   class="form-control" placeholder="type">
									</div>
								<div class="panel-body">
										<input type="hidden" name="id" value="${id}"
											class="form-control" placeholder="id">
									</div>
									<div class="panel-body">
										<input type="hidden" name="createTime" value="${createTime}"
											   class="form-control" placeholder="createTime">
									</div>
									<div class="panel-body">
										<input type="hidden" name="createor" value="${createor}"
											   class="form-control" placeholder="createor">
									</div>
									<div class="panel-body">
									<span><label>专题名称</label></span>
										<input type="text" name="name"required="required" value="${name}"
											class="form-control" placeholder="专题名称">
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6"></div>
											<div class="col-md-6">
												<button class="btn btn-primary btn-block">提交</button>
											</div>
										</div>
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
