var userManage = {
    init: function () {
        var that = this;
        that.initUserTable();
        that.bindEvents();
    },
    bindEvents: function () {

    },
    initUserTable: function () {
        $("#userTable").bootstrapTable({
            url: '/system/users',
            method: 'post',
            mobileResponsive: true,
            cache: false,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 30],
            sidePagination: "server",
            search: true,
            searchOnEnterKey: true,
            toolbar: '#userTableToolbar',
            toolbarAlign: 'left',
            searchAlign: 'left',
            striped: true,
            singleSelect: true,
            clickToSelect: true,
            formatLoadingMessage: function () {
                return '<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>';
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
            }]
        });
    }
};



$(function () {
    userManage.init();
});