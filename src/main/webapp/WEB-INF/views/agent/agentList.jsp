<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>
<head>
<title>代理列表</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		changPageSize();
	});
	function list(pageNow, pageSize) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/agentselect",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {
				pageNow : pageNow,
				pageSize : pageSize,
				name : $('#nameId').val(),
			},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$.each(datas.list, function(i, obj) {
					cont = cont + '<tr>';
					cont = cont + '<td>' + obj.name + '</td>';
					cont = cont + '<td>' + obj.code + '</td>';
					cont = cont + '<td>' + obj.phone + '</td>';
					cont = cont + '<td>' + obj.addr + '</td>';
					if (obj.terminalCode == null) {
						cont = cont + '<td>未绑定</td>';
					} else {
						cont = cont + '<td>' + obj.terminalCode + '</td>';
					}
					cont += '<td><a href=javascript:del("' + obj.id
							+ '"); title="删除">删除</a>  ';
					cont += '<a href="agentUpdate?id=' + obj.id + '">修改</a> ';
					if (obj.terminalCode != null) {
						cont += '  <a href=javascript:unbind("' + obj.id
								+ '")>解绑终端</a>';
					}
					if (obj.terminalCode == null) {
						cont += '  <a href="findbind?id=' + obj.id
								+ '">绑定终端</a>';
					}
					cont += '</td>';
					cont = cont + '</tr>';
				});
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont)
				$("#number").html(datas.number)
			}
		});
	}
	function del(code) {
		if (confirm("确定要删除吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/agentDelete",
				async : false,
				data : {
					id : code
				},
				success : function(data) {
					var pageNow = $("#pageNowSpanId").text();
					var pageSize = $("#pageSizeId").val();
					list(pageNow, pageSize);
				}
			});

		} else {
			var pageNow = $("#pageNowSpanId").text();
			var pageSize = $("#pageSizeId").val();
			list(pageNow, pageSize);
		}
	}

	function unbind(code) {
		if (confirm("确定要解除绑定吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/agentunbind",
				async : false,
				data : {
					id : code
				},
				success : function(data) {
					var pageNow = $("#pageNowSpanId").text();
					var pageSize = $("#pageSizeId").val();
					list(pageNow, pageSize);
				}
			});
		} else {
			var pageNow = $("#pageNowSpanId").text();
			var pageSize = $("#pageSizeId").val();
			list(pageNow, pageSize);
		}
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
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
									<div class="input-group">
										<input type="text" name="name" id="nameId"
											class="form-control" placeholder="Search dashboard...">
										<span class="input-group-btn"><button
												onclick="search();" type="submit" class="btn btn-primary">Go</button></span>
									</div>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th width="50">名称</th>
												<th width="30">编号</th>
												<th width="80">电话</th>
												<th width="80">地址</th>
												<th width="40">终端</th>
												<th width="80">操作</th>
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