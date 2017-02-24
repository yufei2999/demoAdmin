<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title><%=SysFunc.TABLE_ALIAS%> 维护</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/cupertino/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function(){
            InitTreeData();
        }) ;

        //查询
        function InitTreeData() {
            $('#tt').tree({
                url: '${ctx }/sysFunc/getTree',
                checkbox: false,
                dataType: 'json',
                onContextMenu: function(e, node){
                    e.preventDefault();
                    $('#tt').tree('select', node.target);
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            });
        }

        //修改弹出框
        function editFuncT() {
            var node = $("#tt").tree("getSelected");
            $("#addBtn").hide();
            $("#editBtn").show();
// 	   			$("#tcode").show();
// 	            $("#rcode").show();
// 	            $("#funcCode").val(node.attributes.funcCode);
// 	   			$("#funcCode").attr("readonly","readonly");
            //表单赋值
            $("#funcName").val(node.attributes.funcName);
            $("#url").val(node.attributes.url);
//            $("#icon").val(node.attributes.icon);
            $("#sort").textbox('setValue',node.attributes.sort);
            $("#remark").val(node.attributes.remark);
            $("#dlg").dialog("open").dialog('setTitle', '编辑');
        }

        //新增弹出框
        function addFuncT() {
            $("#addBtn").show();
            $("#editBtn").hide();
            //清空input
            $("#funcName").val("");
// 	   			$("#funcCode").val("");
            $("#url").val("");
//            $("#icon").val("");
            $("#remark").val("");
            $("#sort").val("");
// 	            $("#tcode").hide();
// 	            $("#rcode").hide();
            $("#dlg").dialog("open").dialog('setTitle', '编辑');
        }

        //新增保存
        function saveFuncTree(){
            // 获取选中节点
            var selectNode = $("#tt").tree("getSelected");
            // 父节点id
            $("#parentId").val(selectNode.id);
            $('#fm').form('submit',{
                url:"${ctx}/sysFunc/addFuncTree",
                onSubmit:function(){
                    return $(this).form('enableValidation').form('validate');
                },success:function(data){
                    var result = $.parseJSON(data);
                    if(result.code=="0000"){
                        $.messager.alert('提示', "操作成功！", 'info',function(){
                            var t = $('#tt');
                            var node = t.tree('getSelected');
                            // 父节点刷新整棵树
                            if(selectNode.attributes.parentId==0){
                                $("#tt").tree("reload");
                            }else{
                                // 父节点
                                var parentId = $("#tt").tree('getParent',selectNode.target);
                                $('#tt').tree('reload',parentId.target);
                                d_close();
                            }
// 	   							t.tree('append', {
// 									parent: (node?node.target:null),
// 									data: result.data
// 								});
                        });
                    }else{
                        $.messager.alert('提示', result.message, 'info');
                    }
                    d_close();
                }
            });
        }

        //修改
        function editFuncTree(){
            var selectNode = $("#tt").tree("getSelected");
            $("#id").val(selectNode.id);
            // 根节点
            if(selectNode.attributes.parentId==0){
                // 父节点id
                $("#parentId").val(selectNode.attributes.parentId);
            }else{
                // 父节点
                var parentId = $("#tt").tree('getParent',selectNode.target);
                // 父节点id
                $("#parentId").val(parentId.id);
            }
            $('#fm').form('submit',{
                url:"${ctx}/sysFunc/editFuncTree",
                onSubmit:function(){
                    return $(this).form('enableValidation').form('validate');
                },success:function(data){
                    var result = $.parseJSON(data);
                    if(result.code=="0000"){
                        $.messager.alert('提示', "操作成功！", 'info',function(){
                            // 修改根节点刷新整棵树
                            if(selectNode.attributes.parentId==0){
                                $('#tt').tree('reload');
                                d_close();
                            }else{
                                // 父节点
                                var parentId = $("#tt").tree('getParent',selectNode.target);
                                $('#tt').tree('reload',parentId.target);
                                d_close();
                            }
                        });
                    }else{
                        $.messager.alert('提示', result.message, 'info');
                    }
                }
            });
        }

        function d_close(){
            $('#dlg').dialog('close');
        }

        //删除
        function removeit(){
            removeTree();
        }

        function removeTree(){
            var selectNode = $("#tt").tree("getSelected");
            var msg = "";
            $.post("${ctx}/sysFunc/getTree", {"id":selectNode.id},
                    function(data){
                        if(data==""){
                            msg = "确定要删除吗?";
                        }else{
                            var result = $.parseJSON(data);
                            msg = "您选择的节点下有子节点,将会把子节点也删除,确定删除吗?";
                        }
                        $.messager.confirm('确认', msg ,function(result){
                            if (result){
                                $.post("${ctx}/sysFunc/deleteTree", {"id":selectNode.id},
                                        function(data){
                                            var result = $.parseJSON(data);
                                            if(result.code=="0000"){
                                                $.messager.alert('提示', "操作成功！", 'info',function(){
                                                    // 父节点
                                                    var parentId = $("#tt").tree('getParent',selectNode.target);
                                                    $('#tt').tree('reload',parentId.target);
                                                });
                                            }else{
                                                $.messager.alert('提示', result.message, 'info');
                                            }
                                        }
                                );
                            }
                        });
                    }
            );
        }

    </script>
</head>

<body>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="权限" style="width:100%;height: 780px;">
    <ul id="tt" class="easyui-tree" data-options="method: 'get',animate: true">
    </ul>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="addFuncT()" data-options="iconCls:'icon-add'">新增</div>
    <div onclick="editFuncT()" data-options="iconCls:'icon-edit'">修改</div>
    <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
    <div class="menu-sep"></div>
    <!-- 				<div onclick="expand()">Expand</div> -->
    <!-- 				<div onclick="collapse()">Collapse</div> -->
</div>
<div id="w" style="padding: 5px">
    <table id="userPostDataGrid"></table>
</div>
<div id="dlg" class="easyui-dialog"  style="width: 400px; height: 240px; padding: 10px 20px;"   buttons="#dlg-buttons" data-options="modal:true,closed:true">
    <form id="fm"  action=""  method="post" >
        <input type="hidden" id="id" name="id" />
        <input type="hidden" id="parentId" name="parentId" />
        <table>
            <tr>
                <td>权限名称</td>
                <td><input id="funcName" name="funcName" value="" class="easyui-validatebox" required="true" /></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>权限url</td>
                <td><input id="url" name="url" class="easyui-validatebox" required="false" /></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input id="sort" name="sort" class="easyui-numberbox"   required="false" /></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><input id="remark" name="remark" class="" required="false" /></td>
            </tr>
        </table>

        <div id="dlg-buttons">
            <a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" onclick="saveFuncTree()" iconcls="icon-save">保存</a>
            <a href="javascript:void(0)" id="editBtn" class="easyui-linkbutton" onclick="editFuncTree()" iconcls="icon-edit">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"   iconcls="icon-cancel">取消</a>
        </div>
    </form>
</div>
</body>
</html>
