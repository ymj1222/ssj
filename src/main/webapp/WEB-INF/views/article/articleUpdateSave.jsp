<%@ page language="java" contentType="text/html; charset=utf-8"
		 isErrorPage="true"%>
<!doctype html>
<html>

<head>
	<title>文章修改</title>
	<meta charset="utf-8">
	<%@ include file="../../../common.jsp"%>
</head>
<body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<div id="wrapper">
	<!-- 头 -->
	<%@ include file="../../../header.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../mune.jsp"%>
	<form action="articleUpdateSave" method="post">

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">修改文章</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-body">
									<input type="hidden" name="code" value="${code}"
										   class="form-control" placeholder="编号">
									<input type="hidden" name="id" value="${id}"
										   class="form-control" placeholder="id">
									<input type="hidden" name="issue" value="${issue}"
										   class="form-control" placeholder="issue">
									<input type="hidden" name="createor" value="${createor}"
										   class="form-control" placeholder="createor">
									<input type="hidden" name="createTime" value="${createTime}"
										   class="form-control" placeholder="createTime">
									<input type="hidden" name="special" value="${special}"
										   class="form-control" placeholder="special">
									<input type="hidden" name="specialCode" value="${specialCode}"
										   class="form-control" placeholder="specialCode">
									<input type="hidden" name="specialName" value="${specialName}"
										   class="form-control" placeholder="specialName">
									<input type="hidden" name="releaseTime" value="${releaseTime}"
										   class="form-control" placeholder="releaseTime">
								</div>
								<div class="panel-body">
									<span><label>文章名称</label></span>
									<input type="text" name="name" value="${name}"
										   class="form-control" placeholder="文章名">
								</div>
								<div class="panel-body">
									<span><label>文章内容</label></span>
									<input type="text" name="content" value="${content}"
										   class="form-control" placeholder="内容">
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
