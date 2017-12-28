package com.insigma.mvc.service.sysmanager.role;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysRole;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysRoleService {
	
    public HashMap<String,Object>  getAllRoleList( SysRole role);
	
	public AjaxReturnMsg getRoleDataById(String id);
	
	public AjaxReturnMsg saveOrUpdateRoleData(SysRole srole);
	
	public AjaxReturnMsg deleteRoleDataById(String id);
	
	public List<SysRole> getRolePermTreeData(String roleid);
	
	public AjaxReturnMsg saveRolePermData(SysRole srole);

}
