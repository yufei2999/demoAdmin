<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<title><%=SysUser.TABLE_ALIAS%> 维护</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/cupertino/easyui.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
<div style="width:1200px;padding:0px;">
<form id="queryForm" name="queryForm" action="${ctx}/sysUser/listAjax" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="1"/>
	<input type="hidden" id="pageSize" name="pageSize" value="10" />
	<input type="hidden" id="order" name="order" value="" />
	<input type="hidden" id="sort" name="sort" value="" />
	<div id="ts">
		<div>
			<table style="font-size: 12px">
				<tr>
					<td><%=SysUser.ALIAS_USER_NAME%></td>
					<td>
						<input class="easyui-textbox" id="userName" name="userName"/>
                    </td>
					<td><%=SysUser.ALIAS_SHOW_NAME%></td>
					<td>
						<input class="easyui-textbox" id="showName" name="showName"/>
                    </td>
				</tr>
				<tr>
					<td><%=SysUser.ALIAS_MOBILE%></td>
					<td>
						<input class="easyui-textbox" id="mobile" name="mobile"/>
                    </td>
					<td>状态</td>
					<td><select class="easyui-combobox" id="status" name="status" style="width: 160px">
						<option value="" selected="selected">空</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select></td>
					<td><%=SysUser.ALIAS_LAST_LOGIN_IP%></td>
					<td>
						<input class="easyui-textbox" id="lastLoginIp" name="lastLoginIp"/>
                    </td>
				</tr>
			</table>
		</div>
		<div style="padding:2px 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryList()">查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="show()">查看</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="del()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="match()">匹配角色</a>
		</div>
	</div>
	<div>
		<table id="userDataGrid"></table>
	</div>
</form>

	<div>
		<div id="dlg" class="easyui-dialog"  style="width: 400px; height: 280px; "  buttons="#dlg-buttons" data-options="modal:true,closed:true">
			<form id="fm"  action="${ctx}/sysRole/listAjax" method="post" >
				<div id="roletool">
					<div>
						<table style="font-size: 12px">
							<tr>
								<td>角色名</td>
								<td><input class="easyui-textbox" id="roleName" name="roleName" /></td>
							</tr>
						</table>
					</div>
					<div style="padding:2px 5px;">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="findRole()">查询</a>
					</div>
				</div>
				<table id="role"></table>
				<div id="dlg-buttons">
					<a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" onclick="saveOrUpdateUserRole();" iconcls="icon-save">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"   iconcls="icon-cancel">取消</a>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>

