<%@ page language="java" contentType="text/html; charset=utf-8"
	isErrorPage="true"%>
<!doctype html>
<html lang="en">

<head>
<title>导购员评价</title>
<meta charset="utf-8">
<%@ include file="../../../common.jsp"%>
</head>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>

<body>
	<div id="wrapper">
		<!-- 头 -->
		<%@ include file="../../../header.jsp"%>
		<!-- 菜单 -->
		<%@ include file="../../../mune.jsp"%>
		<form action="updatetypefuck" method="post">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<h3 class="page-title">导购评价</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="panel">
									<div>
										<input type="hidden" name="ShoppingGuideId" value="${id}" />
									</div>
									<div>
										<input type="hidden" name="StartTime" value="${starttime}" />
									</div>
									<div>
										<input type="hidden" name="endTime" value="${endtime}" />
									</div>
									<div class="panel-body">
										满意度: <span class="select-box"> <select
											class="form-control" name="staisfaction">
												<option value="3003">一般</option>
												<option value="3001">极差</option>
												<option value="3002">差</option>
												<option value="3004">满意</option>
												<option value="3005">非常满意</option>
										</select>
										</span>
									</div>
									<div class="panel-body">
										描述:<input type="text" name="detailed" class="form-control"
											placeholder="描述" required="required">
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
											<div class="col-md-6">
												<button class="btn btn-primary btn-block">提交</button>
											</div>
										</div>
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
