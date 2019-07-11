<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>我的订单</title>
<%@ include file="../../../qcommon.jsp"%>
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
			url : "../ssj/ordersSelect",//请求url
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
					cont = cont + '<td>' + obj.productName + '</td>';
					cont = cont + '<td>' + obj.price + '</td>';
					cont = cont + '<td>' + obj.amount + '</td>';
					cont = cont + '<td>' + obj.receivingAddress + '</td>';
					cont = cont + '<td>' + obj.phone + '</td>';
					cont = cont + '<td>' + obj.consignee + '</td>';
					cont = cont + '<td>' + obj.receivingTime + '</td>';
					if(obj.isconfirmreceipt=="0"&&obj.orderState=="1"&&obj.isOutOfStock=="1"){
					cont+= '<td><a href="myordersUpdateIs?code='+ obj.code+ '">确认收货</a></td>';
					cont+='<td><a href=javascript:del("'+ obj.code+'","'+obj.amount+'"); title="取消订单">取消订单</a></td>';
					cont+= '<td><a href="myOrdersUpdate?code='+ obj.code+ '">修改订单</a></td>';
					cont=cont+'<td>'+"签收后可评价"+'</td>'
					cont=cont+'<td><a href="toLogisticsAddr?logisticsNumber='+ obj.logisticsNumber+ '">物流跟踪</a></td>'
					}
					else if(obj.isconfirmreceipt=="0"&&obj.orderState=="0"){
						cont=cont+'<td>'+"订单已取消"+'</td>';	
						cont=cont+'<td>'+"订单已取消"+'</td>';
						cont=cont+'<td>'+"订单已取消"+'</td>';
						cont=cont+'<td>'+"签收后可评价"+'</td>';
						cont=cont+'<td>'+"订单已取消"+'</td>';
					}else if(obj.isconfirmreceipt=="1"||obj.orderState=="0"){
						cont=cont+'<td>'+"已签收"+'</td>';
						cont=cont+'<td>'+"已签收"+'</td>';
						cont=cont+'<td>'+"已签收"+'</td>';
						cont=cont+'<td><a href="ordersOutPj?productCode='+ obj.productCode+ '">评价</a></td>'
						cont=cont+'<td><a href="toLogisticsAddr?logisticsNumber='+ obj.logisticsNumber+ '">物流跟踪</a></td>'
					}else if(obj.isOutOfStock=="0"){
						cont=cont+'<td>'+"订单尚未发货"+'</td>';
						cont+='<td><a href=javascript:delc("'+ obj.code+'","'+obj.amount+'"); title="取消订单">取消订单</a></td>';
						cont+= '<td><a href="myOrdersUpdate?code='+ obj.code+ '">修改订单</a></td>';
						cont=cont+'<td>'+"签收后可评价"+'</td>';
						cont=cont+'<td>'+"订单尚未发货"+'</td>';
					}
					cont = cont + '</tr>';
				});
				page(Number(datas.pageNow), Number(datas.pageCount));
				$("#tbodyId").html(cont);
				$("#number").html(datas.number)
			}
		});
	}
	function del(code,amount) {
		if (confirm("确定要取消订单吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/ordersCanel",
				async : false,
				data : {
					code : code,
					amount:amount
				},
				success : function(data) {
					changPageSize();
				}
			});

		} else {
			changPageSize();
		}
	}
	function delc(code,amount) {
		if (confirm("确定要取消订单吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/Canel",
				async : false,
				data : {
					code : code,
					amount:amount
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
<!-- start header -->


<!-- start main -->
<div id="wrapper">
		<%@ include file="../../../qheader.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../qmune.jsp"%>
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
								<h3 class="page-title">我的订单</h3>
									<form class="navbar-form navbar-left">
									</form>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th width="30">商品名称</th>
												<th width="30">商品单价</th>
												<th width="30">商品数量</th>
												<th width="80">收货地址</th>
												<th width="80">收货人电话</th>
												<th width="30">收货人</th>
												<th width="80">收货时间</th>
												<th width="80">是否签收</th>
												<th width="80">取消订单</th>
												<th width="80">修改订单</th>
												<th width="30">评价</th>
												<th width="40">物流跟踪</th>
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
<!-- 以下函数代码用于右下角箭头上滑 -->
<%@ include file="../../../qtop.jsp"%>
</body>
</html>