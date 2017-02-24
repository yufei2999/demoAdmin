package com.yufei.sys.action;

import cn.org.rapid_framework.page.Page;
import com.yufei.common.AjaxResult;
import com.yufei.common.easyui.bean.EasyUITree;
import com.yufei.common.easyui.bean.EasyUITreeNode;
import com.yufei.sys.model.SysFunc;
import com.yufei.sys.query.SysFuncQuery;
import com.yufei.sys.service.SysFuncService;
import com.yufei.sys.service.SysRoleFuncService;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.DataTypeUtils;
import com.yufei.utils.JsonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "/sysFunc")
public class SysFuncAction {

    private static final Log log = LogFactory.getLog(SysFuncAction.class);

    @Autowired
    private SysFuncService sysFuncService;
    @Autowired
    private SysRoleFuncService sysRoleFuncService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/sys/SysFunc/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(SysFuncQuery query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<SysFunc> page = sysFuncService.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("SysFunc列表查询异常", e);
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
        model.addAttribute("model", sysFuncService.getById(id));
        return "/sys/SysFunc/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/sys/SysFunc/create";
    }

    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(SysFunc entity) {
        AjaxResult result = new AjaxResult();
        try {
            CommonUtil.initSetProperties(entity, DataTypeUtils.OPERATION_ADD);
            sysFuncService.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysFunc保存异常！");
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
        model.addAttribute("model", sysFuncService.getById(id));
        return "/sys/SysFunc/edit";
    }

    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(SysFunc entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysFunc sysFunc = sysFuncService.getById(entity.getId());
            CommonUtil.initSetProperties(sysFunc, DataTypeUtils.OPERATION_UPDATE);
            sysFuncService.update(sysFunc);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysFunc更新异常！");
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
                sysFuncService.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("SysFunc删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /******************************************* 以下为新增加的方法 ***************************************************/

    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping(value = "/listTree")
    public String listTree() {
        return "/sys/SysFunc/listTree";
    }

    /**
     * 权限列表
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTree")
    public String getTree(String id) {
        String result = "";
        try {
            if (id == null) {
                result = EasyUITree.getTreeRootJson(EasyUITreeNode.DEFAULT_ROOT_ID, "权限管理");
            } else {
                List<SysFunc> list = sysFuncService.findByParentId(id);
                result = EasyUITree.getTreeNodeJson(list, "id", "funcName");
            }
        } catch (Exception e) {
            log.error("get tree error", e);
        }
        return result;
    }

    /**
     * 新增
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addFuncTree")
    public String addFuncTree(SysFunc sysFunc) {
        AjaxResult result = new AjaxResult();
        try {
            SysFunc querySysFunc = (SysFunc) sysFuncService.getById(sysFunc.getParentId());
            String parentIds = DataTypeUtils.MENU_FIRST_LEVEL_PARENT_IDS;
            if (querySysFunc != null) {
                if (StringUtils.equals(querySysFunc.getParentIds(), parentIds)) {
                    parentIds = sysFunc.getParentId();
                } else {
                    parentIds = querySysFunc.getParentIds() + "," + sysFunc.getParentId();
                }
            }
            sysFunc.setParentIds(parentIds);
            CommonUtil.initSetProperties(sysFunc, DataTypeUtils.OPERATION_ADD);
            sysFunc.setFuncCode(sysFuncService.generateCode(sysFunc.getParentId(), "sys_func", "func_code"));
            sysFuncService.save(sysFunc);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("TsysFunc保存操作异常,请联系管理员！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editFuncTree")
    public String editFuncTree(SysFunc entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysFunc sysFunc = sysFuncService.getById(entity.getId());
            CommonUtil.initSetProperties(sysFunc, DataTypeUtils.OPERATION_UPDATE);
            sysFunc.setFuncName(entity.getFuncName());
            sysFunc.setUrl(entity.getUrl());
            sysFunc.setSort(entity.getSort());
            sysFunc.setRemark(entity.getRemark());
            sysFuncService.update(sysFunc);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("TsysFunc保存操作异常,请联系管理员！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTree")
    public String deleteTree(String id) {
        AjaxResult result = new AjaxResult();
        try {
            if (id != null) {
                this.deleteTreeOpt(id);
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("TsysFunc保存操作异常,请联系管理员！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 递归逻辑删除
     *
     * @param id
     */
    private void deleteTreeOpt(String id) {
        List<SysFunc> list = sysFuncService.findByParentId(id);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                SysFunc func = list.get(i);
                deleteTreeOpt(func.getId());
                if (list.size() == i + 1) {
//					sysFuncService.deleteByIds(id);
                    sysFuncService.deleteFuncTree(id);
                }
            }
        } else {
//			sysFuncService.deleteByIds(id);
            sysFuncService.deleteFuncTree(id);
        }
    }

    /**
     * 角色权限树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleFuncTree")
    public String getRoleFuncTree(String roleId, String id) {
        String result = "";
        List<String> funcIdList = sysRoleFuncService.getFuncIdListbyRoleId(roleId);
        try {
            if(StringUtils.isBlank(id)) {
                id = EasyUITreeNode.DEFAULT_ROOT_ID;
            }
            List<SysFunc> list = sysFuncService.findByParentId(id);
            result = EasyUITree.getTreeNodeJson(list, "id", "funcName", funcIdList);
        } catch (Exception e) {
            log.error("get role func tree error", e);
        }
        return result;
    }

}
