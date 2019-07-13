<%@ page language="java" contentType="text/html; charset=utf-8"
		 isErrorPage="true"%>
<!doctype html>
<html>
<head>
	<title>文章举报</title>
	<meta charset="utf-8">
	<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var code = getUrlParam("code");
		get(code);
	});
	function get(code){
		$.ajax({
			type : "POST",
			url : "../ssj/articleReport",
			dataType : 'JSON',
			async : false,
			data : {
				code : code
			},
			success : function(datas) {
				$("#code").text(datas.code);
				$("#name").text(datas.name);
				$("#content").text(datas.content);
			}
		});
	}
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		if (r != null) return unescape(r[2]); return null; //返回参数值
	}
</script>
</head>
<body>
<div id="wrapper">
	<!-- 头 -->
	<%@ include file="../../../header.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../mune.jsp"%>
	<form action="articleReportAdd" method="post">
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">举报</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-body">
									<input type="hidden" name="articleCode" value="${code}"
										   class="form-control" placeholder="文章编号">
								</div>
								<div class="panel-body">
									<input type="text" name="articleName" value="${name}"
										   class="form-control" placeholder="文章">
								</div>
								<div class="panel-body">
									<input type="text" name="contents" value="${content}"
										   class="form-control" placeholder="文章内容">
								</div>
								<div class="panel-body">
									<select name="type">
										<option>请选择举报类型</option>
										<option value="8001">色情/暴力</option>
										<option value="8002">内容不符</option>
										<option value="8003">虚假宣传</option>
										<option value="8004">不符合社会主义核心价值观</option>
									</select>
								</div>
								<div class="panel-body">
										<textarea name="context" class="form-control"
												  placeholder="举报内容">
											</textarea>
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
