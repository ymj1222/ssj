<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">

<head>
<title>商品類型添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
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
									<form action="/ssj/ProductBrandInsert" method="post">
										<input type="text" class="form-control" placeholder="品牌名稱"
											name="brand" required="required"> <br>
										<button type="submit" class="btn btn-default">提交</button>
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
