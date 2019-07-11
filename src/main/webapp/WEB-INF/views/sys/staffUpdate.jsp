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
			url : "/ssj/staffGet",
			dataType : 'JSON',
			async : false,
			data : {
				code : $("#code").val()
			},
			success : function(datas) {
				$("#name").val(datas.name);
				if(datas.state=="0"){
					$("input[type=radio][name=state][value=0]").attr("checked",'checked');
					}
					if(datas.state=="1"){
						$("input[type=radio][name=state][value=1]").attr("checked",'checked');
						}
					if(datas.state=="2"){
						$("input[type=radio][name=state][value=2]").attr("checked",'checked');
						}
				$("#birthday").val(datas.birthday);
				$("#tel").val(datas.tel);
				getOrgCode();
				 $("#orgCode").val(datas.orgCode);
			}
		});
	}
	function getOrgCode(){
		$.ajax({
			type : "POST",
			url : "/ssj/orgQuery",
			dataType : 'JSON',
			async : false,
			data : {
			},
			success : function(datas) {
				$("#orgCode").html(cont)
				var cont = '';
				cont =cont+ "<option value='无'>无</option>";
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
		<form action="staffUpdate" method="POST">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">编辑成员信息</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="hidden" id="code" name="code" value="${code}"required="required" />
									</div>
									<div class="panel-body">
										姓名: 
										<input type="text" id="name" name="name"required="required" />
									</div>
									<div class="panel-body">
										生日: 
										<input type="text" onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})"id="birthday" name="birthday" required="required" />
									</div>
									<div class="panel-body">
									电话: <br />
									<input type="text" id="tel" name="tel" maxlength="11" onchange = "checkPhone();"required="required"/><br />
									</div>
									任职状态
									<label class="fancy-checkbox">
										<input type="radio" id="state1" required="required"name="state" value="0">
										<span>试用期</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio" id="state2"required="required" name="state" value="1">
										<span>正式员工</span>
									</label>
									<label class="fancy-checkbox">
										<input type="radio"id="state3" required="required" name="state" value="2">
										<span>离职</span>
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
