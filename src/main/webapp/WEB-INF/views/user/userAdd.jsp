<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>user添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="addUser" method="POST" enctype="multipart/form-data">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">新增图片</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										输入图片分类相册名<input type="text" maxlength="10" required="required" name="name"  placeholder="名字">
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

</html>
