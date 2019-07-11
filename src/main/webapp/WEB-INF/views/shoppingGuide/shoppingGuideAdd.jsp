<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>导购员添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>

<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="shoppingGuideAdd" method="post"
			enctype="multipart/form-data">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">新增导购</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										名称:<input type="text" name="name" class="form-control"
											placeholder="名称" required="required">
									</div>
									<div class="panel-body">
										性别:<input type="text" name="sex" class="form-control"
											placeholder="性别" required="required">
									</div>
									<div class="panel-body">
										照骗:<span class="select-box"> <input type="file"
											name="file" />
										</span>
									</div>
									<div class="panel-body">
										微信二维码:<span class="select-box"> <input type="file"
											name="file1" />
										</span>
									</div>
									<div class="panel-body">
										电话: <input type="text" name="phone" class="form-control"
											maxlength="11"
											onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")'
											onblur='checkPhone(this.value)' placeholder="只能输入11位的纯数字的手机号"
											required="required" />
									</div>
									<div class="panel-body">
										爱好:<input type="text" name="hobby" class="form-control"
											placeholder="爱好" required="required">
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
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
<script>
	function checkPhone(value) {
		const rule = /^1[3|4|5|6|7|8|9][0-9]\d{4,11}$/;
		if (rule.test(value)) {
		} else {
			alert("手机号输入错误");
		}
	}
</script>
</html>
