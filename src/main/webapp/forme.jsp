<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">

<head>
	<title>录入</title>
	<meta charset="utf-8">
	<%@ include file="common.jsp"%>
</head>
<body>
	<div id="wrapper">
		<!-- 头 -->
	<%@ include file="header.jsp"%>
	<!-- 菜单 -->
	<%@ include file="mune.jsp"%>
	
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">新增商品</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Inputs</h3>
								</div>
								<div class="panel-body">
									<input type="text" class="form-control" placeholder="text field">
									<br>
									<input type="password" class="form-control" value="asecret">
									<br>
									<textarea class="form-control" placeholder="textarea" rows="4"></textarea>
									<br>
									<select class="form-control">
										<option value="cheese">Cheese</option>
										<option value="tomatoes">Tomatoes</option>
										<option value="mozarella">Mozzarella</option>
										<option value="mushrooms">Mushrooms</option>
										<option value="pepperoni">Pepperoni</option>
										<option value="onions">Onions</option>
									</select>
									<br>
									<label class="fancy-checkbox">
										<input type="checkbox">
										<span>Fancy Checkbox 1</span>
									</label>
									<label class="fancy-checkbox">
										<input type="checkbox">
										<span>Fancy Checkbox 2</span>
									</label>
									<label class="fancy-checkbox">
										<input type="checkbox">
										<span>Fancy Checkbox 3</span>
									</label>
									<br>
									<label class="fancy-radio">
										<input name="gender" value="male" type="radio">
										<span><i></i>Male</span>
									</label>
									<label class="fancy-radio">
										<input name="gender" value="female" type="radio">
										<span><i></i>Female</span>
									</label>
								</div>
							</div>
							<!--form表单自写，按钮标签在下面自用-->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Buttons</h3>
								</div>
								<div class="panel-body">
									<p class="demo-button">
										<button type="button" class="btn btn-default">Default</button>
										<button type="button" class="btn btn-primary">Primary</button>
										<button type="button" class="btn btn-info">Info</button>
										<button type="button" class="btn btn-success">Success</button>
										<button type="button" class="btn btn-warning">Warning</button>
										<button type="button" class="btn btn-danger">Danger</button>
									</p>
									<br>
									<p class="demo-button">
										<button type="button" class="btn btn-primary btn-lg">Large Size</button>
										<button type="button" class="btn btn-primary">Default Size</button>
										<button type="button" class="btn btn-primary btn-sm">Small Size</button>
										<button type="button" class="btn btn-primary btn-xs">Extra Small Size</button>
									</p>
									<br>
									<p class="demo-button">
										<button type="button" class="btn btn-default"><i class="fa fa-plus-square"></i> Default </button>
										<button type="button" class="btn btn-primary"><i class="fa fa-refresh"></i> Primary</button>
										<button type="button" class="btn btn-info"><i class="fa fa-info-circle"></i> Info</button>
										<button type="button" class="btn btn-primary" disabled="disabled"><i class="fa fa-refresh fa-spin"></i> Refreshing...</button>
									</p>
									<br>
									<p class="demo-button">
										<button type="button" class="btn btn-success"><i class="fa fa-check-circle"></i> Success</button>
										<button type="button" class="btn btn-warning"><i class="fa fa-warning"></i> Warning</button>
										<button type="button" class="btn btn-danger"><i class="fa fa-trash-o"></i> Danger</button>
										<button type="button" class="btn btn-primary" disabled="disabled"><i class="fa fa-spinner fa-spin"></i> Loading...</button>
									</p>
									<br>
									<div class="row">
										<div class="col-md-6">
											<button type="button" class="btn btn-primary btn-block">Button Block</button>
										</div>
										<div class="col-md-6">
											<button type="button" class="btn btn-warning btn-block">Button Block</button>
										</div>
									</div>
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
