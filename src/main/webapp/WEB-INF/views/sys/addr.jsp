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
									</form>
									<form action="" name="form1">
           <select name="province" id="province" onChange="getChild()">
            <option>== 请选择省份 ==</option>
            <option>北京市</option>
            <option>山西省</option>
            <option>山东省</option>
            <option>河北省</option>
            <option>河南省</option>
        </select>
        
        <select name="city" class="city">
            <option>== 请选择市区 ==</option>
        </select>
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