<!DOCTYPE html>
<html>

<head>
    <title>Zeus后台管理系统 - 用户管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <#-- favicon -->
    <link href="favicon.ico" rel="shortcut icon">
    <#-- base css -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/css/animate.min.css" rel="stylesheet">
    <link href="/assets/css/style.min.css" rel="stylesheet">

    <link href="/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/assets/js/plugins/layer/skin/layer.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">

    </div>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>用户列表</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">选项1</a>
                        </li>
                        <li><a href="#">选项2</a>
                        </li>
                    </ul>
                    <#--<a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>-->
                </div>
            </div>
            <div class="ibox-content">
                <table id="userTable" width="100%">
                    <thead>
                    <tr>
                        <th>用户编号</th>
                        <th>用户名</th>
                        <th>邮箱</th>
                        <th>联系电话</th>
                        <th>最后登录时间</th>
                        <th>是否有效</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <#-- base script -->
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/content.min.js"></script>

    <script src="/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/assets/js/plugins/layer/layer.min.js"></script>
    <script src="/app/js/common/common.jquery.js"></script>
    <script src="/app/js/userManage.js"></script>
</body>

</html>
