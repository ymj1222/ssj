<%@ page language="java" contentType="text/html; charset=utf-8"
		 isErrorPage="true"%>
<!doctype html>
<html>
<head>
	<title>举报列表</title>
	<meta charset="utf-8">
	<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		changPageSize();
	});
	function list(pageNow, pageSize) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/articleReportSelect",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {
				sname : $('#sname').val(),
				pageNow : pageNow,
				pageSize : pageSize,
			},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$.each(datas.list, function(i, obj) {
					cont = cont + '<tr>';
					cont = cont + '<td>' + obj.articleName + '</td>';
					cont = cont + '<td>' + obj.type + '</td>';
					cont = cont + '<td>' + obj.context + '</td>';
					cont = cont + '<td>' + obj.reportingDate + '</td>';
					cont = cont + '</tr>';
				});
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont)
				$("#number").html(datas.number)
			}
		});
	}
	function page(pageNow, pageCount) {
		$("#pageNowSpanId").text(pageNow);
		$("#pageCountSpanId").text(pageCount);

		$("#pageCountId").val(pageCount);
		if (pageNow > 1) {
			$("#upPageId").val(pageNow - 1);
		} else {
			$("#upPageId").val(1);
		}
		if (pageNow == pageCount) {
			$("#downPageId").val(pageCount);
		} else {
			$("#downPageId").val(pageNow + 1);
		}
	}

	function lastPage() {
		list($("#pageCountId").val(), $("#pageSizeid").val());
	}
	function upPage() {
		list($("#upPageId").val(), $("#pageSizeid").val());
	}
	function downPage() {
		list($("#downPageId").val(), $("#pageSizeid").val());
	}
	function changPageSize() {
		var pageSize = $("#pageSizeId").val();
		list(1, pageSize);
	}
	function search() {
		changPageSize();
	}
</script>
<body>
<div id="wrapper">
	<%@ include file="../../../header.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../mune.jsp"%>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<h3 class="page-title">举报</h3>
				<div class="row">
					<div class="col-md-12">
						<!-- BORDERED TABLE -->
						<div class="panel">
							<div class="panel-body">
								<form class="navbar-form navbar-left">
									<div class="input-group">
										<input type="text" name="sname" id="sname"
											   class="form-control" placeholder="文章..."> <span
											class="input-group-btn"><button type="button"
																			class="btn btn-primary" onclick="search();" type="submit">Go</button></span>
									</div>
								</form>
								<table class="table table-bordered">

									<thead>
									<tr>
										<th width="80">文章名</th>
										<th width="80">举报类型</th>
										<th width="80">举报内容</th>
										<th width="30">举报日期</th>
									</tr>
									</thead>
									<tbody id="tbodyId">
									</tbody>
								</table>
								<div>
									当前页面 <span id="pageNowSpanId"></span> 总页面: <span
										id="pageCountSpanId"></span> 每页显示: <select id="pageSizeId"
																				   onchange="changPageSize();">
									<option value="3">3</option>
									<option value="5">5</option>
									<option value="10">10</option>
								</select> <a href="javascript:changPageSize();">首页</a> <a
										href="javascript:upPage();"> <input type="hidden"
																			id="upPageId" /> 上一页
								</a> <a href="javascript:downPage();"> <input type="hidden"
																			  id="downPageId" /> 下一页
								</a> <a href="javascript:lastPage();"> <input type="hidden"
																			  id="pageCountId" /> 尾页
								</a>
								</div>
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
