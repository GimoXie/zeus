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
    <link href="/assets/css/style.min.css" rel="stylesheet">
    <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/plugins/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/plugins/animate/animate.min.css" rel="stylesheet">

    <link href="/assets/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/assets/plugins/layer/skin/layer.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">

    </div>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>用户列表</h5>
                <#--<div class="ibox-tools">
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
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>-->
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

        <!-- table自定义工具条 -->
        <div id="userToolbar" class="btn-group" style="width: 180px;">
            <button type="button" class="btn btn-default user-add" data-toggle="tooltip" data-placement="bottom" title="新增用户">
                <i class="fa fa-user-plus"></i>
            </button>
            <button type="button" class="btn btn-default user-edit" data-toggle="tooltip" data-placement="bottom" title="编辑用户">
                <i class="fa fa-edit"></i>
            </button>
            <button type="button" class="btn btn-default user-role-assign" data-toggle="tooltip" data-placement="bottom" title="分配角色">
                <i class="fa fa-odnoklassniki"></i>
            </button>
            <button type="button" class="btn btn-default user-refresh" data-toggle="tooltip" data-placement="bottom" title="刷新">
                <i class="fa fa-refresh"></i>
            </button>
        </div>
    </div>

    <!-- dialogs -->
    <div class="modal fade" id="userModel" tabindex="-1" role="dialog" aria-labelledby="userModel" aria-hidden="true"
         data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
                    <h4 class="modal-title user-title"></h4>
                </div>
                <div class="modal-body">
                    <form id="userForm" class="m-t" role="form">
                        <input type="hidden" id="id" value="">
                        <div class="form-group">
                            <label for="username">用户名称</label>
                            <input type="text" class="form-control" id="username" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">电子邮箱</label>
                            <input type="email" class="form-control" id="email" placeholder="请输入电子邮箱">
                        </div>
                        <div class="form-group">
                            <label for="telephone">联系电话</label>
                            <input type="text" class="form-control" id="telephone" placeholder="请输入联系电话">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
                    <button type="button" <#--data-dismiss="modal"--> class="btn btn-primary" onclick="userManage.modifyUser()">确定</button>
                </div>
            </div>
        </div>
    </div>

    <#-- base script -->
    <script src="/assets/plugins/jquery/jquery.min.js"></script>
    <script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/js/content.min.js"></script>

    <script src="/assets/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/assets/plugins/bootstrap-table/extensions/mobile/bootstrap-table-mobile.min.js"></script>
    <script src="/assets/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/assets/plugins/layer/layer.min.js"></script>
    <script src="/assets/plugins/jquery-form/jquery.form.min.js"></script>
    <script src="/app/js/common/common.jquery.js"></script>
    <script src="/app/js/system/userManage.js"></script>
</body>

</html>
