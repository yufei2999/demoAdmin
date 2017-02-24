<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>main</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/themes/cupertino/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/head.css"/>
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/locale/easyui-lang-zh_CN.js"></script>
    <script>
        var _menus = new Object();

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');
            var $txtOldPass = $('#txtOldPass');
            $("#oldEq").val("");
            if ($txtOldPass.val() == '') {
                msgShow('系统提示', '请输入原密码！', 'warning');
                return false;
            }
            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('${ctx}/sysUser/getPresentPassword',{"presentPassword": $txtOldPass.val()}, function(msg) {
//           	 $("#oldEq").val(msg);
                if(msg!="0"){
                    msgShow('系统提示', '原密码输入错误！', 'warning');
                    return false;
                }else{
                    $.post('${ctx}/sysUser/modifyPassword',{"newPassword": $newpass.val()}, function(msg) {
                        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                        $txtOldPass.val('');
                        $newpass.val('');
                        $rePass.val('');
                        $("#oldEq").val("");
                        closePwd();
                    });
                }
            });
        }

        // 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
        function msgShow(title, msgString, msgType) {
            $.messager.alert(title, msgString, msgType);
        }

        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        $(function () {
            $.post('${ctx}/login/rightTreeAjax', {},
                    function (data) {
                        _menus = JSON.parse(data);
                        tabClose();
                        tabCloseEven();

                        // 导航菜单绑定初始化
                        $("#wnav").accordion({
                            animate: false
                        });

                        addNav(_menus);
                        InitLeftMenu();
                        // 点击修改密码弹出层
                        $('#editpass').click(function () {
                            $('#txtNewPass').val('');
                            $('#txtRePass').val('');
                            $('#txtOldPass').val('');
                            $('#oldEq').val('');
                            $('#w').window('open');
                        });
                        // 修改密码确定按钮
                        $('#btnEp').click(function () {
                            serverLogin();
                        })
                        // 修改密码取消按钮
                        $('#btnCancel').click(function () {
                            closePwd();
                        })
                        //弹出新窗口的初始化，同时进行新窗口里树的初始化---zhanghao
                        $('#w').window({
                            onClose: function () {
                                $('#txtNewPass').val('');
                                $('#txtRePass').val('');
                                $('#txtOldPass').val('');
                                $('#oldEq').val('');
                            }
                        });
                        // 安全退出
                        $('#loginOut').click(function () {
                            $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {
                                if (r) {
                                    location.href = '${ctx}/login/exit';
                                }
                            });

                        });
                    }
            )
        });

        function tabClose() {
            /* 双击关闭TAB选项卡 */
            $('.tabs-inner').dblclick(function () {
                var subtitle = $(this).children('.tabs-closable').text();
                $('#tabs').tabs('close', subtitle);
            });
            /* 为选项卡绑定右键 */
            $('.tabs-inner').bind('contextmenu', function (e) {
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });

                var subtitle = $(this).children('.tabs-closable').text();

                $('#mm').data('currtab', subtitle);
                $('#tabs').tabs('select', subtitle);
                return false;
            });
        }
        // 绑定右键菜单事件
        function tabCloseEven() {
            // 刷新
            $('#mm-tabupdate').click(function () {
                var currTab = $('#tabs').tabs('getSelected');
                var url = $(currTab.panel('options').content).attr('src');
                $('#tabs').tabs('update', {
                    tab: currTab,
                    options: {
                        content: createFrame(url)
                    }
                });
            });
            // 关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $('#mm').data('currtab');
                $('#tabs').tabs('close', currtab_title);
            });
            // 全部关闭
            $('#mm-tabcloseall').click(function () {
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    $('#tabs').tabs('close', t);
                });
            });
            // 关闭除当前之外的TAB
            $('#mm-tabcloseother').click(function () {
                $('#mm-tabcloseright').click();
                $('#mm-tabcloseleft').click();
            });
            // 关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function () {
                var nextall = $('.tabs-selected').nextAll();
                if (nextall.length == 0) {
                    // msgShow('系统提示','后边没有啦~~','error');
                    alert('后边没有啦~~');
                    return false;
                }
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                return false;
            });
            // 关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                if (prevall.length == 0) {
                    alert('到头了，前边没有啦~~');
                    return false;
                }
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                return false;
            });

            // 退出
            $('#mm-exit').click(function () {
                $('#mm').menu('hide');
            });
        }

        function addNav(data) {
            if (data && data.length > 0) {
                $.each(data, function (i, sm) {
                    var menuList = '';
                    menuList += '<ul>';
                    $.each(sm.menus, function (j, o) {
                        menuList += '<li><div><a ref="' + o.menuId + '" href="#" rel="${ctx}'
                                + o.url + '" ><span class="icon icon-nav" >&nbsp;</span><span class="nav">' + o.menuName
                                + '</span></a></div></li> ';
                    });
                    menuList += '</ul>';

                    $('#wnav').accordion('add', {
                        title: sm.menuName,
                        content: menuList,
                        iconCls: 'icon  icon-nav'
                    });
                });

                var pp = $('#wnav').accordion('panels');
                var t = pp[0].panel('options').title;
                $('#wnav').accordion('select', t);
            }
        }

        // 初始化左侧
        function InitLeftMenu() {

            hoverMenuItem();

            $('#wnav li a').on('click', function () {
                var tabTitle = $(this).children('.nav').text();
                var url = $(this).attr('rel');
                var menuId = $(this).attr('ref');
                var icon = getIcon(menuId, icon);
                addTab(tabTitle, url, icon);
                $('#wnav li div').removeClass('selected');
                $(this).parent().addClass('selected');
            });

        }

        /**
         * 菜单项鼠标Hover
         */
        function hoverMenuItem() {
            $('.easyui-accordion').find('a').hover(function () {
                $(this).parent().addClass('hover');
            }, function () {
                $(this).parent().removeClass('hover');
            });
        }

        // 获取左侧导航的图标
        function getIcon(menuid) {
            var icon = 'icon ';
            $.each(_menus, function (j, o) {
                $.each(o.menus, function (k, m) {
                    if (m.menuid == menuid) {
                        icon += m.icon;
                        return false;
                    }
                });
            });
            return icon;
        }

        function addTab(subtitle, url, icon) {

            if (!$('#tabs').tabs('exists', subtitle)) {
                $('#tabs').tabs('add', {
                    title: subtitle,
                    content: createFrame(url),
                    closable: true,
                    icon: icon
                });
            } else {
                $('#tabs').tabs('select', subtitle);
                $('#mm-tabupdate').click();
            }
            tabClose();
        }

        function createFrame(url) {
            var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
            return s;
        }

    </script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="${ctx}/static/images/frame/noscript.gif" alt='抱歉，请开启脚本支持！'/>
    </div>
