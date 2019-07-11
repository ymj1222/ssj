<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>代理添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		getqueryCode();
	});

	function checkPhone(value) {
		const rule = /^1[3|4|5|6|7|8|9][0-9]\d{4,11}$/;
		if (rule.test(value)) {
		} else {
			alert("手机号输入错误");
		}
	}
	function getqueryCode() {
		$.ajax({
			type : "POST",
			url : "/ssj/queryTerminalCode",
			dataType : 'JSON',
			async : false,
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				var fcont = '';
				$('#terminalId').html(fcont);
				fcont = fcont + '<option value="">请选择终端</option>';
				$.each(datas.list, function(i, obj) {
					fcont = fcont + '<option value="'+obj.id+'">' + obj.name
							+ '</option>';
				});
				$('#terminalId').html(fcont);
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
		<form action="agentAdd" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">新增代理</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="text" name="name" class="form-control"
											placeholder="名称" required="required">
									</div>
									<div class="panel-body">
										<input type="text" name="phone" class="form-control"
											maxlength="11"
											onkeyup='this.value=this.value.replace(/[^1-9]*$/,"")'
											onblur='checkPhone(this.value)' placeholder="只能输入11位的纯数字的手机号"
											required="required" />
									</div>
									<div class="panel-body">
										<input type="text" name="addr" class="form-control"
											placeholder="地址" required="required">
									</div>
									<div class="panel-body">
										<span class="select-box"> <select class="form-control"
											name="terminalId" id="terminalId"></select>
										</span>
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
