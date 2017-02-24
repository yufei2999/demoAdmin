<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=SysRole.TABLE_ALIAS%>编辑</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/default/easyui.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/default.css">
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
	<script>
		function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(data){
					var result = $.parseJSON(data);
					if(result.code=="0000"){
						$.messager.alert('提示', "操作成功！", 'info',function(){
							window.location.href="${ctx}/sysRole/list";
						});
					}else{
						$.messager.alert('提示', result.message, 'info');
					}
				}
			});
		}
		function resetForm(){
			$('#ff').form('reset');
		}
		function goBack(){
			window.location='${ctx}/sysRole/list';
		}
	</script>
</head>

<body>
<h2><%=SysRole.TABLE_ALIAS%>编辑</h2>
<p>请填写完整信息并保存.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="信息编辑" style="width:600px">
	<div style="padding:10px 60px 20px 60px">
		<form id="ff" action="${ctx}/sysRole/update" method="post" class="easyui-form" data-options="novalidate:true">
			<input type="hidden" id="id" name="id" value="${model.id}"/>
		<table cellpadding="5" style="font-size: 12px">
	<!-- ONGL access static field: @package.class@field or @vs@field -->
			<tr>
				<td>
					<%=SysRole.ALIAS_ROLE_NAME%>:
				</td>
				<td>
					<input id="roleName" name="roleName" value="${model.roleName}"
						   type="text"  class="easyui-textbox"  data-options="prompt:'请输入<%=SysRole.ALIAS_ROLE_NAME%>',required:true,validType:'length[0,50]'" />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysRole.ALIAS_REMARK%>:
				</td>
				<td>
					<input id="remark" name="remark" value="${model.remark}"
						   type="text"  class="easyui-textbox"  data-options="prompt:'请输入<%=SysRole.ALIAS_REMARK%>',required:true,validType:'length[0,50]'" />
				</td>
			</tr>
		</table>
		</form>
		<div style="text-align:center;padding:20px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="submitForm()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="resetForm()">重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="goBack()">返回</a>
		</div>
	</div>
</div>
</body>
</html>
