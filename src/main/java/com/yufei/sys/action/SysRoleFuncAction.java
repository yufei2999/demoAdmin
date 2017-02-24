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
@RequestMapping(value = "/sysRoleFunc")
public class SysRoleFuncAction {

    private static final Log log = LogFactory.getLog(SysRoleFuncAction.class);

    @Autowired
    private SysRoleFuncService sysRoleFuncService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/sys/SysRoleFunc/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(SysRoleFuncQuery query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<SysRoleFunc> page = sysRoleFuncService.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("SysRoleFunc列表查询异常", e);
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
        model.addAttribute("model", sysRoleFuncService.getById(id));
        return "/sys/SysRoleFunc/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/sys/SysRoleFunc/create";
    }

    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(String roleId, String funcIds) {
        AjaxResult result = new AjaxResult();
        try {
            // 删除该角色下的所有权限，重新保存
            sysRoleFuncService.deleteFuncByRoleId(roleId);
            if (StringUtils.isNotBlank(funcIds)) {
                String[] funcIdArray = funcIds.split(",");
                SysRoleFunc sysRoleFunc = new SysRoleFunc();
                for (String item : funcIdArray) {
                    CommonUtil.initSetProperties(sysRoleFunc, DataTypeUtils.OPERATION_ADD);
                    sysRoleFunc.setRoleId(roleId);
                    sysRoleFunc.setFuncId(item);
                    sysRoleFunc.setId("");
                    sysRoleFuncService.save(sysRoleFunc);
                }
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysRoleFunc保存异常！");
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
        model.addAttribute("model", sysRoleFuncService.getById(id));
        return "/sys/SysRoleFunc/edit";
    }

    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(SysRoleFunc entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysRoleFunc sysRoleFunc = sysRoleFuncService.getById(entity.getId());
            CommonUtil.initSetProperties(sysRoleFunc, DataTypeUtils.OPERATION_UPDATE);
            sysRoleFuncService.update(sysRoleFunc);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysRoleFunc更新异常！");
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
                sysRoleFuncService.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("SysRoleFunc删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

}
