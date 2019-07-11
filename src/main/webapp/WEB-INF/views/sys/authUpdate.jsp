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
	$(function() {
		get(); 
	});
	
	function get(){
		$.ajax({
			type : "POST",
			url : "/ssj/authGet",
			dataType : 'JSON',
			async : false,
			data : {
				 code : $("#code").val()
			},
			success : function(datas) {
				$("#name").val(datas.name);
				$("#code").val(datas.code);
				$("#url").val(datas.url);
				if(datas.isBase=="0"){
					$("input[type=radio][name=isBase][value=0]").attr("checked",'checked');
					}
					if(datas.isBase=="1"){
						$("input[type=radio][name=isBase][value=1]").attr("checked",'checked');
						}
				if(datas.menuType=="0"){
					$("input[type=radio][name=menuType][value=0]").attr("checked",'checked');
					}
					if(datas.menuType=="1"){
						$("input[type=radio][name=menuType][value=1]").attr("checked",'checked');
						}
					if(null==datas.descri||""==datas.descri){
						$("#descri").val("无");
					}else{
				$("#descri").val(datas.descri);
					}
				fuck();
				$("#pCode").val(datas.pCode);
			}
		});
	}
	
function fuck(){
		$.ajax({
			type : "POST",
			url : "/ssj/authQuery",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				$("#pCode").html(cont)
				var cont = '';
				cont =cont+ "<option name='pCode' value=''>无</option>";
				$.each(datas,function(i,obj){
					cont =cont+ "<option name='pCode' value='"+obj.code+"'>"+obj.name+"</option>";
				});
				$("#pCode").html(cont)
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
		<form action="authUpdate" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">编辑权限信息</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="hidden" id="code" name="code" value="${code}" />
										名称: 
										<input type="text" id="name" name="name"required="required"/><br />
										url: 
										<input type="text" id="url" name="url"required="required"/><br />
										是否为基础权限: <br />
										是<input type="radio" id="isBase" name="isBase" value="0"required="required"/><br />
										否<input type="radio" id="isBase1" name="isBase" value="1"required="required"/><br />
										 是否菜单: <br />
										是<input type="radio" id="menuType" name="menuType" value="0"required="required"/><br />
										否<input type="radio" id="menuType1" name="menuType"value="1"required="required"/><br />
										描述: <br />
										<input type="text" id="descri" name="descri"required="required"/><br />
										<select class="form-control" id="pCode" name="pCode">
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
