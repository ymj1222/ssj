<%@ page language="java" contentType="text/html; charset=utf-8"%>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
	<link rel="stylesheet" href="assets/css/main.css">
	<link rel="stylesheet" href="assets/css/demo.css">
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script type="text/javascript" src="assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript">
	function session() {
		$.ajax({
			type : "POST",
			url : "/ssj/removeSession",
			dataType : 'JSON',
			async : false,
			data : {},
			success : function(datas) {
				window.location.href = 'login.jsp';
			}
		});
	}
	$(function account() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
		$.ajax({
			type : "POST",//请求方式为post
			url : "/ssj/getAccountName", //请求url
			dataType : 'JSON',//数据格式为json
			async : false,//是否同步，false表示为异步 
			data : {},
			success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
				$("#login").html(datas.name);
			}
		});
	});
</script>