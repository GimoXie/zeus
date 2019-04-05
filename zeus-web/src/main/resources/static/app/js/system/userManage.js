const userManage = {
    tableData: {},
    init: function () {
        this.initTable();
        this.bindEvents();
    },
    bindEvents: function () {
        // 新增用户信息
        $('.user-add').on('click', function () {
            $('#userForm').clearForm();
            $('#username').removeAttr("readonly");
            $('.user-title').text('新增用户');
            $('.user-modify').attr('onclick', 'userManage.saveUser();');
            $('#userModel').modal('show');
        });
        // 修改用户信息
        $('.user-edit').on('click', function () {
            $('#userForm').clearForm();
            $('#username').attr("readonly","readonly");
            let rows = $('#userTable').bootstrapTable('getSelections');
            if (rows.length === 0) {
                $.alert('你必须选择一条数据');
                return;
            }
            let user = rows[0];
            $('#id').val(user.id);
            $('#username').val(user.username);
            $('#nickName').val(user.nickName);
            $('#email').val(user.email);
            $('#telephone').val(user.telephone);
            $('.user-title').text('修改用户');
            $('.user-modify').attr('onclick', 'userManage.updateUser();');
            $('#userModel').modal('show');
        });
    },
    saveUser: function () {
        let params = {
            username: $('#username').val(),
            nickName: $('#nickName').val(),
            email: $('#email').val(),
            telephone: $('#telephone').val()
        };
        $.ajax({
            type: "POST",
            url: "/users",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code === 1) {
                    $.alert("新增用户成功!");
                    $('#userTable').bootstrapTable('refresh');
                    $('#userModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });
    },
    updateUser: function () {
        let params = {
            nickName: $('#nickName').val(),
            email: $('#email').val(),
            telephone: $('#telephone').val()
        };
        $.ajax({
            type: "PUT",
            url: "/users/" + $('#id').val(),
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code === 1) {
                    $.alert("更新用户信息成功!");
                    $('#userTable').bootstrapTable('refresh');
                    $('#userModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });
    },
    initTable: function () {
        $("#userTable").bootstrapTable({
            url: '/users',
            method: 'GET',
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
                title: '昵称',
                field: 'nickName',
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
                field: 'active',
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