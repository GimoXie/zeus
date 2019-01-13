var permissionManage = {
    tableData: {},
    init: function () {
        this.initTable();
        this.bindEvents();
    },
    initTable: function () {
        var $table = $("#permissionTable");
        $table.bootstrapTable({
            url: '/system/permissions',
            method: 'post',
            search: true,
            searchAlign: 'left',
            idField: 'id',
            cache: false,
            striped: true,
            clickToSelect: true,
            formatLoadingMessage: function () {
                return '<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>';
            },
            queryParams: function (params) {
                return {
                    search: params.search
                }
            },
            columns: [{
                title: '#',
                checkbox: true
            }, {
                title: '权限名称',
                field: 'name',
                sortable: false,
                align: 'left',
                valign: 'left'
            }, {
                title: '描述',
                field: 'description',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }],
            treeShowField: 'name',
            parentIdField: 'parentId',
            onLoadSuccess: function (result) {
                $table.treegrid({
                    treeColumn: 1
                })
            }
        });
    }
};
$(function () {
    permissionManage.init();
});