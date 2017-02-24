package com.yufei.sys.action;

import cn.org.rapid_framework.page.Page;
import com.yufei.common.AjaxResult;
import com.yufei.sys.model.SysUser;
import com.yufei.sys.model.result.UserInfo;
import com.yufei.sys.query.SysUserQuery;
import com.yufei.sys.service.SysUserService;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.DataTypeUtils;
import com.yufei.utils.JsonUtil;
import com.yufei.utils.MD5;
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
@RequestMapping(value = "/sysUser")
public class SysUserAction {

    private static final Log log = LogFactory.getLog(SysUserAction.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/sys/SysUser/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(SysUserQuery query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<SysUser> page = sysUserService.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("SysUser列表查询异常", e);
        }
        return JsonUtil.toJSONString(map);
    }

    /**
     * 查看对象
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/show")
    public String show(Model model, String id) {
        model.addAttribute("model", sysUserService.getById(id));
        return "/sys/SysUser/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/sys/SysUser/create";
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(SysUser entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysUserQuery query = new SysUserQuery();
            query.setUserName(entity.getUserName());
            if (sysUserService.getObject(query) != null) {
                result.setCode(AjaxResult.RESULT_CODE_0001);
                result.setMessage("该用户名已存在，请更换用户名");
                return JsonUtil.toJSONString(result);
            }
            CommonUtil.initSetProperties(entity, DataTypeUtils.OPERATION_ADD);
            entity.setPassword(MD5.encode(entity.getPassword()));
            sysUserService.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysUser保存异常！");
            }
            log.error(result.getMessage(),e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 进入更新页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, String id) {
        model.addAttribute("model", sysUserService.getById(id));
        return "/sys/SysUser/edit";
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(SysUser entity) {
        AjaxResult result = new AjaxResult();
        try {
            SysUser sysUser = sysUserService.getById(entity.getId());
            CommonUtil.initSetProperties(sysUser, DataTypeUtils.OPERATION_UPDATE);
            sysUser.setUserName(entity.getUserName());
            sysUser.setShowName(entity.getShowName());
            sysUser.setMobile(entity.getMobile());
            sysUser.setStatus(entity.getStatus());
            sysUser.setRemark(entity.getRemark());
            sysUserService.update(sysUser);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("SysUser更新异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        try {
            String[] ids = request.getParameterValues("ids");
            if (ArrayUtils.isNotEmpty(ids)) {
                sysUserService.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("SysUser删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 获取当前密码
     *
     * @param request
     * @param presentPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPresentPassword")
    public String getPresentPassword(HttpServletRequest request, String presentPassword) {
        try {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY);
            SysUser sysUser = sysUserService.getById(userInfo.getId());
            if (MD5.encode(presentPassword).equals(sysUser.getPassword())) {
                return "0";
            }
        } catch (Exception e) {
            log.error("get present password error", e);
        }
        return "1";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPassword")
    public String modifyPassword(HttpServletRequest request, String newPassword) {
        AjaxResult result = new AjaxResult();
        try {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY);
            SysUser sysUser = sysUserService.getById(userInfo.getId());
            sysUser.setPassword(MD5.encode(newPassword));
            sysUserService.update(sysUser);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("TsysUser更新操作异常,请联系管理员！");
            }
            log.error("modify password error", e);
        }
        return newPassword;
    }
}
