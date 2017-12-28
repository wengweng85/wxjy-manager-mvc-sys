package com.insigma.mvc.dao.sysmanager.role;

import java.util.List;

import com.insigma.mvc.model.SysRole;


/**
 * 管理功能之角色管理
 * @author wengsh
 *
 */
public interface SysRoleMapper {
	
	public List<SysRole> getAllRoleList();
	
	public SysRole getRoleDataById(String id);
	
	public SysRole isRoleCodeExist(SysRole srole);
	
	public SysRole isRoleUsedbyUser(String roleid);
	
	public int saveRoleData(SysRole spermission);
	
	public int updateRoleData(SysRole spermission);
	
	public int deleteRoleDataById(String id);
	
	public  List<SysRole> getRolePermTreeData(String roleid);
	
	public int deleteRolePermbyRoleid(String roleid);
	
	public int saveRolePermData(SysRole srole);

}
