package com.yufei.sys.model.result;

import com.yufei.base.BaseBean;
import com.yufei.sys.model.SysFunc;
import com.yufei.sys.model.SysRole;
import com.yufei.sys.model.SysUser;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2016-11-04.
 */
public class UserInfo extends BaseBean {

    private String id;
    private String userName;
    private String showName;
    private String password;
    private String mobile;
    private Integer status;
    private String lastLoginIp;
    private java.util.Date lastLoginTime;
    private String remark;
    private String creator;
    private java.util.Date createTime;
    private String lastModifier;
    private java.util.Date lastModifyTime;
    private Integer deleted;
    /**
     * 角色
     */
    private List<SysRole> roleList;
    /**
     * 权限
     */
    private List<SysFunc> funcList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysFunc> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<SysFunc> funcList) {
        this.funcList = funcList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
