<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
			<ul class="nav">
				<li><a href="#subusers" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-home"></i> <span>用户管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="subusers" class="collapse ">
						<ul class="nav">
							<li><a href="toUsersList" class="">用户管理</a></li>
						</ul>
					</div></li>
				<li><a href="#subroles" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>权限管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="subroles" class="collapse ">
						<ul class="nav"> 
							<li><a href="toAccountList" class="">账户管理</a></li>
							<li><a href="toOrgList" class="">部门管理</a></li>
							<li><a href="toStaffList" class="">成员管理</a></li>
							<li><a href="toAuthList" class="">权限管理</a></li>
							<li><a href="toRoleList" class="">角色管理</a></li>
							<li><a href="toOptionLogList" class="">操作日志</a></li>
						</ul>
					</div></li>
				<li><a href="#subproducts" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>商品管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="subproducts" class="collapse ">
						<ul class="nav">
							<li><a href="/ssj/ToProductInsert" class="">商品添加</a></li>
							<li><a href="/ssj/ToProductList" class="">商品编辑</a></li>
							<li><a href="/ssj/Toboth" class="">商品查看</a></li>
							<li><a href="/ssj/ToproductselectStatus" class="">商品审核</a></li>
							<li><a href="/ssj/Toproductselectrecover" class="">商品回收站</a></li>
							<li><a href="/ssj/ToProductEvaluationSelect" class="">商品评价</a></li>
							<li><a href="/ssj/ToBrandList" class="">商品品牌</a></li>
							<li><a href="/ssj/ToProductTypeselect" class="">商品類型</a></li>
						</ul>
					</div></li>
				<li><a href="#subordels" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>订单管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="subordels" class="collapse ">
						<ul class="nav">
							<li><a href="findOrdersList" class="">订单查看</a></li>
									<li><a href="findOrdersOut" class="">订单发货</a></li>
									<li><a href="findordersDownLoad" class="">订单导出</a></li>
						</ul>
					</div></li>
				<li><a href="#substock" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>库存管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="substock" class="collapse ">
						<ul class="nav">
							<li><a href="/ssj/ToWarehouseSelect" class="">库存管理</a></li>
							<li><a href="/ssj/ToOutSelect" class="">出库查询</a></li>

						</ul>
					</div></li>
				<li><a href="#submaterials" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>素材库管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="submaterials" class="collapse ">
						<ul class="nav">
							<li><a href="findphotoList" class="">图片管理</a></li>
							<li><a href="findvideoList" class="">视频管理</a></li>
						</ul>
					</div></li>
				<li><a href="#Advertisement" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>广告管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="Advertisement" class="collapse ">
						<ul class="nav">
							<li><a href="findadvertisementList" class="">广告管理</a></li>
							<li><a href="findadvertisementClickList" class="">广告统计</a></li>

						</ul>
					</div></li>
				<li><a href="#agent" data-toggle="collapse" class="collapsed"><i
						class="lnr lnr-text-format"></i> <span>代理管理</span> <i
						class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="agent" class="collapse ">
						<ul class="nav">
							<li><a href="findagentAdd" class="">代理新增</a></li>
							<li><a href="findagentList" class="">代理列表</a></li>
						</ul>
					</div></li>
				<li>
							<li><a href="#content" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>内容管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="content" class="collapse ">
						<ul class="nav">
							<li><a href="findSpecialList" class="">专题管理</a></li>
							<li><a href="findArticleList" class="">文章管理</a></li>
							<li><a href="findArticleReportList" class="">举报管理</a></li>
						</ul>
					</div></li>
				<li><a href="#terminal" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>终端管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="terminal" class="collapse">
						<ul class="nav">
							<li><a href="findterminalAdd" class="">终端新增</a></li>
							<li><a href="findterminalList" class="">终端查询</a></li>
						</ul>
					</div></li>
				<li><a href="#shopping" data-toggle="collapse"
					class="collapsed"><i class="lnr lnr-text-format"></i> <span>导购管理</span>
						<i class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="shopping" class="collapse ">
						<ul class="nav">
							<li><a href="findshoppingGuideAdd" class="">导购新增</a></li>
							<li><a href="findshoppingGuideList" class="">导购查询</a></li>
							<li><a href="findserviceRecordList" class="">导购服务记录</a></li>
						</ul>
					</div></li>
				<li><a href="#push" data-toggle="collapse" class="collapsed"><i
						class="lnr lnr-text-format"></i> <span>推送管理</span> <i
						class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="push" class="collapse ">
						<ul class="nav">
							<li><a href="findinformationPushList" class="">短信推送</a></li>
						</ul>
					</div></li>
				<li><a href="#other" data-toggle="collapse" class="collapsed"><i
						class="lnr lnr-text-format"></i> <span>其他设置</span> <i
						class="icon-submenu lnr lnr-chevron-left"></i></a>
					<div id="other" class="collapse ">
						<ul class="nav">
							<li><a href="toPlatformIList" class="">平台信息设置</a></li>
							<li><a href="toLogisticsList" class="">物流公司设置</a></li>
							<li><a href="toPayList" class="">支付设置</a></li>
							<li><a href="toAddrList" class="">区域设置</a></li>
						</ul>
					</div></li>
			</ul>
		</nav>
	</div>
</div>

