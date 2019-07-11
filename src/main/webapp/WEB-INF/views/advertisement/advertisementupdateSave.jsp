<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html>

<head>
<title>廣告信息修改</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript">
$(function(){
	getPhtotUrl();
	getVideoUrl();
});
	function getPhtotUrl() {
		$.ajax({
					type : "POST",
					url : "/ssj/advertisementPhotoselect",
					dataType : 'JSON',
					async : false,
					data : {
					},
					success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
						var fcont = '';
						$('#photoId').html(fcont);
						fcont = fcont + '<option value="">${advertisement.photo}</option>';
						$.each(datas, function(i, obj) {
							fcont = fcont + '<option value="'+obj.url+'">'
									+ obj.name + '</option>';
						});
						$('#photoId').html(fcont);
					}
				});
	}
	function getVideoUrl() {
		$.ajax({
					type : "POST",
					url : "/ssj/advertisementVideoselect",
					dataType : 'JSON',
					async : false,
					data : {
					},
					success : function(datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
						var fcont = '';
						$('#videoId').html(fcont);
						fcont = fcont + '<option value="">${advertisement.video}</option>';
						$.each(datas, function(i, obj) {
							fcont = fcont + '<option value="'+obj.url+'">'
									+ obj.name + '</option>';
						});
						$('#videoId').html(fcont);
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
		<form action="advertisementUpdateSave" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">修改</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div class="panel-body">
										<input type="hidden" name="code" value="${advertisement.code}"	>
									</div>
									<div class="panel-body">
										<span class="select-box"> <select class="select"
											name="photo" id="photoId" required="required"></select>
										</span>
									</div>
									<div class="panel-body">
										<span class="select-box" > <select class="select"
											name="video" id="videoId" required="required"></select>
										</span>
									</div>
									<div class="panel-body">
										<button type="submit" class="btn btn-primary">Submit</button>
										<button  type="reset" class="btn btn-default">Reset</button>
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
