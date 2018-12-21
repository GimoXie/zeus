(function ($) {
    $.extend({
        alert: function (content) {
            layer.alert('<span class="alert-content">' + content + '</span>', {
                title: "提示",
                icon: 7,
                btn: ['OK']
            });
        },
        /**
         * 给bootstrap-table加载数据
         * @param result  服务返回结果
         * @param obj     js对象
         * @param tableId 表格id
         */
        loadData: function (result, obj, tableId) {
            if (result.code == 1) {
                if (result.data != null) {
                    obj.tableData = result.data;
                    $('#' + tableId).bootstrapTable('load', result.data);
                }
            } else {
                $.alert(result.message);
            }
        },
        loading: function (type) {
            if (type == null || type == undefined) {
                type = 2;
            }
            return layer.load(type, {shade: [0.5, '#393D49']});
        },
        closePopup: function (index) {
            layer.close(index);
        },
        alertCallback: function (content, callback) {
            layer.alert('<span class="alert-content">' + content + '</span>', {
                title: "提示",
                icon: 7,
                btn: ['OK']
            }, function (index) {
                callback();
                layer.close(index);
            });
        },
        confirm: function (content, callback) {
            layer.confirm('<span class="alert-content">' + content + '</span>', {
                title: "提示",
                icon: 7,
                btn: ['确定', '取消']
            }, function (index) {
                layer.close(index);
                callback();
            }, function (index) {
                layer.close(index);
                return false;
            });
        },
        getUrlParam: function (name) {
            var vars = [], hash;
            var url = window.location.href;
            if (url.indexOf('?') > 0) {
                var hashes = url.slice(url.indexOf('?') + 1).split('&');
                for (var i = 0; i < hashes.length; i++) {
                    hash = hashes[i].split('=');
                    vars.push(hash[0]);
                    vars[hash[0]] = hash[1];
                }
                return vars.length === 0 ? '' : (unescape(vars[name]) === 'undefined' ? '' : unescape(vars[name])) ;
            } else {
                return '';
            }
        },
        parseDate: function (date) {
            var y = date.year;
            var m = date.monthValue;
            m = m < 10 ? ('0' + m) : m;
            var d = date.dayOfMonth;
            d = d < 10 ? ('0' + d) : d;
            var h = date.hour;
            h = h < 10 ? ('0' + h) : h;
            var minute = date.minute;
            var second = date.second;
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        }
    });
})(jQuery);