<script type="text/javascript">

    function queryList() {
        $("#queryForm").submit();
    }
    function show() {
        var selected = $('#userDataGrid').datagrid('getSelected');
        if (selected) {
            location.href = "${ctx}/sysUser/show?id=" + selected.id;
        }
    }
    function add() {
        location.href = "${ctx}/sysUser/create";
    }
    function edit() {
        var selected = $('#userDataGrid').datagrid('getSelected');
        if (selected) {
            location.href = "${ctx}/sysUser/edit?id="
                    + selected.id;
        }
    }
    function del() {
        $.messager.confirm('确认', "确定要删除吗？", v_deleteItems);
    }

    /**用于回调*/
    function v_deleteItems(result) {
        if (result) {
            var rows = $('#userDataGrid').datagrid('getSelections');
            var ids = [];
            if (rows != null && rows.length > 0) {
                $.each(rows, function (i, n) {
                    ids.push(n.id)
                });
                $.ajax({
                    url: "${ctx}/sysUser/delete",
                    type: "get",
                    data: {"ids": ids},
                    traditional: true,
                    success: function (data) {
                        var result = $.parseJSON(data);
                        if (result.code == "0000") {
                            $.messager.alert('提示', "操作成功！", 'info', function () {
                                $('#queryForm').submit();
                            });
                        } else {
                            $.messager.alert('提示', result.message, 'info');
                        }
                    }
                });
            } else {
                $.message.alert("请选择后进行删除操作！", "warning");
            }
        }
    }

    // 用户列表
    var option = {
        title: '<%=SysUser.TABLE_ALIAS%>',
        iconCls: 'icon-save',
        width: 1200,
        height: 800,
        nowrap: true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
        fitColumns: false,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
        resizeHandle: "right", //调整列的位置，可用的值有：'left'、'right'、'both'。当设置为 'right' 时，用户可通过拖拽列头部的右边缘来调整列。该属性自版本 1.3.2 起可用。
        autoRowHeight: false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
        striped: true,//设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
        collapsible: true,
        url: "${ctx}/sysUser/listAjax",//从远程站点请求数据的 URL
        loadMsg: "正在努力加载数据...",
        remoteSort: false,
        idField: 'id',//指示哪个字段是标识字段。
        singleSelect: false,//设置为 true，则只允许选中一行。
        checkOnSelect: true,//如果设置为 true，当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。该属性自版本 1.3 起可用。
        multiSort: false,//定义是否启用多列排序。该属性自版本 1.3.4 起可用。
        remoteSort: true,//定义是否从服务器排序数据。
        showHeader: true,//定义是否显示行的头部。
        showFooter: true,//定义是否显示行的底部。
        scrollbarSize: 18,//滚动条宽度（当滚动条是垂直的时候）或者滚动条的高度（当滚动条是水平的时候）。
        columns: [[
            {field: 'ck', checkbox: true}
            , {field: 'userName', title: '<%=SysUser.ALIAS_USER_NAME%>', width: 120, sortable: true}
            , {field: 'showName', title: '<%=SysUser.ALIAS_SHOW_NAME%>', width: 120, sortable: true}
            , {field: 'mobile', title: '<%=SysUser.ALIAS_MOBILE%>', width: 120, sortable: true}
            , {
                field: 'status',
                title: '<%=SysUser.ALIAS_STATUS%>',
                width: 120,
                sortable: true,
                formatter: function (value) {
                    if (value == 1) {
                        return '启用'
                    } else if (value == 0) {
                        return '禁用'
                    }
                }
            }
            , {field: 'lastLoginIp', title: '<%=SysUser.ALIAS_LAST_LOGIN_IP%>', width: 120, sortable: true}
            , {field: 'lastLoginTimeString', title: '<%=SysUser.ALIAS_LAST_LOGIN_TIME%>', width: 120, sortable: true}
            , {field: 'remark', title: '<%=SysUser.ALIAS_REMARK%>', width: 120, sortable: true}
            , {field: 'createTimeString', title: '<%=SysUser.ALIAS_CREATE_TIME%>', width: 120, sortable: true}
        ]],
        pagination: true,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏
        pageList: [10, 20, 50, 100, 200],
        rownumbers: true,//设置为 true，则显示带有行号的列。
        toolbar: "#ts",
        onSortColumn: function (sort, order) {
            $("#sort").val(sort);
            $("#order").val(order);
        }
    };

    // 角色列表
    var optionRole = {
        title: '<%=SysRole.TABLE_ALIAS%>',
        iconCls: 'icon-save',
        width: 350,
        height: 500,
        nowrap: true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
        fitColumns: false,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
        resizeHandle: "right", //调整列的位置，可用的值有：'left'、'right'、'both'。当设置为 'right' 时，用户可通过拖拽列头部的右边缘来调整列。该属性自版本 1.3.2 起可用。
        autoRowHeight: false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
        striped: true,//设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
        collapsible: true,
        url: "${ctx}/sysRole/listAjax",//从远程站点请求数据的 URL
        loadMsg: "正在努力加载数据...",
        remoteSort: false,
        idField: 'id',//指示哪个字段是标识字段。
        singleSelect: false,//设置为 true，则只允许选中一行。
        checkOnSelect: true,//如果设置为 true，当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。该属性自版本 1.3 起可用。
        multiSort: false,//定义是否启用多列排序。该属性自版本 1.3.4 起可用。
        remoteSort: true,//定义是否从服务器排序数据。
        showHeader: true,//定义是否显示行的头部。
        showFooter: true,//定义是否显示行的底部。
        scrollbarSize: 18,//滚动条宽度（当滚动条是垂直的时候）或者滚动条的高度（当滚动条是水平的时候）。
        columns: [[
            {field: 'ck', checkbox: true}
            , {field: 'id', hidden: true}
            , {field: 'roleName', title: '<%=SysRole.ALIAS_ROLE_NAME%>', width: 120}
            , {field: 'remark', title: '<%=SysRole.ALIAS_REMARK%>', width: 120}
        ]],
        pagination: true,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏
        pageList: [10, 20, 50, 100, 200],
        rownumbers: true,//设置为 true，则显示带有行号的列。
        toolbar: "#roletool"
    };

    //重要$(document).ready(handler)
    $(function () {
        //设置form为ajax提交
        $('#queryForm').form({
            success: function (data) {
                data = eval('(' + data + ')');
                $('#userDataGrid').datagrid("loadData", data);
            }
        });
        //设置datagrid
        $('#userDataGrid').datagrid(option);
        //设置底部的上一页和下一页
        var p = $('#userDataGrid').datagrid('getPager');
        if (p) {
            $(p).pagination('refresh');
            $(p).pagination({
                onSelectPage: function (pageNumber, pageSize) {
                    $("input[name='pageNumber']").val(pageNumber);
                    $("input[name='pageSize']").val(pageSize);
                    $('#queryForm').submit();
                }
            });
        }

        $('#dlg').dialog({
            title: '选择角色',
            left: 500,
            top: 0,
            width: 350,
            height: 550,
            closed: true,
            cache: false,
            modal: true,
        });
        $('#role').datagrid(optionRole);
    }); //end $(document).ready(handler)

    //以下这些方法放在这几是为了提供编写javascript参考用，可根据实际情况删除
    function resize() {
        $('#userDataGrid').datagrid('resize', {
            width: 1100,
            height: 400
        });
    }
    function getSelected() {
        var selected = $('#userDataGrid').datagrid('getSelected');
        if (selected) {
            alert(selected.id);
        }
    }
    function getSelections() {
        var ids = [];
        var rows = $('#userDataGrid').datagrid('getSelections');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i].id);
        }
        alert(ids.join(':'));
    }
    function clearSelections() {
        $('#userDataGrid').datagrid('clearSelections');
    }
    function selectRow() {
        $('#userDataGrid').datagrid('selectRow', 1); //行号从0开始
    }
    function selectRecord() {
        $('#userDataGrid').datagrid('selectRecord', '1'); //'1'是id值
    }
    function unselectRow() {
        $('#userDataGrid').datagrid('unselectRow', 1);//行号从0开始
    }

    // 角色列表
    function findRole() {
        var roleName = $('#roleName').val()
        var url = "${ctx}/sysRole/listAjax";
        if (roleName != "") {
            url += "?roleName=" + roleName;
        }
        $.ajax({
            url: url,
            type: "json",
            success: function (data) {
                data = eval('(' + data + ')');
                $('#role').datagrid("loadData", data);
            }
        });
    }

    // 匹配角色
    function match() {
        var selected = $('#userDataGrid').datagrid('getSelected');
        if (selected) {
            $('#dlg').dialog({
                closed: false
            });
            $('#role').datagrid("unselectAll");
            $.ajax({
                url: "${ctx}/sysUserRole/findUserRole",
                data: {
                    userId: selected.id
                },
                success: function (data) {
                    var result = $.parseJSON(data);
                    if (result.code == "0000") {
                        var arr = result.data.split(",");
                        var roles = $('#role').datagrid('getRows');
                        if (arr.length > 0 && roles.length > 0) {
                            for (var i = 0; i < arr.length; i++) {
                                for (var j = 0; j < roles.length; j++) {
                                    var row = $('#role').datagrid('getData').rows[j];
                                    if (arr[i] == row.id) {
                                        $('#role').datagrid('selectRow', j);
                                    }
                                }
                            }
                        }
                    }
                }
            });

            var p = $('#role').datagrid('getPager');
            if (p) {
                $(p).pagination({
                    onBeforeRefresh: function () {
                        alert('before refresh');
                    }
                });
                $(p).pagination({
                    onSelectPage: function (pageNumber, pageSize) {
                        var queryParams = $('#role').datagrid('options').queryParams;
                        queryParams.pageNumber = pageNumber;
                        queryParams.pageSize = pageSize;
                        $("input[name='pageNumber']").val(pageNumber);
                        $("input[name='pageSize']").val(pageSize);
                        var roleName = $('#roleName').val();
                        var url = "${ctx}/sysRole/listAjax";
                        if (roleName != "") {
                            url += "?roleName=" + roleName + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize;
                        } else {
                            url += "?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
                        }
                        $.ajax({
                            url: url,
                            type: "json",
                            success: function (data) {
                                data = eval('(' + data + ')');
                                $('#role').datagrid("loadData", data);
                            }

                        });
                    }
                });
            }
        }
    }

    // 保存角色
    function saveOrUpdateUserRole() {
        var rows = $('#role').datagrid('getSelections');
        var ids = [];
        var userId = $('#userDataGrid').datagrid('getSelected').id;
        if (rows != null && rows.length > 0) {
            $.each(rows, function (i, n) {
                ids.push(n.id)
            });
            var roleids = ids.join(',');
            var url = "${ctx}/sysUserRole/saveOrUpdateUserRole"
            $.ajax({
                url: url,
                data: {
                    "roleids": roleids,
                    "userId": userId,
                },
                success: function (data) {
                    var result = $.parseJSON(data);
                    if (result.code == "0000") {
                        $.messager.alert('提示', "操作成功！", 'info', function () {
                            location.href = "${ctx}/sysUser/list";
                        });
                    } else {
                        $.messager.alert('提示', result.message, 'info');
                    }
                }
            });
        }
    }

</script>
