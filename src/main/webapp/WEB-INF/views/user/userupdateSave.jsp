<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>

<head>
<title>信息修改</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="userUpdateSave" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">修改</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="hidden" name="id" value="${user.id}"	>
										<input type="text" name="name" value="${user.name}"
											class="form-control">
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
