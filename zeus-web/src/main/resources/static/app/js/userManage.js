var userManage = {
    tableData: {},
    init: function () {
        var that = this;
        that.initUserTable();
        that.bindEvents();
    },
    bindEvents: function () {
        var that = this;
        // 新增用户信息
        $('.user-add').on('click', function () {
            that.resetForm();
            $('.user-title').text('新增用户');
            $('#userModel').modal('show');
        });
        // 修改用户信息
        $('.user-edit').on('click', function () {
            that.resetForm();
            $('.user-title').text('修改用户');
            $('#userModel').modal('show');
        });
    },
    resetForm: function () {
        $('#userForm')[0].reset();
        $('#id').val(null);
    },
    modifyUser: function () {
        var params = {
            id: $('#id').val(),
            username: $('#username').val(),
            email: $('#email').val(),
            telephone: $('#telephone').val()
        }
        $.alert(JSON.stringify(params));
        // TODO：调用服务接口

    },
    initUserTable: function () {
        $("#userTable").bootstrapTable({
            url: '/system/users',
            method: 'post',
            mobileResponsive: true,
            toolbar: '#userToolbar',
            toolbarAlign: 'right',
            search: true,
            searchAlign: 'left',
            cache: false,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 30],
            sidePagination: 'server',
            striped: true,
            singleSelect: true,
            clickToSelect: true,
            formatLoadingMessage: function () {
                return '<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>';
            },
            queryParams: function (params) {
                return {
                    limit: params.limit,
                    offset: params.offset,
                    sort: params.sort,
                    order: params.order,
                    search: params.search
                }
            },
            columns: [{
                title: '#',
                checkbox: true
            }, {
                title: '用户编号',
                field: 'id',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '用户名',
                field: 'username',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '邮箱',
                field: 'email',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '联系电话',
                field: 'telephone',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '最后登录时间',
                field: 'lastLoginTime',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '是否有效',
                field: 'isActive',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }],
            onLoadSuccess: function (result) {
                $.loadData(result, userManage, "userTable");
            }
        });
    }
};

$(function () {
    userManage.init();
});