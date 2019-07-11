<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>添加订单</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		list();
	});
	function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/getProduct",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				var cont = '';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.name+','+obj.code+'">' + obj.name
							+ '</option>';
				});
				$("#productName").html(cont)
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
		<form action="ordersAdd" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">添加订单</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<select id="productName" name="productName"
											class="form-control">
										</select>
									</div>
									<div class="panel-body">
										<input type="text" name="sellValue" required="required" class="form-control"
											placeholder="商品单价">
									</div>
									<div class="panel-body">
										<input type="text" name="amount"required="required" class="form-control"
											placeholder="购买数量">
									</div>
									<div class="panel-body">
										<input type="text" name="receivingAddress"required="required"
											class="form-control" placeholder="收货地址">
									</div>
									<div class="panel-body">
										<input type="text" name="phone"required="required" class="form-control"
											placeholder="收货人电话">
									</div>
									<div class="panel-body">
										<input type="text" name="consignee" required="required"class="form-control"
											placeholder="收货人名称">
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
