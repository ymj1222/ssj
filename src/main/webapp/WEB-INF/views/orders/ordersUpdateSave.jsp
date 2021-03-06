<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>

<head>
<title>订单修改</title>
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
		url : "../ssj/ordersUpdateQuery",
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
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="ordersUpdateSave?code=${code}">
		
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">修改订单</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
								<div class="panel-body">
										<input type="hidden" name="code" value="${code}"
											class="form-control" placeholder="编号">
									</div>
									<div class="panel-body">
										<input type="text"required="required" name="amount" value="${amount}"
											class="form-control" placeholder="购买数量">
									</div>
									<div class="panel-body">
										<input type="text" name="receivingAddress" required="required"value="${receivingAddress}"
											class="form-control" placeholder="收货地址">
									</div>
									<div class="panel-body">
										<input type="text" required="required"name="phone" value="${phone}"
											class="form-control" placeholder="收货人电话">
									</div>
									<div class="panel-body">
										<input type="text" name="consignee" value="${consignee}"required="required"
											class="form-control" placeholder="收货人姓名">
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
