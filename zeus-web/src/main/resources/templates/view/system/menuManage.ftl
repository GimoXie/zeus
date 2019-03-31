<!DOCTYPE html>
<html>

<head>
    <title>Zeus后台管理系统 - 菜单管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <#-- favicon -->
    <link href="favicon.ico" rel="shortcut icon">
    <#-- base css -->
    <link href="/assets/css/style.min.css" rel="stylesheet">
    <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/plugins/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/plugins/animate/animate.min.css" rel="stylesheet">

    <link href="/assets/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/assets/plugins/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet">
    <link href="/assets/plugins/layer/skin/layer.css" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">

</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>菜单列表</h5>
        </div>
        <div class="ibox-content">
            <table id="menuTable" width="100%">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>图标</th>
                    <th>路径</th>
                    <th>描述</th>
                    <th>优先级</th>
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
<script src="/assets/plugins/jquery/jquery.min.js"></script>
<script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/js/content.min.js"></script>

<script src="/assets/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/assets/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/assets/plugins/bootstrap-table/extensions/mobile/bootstrap-table-mobile.min.js"></script>
<script src="/assets/plugins/bootstrap-table/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>
<script src="/assets/plugins/jquery-treegrid/js/jquery.treegrid.js"></script>
<script src="/assets/plugins/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/assets/plugins/layer/layer.min.js"></script>
<script src="/assets/plugins/jquery-form/jquery.form.min.js"></script>
<script src="/app/js/common/common.jquery.js"></script>
<script src="/app/js/system/menuManage.js"></script>
</body>

</html>