</noscript>

<div region="north" split="true" border="false" class="content" style="height:55px">
    <div class="head-cons">
        <div class="head-con">
            <%--<div class="head-left">
                <img src="${ctx}/static/images/logo.png"/>
            </div>--%>
            <div class="head-right">
                <span>Welcome&nbsp;<b> ${sessionScope.loginUser.userName}</b>！</span>
                <span><a id="editpass" href="javascript:;"><img src="${ctx}/static/images/lock.png"/>修改密码</a></span>
                <span><a id="loginOut" href="javascript:;"><img src="${ctx}/static/images/close.png"/>安全退出</a></span>
            </div>
        </div>
    </div>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">管理后台</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id='wnav' class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>
</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
        </div>
    </div>
</div>

<!--修改密码窗口-->
<div id="w" class="easyui-window" title="修改密码" closed="true" collapsible="false" minimizable="false" maximizable="false"
     icon="icon-save" style="width: 500px; height: 300px; padding: 5px; background: #fafafa;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>旧密码：</td>
                    <td>
                        <input id="txtOldPass" type="password" class="txt01"/>
                        <input id="oldEq" type="hidden" class="txt01"/>
                    </td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="password" class="txt01"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="password" class="txt01"/></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)"> 确定</a>
            <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>

</body>
</html>
