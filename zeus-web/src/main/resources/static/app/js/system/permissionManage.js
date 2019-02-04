var permissionManage = {
    tableData: {},
    permissionId: [],
    init: function () {
        this.initTable();
        // this.bindEvents();
    },
    initTable: function () {
        let $table = $("#rolePermissionTable");
        $table.bootstrapTable({
            url: '/system/permissions',
            method: 'post',
            search: true,
            searchAlign: 'left',
            toolbar: '#rolePermissionToolbar',
            toolbarAlign: 'right',
            idField: 'id',
            treeShowField: 'name',
            parentIdField: 'parentId',
            clickToSelect: true,
            columns: [{
                field: 'check',
                checkbox: true,
                formatter: function (value, row) {
                    let permissionId = permissionManage.permissionId;
                    if (permissionId === null || permissionId.length === 0) {
                        if (row.check === true) {
                            return {
                                checked: true
                            };
                        }

                    }
                    for (let i = 0; i < permissionId.length; i++) {
                        if (row.id === permissionId[i]) {
                            return {
                                checked: true
                            };
                        }
                    }
                }
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
            formatLoadingMessage: function () {
                return '<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>';
            },
            queryParams: function (params) {
                return {
                    search: params.search
                }
            },
            onLoadSuccess: function (result) {
                $.loadData(result, permissionManage, "rolePermissionTable");
                // 加载完毕后清空从roleManage.js带过来的permissionId,否认则父子节点的checkbox会失效。
                permissionManage.permissionId = [];
            },
            onResetView: function () {
                $table.treegrid({
                    //initialState: 'collapsed',// 所有节点都折叠
                    initialState: 'expanded',// 所有节点都展开，默认展开
                    treeColumn: 1,
                    expanderExpandedClass: 'treegrid-expander-expanded',  //图标样式
                    expanderCollapsedClass: 'treegrid-expander-collapsed',
                    onChange: function () {
                        $table.bootstrapTable('resetWidth');
                    }
                });
                //只展开树形的第一级节点
                //$table.treegrid('getRootNodes').treegrid('expand');
            },
            onCheck: function (row) {
                let permissionId = permissionManage.permissionId;
                if (permissionId != null && permissionId.length !== 0) {
                    return;
                }
                // 勾选子类
                permissionManage.selectChild(permissionManage.tableData, row, "id", "parentId", true);
                // 勾选父类
                permissionManage.selectParentChecked(permissionManage.tableData, row, "id", "parentId")
                // 刷新数据
                $table.bootstrapTable('load', permissionManage.tableData);
            },

            onUncheck: function (row) {
                let permissionId = permissionManage.permissionId;
                if (permissionId != null && permissionId.length !== 0) {
                    return;
                }
                permissionManage.selectChild(permissionManage.tableData, row, "id", "parentId", false);
                $table.bootstrapTable('load', permissionManage.tableData);
            },
        });
    },
    selectChild: function (tableData, row, id, pid, checked) {
        for (let i in tableData) {
            if (tableData[i][pid] === row[id]) {
                tableData[i].check = checked;
                permissionManage.selectChild(tableData, tableData[i], id, pid, checked);
            }
        }
    },
    selectParentChecked: function (tableData, row, id, pid) {
        for (let i in tableData) {
            if (tableData[i][id] === row[pid]) {
                tableData[i].check = true;
                permissionManage.selectParentChecked(tableData, tableData[i], id, pid);
            }
        }
    }
};
$(function () {
    permissionManage.init();
});