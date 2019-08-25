var roleManage = {
    tableData: {},
    permissionId: [],
    init: function () {
        this.initTable();
        this.bindEvents();
    },
    bindEvents: function() {
        // 新增角色信息
        $('.role-add').on('click', function () {
            $('#roleForm').clearForm();
            $('#name').removeAttr("readonly");
            $('.role-title').text('新增角色');
            $('.role-modify').attr('onclick', 'roleManage.saveRole();');
            $('#roleModel').modal('show');
        });
        // 编辑角色信息
        $('.role-edit').on('click', function () {
            $('#roleForm').clearForm();
            $('#name').attr("readonly","readonly");
            let rows = $('#roleTable').bootstrapTable('getSelections');
            if (rows.length === 0) {
                $.alert('你必须选择一条数据');
                return;
            }
            let role = rows[0];
            $('#id').val(role.id);
            $('#name').val(role.name);
            $('#code').val(role.code);
            $('#description').val(role.description);
            $('.role-title').text('修改角色');
            $('.role-modify').attr('onclick', 'roleManage.updateRole();');
            $('#roleModel').modal('show');
        });
        // 保存角色权限信息
        $('.role-permission-assign').on('click', function () {
            $.alert('保存成功！');// todo
        });
    },
    saveRole: function () {
        let params = {
            name: $('#name').val(),
            code: $('#code').val(),
            description: $('#description').val(),
            telephone: $('#telephone').val()
        };
        $.ajax({
            type: "POST",
            url: "/roles",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code === 1) {
                    $.alert("新增角色成功!");
                    $('#roleTable').bootstrapTable('refresh');
                    $('#roleModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });
    },
    updateRole: function () {
        let params = {
            name: $('#name').val(),
            code: $('#code').val(),
            description: $('#description').val(),
            telephone: $('#telephone').val()
        };
        $.ajax({
            type: "PUT",
            url: "/roles/" + $('#id').val(),
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code === 1) {
                    $.alert("更新角色信息成功!");
                    $('#roleTable').bootstrapTable('refresh');
                    $('#roleModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });
    },
    initTable: function () {
        const $table = $("#roleTable");
        $table.bootstrapTable({
            url: '/roles',
            method: 'GET',
            mobileResponsive: true,
            toolbar: '#roleToolbar',
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
                title: '角色编号',
                field: 'id',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '角色名称',
                field: 'name',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '角色编码',
                field: 'code',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }, {
                title: '描述',
                field: 'description',
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
                $.loadData(result, roleManage, "roleTable");
            },
            onCheck: function (row) {
                let $permissionTable = $("#permissionTable");
                $.ajax({
                    url: '/rolePermissions/?roleId=' + row.id,
                    type: 'GET',
                    async: false,
                    success: function (result) {
                        for (let i = 0 ; i < result.data.length; i++) {
                            roleManage.permissionId[i] = result.data[i].permissionId;
                        }
                    }
                });
                $permissionTable.bootstrapTable('refresh');
            },
            onUncheck: function () {
                let $permissionTable = $("#permissionTable");
                roleManage.permissionId = [];
                $permissionTable.bootstrapTable('refresh');
            }
        });
    }
};
$(function () {
    roleManage.init();
});