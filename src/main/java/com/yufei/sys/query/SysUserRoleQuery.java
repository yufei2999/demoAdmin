package com.yufei.sys.query;

import com.yufei.base.BaseQuery;
import com.yufei.sys.model.SysUserRole;
import java.util.Calendar;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class SysUserRoleQuery extends BaseQuery<SysUserRole> {
    
    private static final long serialVersionUID = 1L;

	/** 主键 */
	private java.lang.String id;
	/** 用户id */
	private java.lang.String userId;
	/** 角色id */
	private java.lang.String roleId;
	/** 创建人 */
	private java.lang.String creator;
	/** 创建时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeEnd;
	/** 最后修改人 */
	private java.lang.String lastModifier;
	/** 最后修改时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeEnd;
	/** 删除标记：0删除；1正常 */
	private Integer deleted;
	public java.lang.String getId() {
		return this.id;
	}
	public void setId(java.lang.String value) {
		this.id = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	public java.lang.String getCreator() {
		return this.creator;
	}
	public void setCreator(java.lang.String value) {
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
	public java.lang.String getLastModifier() {
		return this.lastModifier;
	}
	public void setLastModifier(java.lang.String value) {
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

