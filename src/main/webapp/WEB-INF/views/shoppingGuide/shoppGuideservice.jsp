<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html lang="en">
<head>
<title>服务中</title>
<%@ include file="../../../common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<div class="main">
			<img src="photo/1563018647701.jpg">
		</div>
	</div>
	<form action="updatetypeon" method="post">
		<input type="hidden" name="id" value="${id}" />
		<input type="hidden" name="starttime" value="${starttime}" />
		<button type="submit">退出</button>
	</form>
</body>
</html>
