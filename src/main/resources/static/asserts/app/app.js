var App = function () {
    function del(url, params) {
        $.ajax({
            "url": url,
            "data": params,//如果是toString方法    前端传到后端的是一个以逗号分割的字符串   如果直接传数组那样传过来的参数名是ids[]
            "type": "POST",
            "dataType": "JSON",
            "success": function (data) {
                //删除成功
                if (data.status == 200) {

                }
            },
            "error": function (data) {
                console.log("出错了" + data);
            }
        });

    }


    //批量删除
    var deleteMulti = function (url) {
        //定义一个存放选中id的数组
        var checkedInput = new Array();
        //遍历全部的选择框，选出被选中的
        check_box.each(function () {
            var _id = $(this).attr('id');
            if (_id != null && $(this).is(':checked'))
                checkedInput.push(_id);
        });

        //如果没有选择数据弹出提示
        if (checkedInput.length === 0) {
            $("#my-modal-message").html("请至少选择一项数据");
            // 绑定确定按钮点击消失事件
            $('.modal-footer,.my-btn-concern').bind('click', function () {
                $("#modal-default").modal("hide");
            })
        } else {
            $("#my-modal-message").html("您确定删除数据吗");
            //绑定确认按钮点击触发删除函数事件
            $('.modal-footer,.my-btn-concern').bind('click', function () {
                del(url, {"ids": checkedInput});//如果是toString方法    前端传到后端的是一个以逗号分割的字符串   如果直接传数组那样传过来的参数名是ids[]
                checkedInput.length = 0;
            });
        }
        $('#modal-default').modal("show");
    };

    /**
     * 删除单笔记录
     * @param url 删除链接
     * @param id 需要删除数据的 ID
     * @param layer
     * @param table
     * @param tableId
     * @param msg
     */
    var handlerDeleteSingle = function (url, id,layer,table,tableId, msg) {
        // 可选参数
        if (!msg) msg = "您确定删除数据项吗？";
        layer.confirm(msg, {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                "url": url,
                "data": {'id': id},
                "type": "POST",
                "dataType": "JSON",
                error: erryFunction,  //错误执行方法
                success: succFunction //成功执行方法
            });



            function erryFunction() {
                layer.msg("出错了");
            }

            function succFunction(result) {
                console.log(result);
                if (result.status === 200) {
                    parent.layer.msg("操作成功!", {icon: 6,time: 500}, function () {
                        layer.closeAll();
                        reloadTable(table,tableId);
                    });
                    //重新加载
                    // table.reload(tableId);

                } else {
                    layer.msg("操作失败", {icon: 5, time: 2000});
                }
            }

        });


    };

    /**
     * 执行重载  可以带参数  动态查询
     * @param table layui表格对象
     * @param tableId  表格的id   不需要#号   例如：‘demo’
     * @param params
     */
    var reloadTable = function (table, tableId, params) {
        var reloadObj;
        //没传参数params
        if (!params) {
            reloadObj = {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            }
        } else {
            reloadObj = {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: params
            }
        }
        //执行重载
        table.reload(tableId,reloadObj);

    };


    var saveForm = function (url, data,layer,listUrl) {
        console.log(data);
        var msg = "您确定保存修改吗？";
        layer.confirm(msg, {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                "url": url,
                "data": data,
                "type": "POST",
                "dataType": "JSON",
                beforeSend: LoadFunction, //加载执行方法
                error: erryFunction,  //错误执行方法
                success: succFunction //成功执行方法
            });

            function LoadFunction() {
                layer.msg('加载中', {
                    icon: 16
                    , shade: 0.01
                });
            }

            function erryFunction() {
                layer.msg("出错了");
            }

            function succFunction(result) {
                console.log(result);
                if (result.status == 200) {
                    parent.layer.msg(result.message, {icon: 6, time: 500}, function () {
                        if (listUrl!==undefined&&listUrl!=null)
                            parent.window.location=listUrl;
                    });

                    layer.closeAll();
                } else {
                    layer.msg(result.message, {icon: 5, time: 2000});
                }
            }

        });


    };


    /**
     * 展示用户信息详情
     * @param url
     */
    var showUserDetail = function (url) {
        $.ajax({
            "url": url,
            "type": "GET",
            "dataType": "HTML",
            "success": function (data) {

            }
        })
    };

    function formToJSON(f) {
        var fd = $(f).serializeArray();
        var d = {};
        $(fd).each(function() {
            if (d[this.name] !== undefined){
                if (!Array.isArray(d[this.name])) {
                    d[this.name] = [d[this.name]];
                }
                d[this.name].push(this.value);
            }else{
                d[this.name] = this.value;
            }
        });
        return d;
    }

    var handleZTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                dblClickExpand: true,
                showLine: true,
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam //自动会传递的数据，一般传递的是记录的id
            }
        };

        //初始化指定id的表格
        $.fn.zTree.init($("#tree"), setting);

        $(".my-contern").bind('click', function () {

            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodes = zTree.getSelectedNodes();
            //未选中
            if (nodes.length === 0) {
                alert("请先选择一个父类目");
            }
            //已选择，只能单选，所以获取第一个
            else {
                callback(nodes);
            }
        })

    };



    return {
        init: function () {
        },
        handleDeleteMulti: function (url) {
            deleteMulti(url);
        },
        handleDetail: function (url) {
            showUserDetail(url);
        },
        deleteSingle: function (url, id,layer,table,tableId,msg) {
            handlerDeleteSingle(url, id,layer,table,tableId,msg);
        },
        handleReloadTable:function (table,tableId,params) {
            reloadTable(table,tableId,params);
        },
        handleZTree:function (url,autoParam,callback) {
            handleZTree(url,autoParam,callback);
        },
        handleFormToJson:function (f) {
            formToJSON(f);
        },
        handleSaveForm:function (url, data,layer,listUrl){
            saveForm(url, data,layer,listUrl)
        }
    }
}();
$(function () {
    App.init();

});