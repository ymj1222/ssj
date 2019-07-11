<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<title>商品添加</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script>
	$(function() {
		agent();
		list();
		brand();
	});
	function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/ToProductoption",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#type").html(cont)
				var cont = '<option value="0">請選擇類型</option>';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.code+'">' + obj.name
							+ '</option>';
				});
				$("#type").html(cont)
			}
		});
	}
	function brand() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/Brand",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#brand").html(cont)
				var cont = '<option value="0">請選擇品牌</option>';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.code+'">' + obj.name
							+ '</option>';
				});
				$("#brand").html(cont)
			}
		});
	}
	function agent() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "../ssj/queryagentCode22",//请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				var cont = '<option value="0">請選擇代理人</option>';
				$.each(datas, function(i, obj) {
					cont = cont + '<option value="'+obj.id+'">' + obj.name
							+ '</option>';
				});
				$("#agentCode").html(cont)
			}
		});
	}
	function addFile() {
		var fileLength = $("input[name=file]").length + 1;
		var inputFile = "<p id='addFile"+fileLength+"'><input type='file' id='file"
				+ fileLength
				+ "' name='file' onclick='concatMarkVal();'/>"
				+ "<a id='add' href='javascript:void();' rel='external nofollow' onclick='addFile();'>添加</a>&nbsp<a href='javascript:void();' onclick='delFile("
				+ fileLength + ");'>删除</a> </p>";
		$("#add").after(inputFile);
	}
	//删除附件 
	function delFile(id) {
		$("#addFile" + id).remove();
	}
	function check() {
		var options = $("#agentCode").val(); //获取选中的项
		var optionb = $("#type").val(); //获取选中的项
		var optionc = $("#brand").val(); //获取选中的项
		if (options == "0") {
			alert("請選擇代理人");
		}
		if (optionb == "0") {
			alert("請選擇類型");
		}
		if (optionc == "0") {
			alert("請選擇品牌");
		}
		return;
	}
	function clearNoNum(obj)
	{
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
</script>
<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">新增商品</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">商品資料</h3>
								</div>
								<div class="panel-body">
									<form action="/ssj/ProductInsert" method="post"
										enctype="multipart/form-data">
										<input type="text" class="form-control" placeholder="商品名稱"
											name="name" required="required"> <br> <select
											id="agentCode" name="agent.id" class="form-control"
											required="required"></select><br> <select id="type"
											name="producttype.code" class="form-control" required="required">
										</select><br> <input type="text" class="form-control"
											placeholder="入庫數量" name="amount" required="required"onkeyup="this.value=this.value.replace(/[^\d]/g,'');" >
										<br /> <select id="brand" name="brand.code"
											class="form-control" required="required"></select><br /> <input
											type="text" class="form-control" placeholder="如是衣服填尺寸"
											name="size"> <br> <input type="text"
											class="form-control" placeholder="顏色" name="color"> <br>
										<input type="text" class="form-control" placeholder="銷售價"
											name="sellValue" required="required" onkeyup="clearNoNum(this)"> <br> <input
											type="text" class="form-control" placeholder="市場價"
											name="marketValue" required="required" onkeyup="clearNoNum(this)"><br /> <input
											type="text" class="form-control" placeholder="介紹"
											name="induction"> <br> 圖片:<input type="file"
											id="file1" name="file" onclick=" concatMarkVal();" /><a
											id="add" href="javascript:void();" rel="external nofollow"
											onclick="addFile();">添加</a>
										<button type="submit" class="btn btn-default"
											onmouseover="check();">提交</button>
									</form>
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
