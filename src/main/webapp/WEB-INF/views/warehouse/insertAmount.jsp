<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">

<head>
<title>商品庫存添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script>
	
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
					<h3 class="page-title">商品庫存添加</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">數量資料</h3>
								</div>
								<div class="panel-body">
									<form action="/ssj/addamount" method="post">
										<input type="hidden" name="id" value="${id}"> <input
											type="text" class="form-control" placeholder="數量"
											name="amount" required="required" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"> <br>
										<button type="submit" class="btn btn-default"
											onmouseover="check();">提交</button>
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
