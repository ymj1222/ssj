<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html lang="en">

<head>
	<title>Tables</title>
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
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-body">
									<form class="navbar-form navbar-left">
										<div class="input-group">
											<input type="text" value="" class="form-control" placeholder="Search dashboard...">
											<span class="input-group-btn"><button type="button" onclick="search();" class="btn btn-primary">Go</button></span>
										</div>
									</form>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th></th>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Username</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Steve</td>
												<td>Jobs</td>
												<td>@steve</td>
											</tr>
											<tr>
												<td>2</td>
												<td>Simon</td>
												<td>Philips</td>
												<td>@simon</td>
											</tr>
											<tr>
												<td>3</td>
												<td>Jane</td>
												<td>Doe</td>
												<td>@jane</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
	</div>	
</body>

</html>