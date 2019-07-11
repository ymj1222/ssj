<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>
<head>
<title>文章列表</title>
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
			url : "../ssj/articleSelect",//请求url
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
					cont = cont + '<td>' + obj.name + '</td>';
					cont = cont + '<td>' + obj.content + '</td>';
					cont = cont + '<td>' + obj.releaseTime + '</td>';
					cont = cont + '<td>' + obj.specialName + '</td>';
					if(obj.issue==null||obj.issue=="0"){
						cont+= '<td><a href="articleUpdateIs?code='+ obj.code+ '">发布文章</a></td>';
					}else if(obj.issue=="1"){
						cont = cont + '<td>' +"已发布" + '</td>';
					}
					cont+='<td><a href=javascript:del("'+ obj.code+ '"); title="删除文章">删除</a></td>'
					cont+= '<td><a href="articleUpdate?code='+ obj.code+ '">修改文章</a></td>';
					cont = cont + '</tr>';
				});
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont)
				$("#number").html(datas.number)
			}
		});
	}
	function del(code) {
		if (confirm("确定要删除文章吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/articleDelete",
				async : false,
				data : {
					code : code
				},
				success : function(data) {
					changPageSize();
				}
			});

		} else {
			changPageSize();
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
				<h3 class="page-title">文章</h3>
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
								<div class="navbar-form navbar-left">
										<button type="button" class="btn btn-primary"><i class="lnr lnr-pencil"><a href="finArticleAdd">新建文章</a></i></button>
									</div>
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
												<th width="80">内容</th>
												<th width="80">发表时间</th>
												<th width="30">所属专题</th>
												<th width="30">发布文章</th>
												<th width="30">删除文章</th>
												<th width="30">修改文章</th>
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
