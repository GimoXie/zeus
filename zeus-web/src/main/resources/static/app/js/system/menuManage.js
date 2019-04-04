const menuManage = {
    tableData: {},
    init: function () {
        this.initTable();

    },
    initTable: function () {
        let $table = $("#menuTable");
        $table.bootstrapTable({
            url: '/permissions',
            method: 'GET',
            search: true,
            searchAlign: 'left',
            idField: 'id',
            //clickToSelect: true,
            treeShowField: 'name',
            parentIdField: 'parentId',
            columns: [{
                field: 'check',
                checkbox: true
            }, {
                title: '名称',
                field: 'name',
                sortable: false,
                align: 'left',
                valign: 'left'
            }, {
                title: '图标',
                field: 'icon',
                sortable: false,
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    return '<i class = "' + value + '"></i>';
                }
            }, {
                title: '路径',
                field: 'uri',
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
                title: '优先级',
                field: 'priority',
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
            formatLoadingMessage: function () {
                return '<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>';
            },
            queryParams: function (params) {
                return {
                    search: params.search
                }
            },
            onLoadSuccess: function (result) {
                $.loadData(result, menuManage, "menuTable");
            },
            onResetView: function () {
                $table.treegrid({
                    initialState: 'expanded',// 所有节点都展开，默认展开
                    treeColumn: 1,
                    expanderExpandedClass: 'treegrid-expander-expanded',  //图标样式
                    expanderCollapsedClass: 'treegrid-expander-collapsed',
                    onChange: function () {
                        $table.bootstrapTable('resetWidth');
                    }
                });
            },
            onCheck: function (row) {
                let data = menuManage.tableData;
                // 勾选子类
                menuManage.selectChild(data, row, "id", "parentId", true);

                // 勾选父类
                menuManage.selectParentChecked(data, row, "id", "parentId")

                // 刷新数据
                $table.bootstrapTable('load', data);
            },

            onUncheck: function (row) {
                let data = menuManage.tableData;
                menuManage.selectChild(data, row, "id", "parentId", false);
                $table.bootstrapTable('load', data);
            }
        });
    },
    selectChild: function (data, row, id, pid, checked) {
        data.forEach(item => {
            if (item[pid] === row[id]) {
                item.check = checked;
                menuManage.selectChild(data, item, id, pid, checked);
            }
        });
    },
    selectParentChecked: function (data, row, id, pid) {
        data.forEach(item => {
          if (item[id] === row[pid]) {
              item.check = true;
              menuManage.selectParentChecked(data, item, id, pid);
          }
        });
    }
};

$(function () {
    menuManage.init();
});