<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>注册页面</title>
<%@ include file="../../../common.jsp"%>
</head>
<body>
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
						<div class="content">
							<div class="header">
								<div class="logo text-center">泽俊生活管理系统</div>
								<p class="lead">create to your account</p>
							</div>
							<form name="form1"class="form-auth-small" action="accountAdd" method="POST">
								<div class="form-group">
									昵称<input type="text" class="form-control" id="name" name="name"required="required" >
								</div>
								<div class="form-group">
								密码
									<input type="password" class="form-control" id="password" name="password" required="required" >
								</div>
								<div class="form-group">
								确认密码
									<input type="password" class="form-control" id="rPassword" onchange="fuck();" name="rPassword"required="required" >
									<input type="hidden" class="form-control" id="fuck" name="fuck"  value="${fuck}" >
									<input type="hidden" class="form-control" id="isAdmin" name="isAdmin"  value="" >
								</div>
								<div class="form-group">
								电话号码
									<input type="text" maxlength="11" onchange = "checkPhone();" class="form-control" id="phone" name="phone"  required="required">
								</div>
								<div class="form-group">
								微信号
									<input type="text" class="form-control" id="wechat" name="wechat" required="required" >
								</div>
								<div class="form-group">
								性别:
									<label class="fancy-checkbox">
										<input type="radio" id="sex1" name="sex"required="required" value="1" >
										<span>男</span>
										<input type="radio"id="sex2" name="sex"required="required" value="0" >
										<span>女</span>
									</label>
								</div>
								<div class="form-group">
								年龄
									<input type="text" required="required" class="form-control" id="age" name="age"  >
								</div>
								<div class="form-group">
								所在城市:
									<select name="province" id="province" onChange="getChild()">
            <option> 请选择省份 </option>
            <option>北京市</option>
            <option>山西省</option>
            <option>山东省</option>
            <option>河北省</option>
            <option>河南省</option>
        </select>
        <select name="city" class="city">
            <option> 请选择市区 </option>
        </select>
								</div>
								<button type="submit"  class="btn btn-primary btn-lg btn-block">确定</button>
								<div class="bottom">
									<span class="helper-text"> <a id="href" onclick="qLogin();" href="qLogin.jsp">返回登录页面</a></span>
								</div>
							</form>
						</div>
			</div>
		</div>
</body>
<script type="text/javascript">
$(function() {
	err();
	isAdmin();
});
	function err()
	{
		var hiddenCodeId = $("#hiddenCodeId").val();
		if(hiddenCodeId =="")
		{
			document.getElementById("code").style.display = "none";//将验证码输入框隐藏起来
		}
		else if(hiddenCodeId == "1")
		{
			document.getElementById("code").style.display = "block";//将验证码输入框隐藏起来
		}
	}
	function fuck(){
		var password=$("#password").val();
		var rPassword=$("#rPassword").val();
		if(password!=rPassword){
			alert("请确保密码和确认密码一致");
			$("#rPassword").val("");
		}
	}
	
	function changeCode()
	{
		
		var imgNode = document.getElementById("vimg");
		//Math.random(); 随机数，只是为了每次都请求sevlet，如果每次请求的都是同一个值，那么只会请求一次servlet
		imgNode.src = "/zj/image";
	}
	  //二级联动
    //定义城市数组
    var ctiyList = [
       ["东城区","西城区","朝阳区","丰台区","海淀区","昌平区"],
       ["太原市","大同市","运城市","临汾市","忻州市","晋中市"],
       ["济南市","青岛市","威海市","烟台市","临沂市","德州市"],
       ["石家庄市","邢台市","保定市"],
       ["郑州市","焦作市","三门峡市"],
     ];
    //点击选择框触发得到方法
    function getChild(){
        var sltProvince = document.form1.province;//获取name为form1下province
        var sltCity = document.form1.city;//获取name为form1下city
        var pc=ctiyList[sltProvince.selectedIndex - 1]; //省份列表下标要和城市列表下标一致
        sltCity.length=1;//每次选择都清空城市列表
        //遍历根据省份下标与之对应的城市下标
        for(var i=0;i<pc.length;i++){
            sltCity[i+1]=new Option(pc[i],pc[i]);    
        }
    }
    function checkPhone(){ 
        var phone = document.getElementById('phone').value;
        if(!(/^1[34578]\d{9}$/.test(phone))){ 
        	$("#phone").val("");
            alert("手机号码有误，请重填");  
            return false; 
        }else{
        	vaild();
        }
    }
    function vaild(){
		$.ajax({
			type : "POST",
			url : "/ssj/accountGet",
			dataType : 'JSON',
			async : false,
			data : {
				account:$("#phone").val()
			},
			success : function(datas) {
				if(datas.account !=null && datas.account !=""){
					$("#phone").val("");
					 alert("该手机号码已被注册"); 
					 return false;
				}
			}
		});
	}
</script>
</html>
