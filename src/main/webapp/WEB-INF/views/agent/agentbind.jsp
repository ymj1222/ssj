<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>终端绑定</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		getqueryCode();
	});
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
		<form action="agentbind" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">终端绑定</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<span class="select-box"> <select class="form-control"
											name="terminalid" id="terminalId"></select>
										</span>
									</div>
									<input type="hidden" name="id" value="${id}">
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
