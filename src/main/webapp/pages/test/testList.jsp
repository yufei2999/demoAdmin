<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>test</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/cupertino/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>

<div style="width:1200px;padding:0px;">
    <form id="queryForm" name="queryForm" action="${ctx}/test/queryTestListAjax" method="post" style="display: inline;">
        <input type="hidden" id="pageNumber" name="pageNumber" value="1"/>
        <input type="hidden" id="pageSize" name="pageSize" value="10"/>
        <input type="hidden" id="order" name="order" value="" />
        <input type="hidden" id="sort" name="sort" value="" />

        <div id="ts">
            <div>
                <table style="font-size: 12px">
                    <tr>
                        <td>testId：</td>
                        <td>
                            <input class="easyui-textbox" id="testId" name="testId">
                        </td>
                        <td>createTime：</td>
                        <td>
                            <input class="easyui-datebox" id="createTimeBegin" name="createTimeBegin">
                            <input class="easyui-datebox" id="createTimeEnd" name="createTimeEnd">
                        </td>
                    </tr>
                </table>
            </div>
            <div style="padding:2px 5px;">
                <a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryList()">查询</a>
            </div>
        </div>
        <br>

        <div>
            <table id="dataGrid"></table>
        </div>
    </form>
</div>

</body>

<script type="text/javascript">

var option = {
    title: 'test',
    iconCls: 'icon-save',
    width: 1200,
    height: 800,
    nowrap: true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
    fitColumns: false,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
    resizeHandle: "right", //调整列的位置，可用的值有：'left'、'right'、'both'。当设置为 'right' 时，用户可通过拖拽列头部的右边缘来调整列。该属性自版本 1.3.2 起可用。
    autoRowHeight: false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
    striped: true,//设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
    collapsible: true,
    url: "${ctx}/test/queryTestListAjax",//从远程站点请求数据的 URL
    loadMsg: "正在努力加载数据...",
    remoteSort: false,
    idField: 'testId',//指示哪个字段是标识字段。
    singleSelect: false,//设置为 true，则只允许选中一行。
    checkOnSelect: true,//如果设置为 true，当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。该属性自版本 1.3 起可用。
    multiSort: false,//定义是否启用多列排序。该属性自版本 1.3.4 起可用。
    remoteSort: true,//定义是否从服务器排序数据。
    showHeader: true,//定义是否显示行的头部。
    showFooter: true,//定义是否显示行的底部。
    scrollbarSize: 18,//滚动条宽度（当滚动条是垂直的时候）或者滚动条的高度（当滚动条是水平的时候）。
    columns: [[
        {field: 'ck', checkbox: true}
        , {field: 'testId', title: 'testId', width: 120, sortable: false}
        , {field: 'content', title: 'content', width: 120, sortable: false}
        , {field: 'createTimeString', title: 'createTime', width: 120, sortable: false}
    ]],
    pagination: true,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏
    pageList: [10, 20, 50, 100, 200],
    rownumbers: true,//设置为 true，则显示带有行号的列。
    toolbar: "#ts",
    onSortColumn:function(sort,order){
        $("#sort").val(sort);
        $("#order").val(order);
    }

};

$(function () {
    //设置form为ajax提交
    $('#queryForm').form({
        success: function (data) {
            data = eval('(' + data + ')');
            $('#dataGrid').datagrid("loadData", data);
        }
    });
    //设置datagrid
    $('#dataGrid').datagrid(option);
    //设置底部的上一页和下一页
    var p = $('#dataGrid').datagrid('getPager');
    if (p) {
        $(p).pagination({
            onBeforeRefresh: function () {
            }
        });
        $(p).pagination({
            onSelectPage: function (pageNumber, pageSize) {
                var queryParams = $('#dataGrid').datagrid('options').queryParams;
                queryParams.pageNumber=pageNumber;
                queryParams.pageSize=pageSize;

                $("input[name='pageNumber']").val(pageNumber);
                $("input[name='pageSize']").val(pageSize);
                $('#queryForm').submit();
            }
        });
    }
});

function queryList() {
    $("#queryForm").submit();
}

</script>

</html>

    