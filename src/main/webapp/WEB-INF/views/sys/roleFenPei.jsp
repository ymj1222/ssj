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
			url : "/ssj/roleFenPei",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				var tablehead = '<table class="table table-border table-bordered table-bg mt-20" '+
					' style="width: 50%; margin-left: 170px; margin-top: 10px;" > '+
					' <thead> <tr> <th colspan="2" scope="col">勾选权限</th> </tr> </thead> <tbody>';
				var tablefoot = '  </tbody> </table>';
				var chboxtr = '';
				var y = 0;
				$.each(datas,function(i,obj){ 
					y = y + i;
					chboxtr += '<tr> <td>';
					$.each(obj,function(j,au){
						chboxtr += '<input type="checkbox" name="authCode" value="'+au.code+'">'+au.name +'&nbsp;';
					});
					chboxtr += '</td></tr>';
				});
				var cont = '';
				cont = tablehead + chboxtr + tablefoot ;
				$('#tcont').html(cont);
			}
		});
	}
	 function show() {
         obj = document.getElementsByName("authCode");
         console.log(obj);
         check_val = [];
         for(k in obj) {
             if(obj[k].checked)
                 check_val.push(obj[k].value);
         }
         $("#code").val(check_val);
     }
		</script>	
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="roleAuthAdd" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">编辑角色信息</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input id="code" type="hidden" name="code">
			<input id="roleCode" type="hidden" name="roleCode" value="${roleCode}">
			<span id="tcont"></span>    
									</div>
									<div class="panel-body" >
										<button type="submit" onclick="show()" class="btn btn-primary">Submit</button>
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
