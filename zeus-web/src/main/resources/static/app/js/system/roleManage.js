var roleManage = {
    tableData: {},
    init: function () {
        var that = this;
        that.initTable();
        that.bindEvents();
    },
    initTable: function () {
        $("#roleTable").bootstrapTable({
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
            }
        });
    }
};
$(function () {
    roleManage.init();
});