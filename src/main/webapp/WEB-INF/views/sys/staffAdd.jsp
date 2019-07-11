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
		getOrgCode(); 
	});
	
	function getOrgCode(){
		$.ajax({
			type : "POST",
			url : "/sj/orgQuery",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				$("#orgCode").html(cont)
				var cont = '';
				cont =cont+ "<option name='orgCode' value='无'>无</option>";
				$.each(datas,function(i,obj){
					cont =cont+ "<option name='orgCode' value='"+obj.code+"'>"+obj.name+"</option>";
				});
				$("#orgCode").html(cont)
			}
		});
	}
	 function checkPhone(){ 
	        var tel = document.getElementById('tel').value;
	        if(!(/^1[34578]\d{9}$/.test(tel))){ 
	        	$("#tel").val("");
	            alert("手机号码有误，请重填");  
	            return false; 
	        } 
	    }
		</script>	
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="staffAdd" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">编辑成员信息</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										姓名: 
										<input type="text" id="name"name="name" required="required" />
									</div>
									<div class="panel-body">
										账号: 
										<input type="text" id="account" name="account" required="required"/>
									</div>
									<div class="panel-body">
										生日: 
										<input type="text" onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})"id="birthday" name="birthday" required="required" />
									</div>
									<div class="panel-body">
									电话: <br />
									<input type="text" id="tel" maxlength="11" onchange = "checkPhone();"name="tel"required="required" /><br />
									</div>
									任职状态:
									<label class="fancy-checkbox">
										<input type="radio" id="state1" name="state" required="required"value="0">
										<span>试用期</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio" id="state2" name="state"required="required" value="1">
										<span>在职</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio"id="state3" name="state" required="required"value="2">
										<span>离职</span>
									</label>
									性别:
									<label class="fancy-checkbox">
										<input type="radio" id="sex1" name="sex" required="required"value="1">
										<span>男</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio"id="sex2" name="sex"required="required" value="0">
										<span>女</span>
									</label>
									所在部门
									<select class="form-control" id="orgCode" name="orgCode">
									</select>
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
