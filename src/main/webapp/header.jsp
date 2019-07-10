<%@ page language="java" contentType="text/html; charset=utf-8"%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
		<a href="index.jsp">泽俊生活管理系统</a>
	</div>
	<div class="container-fluid">
		<div class="navbar-btn">
			<button type="button" class="btn-toggle-fullwidth">
				<i class="lnr lnr-arrow-left-circle"></i>
			</button>
		</div>

		<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><img src="assets/img/小超超.png"
						class="img-circle" alt="Avatar"> <span id="login"></span> <i
						class="icon-submenu lnr lnr-chevron-down"></i></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="lnr lnr-user"></i> <span>我的信息</span></a></li>
						
						<li><a href="javascript:session();" data-href="login.jsp">注销</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>
