<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=SysUserRole.TABLE_ALIAS%>详情</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/default/easyui.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/default.css">
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
	<script>
		function goBack(){
			window.location='${ctx}/sysUserRole/list';
		}
	</script>
</head>

<body>
<h2><%=SysUserRole.TABLE_ALIAS%>详情</h2>
<p>以下是已填写的信息.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="信息详情" style="width:600px">
	<div style="padding:10px 60px 20px 60px">
			<input type="hidden" id="id" name="id" value="${model.id}"/>
		<table cellpadding="5" style="font-size: 12px">
	<!-- ONGL access static field: @package.class@field or @vs@field -->
			<tr>
				<td>
					<%=SysUserRole.ALIAS_USER_ID%>:
				</td>
				<td>
					<input  value="${model.userId}" id="userId" name="userId" type="text"  class="easyui-textbox"  readonly="readonly"  />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_ROLE_ID%>:
				</td>
				<td>
					<input  value="${model.roleId}" id="roleId" name="roleId" type="text"  class="easyui-textbox"  readonly="readonly"  />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_CREATOR%>:
				</td>
				<td>
					<input  value="${model.creator}" id="creator" name="creator" type="text"  class="easyui-textbox"  readonly="readonly"  />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_CREATE_TIME%>:
				</td>
				<td>
					<input value="${model.createTimeString}" id="createTimeString" name="createTimeString"
						   type="text" class="easyui-textbox" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_LAST_MODIFIER%>:
				</td>
				<td>
					<input  value="${model.lastModifier}" id="lastModifier" name="lastModifier" type="text"  class="easyui-textbox"  readonly="readonly"  />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_LAST_MODIFY_TIME%>:
				</td>
				<td>
					<input value="${model.lastModifyTimeString}" id="lastModifyTimeString" name="lastModifyTimeString"
						   type="text" class="easyui-textbox" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					<%=SysUserRole.ALIAS_DELETED%>:
				</td>
				<td>
					<input  value="${model.deleted}" id="deleted" name="deleted" type="text"  class="easyui-textbox"  readonly="readonly"  />
				</td>
			</tr>
		</table>
		<div style="text-align:center;padding:20px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="goBack()">返回</a>
		</div>
	</div>
</div>
</body>
</html>
