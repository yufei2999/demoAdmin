package com.yufei.sys.action;

import cn.org.rapid_framework.page.Page;
import com.yufei.common.AjaxResult;
import com.yufei.sys.model.SysRole;
import com.yufei.sys.query.SysRoleQuery;
import com.yufei.sys.service.SysRoleService;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.DataTypeUtils;
import com.yufei.utils.JsonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleAction {

    private static final Log log = LogFactory.getLog(SysRoleAction.class);

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/sys/SysRole/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(SysRoleQuery query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<SysRole> page = sysRoleService.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("SysRole列表查询异常", e);
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
        model.addAttribute("model", sysRoleService.getById(id));
        return "/sys/SysRole/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/sys/SysRole/create";
    }

    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(SysRole entity) {
        AjaxResult result = new AjaxResult();
        try {
            CommonUtil.initSetProperties(entity, DataTypeUtils.OPERATION_ADD);
            sysRoleService.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysRole保存异常！");
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
        model.addAttribute("model", sysRoleService.getById(id));
        return "/sys/SysRole/edit";
    }

    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(SysRole entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysRole sysRole = sysRoleService.getById(entity.getId());
            CommonUtil.initSetProperties(sysRole, DataTypeUtils.OPERATION_UPDATE);
            sysRole.setRoleName(entity.getRoleName());
            sysRole.setRemark(entity.getRemark());
            sysRoleService.update(sysRole);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysRole更新异常！");
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
                sysRoleService.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("SysRole删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

}
