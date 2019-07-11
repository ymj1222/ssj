<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>

<head>
<title>终端信息修改</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form:form action="terminalUpdateSave" method="post"
			modelAttribute="ter">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">修改终端</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<form:input type="hidden" name="id" path="id"
										class="form-control" placeholder="id" />
									<form:input type="hidden" name="code" path="code"
										class="form-control" placeholder="code" />
									<form:input type="hidden" name="creator" path="creator"
										class="form-control" placeholder="creator" />
									<form:input type="hidden" name="createTime" path="createTime"
										class="form-control" placeholder="createTime" />
									<div class="panel-body">
										名称:
										<form:input type="text" name="name" path="name"
											class="form-control" placeholder="名称" required="required" />
									</div>
									<div class="panel-body">
										电话:
										<form:input type="text" name="phone" path="phone"
											class="form-control" maxlength="11"
											onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")'
											onblur='checkPhone(this.value)' placeholder="只能输入11位的纯数字的手机号"
											required="required" />
									</div>
									<div class="panel-body">
										地址:
										<form:input type="text" name="addr" path="addr"
											class="form-control" placeholder="地址" required="required" />
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
		</form:form>
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
