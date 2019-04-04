var roleManage = {
    tableData: {},
    permissionId: [],
    init: function () {
        this.initTable();
        //this.bindEvents();
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