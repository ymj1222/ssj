<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">

<head>
<title>商品類型添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script>
	function check() {
		var options = $("#level").val(); //获取选中的项
		if (options == "0") {
			alert("請選擇等級");
		}
		return;
	}
</script>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">新增商品類型</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">類別資料</h3>
								</div>
								<div class="panel-body">
									<form action="/ssj/ProductTypeInsert" method="post">
										<input type="text" class="form-control"
											placeholder="類別(如手機,食物等)" name="type" required="required">
										<br> <select class="form-control" name="level" id="level">
											<option value="0">請選擇等級</option>
											<option value="1">等級一</option>
											<option value="2">等級二</option>
										</select> <br>
										<button type="submit" class="btn btn-default" onmouseover="check();">提交</button>
									</form>
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
