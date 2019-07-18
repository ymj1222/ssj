<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">
<head>
	<title>Tables</title>
	<%@ include file="../../../common.jsp"%>
</head>
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="../js/jquery-2.2.4.js"></script>
	<script>
		$(function() {
			changPageSize(); 
		});
			function list(pageNow,pageSize){ //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
				$.ajax({
					type:"POST",//请求方式为post
					url:"/ssj/usersList",//请求url
					dataType:'JSON',//数据格式为json
					async:false,//是否同步，false表示为异步 
					data : {
						 pageNow : pageNow,
						 pageSize: pageSize,
						 sname:$("#snameId").val()
					},
					success : function(datas){ //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
						$("#tbodyId").html(cont)
						var cont = '';
						$.each(datas.list,function(i,obj){
							cont = cont +'<tr>';
							cont = cont +'<td>'+obj.name+'</td>';
							cont = cont +'<td>'+obj.phone+'</td>';
							cont = cont +'<td>'+obj.wechat+'</td>';
							if(obj.sex==0){
								
							cont = cont +'<td>女</td>';
							}else{
							cont = cont +'<td>男</td>';
								
							}
							cont = cont +'<td>'+obj.city+'</td>';
							cont = cont +'<td>'+obj.age+'</td>';
							cont = cont +'<td>'+obj.account+'</td>';
							cont = cont +'<td>'+obj.levelMark+'</td>';
							cont = cont +'<td>'+obj.goldCoin+'</td>';
							if(obj.state=='1'){
								cont+='<td>有效</td>'
							}else{
								cont+='<td>失效</td>'
							}
							cont = cont +'<td>'+obj.integral+'</td>';
							if(obj.state=="1"){
							cont+='<td><a href="usersDelete?state=0&&code='+obj.code+'" title="拉入黑名单">拉入黑名单</a></td>';	
							}else{
								cont+='<td><a href="usersDelete?state=1&&code='+obj.code+'" title="移出黑名单">移出黑名单</a></td>';
							}
							cont+='<td><a href="toBrowsingHistoryList?code='+obj.code+'" title="浏览历史">浏览历史</a></td>';
							cont+='<td><a href="toOrdersHistoryList?usersCode='+obj.code+'" title="订单历史">订单历史</a></td>';
							cont = cont +'</tr>';							
						});
						page(Number(datas.pageNow),Number(datas.pageCount));
						$("#tbodyId").html(cont)
						$("#number").html(datas.number)
					}
				});
			}
			function page(pageNow,pageCount){
				$("#pageNowSpanId").text(pageNow);
				$("#pageCountSpanId").text(pageCount);
				
				$("#pageCountId").val(pageCount);
				if (pageNow > 1){
					$("#upPageId").val(pageNow-1);
				} else {
					$("#upPageId").val(1);
				}
				if (pageNow == pageCount){
					$("#downPageId").val(pageCount);
				} else {
					$("#downPageId").val(pageNow+1);
				}
			}
			function lastPage(){
				list($("#pageCountId").val(),$("#pageSizeid").val());
			}
			function upPage(){
				list($("#upPageId").val(),$("#pageSizeid").val()); 
			}
			function downPage(){
				list($("#downPageId").val(),$("#pageSizeid").val());
			}
			function changPageSize(){
				var pageSize = $("#pageSizeId").val();
				list(1,pageSize);
			}
			function search(){ 
				changPageSize();
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
								<i class="lnr lnr-pencil" class="btn btn-primary"><a href="toUsersAdd">添加用户</a></i>
										<div class="input-group">
											<input type="text"  name="sname" id="snameId" class="form-control" placeholder="Search dashboard...">
											<span
												class="input-group-btn"><button onclick="search();" type="submit"
													class="btn btn-primary">搜索</button></span>
										</div>
									<table class="table table-bordered" >
										<thead>
											<tr>
												<th>姓名</th>
												<th>电话</th>
												<th>微信</th>
												<th>性别</th>
												<th>城市</th>
												<th>年龄</th>
												<th>账户</th>
												<th>级别分</th>
												<th>眼币</th>
												<th>状态</th>
												<th>积分</th>
												<th>操作</th>
												<th>历史记录</th>
												<th>订单历史</th>
											</tr>
										</thead>
										<tbody id="tbodyId">
											
										</tbody>
									</table>
	当前页面<span id="pageNowSpanId"></span>
	总页面:<span id="pageCountSpanId"></span>
	每页显示:<select id="pageSizeId" onchange="changPageSize();">
	<option value="3">3</option>
	<option value="5">5</option>
	<option value="10">10</option>
	</select>
	<a href="javascript:changPageSize();">首页</a>
	<a href="javascript:upPage();">
	<input type="hidden" id="upPageId"/>
	上一页
	</a>
	<a href="javascript:downPage();">
	<input type="hidden" id="downPageId"/>
	下一页
	</a>
	<a href="javascript:lastPage();">
	<input type="hidden" id="pageCountId"/>
	尾页
	</a> 
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