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
				url:"/ssj/optionLogList",//请求url
				dataType:'JSON',//数据格式为json
				async:false,//是否同步，false表示为异步 
				data : {
					 pageNow : pageNow,
					 pageSize: pageSize,
				},
				success : function(datas){ //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
					$("#tbodyId").html(cont)
					var cont = '';
					$.each(datas.list,function(i,obj){
						cont = cont +'<tr>';
						cont = cont +'<td>'+obj.operator+'</td>';
						cont = cont +'<td>'+obj.operateTime+'</td>';
						if(obj.operateType=="1"){
							cont = cont +'<td>添加成员</td>';
							}
							 if(obj.operateType=="2"){
								cont = cont +'<td>删除成员</td>';
								}
							
							if(obj.operateType=="3"){
								cont = cont +'<td>修改成员</td>';
							}
							if(obj.operateType=="4"){
							cont = cont +'<td>添加部门</td>';
							}
							 if(obj.operateType=="5"){
								cont = cont +'<td>删除部门</td>';
								}
							
							if(obj.operateType=="6"){
								cont = cont +'<td>修改部门</td>';
							}
							if(obj.operateType=="7"){
							cont = cont +'<td>添加角色</td>';
							}
							 if(obj.operateType=="8"){
								cont = cont +'<td>删除角色</td>';
								}
							
							if(obj.operateType=="9"){
								cont = cont +'<td>修改角色</td>';
							}
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
									<form class="navbar-form navbar-left">
										<div class="input-group">
											<span class="input-group-btn"></span>
										</div>
									</form>
									<table class="table table-bordered" >
										<thead>
											<tr>
												<th>操作者账号</th>
												<th>操作时间</th>
												<th>操作类型</th>
											</tr>
										</thead>
										<tbody id="tbodyId">
											
										</tbody>
									</table>
									<div class="pageTag">
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
	</div>	
</body>

</html>