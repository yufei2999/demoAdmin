package com.yufei.sys.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import com.yufei.sys.dao.SysFuncDao;
import com.yufei.sys.model.SysFunc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("sysFuncDao")
public class SysFuncDaoImpl extends BaseDaoImpl<SysFunc,java.lang.String> implements SysFuncDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SysFunc";
    }

    @Override
    protected void prepareObjectForSave(SysFunc entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdGenerator.genUUIDStr());
        }
    }

    @Override
    public List<SysFunc> findByParentId(String parentId) {
        return getSqlSession().selectList("manual-SysFunc.findByParentId", parentId);
    }

    @Override
    public String findCodeByParentId(String parentId, String tableName, String codeColumnName) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentId", parentId);
        map.put("tableName", tableName);
        map.put("codeColumnName", codeColumnName);
        return getSqlSession().selectOne("manual-SysFunc.findCodeByParentId", map).toString();
    }

    @Override
    public List<String> findChildrenCodeListByParentId(String parentId, String tableName, String codeColumnName) {
        Map<String,Object>  map = new  HashMap<String,Object>();
        map.put("parentId", parentId);
        map.put("tableName", tableName);
        map.put("codeColumnName", codeColumnName);
        return getSqlSession().selectList("manual-SysFunc.findChildrenCodeListByParentId", map);
    }

    @Override
    public List<String> findByAllCodeParentIdEQZero(String parentIdZero, String tableName, String codeColumnName) {
        Map<String,Object>  map = new  HashMap<String,Object>();
        map.put("parentIdZero", parentIdZero);
        map.put("tableName", tableName);
        map.put("codeColumnName", codeColumnName);
        return getSqlSession().selectList("manual-SysFunc.findByAllCodeParentIdEQZero", map);
    }

    @Override
    public void deleteFuncTree(String parentId) {
        getSqlSession().selectList("manual-SysFunc.deleteFuncTree", parentId);
    }

    @Override
    public List<SysFunc> getFuncListByRoleIds(String roleIds) {
        if (StringUtils.isBlank(roleIds)) {
            return null;
        }
        String[] roleIdArray = roleIds.split(",");
        return getSqlSession().selectList("manual-SysFunc.getFuncListByRoleIds", roleIdArray);
    }
}
