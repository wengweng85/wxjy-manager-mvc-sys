package com.insigma.mvc.dao.sysmanager.perm;

import java.util.List;

import com.insigma.mvc.model.SysPermission;


/**
 * 管理功能之权限管理
 * @author wengsh
 *
 */
public interface SysPermMapper {
	
	public List<SysPermission> getPermTreeList();
	
	public SysPermission getPermDataById(String id);
	
	public SysPermission isPermCodeExist(SysPermission spermission);
	
	public SysPermission isPermUrlExist(SysPermission spermission);
	
	public int savePermissionData(SysPermission spermission);
	
	public int updatePermissionData(SysPermission spermission);
	
	public List<SysPermission> getPermListDataByParentid(String parentid);
	
	public int deletePermDataById(String id);
	
	public int batdeletePermData(String []  ids);
	
	

}
