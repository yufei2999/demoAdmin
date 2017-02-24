<%@page import="com.yufei.sys.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><%=SysFunc.TABLE_ALIAS%>新增</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/static/css/default.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
    <script>
        function submitForm() {
            $('#ff').form('submit', {
                onSubmit: function () {
                    return $(this).form('enableValidation').form('validate');
                }, success: function (data) {
                    var result = $.parseJSON(data);
                    if (result.code == "0000") {
                        $.messager.alert('提示', "操作成功！", 'info', function () {
                            window.location.href = "${ctx}/sysFunc/list";
                        });
                    } else {
                        $.messager.alert('提示', result.message, 'info');
                    }
                }
            });
        }
        function clearForm() {
            $('#ff').form('clear');
        }
        function goBack() {
            window.location = '${ctx}/sysFunc/list';
        }
    </script>
</head>

<body>
<h2><%=SysFunc.TABLE_ALIAS%>新增</h2>
<p>请填写完整信息并保存.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="信息保存" style="width:600px">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" action="${ctx}/sysFunc/save" method="post" class="easyui-form" data-options="novalidate:true">
            <input type="hidden" id="id" name="id" value="${model.id}"/>
        <table cellpadding="5" style="font-size: 12px">
    <!-- ONGL access static field: @package.class@field or @vs@field -->
            <tr>
                <td>
                    <%=SysFunc.ALIAS_FUNC_NAME%>:
                </td>
                <td>
                    <input id="funcName"
                       name="funcName"
                       value="${model.funcName}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_FUNC_NAME%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_FUNC_CODE%>:
                </td>
                <td>
                    <input id="funcCode"
                       name="funcCode"
                       value="${model.funcCode}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_FUNC_CODE%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_URL%>:
                </td>
                <td>
                    <input id="url"
                       name="url"
                       value="${model.url}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_URL%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_PARENT_ID%>:
                </td>
                <td>
                    <input id="parentId"
                       name="parentId"
                       value="${model.parentId}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_PARENT_ID%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_PARENT_IDS%>:
                </td>
                <td>
                    <input id="parentIds"
                       name="parentIds"
                       value="${model.parentIds}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_PARENT_IDS%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_REMARK%>:
                </td>
                <td>
                    <input id="remark"
                       name="remark"
                       value="${model.remark}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_REMARK%>',validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_CREATOR%>:
                </td>
                <td>
                    <input id="creator"
                       name="creator"
                       value="${model.creator}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_CREATOR%>',required:true,validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_CREATE_TIME%>:
                </td>
                <td>
                    <input value="${model.createTimeString}"
                           id="createTimeString"
                           name="createTimeString"
                           type="text" class="easyui-datetimebox" editable="false"
                           data-options="prompt:'请输入<%=SysFunc.ALIAS_CREATE_TIME%>'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_LAST_MODIFIER%>:
                </td>
                <td>
                    <input id="lastModifier"
                       name="lastModifier"
                       value="${model.lastModifier}"
                       type="text" class="easyui-textbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_LAST_MODIFIER%>',validType:'length[0,50]'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_LAST_MODIFY_TIME%>:
                </td>
                <td>
                    <input value="${model.lastModifyTimeString}"
                           id="lastModifyTimeString"
                           name="lastModifyTimeString"
                           type="text" class="easyui-datetimebox" editable="false"
                           data-options="prompt:'请输入<%=SysFunc.ALIAS_LAST_MODIFY_TIME%>'"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%=SysFunc.ALIAS_DELETED%>:
                </td>
                <td>
                    <input id="deleted"
                       name="deleted"
                       value="${model.deleted}"
                       type="text" class="easyui-numberbox"
                       data-options="prompt:'请输入<%=SysFunc.ALIAS_DELETED%>',required:true,min:0,max:10000"/>
                </td>
            </tr>
        </table>
        </form>
        <div style="text-align:center;padding:20px">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px"
               onclick="submitForm()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">清空</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px"
               onclick="goBack()">返回</a>
        </div>
    </div>
</div>
</body>
</html>
