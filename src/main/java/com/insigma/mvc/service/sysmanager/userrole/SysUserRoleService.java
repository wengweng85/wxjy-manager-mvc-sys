package com.insigma.mvc.service.sysmanager.userrole;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysUserRoleService {
	
    public List<SysGroup>  getAllGroupList(String parentid);
    
    public AjaxReturnMsg getGroupDataById(String groupid);
	
	public HashMap<String,Object> getUserListByGroupid(SysGroup sgroup);
	
	public HashMap<String,Object> getUserRoleByUserId(SysRole srole);
	
	public AjaxReturnMsg saveUserRole(SysRole srole);
	
	public AjaxReturnMsg saveAddSysUser(SysUser suser);
	
	public AjaxReturnMsg saveAddSysAgency(SysGroup sgroup);
	
	public AjaxReturnMsg deleteSysAgency(String groupid);
	
}
