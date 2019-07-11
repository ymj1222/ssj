<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>消息推送</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="informationPushAdd" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">消息推送添加</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="text" name="phone" path="phone"
											class="form-control" maxlength="11"
											onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")'
											onblur='checkPhone(this.value)' placeholder="只能输入11位的纯数字的手机号"
											required="required" />
									</div>
									<div class="panel-body">
										<input type="text" name="datailed" class="form-control"
											placeholder="内容" required="required">
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
