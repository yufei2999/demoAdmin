package com.yufei.sys.action;

import com.yufei.common.AjaxResult;
import com.yufei.common.easyui.bean.EasyUITreeNode;
import com.yufei.common.servlet.IdentifyingCodeServlet;
import com.yufei.sys.model.SysFunc;
import com.yufei.sys.model.SysUser;
import com.yufei.sys.model.result.UserInfo;
import com.yufei.sys.query.SysUserQuery;
import com.yufei.sys.service.SysFuncService;
import com.yufei.sys.service.SysUserService;
import com.yufei.utils.BeanUtil;
import com.yufei.utils.DataTypeUtils;
import com.yufei.utils.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by pc on 2016-09-28.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction {

    private static final Log log = LogFactory.getLog(LoginAction.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysFuncService sysFuncService;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {

        AjaxResult result = new AjaxResult();
        result.setCode(AjaxResult.RESULT_CODE_0001);

        if (request.getMethod().equalsIgnoreCase("GET")) {
            return DataTypeUtils.ILLEGAL_OPERATION;
        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String identifyingCode = request.getParameter("identifyingCode");
        if (StringUtils.isBlank(identifyingCode)) {
            result.setMessage("请输入验证码");
            return JsonUtil.toJSONString(result);
        }
        Object code = request.getSession().getAttribute(IdentifyingCodeServlet.LOGINRANDOMCODE);
        if (!StringUtils.equalsIgnoreCase(identifyingCode, code == null ? "" : code.toString())) {
            result.setMessage("验证码错误");
            return JsonUtil.toJSONString(result);
        }

        SysUserQuery query = new SysUserQuery();
        query.setUserName(userName);
        SysUser sysUser = sysUserService.getObject(query);
        if (sysUser == null || !StringUtils.equals(password, sysUser.getPassword())) {
            result.setMessage("登录失败，请联系管理员");
            return JsonUtil.toJSONString(result);
        }

        // 更新用户登录信息
        sysUser.setLastLoginIp(getRemoteHost(request));
        sysUser.setLastLoginTime(new Date());
        sysUserService.update(sysUser);

        UserInfo userInfo = new UserInfo();
        BeanUtil.copySimpleProperties(userInfo, sysUser);
        userInfo.setPassword("");
        userInfo.setFuncList(sysFuncService.getUserFuncList(sysUser.getId()));

        request.getSession().setAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY, userInfo);
        result.setCode(AjaxResult.RESULT_CODE_0000);
        return JsonUtil.toJSONString(result);

    }

    /**
     * 获取IP
     *
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMenuList")
    public String getMenuList() {
        return "[{\"menuId\":\"pid\",\"menuName\":\"系统管理\",\"menus\":[{\"menuId\":\"cid\",\"menuName\":\"测试\",\"url\":\"/test/queryTestList\"},{\"menuId\":\"cid1\",\"menuName\":\"用户管理\",\"url\":\"/sysUser/list\"},{\"menuId\":\"cid2\",\"menuName\":\"角色管理\",\"url\":\"/sysRole/list\"},{\"menuId\":\"cid3\",\"menuName\":\"权限管理\",\"url\":\"/sysFunc/listTree\"}]}]";
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> enumNames = session.getAttributeNames();
        while (enumNames.hasMoreElements()) {
            String key = enumNames.nextElement();
            session.removeAttribute(key);
        }
        return "index";
    }

    /**
     * 权限菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rightTreeAjax")
    public String rightTreeAjax(HttpServletRequest request) {

        // 权限树
        List<Map> treeDataList = new ArrayList<>();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY);
        if (userInfo == null) {
            log.info("userInfo is null");
            return JsonUtil.toJSONString(treeDataList);
        }

        List<SysFunc> funcList = userInfo.getFuncList();
        //根据父节点分组
        Map<Object, List<SysFunc>> funcMap = BeanUtil.groupByKeyString("parentId", funcList);

        if (MapUtils.isNotEmpty(funcMap) && funcMap.containsKey(EasyUITreeNode.DEFAULT_ROOT_ID)) {
            // 得到一级菜单的权限列表
            List<SysFunc> parentList = funcMap.get(EasyUITreeNode.DEFAULT_ROOT_ID);
            if (CollectionUtils.isNotEmpty(parentList)) {
                for (SysFunc parent : parentList) {
                    // 一级菜单的属性设置
                    Map<String, Object> parentMap = new HashMap<>();
                    parentMap.put("menuId", parent.getId());
                    parentMap.put("menuName", parent.getFuncName());
                    List<Map<String, Object>> childMapList = new ArrayList<>();
                    parentMap.put("menus", childMapList);
                    // 二级菜单的属性设置
                    // 得到二级菜单的权限列表
                    List<SysFunc> childList = funcMap.get(parent.getId());
                    if (CollectionUtils.isNotEmpty(childList)) {
                        for (SysFunc child : childList) {
                            Map<String, Object> childMap = new HashMap<>();
                            childMap.put("menuId", child.getId());
                            childMap.put("menuName", child.getFuncName());
                            childMap.put("url", child.getUrl());
                            childMapList.add(childMap);
                        }
                    }
                    treeDataList.add(parentMap);
                }
            }
        }
        return JsonUtil.toJSONString(treeDataList);
    }

}
