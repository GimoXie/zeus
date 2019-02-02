var roleManage = {
    tableData: {},
    permissionId: [],
    init: function () {
        this.initTable();
        this.bindEvents();
    },
    bindEvents: function() {
        var self = this;
        // 新增角色信息
        $('.role-add').on('click', function () {
            $('#roleForm').clearForm();
            $('.role-title').text('新增角色');
            $('#roleModel').modal('show');
        });
        // 修改角色信息
        $('.role-edit').on('click', function () {
            $('#roleForm').clearForm();
            var rows = $('#roleTable').bootstrapTable('getSelections');
            if (rows.length === 0) {
                $.alert('你必须选择一条数据');
                return;
            }
            var role = rows[0];
            $('#id').val(role.id);
            $('#name').val(role.name);
            $('#type').val(role.type);
            $('#description').val(role.description);
            $('.role-title').text('修改角色');
            $('#roleModel').modal('show');
        });

        // 新增角色信息
        $('.permission-save').on('click', function () {
            $.alertCallback("确定保存？", self.savePermission);
        });
    },
    savePermission: function() {
        var param = {};
        $.alert("save permission has been clicked");
        /*$.ajax({
            type: "POST",
            url: "/system/roles/savePermission",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code == '1') {
                    $.alert("保存成功!");
                    $('#roleTable').bootstrapTable('refresh');
                    $('#roleModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });*/
    },
    initTable: function () {
        const $table = $("#roleTable");
        $table.bootstrapTable({
            url: '/system/roles',
            method: 'post',
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
                title: '角色类型',
                field: 'type',
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
                field: 'isActive',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }],
            onLoadSuccess: function (result) {
                $.loadData(result, roleManage, "roleTable");
            },
            onCheck: function (row) {
                let $permissionTable = $("#rolePermissionTable");
                $.ajax({
                    url: '/system/rolePermissions/' + row.id,
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
                let $permissionTable = $("#rolePermissionTable");
                roleManage.permissionId = [];
                $permissionTable.bootstrapTable('refresh');
            }
        });
    },
    modifyRole: function () {
        var params = {
            id: $('#id').val(),
            name: $('#name').val(),
            type: $('#type').val(),
            description: $('#description').val()
        };
        $.ajax({
            type: "POST",
            url: "/system/roles/modify",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code == '1') {
                    $.alert("更新成功!");
                    $('#roleTable').bootstrapTable('refresh');
                    $('#roleModel').modal('hide');
                } else {
                    $.alert(data.message);
                }
            }
        });
    }
};
$(function () {
    roleManage.init();
});