<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<title><%=SysRole.TABLE_ALIAS%> 维护</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/cupertino/easyui.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
<div style="width:1200px;padding:0px;">
<form id="queryForm" name="queryForm" action="${ctx}/sysRole/listAjax" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="1"/>
	<input type="hidden" id="pageSize" name="pageSize" value="10" />
	<input type="hidden" id="order" name="order" value="" />
	<input type="hidden" id="sort" name="sort" value="" />
	<div id="ts">
		<div>
			<table style="font-size: 12px">
				<tr>
					<td><%=SysRole.ALIAS_ROLE_NAME%></td>
					<td>
						<input class="easyui-textbox" id="roleName" name="roleName"/>
                    </td>
					<td><%=SysRole.ALIAS_REMARK%></td>
					<td>
						<input class="easyui-textbox" id="remark" name="remark"/>
                    </td>
				</tr>
				<tr>
					<td><%=SysRole.ALIAS_CREATE_TIME%></td>
					<td>
						<input class="easyui-datebox" value="" id="createTimeBegin" name="createTimeBegin"   />
						<input class="easyui-datebox" value="" id="createTimeEnd" name="createTimeEnd"   />
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
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" onclick="newRoleTree()">角色权限绑定</a>
		</div>
	</div>
	<div>
		<table id="userDataGrid"></table>
	</div>
</form>

	<div id="dlg" class="easyui-dialog"  style="width: 400px; height: 280px; "  buttons="#dlg-buttons" data-options="modal:true,closed:true">
		<form id="fm"  action=" "  method="post" >
			<input id="allCheckedRoleId" type="hidden">
			<input id="allCheckedFuncId" type="hidden">
			<ul id="tt" class="easyui-tree" >

			</ul>
			<div id="dlg-buttons">
				<a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" onclick="saveRoleFunc()" iconcls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"   iconcls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>

