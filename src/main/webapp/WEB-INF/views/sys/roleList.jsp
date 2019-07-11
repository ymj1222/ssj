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
				url:"/ssj/roleList",//请求url
				dataType:'JSON',//数据格式为json
				async:false,//是否同步，false表示为异步 
				data : {
					 pageNow : pageNow,
					 pageSize: pageSize,
					 sname:$('#snameId').val(),
				},
				success : function(datas){ //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
					$("#tbodyId").html(cont)
					var cont = '';
					$.each(datas.list,function(i,obj){
						cont = cont +'<tr>';
						cont = cont +'<td>'+obj.name+'</td>';
						if(obj.state=="0"){
						cont = cont +'<td>有效</td>';
						}
						 if(obj.state=="1"){
							cont = cont +'<td>失效</td>';
							}
						
						if(obj.state=="0"){
						cont+='<td><a href=javascript:del("'+obj.code+'",1); title="删除数据">删除</a>&nbsp;&nbsp;<a href="toRoleUpdate?code='+obj.code+'" title="修改数据">修改</a></td>';
						}
						 if(obj.state=="1"){
						cont+='<td><a href=javascript:del("'+obj.code+'",0); title="删除数据">有效</a>&nbsp;&nbsp;<a href="toRoleUpdate?code='+obj.code+'" title="修改数据">修改</a></td>';
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
			function del(code,state){
				if(confirm("确定要进行操作吗？")) {
					$.ajax({
						type : "POST",
						url : "/ssj/roleDelete",
						async : false,
						data : {
							code : code,
							state:state
						},
						success : function(data) {					
							var pageNow=$("#pageNowSpanId").text();
							var pageSize=$("#pageSizeId").val();
							list(pageNow,pageSize);
						}
					});
			        
			    } else {
			    	var pageNow=$("#pageNowSpanId").text();
					var pageSize=$("#pageSizeId").val();
					list(pageNow,pageSize);
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
									<i class="lnr lnr-pencil" class="btn btn-primary"><a href="toRoleAdd">添加角色</a></i>
										<div class="input-group">
											<input type="text"  name="sname" id="snameId" class="form-control" placeholder="Search dashboard...">
											<span
												class="input-group-btn"><button onclick="search();" type="submit"
													class="btn btn-primary">搜索</button></span>
										</div>
									<table class="table table-bordered" >
										<thead>
											<tr>
												<th>名称</th>
												<th>状态</th>
												<th>操作</th>
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