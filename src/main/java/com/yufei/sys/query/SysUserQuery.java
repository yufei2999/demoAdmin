package com.yufei.sys.query;

import com.yufei.base.BaseQuery;
import com.yufei.sys.model.SysUser;
import java.util.Calendar;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class SysUserQuery extends BaseQuery<SysUser> {
    
    private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;
	/** 用户名 */
	private String userName;
	/** 显示名 */
	private String showName;
	/** 密码 */
	private String password;
	/** 手机号 */
	private String mobile;
	/** 状态：0停用；1启用 */
	private Integer status;
	/** 最后登录ip */
	private String lastLoginIp;
	/** 最后登录时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastLoginTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastLoginTimeEnd;
	/** 备注 */
	private String remark;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeEnd;
	/** 最后修改人 */
	private String lastModifier;
	/** 最后修改时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeEnd;
	/** 删除标记：0删除；1正常 */
	private Integer deleted;
	public String getId() {
		return this.id;
	}
	public void setId(String value) {
		this.id = value;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	public String getShowName() {
		return this.showName;
	}
	public void setShowName(String value) {
		this.showName = value;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}
	public void setLastLoginIp(String value) {
		this.lastLoginIp = value;
	}
	public java.util.Date getLastLoginTimeBegin() {
		return this.lastLoginTimeBegin;
	}
	public void setLastLoginTimeBegin(java.util.Date value) {
		this.lastLoginTimeBegin = value;
	}
	public java.util.Date getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}
	public void setLastLoginTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastLoginTimeEnd = calendar.getTime();
		}else {
			this.lastLoginTimeEnd = value;
		}
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getCreator() {
		return this.creator;
	}
	public void setCreator(String value) {
		this.creator = value;
	}
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	public String getLastModifier() {
		return this.lastModifier;
	}
	public void setLastModifier(String value) {
		this.lastModifier = value;
	}
	public java.util.Date getLastModifyTimeBegin() {
		return this.lastModifyTimeBegin;
	}
	public void setLastModifyTimeBegin(java.util.Date value) {
		this.lastModifyTimeBegin = value;
	}
	public java.util.Date getLastModifyTimeEnd() {
		return this.lastModifyTimeEnd;
	}
	public void setLastModifyTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastModifyTimeEnd = calendar.getTime();
		}else {
			this.lastModifyTimeEnd = value;
		}
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer value) {
		this.deleted = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

