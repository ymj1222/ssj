<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>
<head>
<title>订单列表</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		list();
	});
	function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "/ssj/getOrders",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {
				usersCode:$("#usersCode").val()
			},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$.each(datas, function(i, obj) {
					cont = cont + '<tr>';
					cont = cont + '<td>' + obj.productCode + '</td>';
					cont = cont + '<td>' + obj.code + '</td>';
					cont = cont + '<td>' + obj.productName + '</td>';
					cont = cont + '<td>' + obj.price + '</td>';
					cont = cont + '<td>' + obj.amount + '</td>';
					cont = cont + '<td>' + obj.receivingAddress + '</td>';
					cont = cont + '<td>' + obj.phone + '</td>';
					cont = cont + '<td>' + obj.consignee + '</td>';
					cont = cont + '<td>' + obj.deliveryTime + '</td>';
					cont = cont + '<td>' + obj.receivingTime + '</td>';
					if(obj.isconfirmreceipt=="0"&&obj.orderState=="1"&&obj.isOutOfStock=="1"){
					cont+= '<td><a href="ordersUpdateIs?code='+ obj.code+ '">确认收货</a></td>';
					cont+='<td><a href=javascript:del("'+ obj.code+ '"); title="取消订单">取消订单</a></td>'
					cont+= '<td><a href="finOrdersUpdate?code='+ obj.code+ '">修改订单</a></td>';
					}else if(obj.isconfirmreceipt=="0"&&obj.orderState=="0"){
						cont=cont+'<td>'+"订单已取消"+'</td>'
						cont=cont+'<td>'+"订单已取消"+'</td>'
						cont=cont+'<td>'+"订单已取消"+'</td>'
					}else if(obj.isconfirmreceipt=="1"||obj.orderState=="0"){
						cont=cont+'<td>'+"已签收"+'</td>'
						cont=cont+'<td>'+"已签收"+'</td>'
						cont=cont+'<td>'+"已签收"+'</td>'
					}else if(obj.isOutOfStock=="0"){
						cont=cont+'<td>'+"订单尚未发货"+'</td>'
						cont+='<td><a href=javascript:del("'+ obj.code+ '"); title="取消订单">取消订单</a></td>'
						cont+= '<td><a href="ordersUpdate?code='+ obj.code+ '">修改订单</a></td>';
					}
					cont = cont + '</tr>';
				});
				$("#tbodyId").html(cont)
			}
		});
	}
	function del(code) {
		if (confirm("确定要取消订单吗？")) {
			$.ajax({
				type : "POST",
				url : "../ssj/ordersCanel",
				async : false,
				data : {
					code : code
				},
				success : function(data) {
					changPageSize();
				}
			});

		} else {
			list();
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
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
								<div class="navbar-form navbar-left">
								<input type="hidden" id="usersCode" value="${usersCode}">
									</div>
									<form class="navbar-form navbar-left">
										<div class="input-group">
										</div>
									</form>
									<table class="table table-bordered">

										<thead>
											<tr>
												<th width="80">商品编号</th>
												<th width="80">订单编号</th>
												<th width="30">商品名称</th>
												<th width="30">商品单价</th>
												<th width="30">商品数量</th>
												<th width="80">收货地址</th>
												<th width="80">收货人电话</th>
												<th width="30">收货人</th>
												<th width="80">到货时间</th>
												<th width="80">收货时间</th>
												<th width="50">确认收货</th>
												<th width="50">取消订单</th>
												<th width="50">修改订单</th>
											</tr>
										</thead>
										<tbody id="tbodyId">
										</tbody>
									</table>
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
