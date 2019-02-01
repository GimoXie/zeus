<!DOCTYPE html>
<html>

<head>
    <title>Zeus后台管理系统 - 角色管理</title>
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
        <!-- 角色管理 -->
        <div class="col-xs-12 col-sm-7">
            <div class="ibox float-e-margin">
                <div class="ibox-title">
                    <h5>角色列表</h5>
                </div>
                <div class="ibox-content">
                    <table id="roleTable" width="100%">
                        <thead>
                        <tr>
                            <th>角色编号</th>
                            <th>角色名称</th>
                            <th>角色类型</th>
                            <th>描述</th>
                            <th>是否有效</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- 角色表格自定义工具条 -->
            <div id="roleToolbar" class="btn-group" style="width: 180px;">
                <button type="button" class="btn btn-default role-add" data-toggle="tooltip" data-placement="bottom" title="新增角色">
                    <i class="fa fa-plus"></i>
                </button>
                <button type="button" class="btn btn-default role-edit" data-toggle="tooltip" data-placement="bottom" title="编辑角色">
                    <i class="fa fa-edit"></i>
                </button>
                <button type="button" class="btn btn-default role-permission-assign" data-toggle="tooltip" data-placement="bottom" title="删除角色">
                    <i class="fa fa-trash"></i>
                </button>
                <button type="button" class="btn btn-default role-refresh" data-toggle="tooltip" data-placement="bottom" title="刷新">
                    <i class="fa fa-refresh"></i>
                </button>
            </div>
        </div>
        <!-- 权限管理 -->
        <div class="col-xs-12 col-sm-5" style="overflow-y:scroll">
            <div class="ibox float-e-margin ">
                <div class="ibox-title">
                    <h5>权限列表</h5>
                </div>
                <div class="ibox-content">
                    <table id="permissionTable" width="100%">
                        <thead>
                        <tr>
                            <th>权限名称</th>
                            <th>描述</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- 角色管理弹层 -->
    <div class="modal fade" id="roleModel" tabindex="-1" role="dialog" aria-labelledby="roleModel" aria-hidden="true"
         data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
                    <h4 class="modal-title role-title"></h4>
                </div>
                <div class="modal-body">
                    <form id="roleForm" class="m-t" role="form">
                        <input type="hidden" id="id" value="">
                        <div class="form-group">
                            <label for="name">角色名称</label>
                            <input type="text" class="form-control" id="name" placeholder="请输入角色名称">
                        </div>
                        <div class="form-group">
                            <label for="type">角色类型</label>
                            <input type="text" class="form-control" id="type" placeholder="请输入角色类型">
                        </div>
                        <div class="form-group">
                            <label for="description">角色描述</label>
                            <input type="text" class="form-control" id="description" placeholder="请输入角色描述">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
                    <button type="button" <#--data-dismiss="modal"--> class="btn btn-primary" onclick="roleManage.modifyRole()">确定</button>
                </div>
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
    <script src="/app/js/system/roleManage.js"></script>
    <script src="/app/js/system/permissionManage.js"></script>
</body>

</html>
