<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>
<head>
<title>商品查看</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		changPageSize();
		type();
		brand();
		status();
	});
	function list(pageNow, pageSize) { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/both",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {
				name : $('#nameid').val(),
				code : $('#codeid').val(),
				type : $('#type').val(),
				auditStatus : $('#auditStatus').val(),
				brandCode : $('#brandCode').val(),
				typeName : $('#typeName').val(),
				brandName : $('#brandName').val(),
				isEffective : $('#isEffective').val(),
				pageNow : pageNow,
				pageSize : pageSize,
			},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$.each(datas.list, function(i, obj) {
					cont = cont + '<tr>';
					cont = cont + '<td>' + obj.name + '</td>';
					cont = cont + '<td>' + obj.code + '</td>';
					cont = cont + '<td>' + obj.agentName + '</td>';
					cont = cont + '<td>' + obj.price + '</td>';
					cont = cont + '<td>' + obj.typeName + '</td>';
					cont = cont + '<td>' + obj.brandName + '</td>';
					cont = cont + '<td>' + obj.induction + '</td>';
					cont = cont + '<td>' + obj.size + '</td>';
					cont = cont + '<td>' + obj.color + '</td>';
					cont = cont + '<td>' + obj.sellValue + '</td>';
					cont = cont + '<td>' + obj.marketValue + '</td>';
					cont = cont + '<td>' + obj.amount + '</td>';
					cont = cont + '<td>' + status(obj.auditStatus) + '</td>';
					cont = cont + '</tr>';
				});
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont)
				$("#number").html(datas.number)
			}
		});
	}
	function brand() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/Brand",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#brandCode").html(cont)
				var cont = '<option value="0">請選擇品牌</option>';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.code+'">' + obj.name
							+ '</option>';
				});
				$("#brandCode").html(cont)
			}
		});
	}

	function type() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/ToProductoption",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#type").html(cont)
				var cont = '<option value="0">請選擇類型</option>';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.code+'">' + obj.name
							+ '</option>';
				});
				$("#type").html(cont)
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
	function status(status) {
		if (status == 1) {
			return '待審批';
		}
		if (status == 2) {
			return '審批不通過';
		}
		if (status == 3) {
			return '審批已通過';
		}
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
					<h3 class="page-title">商品查看</h3>
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
									<form class="navbar-form navbar-left">
										<div class="input-group">
											<input type="text" name="code" id="codeid"
												class="form-control" placeholder="商品編號搜索" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"> <input
												type="text" name="name" id="nameid" class="form-control"
												placeholder="名稱搜索"> <span class="input-group-btn">
												<select id="type" name="type" class="form-control"></select>
												<select id="auditStatus" name="auditStatus"
												class="form-control">
													<option value="">請選擇狀態</option>
													<option value="1">待審批</option>
													<option value="2">不通過</option>
													<option value="3">已通過</option>
											</select><select id="brandCode" name="brandCode" class="form-control"></select>
												<select id="isEffective" name="isEffective"
												class="form-control">
													<option value="">請選擇上下架</option>
													<option value="0">已下架/未上架</option>
													<option value="1">己上架</option>
											</select> <label class="fancy-checkbox"> </label>
												<button type="button" class="btn btn-primary"
													onclick="search();" type="submit">Go</button>
											</span>
										</div>
									</form>
									<table class="table table-bordered">

										<thead>
											<tr>
												<th width="50">名称</th>
												<th width="30">编号</th>
												<th width="80">代理人</th>
												<th width="80">總價</th>
												<th width="80">類型</th>
												<th width="80">品牌</th>
												<th width="80">介紹</th>
												<th width="80">大小/尺寸</th>
												<th width="80">顏色</th>
												<th width="80">銷售價</th>
												<th width="80">市場價</th>
												<th width="80">數量</th>
												<th width="80">審批狀態</th>
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
