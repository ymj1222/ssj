<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>编辑</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="../js/jquery-2.2.4.js"></script>
	<script>
	$(function getPCode(){
		$.ajax({
			type : "POST",
			url : "/ssj/orgQuery",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				$("#pCode").html(cont)
				var cont = '';
				cont =cont+ "<option name='pCode' value=''>无</option>";
				$.each(datas,function(i,obj){
					cont =cont+ "<option name='pCode'value='"+obj.code+"'>"+obj.name+"</option>";
				});
				$("#pCode").html(cont)
			}
		});
	});
	
	$(function getStaff(){
		$.ajax({
			type : "POST",
			url : "/ssj/staffQuery",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				$("#managerCode").html(cont)
				var cont = '';
				cont =cont+ "<option name='managerCode'value=''>无</option>";
				$.each(datas,function(i,obj){
					cont =cont+ "<option name='managerCode' value='"+obj.code+"'>"+obj.name+"</option>";
				});
				$("#managerCode").html(cont)
			}
		});
	});

		</script>	
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="orgAdd" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title"></h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										名称: <br/>
										<input type="text" id="name" name="name" required="required"/><br/>
										父级部门:
										<select class="form-control" id="pCode" name="pCode">
									</select>
									部门经理:
										<select class="form-control" id="managerCode" name="managerCode">
									</select>
									</div>
									<div class="panel-body">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

</html>
