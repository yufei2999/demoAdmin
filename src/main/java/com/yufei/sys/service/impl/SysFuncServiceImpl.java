package com.yufei.sys.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import com.yufei.common.easyui.bean.EasyUITreeNode;
import com.yufei.sys.dao.SysFuncDao;
import com.yufei.sys.dao.SysUserRoleDao;
import com.yufei.sys.model.SysFunc;
import com.yufei.sys.model.SysUserRole;
import com.yufei.sys.query.SysUserRoleQuery;
import com.yufei.sys.service.SysFuncService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("sysFuncService")
@Transactional
public class SysFuncServiceImpl extends BaseServiceImpl<SysFunc, java.lang.String> implements SysFuncService {

    @Autowired
    private SysFuncDao sysFuncDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    public BaseDao<SysFunc, java.lang.String> getEntityDao() {
        return this.sysFuncDao;
    }

    @Override
    public List<SysFunc> findByParentId(String parentId) {
        return sysFuncDao.findByParentId(parentId);
    }

    @Override
    public String generateCode(String parentId, String tableName, String codeColumnName) {
        String code = "";
        String newCodeZero = "";
        try {
            String parentCode = "";
            List<String> codelList = null;
            if (!(EasyUITreeNode.DEFAULT_ROOT_ID).equals(parentId) && StringUtils.isNotBlank(parentId)) {
                //根据parentId查询parentcode
                parentCode = sysFuncDao.findCodeByParentId(parentId, tableName, codeColumnName);
                //查询所有父级等于parentId的子集code集合
                codelList = sysFuncDao.findChildrenCodeListByParentId(parentId, tableName, codeColumnName);
            }
            if (null != codelList && codelList.size() > 0) {
                //最大的code
                String maxCode = "";
                //zero
                String zero = "";
                //找到最大的code编号
                Collections.sort(codelList);
                //截取到父级
                for (String c : codelList) {
                    maxCode = c.substring(parentCode.length(), c.length());
                }
                //编号数字位数
                int d = Integer.parseInt(maxCode);
                //编号位数包括0
                int numLength = maxCode.length();
                //code自增
                d++;
                //根据位数补“0”
                for (int i = 0; i < numLength - String.valueOf(d).length(); i++) {
                    zero += "0";
                }
                //组装生成的code
                code = parentCode + zero + d;
            } else {
                if ((EasyUITreeNode.DEFAULT_ROOT_ID).equals(parentId) || StringUtils.isBlank(parentId)) {
                    //最大的code
                    String maxCode = "";
                    //zero
                    String zero = "";
                    //获取parentId = 0 的所有code值进行排序
                    List<String> listCode = null;
                    if (StringUtils.isBlank(parentId)) {
                        listCode = sysFuncDao.findByAllCodeParentIdEQZero("", tableName, codeColumnName);
                    } else {
                        listCode = sysFuncDao.findByAllCodeParentIdEQZero(EasyUITreeNode.DEFAULT_ROOT_ID, tableName, codeColumnName);
                    }
                    if (null != listCode && listCode.size() > 0) {
                        //找到最大的code编号
                        Collections.sort(listCode);
                        for (String str : listCode) {
                            maxCode = str;
                        }
                        int intMaxCode = Integer.parseInt(maxCode);
                        int maxCodeLength = maxCode.length();
                        intMaxCode++;
                        for (int i = 0; i < maxCodeLength - String.valueOf(intMaxCode).length(); i++) {
                            zero += "0";
                        }
                        code = zero + intMaxCode;
                    } else {
                        //没有下级重新生成code
                        //循环顶级位数
                        for (int i = 0; i < EasyUITreeNode.DEFAULT_ROOTCODE_LENGTH - 1; i++) {
                            newCodeZero += "0";
                        }
                        //当前层初始值
                        parentCode = parentCode + newCodeZero + "1";
                        //组装生成的code
                        code = parentCode;
                    }
                } else {
                    //没有下级重新生成code
                    //循环顶级位数
                    for (int i = 0; i < EasyUITreeNode.DEFAULT_ROOTCODE_LENGTH - 1; i++) {
                        newCodeZero += "0";
                    }
                    //当前层初始值
                    parentCode = parentCode + newCodeZero + "1";
                    //组装生成的code
                    code = parentCode;
                }
            }

        } catch (Exception e) {
            log.error("生成code操作异常！,请联系管理员！", e);
        }
        return code;
    }

    @Override
    public void deleteFuncTree(String parentId) {
        sysFuncDao.deleteFuncTree(parentId);
    }

    @Override
    public List<SysFunc> getUserFuncList(String userId) {
        SysUserRoleQuery query = new SysUserRoleQuery();
        query.setUserId(userId);
        List<SysUserRole> roleList = sysUserRoleDao.find(query);
        String roleIds = null;
        if (CollectionUtils.isNotEmpty(roleList)) {
            SysUserRole userRole = roleList.get(0);
            roleIds = userRole.getRoleId();
        }
        return sysFuncDao.getFuncListByRoleIds(roleIds);
    }
}
