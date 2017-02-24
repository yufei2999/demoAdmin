package com.yufei.sys.action;

import cn.org.rapid_framework.page.Page;
import com.yufei.sys.model.*;
import com.yufei.sys.service.*;
import com.yufei.sys.query.*;
import com.yufei.common.AjaxResult;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.DataTypeUtils;
import com.yufei.utils.JsonUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "/sysUserRole")
public class SysUserRoleAction {

    private static final Log log = LogFactory.getLog(SysUserRoleAction.class);

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/sys/SysUserRole/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(SysUserRoleQuery query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<SysUserRole> page = sysUserRoleService.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("SysUserRole列表查询异常", e);
        }
        return JsonUtil.toJSONString(map);
    }

    /**
     * 查看对象
     *
     * @return
     */
    @RequestMapping(value = "/show")
    public String show(Model model, String id) {
        model.addAttribute("model", sysUserRoleService.getById(id));
        return "/sys/SysUserRole/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/sys/SysUserRole/create";
    }

    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(SysUserRole entity) {
        AjaxResult result = new AjaxResult();
        try {
            CommonUtil.initSetProperties(entity, DataTypeUtils.OPERATION_ADD);
            sysUserRoleService.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysUserRole保存异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 进入更新页面
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, String id) {
        model.addAttribute("model", sysUserRoleService.getById(id));
        return "/sys/SysUserRole/edit";
    }

    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(SysUserRole entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysUserRole sysUserRole = sysUserRoleService.getById(entity.getId());
            CommonUtil.initSetProperties(sysUserRole, DataTypeUtils.OPERATION_UPDATE);
            sysUserRoleService.update(sysUserRole);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysUserRole更新异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        try {
            String[] ids = request.getParameterValues("ids");
            if (ArrayUtils.isNotEmpty(ids)) {
                sysUserRoleService.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("SysUserRole删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /******************************************* 以下为新增加的方法 ***************************************************/

    /**
     * 指定用户已分配的角色列表
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findUserRole")
    public String findUserRole(String userId) {
        AjaxResult result = new AjaxResult();
        try {
            SysUserRoleQuery query = new SysUserRoleQuery();
            query.setUserId(userId);
            SysUserRole sysUserRole = sysUserRoleService.getObject(query);
            if (null != sysUserRole) {
                result.setCode(AjaxResult.RESULT_CODE_0000);
                result.setData(sysUserRole.getRoleId());
            }
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            }
            log.error("get user role error", e);
        }
        return JsonUtil.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateUserRole")
    public String saveOrUpdateUserRole(String roleids, String userId) {
        AjaxResult result = new AjaxResult();
        try {
            if (StringUtils.isNotBlank(roleids)) {
                SysUserRoleQuery query = new SysUserRoleQuery();
                query.setUserId(userId);
                SysUserRole sysUserRole = sysUserRoleService.getObject(query);
                if (sysUserRole == null) {
                    sysUserRole = new SysUserRole();
                    CommonUtil.initSetProperties(sysUserRole, DataTypeUtils.OPERATION_ADD);
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(roleids);
                    sysUserRoleService.save(sysUserRole);
                } else {
                    sysUserRole.setRoleId(roleids);
                    CommonUtil.initSetProperties(sysUserRole, DataTypeUtils.OPERATION_UPDATE);
                    sysUserRoleService.update(sysUserRole);
                }
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("UserRole保存异常,请联系管理员！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

}