<script type="text/javascript">

	function queryList(){
 		$("#queryForm").submit();
	}
	function show(){
		var selected = $('#userDataGrid').datagrid('getSelected');
		if (selected){
			location.href="${ctx}/sysRole/show?id="+selected.id;
		}
	}
	function add(){
		location.href="${ctx}/sysRole/create";
	}
	function edit(){
		var selected = $('#userDataGrid').datagrid('getSelected');
		if (selected){
			location.href="${ctx}/sysRole/edit?id="
			+selected.id;
		}
	}
	// 删除当前行
	function deleteItem(id) {
		$.messager.confirm('确认', "确定要删除吗？" ,function(){
			var ids = [];
			ids.push(id);
			deleteOpt(ids);
		});
	}
	// 批量删除
	function del(){
		$.messager.confirm('确认', "确定要删除吗？" ,v_deleteItems);
	}
	/**用于回调*/
	function v_deleteItems(result){
		if (result){
			var rows = $('#userDataGrid').datagrid('getSelections');
			var ids = [];
			if(rows!=null&&rows.length>0){
				$.each(rows,function(i,n){ids.push(n.id)
				});
				deleteOpt(ids);
			}else{
				$.message.alert("请选择后进行删除操作！","warning");
			}
		}
	}
	function deleteOpt(ids) {
		$.ajax({
			url : "${ctx}/sysRole/delete",
			type : "get",
			data : {"ids":ids},
			traditional : true,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.code=="0000"){
					$.messager.alert('提示', "操作成功！", 'info',function(){
						$('#queryForm').submit();
					});
				}else{
					$.messager.alert('提示', result.message, 'info');
				}
			}
		});
	}
	var option = {
			title:'<%=SysRole.TABLE_ALIAS%>',
			iconCls:'icon-save',
			width:1200,
			height:800,
			nowrap: true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
			fitColumns:false,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
			resizeHandle:"right", //调整列的位置，可用的值有：'left'、'right'、'both'。当设置为 'right' 时，用户可通过拖拽列头部的右边缘来调整列。该属性自版本 1.3.2 起可用。
			autoRowHeight:false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
			striped: true,//设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
			collapsible:true,
			url: "${ctx}/sysRole/listAjax",//从远程站点请求数据的 URL
			loadMsg:"正在努力加载数据...",
			remoteSort: false,
			idField:'id',//指示哪个字段是标识字段。
			singleSelect:true,//设置为 true，则只允许选中一行。
			checkOnSelect:true,//如果设置为 true，当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。该属性自版本 1.3 起可用。
			multiSort:false,//定义是否启用多列排序。该属性自版本 1.3.4 起可用。
			remoteSort:true,//定义是否从服务器排序数据。	
			showHeader:true,//定义是否显示行的头部。
			showFooter:true,//定义是否显示行的底部。
			scrollbarSize:18,//滚动条宽度（当滚动条是垂直的时候）或者滚动条的高度（当滚动条是水平的时候）。
			columns:[[
				{field:'ck',checkbox:true}
					,{field:'id',title:'<%=SysRole.ALIAS_ID%>',width:120,sortable:true}
					,{field:'roleName',title:'<%=SysRole.ALIAS_ROLE_NAME%>',width:120,sortable:true}
					,{field:'remark',title:'<%=SysRole.ALIAS_REMARK%>',width:120,sortable:true}
					,{field:'creator',title:'<%=SysRole.ALIAS_CREATOR%>',width:120,sortable:true}
					,{field:'createTimeString',title:'<%=SysRole.ALIAS_CREATE_TIME%>',width:120,sortable:true}
					,{field:'lastModifier',title:'<%=SysRole.ALIAS_LAST_MODIFIER%>',width:120,sortable:true}
					,{field:'lastModifyTimeString',title:'<%=SysRole.ALIAS_LAST_MODIFY_TIME%>',width:120,sortable:true}
					,{field:'action',title:'操作',sortable:false,formatter:function(value,row,index){
						return '<a href="${ctx}/sysRole/show?id=' + row.id + '">查看</a>&nbsp;' +
							'<a href="${ctx}/sysRole/edit?id=' + row.id + '">修改</a>&nbsp;' +
							'<a href="javascript:void(0)" onclick="deleteItem(\'' + row.id + '\')">删除</a>';
					}}
			]],
			pagination:true,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏
			pageList:[10,20,50,100,200],
			rownumbers:true,//设置为 true，则显示带有行号的列。
			toolbar:"#ts",
			onSortColumn:function(sort,order){
				$("#sort").val(sort);
				$("#order").val(order);
			}
		}


	//重要$(document).ready(handler)
	$(function(){
		//设置form为ajax提交		
		$('#queryForm').form({
			success:function(data){
			data = eval('('+data+')');
			$('#userDataGrid').datagrid("loadData",data);
			}
		});
		//设置datagrid		
		$('#userDataGrid').datagrid(option);
		//设置底部的上一页和下一页
		var p = $('#userDataGrid').datagrid('getPager');
		if (p){
			$(p).pagination({
				onBeforeRefresh:function(){
					alert('before refresh');
				}
			});
			$(p).pagination({
				onSelectPage:function(pageNumber,pageSize) {
					$("input[name='pageNumber']").val(pageNumber);
					$("input[name='pageSize']").val(pageSize);
					$('#queryForm').submit();
				}
			});
		}
	}); //end $(document).ready(handler)
	//以下这些方法放在这几是为了提供编写javascript参考用，可根据实际情况删除
	function resize(){
		$('#userDataGrid').datagrid('resize', {
			width:1100,
			height:400
		});
	}
	function getSelected(){
		var selected = $('#userDataGrid').datagrid('getSelected');
		if (selected){
			alert(selected.id);
		}
	}
	function getSelections(){
		var ids = [];
		var rows = $('#userDataGrid').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		alert(ids.join(':'));
	}
	function clearSelections(){
		$('#userDataGrid').datagrid('clearSelections');
	}
	function selectRow(){
		$('#userDataGrid').datagrid('selectRow',1); //行号从0开始
	}
	function selectRecord(){
		$('#userDataGrid').datagrid('selectRecord','1'); //'1'是id值
	}
	function unselectRow(){
		$('#userDataGrid').datagrid('unselectRow',1);//行号从0开始
	}

	// 权限树
	function newRoleTree() {
		InitTreeData();
//		var node = $('#tt').tree('getSelected');
// 	    $("#dlg").dialog("open").dialog('setTitle', '权限绑定');
		$('#dlg').dialog({
			title: '权限绑定',
			left: 500,
			top: 0,
			width: 350,
			height: 500,
			closed: false,
			cache: false,
			modal: true,
		});
	}

	// 角色权限树
	function InitTreeData() {
		var allCheckedRoleId = "";
		$("input[name='id']").each(function (i, n) {
			if ($(this).is(':checked')) {
				allCheckedRoleId = $(this).val();
			}
		});
		var selected = $('#userDataGrid').datagrid('getSelected');

		$('#tt').tree({
			url: '${ctx }/sysFunc/getRoleFuncTree?roleId=' + selected.id,
			checkbox: true,
			animate: true,
			autoCheckParent: false,
			cascadeCheck: false,
			dataType: 'json',
			onContextMenu: function (e, node) {
				e.preventDefault();
				$('#tt').tree('select', node.target);
			}
		});
		//有权限默认选中
// 	    var roleIdlist = $("#roleFuncId").val();
// 	    var roots = $("#tt").tree("getChildren");
// 		if(roleIdlist!=""){
//	 	    var ids = roleIdlist.sqlit(",");
//	 	    $.each(ids,function(j){
//	 	    	$('#' + ids[j]).prop("checked", true);
//	 	    });
// 		}

	}

	//保存
	function saveRoleFunc() {
		var allCheckedRoleId = "";
		//获取所有选中的复选框
		$("input[name='id']").each(function (i, n) {
			if ($(this).is(':checked')) {
				allCheckedRoleId += $(this).val() + ",";
			}
		});

		var selected = $('#userDataGrid').datagrid('getSelected');

		$("#allCheckedRoleId").val(selected.id);

		if ($("#allCheckedRoleId").val() == "") {
			$.messager.alert('提示', "请选择角色！", 'info', function () {
				d_close();
			});
		} else {
			var nodes = $('#tt').tree('getChecked');
			var allCheckedFuncId = '';
			for (var i = 0; i < nodes.length; i++) {
				if (allCheckedFuncId != '') allCheckedFuncId += ',';
				allCheckedFuncId += nodes[i].id;
			}
			$("#allCheckedFuncId").val(allCheckedFuncId);
			$.post("${ctx}/sysRoleFunc/save", {
						roleId: $("#allCheckedRoleId").val(),
						funcIds: $("#allCheckedFuncId").val()
					},
					function (data) {
						var result = $.parseJSON(data);
						if (result.code == "0000") {
							$.messager.alert('提示', "操作成功！", 'info', function () {
								d_close();
							});
						} else {
							$.messager.alert('提示', result.message, 'info');
						}
					}
			);
		}
	}

	//关闭
	function d_close() {
		$('#dlg').dialog('close');
	}

</script>
