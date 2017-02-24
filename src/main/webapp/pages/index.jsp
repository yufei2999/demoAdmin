<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>login</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/themes/cupertino/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/themes/icon.css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/md5.js"></script>
<script>
	function submitForm() {

		var userName = $('#userName').val().trim();
		var password = $('#password').val();
		var identifyingCode = $('#identifyingCode').val().trim();
		
		if (!userName) {
			$.messager.alert('提示', '请输入用户名！', 'info');
			return false;
		}
		if (!password) {
			$.messager.alert('提示', '请输入密码！', 'info');
			return false;
		}
		if (!identifyingCode) {
			$.messager.alert('提示', '请输入验证吗！', 'info');
			return false;
		}

		password = hex_md5(password).toUpperCase();

		var url = '${ctx}/login/login';
		var params = {
			userName : userName,
			password : password,
			identifyingCode : identifyingCode
		}
		$.post(url, params, function(data) {
			if (data.code == '0000') {
				$('#password').val(password);
				window.location.href = '${ctx}/main';
			} else {
				$.messager.alert('提示', data.message, 'info');
				flushRandom();
			}
		}, 'json');
	}

	function flushRandom() {
		$('#identifyingCode').val('');
		$('#radomImg').attr('src', '${ctx}/servlet/identifyingCode.jpg?rand=' + Math.random() + '&num=');
	}
</script>
</head>

<body>
	<div id="loginWin" class="easyui-window" title="登录" style="width: 400px; height: 250px; padding: 5px;" minimizable="false" maximizable="false"
		resizable="false" collapsible="false" closable="false" draggable="false">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 12px; background: #fff; border: 1px solid #ccc;">
				<form id="fm" method="post" onKeypress="if (event.keyCode == 13){submitForm();}">
					<div style="padding: 5px 0;">
						<label for="userName">用户名:</label> <input type="text" name="userName" id="userName" style="width: 200px; height: 30px;"/>
					</div>
					<div style="padding: 5px 0;">
						<label for="password">　密码:</label> <input type="password" name="password" id="password" style="width: 200px; height: 30px;"/>
					</div>
					<div style="padding: 5px 0;">
						<label for="identifyingCode">验证码:</label> <input class="icon-y" type="text" name="identifyingCode" id="identifyingCode"
							style="width: 120px; height: 30px;" /> <img onclick="flushRandom();" src="${ctx}/servlet/identifyingCode.jpg?rand=Math.random()&num="
							id="radomImg" style="position: absolute;" />
					</div>
				</form>
			</div>
			<div region="south" border="false" style="text-align: right; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="submitForm()">登录</a> <a class="easyui-linkbutton"
					iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#fm').form('clear');">重置</a>
			</div>
		</div>
	</div>
</body>
</html>
