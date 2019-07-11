<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">
<head>
	<title>物流详情</title>
	<%@ include file="../../../qcommon.jsp"%>
<%@ include file="../../../common.jsp"%>
</head>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="../js/jquery-2.2.4.js"></script>
	<script>
	$(function() {
		list(); 
	});
	var ref = "";
	ref = setInterval(function(){
	    test();
	},2000);
	function test(){
		$.ajax({
			type:"POST",//请求方式为post
			url:"/ssj/logisticsAddrUpdate",//请求url
			dataType:'JSON',//数据格式为json
			async:false,//是否同步，false表示为异步 
			data : {
				wayBillNo:$("#wayBillNo").val()
			},
			success : function(datas){
				$.each(datas,function(i,obj){
					if(obj !=null&&obj!=""){
						update();
					}
				});
			}
		});
		}
	function list(){ //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type:"POST",//请求方式为post
			url:"/ssj/logisticsAddrList",//请求url
			dataType:'JSON',//数据格式为json
			async:false,//是否同步，false表示为异步 
			data : {
				wayBillNo:$("#wayBillNo").val()
			},
			success : function(datas){ //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#tbodyId").html(cont)
				var cont = '';
				$.each(datas,function(i,obj){
					cont = cont +'<tr>';
					cont = cont +'<td>'+obj.name+'</td>';
					cont = cont +'<td>'+obj.time+'</td>';
					cont = cont +'<td>'+obj.distance+'</td>';
					cont = cont +'<td>'+obj.howLong+'天</td>';
					cont = cont +'</tr>';
				});
				$("#tbodyId").html(cont)
			}
		});
	}
		</script>	
<body>
	<div id="wrapper">
	<%@ include file="../../../qheader.jsp"%>
	<!-- 菜单 -->
	<%@ include file="../../../qmune.jsp"%>
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
								<h4 class="page-title">物流信息</h4>
									<form class="navbar-form navbar-left">
									<input type="hidden" id="wayBillNo"value="${wayBillNo}">
										<div class="input-group">
										</div>
									</form>
									<table class="table table-bordered" >
										<thead>
												<tr >
					<th width="80">当前位置</th>
					<th width="80">到达时间</th>
					<th width="80">剩余路线点</th>
					<th width="80">剩余时间</th>
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