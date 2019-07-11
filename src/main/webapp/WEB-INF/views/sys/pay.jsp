<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">
<head>
<title>Tables</title>
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.4.js"></script>
<script>
	$(function() {
		list();
	});
	function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$
				.ajax({
					type : "POST",//请求方式为post
					url : "/ssj/payList",//请求url
					dataType : 'JSON',//数据格式为json
					async : false,//是否同步，false表示为异步 
					data : {
						
					},
					success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
						$("#tbodyId").html(cont)
						var cont = '';
						$
								.each(
										datas,
										function(i, obj) {
											cont = cont + '<tr>';
											cont = cont + '<td><input onclick="update(name'+i+');"type="radio" id="name'+i+'" name="name" value="'+obj.name+","+obj.code+'"/>'+obj.name+'</td>';
											if(i==0){
												$("#sin").val(obj.name+","+obj.code);
											}
											cont = cont + '</tr>';
										});
						$("#tbodyId").html(cont)
						$("input[type=radio][name=name][value='"+$("#sin").val()+"']").attr("checked",'checked');
					}
				});
	}
	function update(id){ //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type:"POST",//请求方式为post
			url:"/ssj/payUpdate",//请求url
			dataType:'JSON',//数据格式为json
			async:false,//是否同步，false表示为异步 
			data : {
				 name: $(id).val(),
			},
			success : function(datas){ //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				list();
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
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
									<form class="navbar-form navbar-left">
									<input type="hidden" name="sin" id="sin">
										<div class="input-group">
										</div>
									</form>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th width="80">支付方式</th>
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