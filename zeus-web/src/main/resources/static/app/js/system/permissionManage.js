const permissionManage = {
    tableData: {},
    init: function () {
        this.initTable();
        //this.bindEvents();
    },
    initTable: function () {
        let $table = $("#permissionTable");
        $table.bootstrapTable({
            url: '/permissions',
            method: 'GET',
            toolbar: '#permissionToolbar',
            toolbarAlign: 'right',
            search: true,
            searchAlign: 'left',
            idField: 'id',
            //clickToSelect: true,
            treeShowField: 'name',
            parentIdField: 'parentId',
            columns: [{
                field: 'check',
                checkbox: true,
                formatter: function (value, row) {
                    let permissionId = roleManage.permissionId;
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
                $.loadData(result, permissionManage, "permissionTable");
                // 加载完毕后清空从roleManage.js带过来的permissionId,否认则父子节点的checkbox会失效。
                roleManage.permissionId = [];
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
                let permissionId = roleManage.permissionId;
                if (permissionId != null && permissionId.length !== 0) {
                    return;
                }
                let data = permissionManage.tableData;
                // 勾选子类
                permissionManage.selectChild(data, row, "id", "parentId", true);

                // 勾选父类
                permissionManage.selectParentChecked(data, row, "id", "parentId")

                // 刷新数据
                $table.bootstrapTable('load', data);
            },

            onUncheck: function (row) {
                let permissionId = roleManage.permissionId;
                if (permissionId != null && permissionId.length !== 0) {
                    return;
                }
                let data = permissionManage.tableData;
                permissionManage.selectChild(data, row, "id", "parentId", false);
                $table.bootstrapTable('load', data);
            }
        });
    },
    selectChild: function (data, row, id, pid, checked) {
        data.forEach(item => {
            if (item[pid] === row[id]) {
                item.check = checked;
                permissionManage.selectChild(data, item, id, pid, checked);
            }
        });
    },
    selectParentChecked: function (data, row, id, pid) {
        data.forEach(item => {
            if (item[id] === row[pid]) {
                item.check = true;
                permissionManage.selectParentChecked(data, item, id, pid);
            }
        });
    }
};
$(function () {
    permissionManage.init();
});