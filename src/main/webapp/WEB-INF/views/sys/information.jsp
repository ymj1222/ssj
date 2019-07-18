<%@ page language="java" contentType="text/html; charset=utf-8"
         isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
    <title>Tables</title>
    <%@ include file="../../../common.jsp" %>
</head>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.4.js"></script>
<script>
    $(function () {
        list();
    });

    function list() { //表示页面节点(元素,dom,标签) 加载完成,自动会执行这里面的内容
        $
            .ajax({
                type: "POST",//请求方式为post
                url: "/ssj/platformIQuery",//请求url
                dataType: 'JSON',//数据格式为json
                async: false,//是否同步，false表示为异步
                data: {},
                success: function (datas) { //function为一个回调函数，data表示为请求servlet后，servlet中返回回来的值
                    $("#tbodyId").html(cont)
                    var cont = '';
                    cont = cont + '<tr>';
                    cont = cont + '<td><img src="' + datas.logo
                        + '" width="200" height="150"></td>';
                    cont = cont + '<td>' + datas.companyWebsite
                        + '</td>';
                    cont = cont + '<td><img src="' + datas.qr
                        + '" width="200" height="150"></td>';
                    cont = cont + '<td>' + datas.tel
                        + '</td>';
                    cont = cont + '</tr>';
                    $("#tbodyId").html(cont)
                }
            });
    }

</script>
<body>
<div id="wrapper">
    <!-- 头 -->
    <%@ include file="../../../header.jsp" %>
    <!-- 菜单 -->
    <%@ include file="../../../mune.jsp" %>
    <div class="main">
        <div class="main-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <!-- BORDERED TABLE -->
                        <div class="panel">
                            <div class="panel-body">
                                <form class="navbar-form navbar-left">
                                    <i class="lnr lnr-pencil" class="btn btn-primary"><a
                                            href="toPlatformIUpdate">修改平台信息</a></i>
                                </form>
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="80">logo</th>
                                        <th width="80">公司网址</th>
                                        <th width="80">二维码</th>
                                        <th width="80">电话</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbodyId">

                                    </tbody>
                                </table>
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