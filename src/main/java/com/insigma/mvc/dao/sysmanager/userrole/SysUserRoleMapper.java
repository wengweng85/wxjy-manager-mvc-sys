package com.insigma.mvc.dao.sysmanager.userrole;

import java.util.List;

import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;


/**
 * 管理功能之机构、用户角色管理
 * @author wengsh
 *
 */
public interface SysUserRoleMapper {
	
	public List<SysGroup> getAllGroupList(String parentid);
	
	public SysGroup getGroupDataById(String groupid);
	
	public List<SysUser> getUserListByGroupid(String groupid);
	
	public List<SysRole> getUserRoleByUserId(String userid);
	
	public int deleteUserRoleByUserId(String userid);
	
	public int saveUserRole(SysRole srole);
	
	public int saveSysUserInfo(SysUser suser);
	
	public int saveSysGroupUser(SysUser suser);
	
	public SysUser getSysUserInfoById(String username);
	
	public int saveSysAgency(SysGroup sgroup);
	
	public SysGroup getSysAgencyById(String name);
	
	public List<SysGroup> getSysAgencyForChildById(String groupid);
	
	public int deleteSysAgencyInfoByGroupId(String groupid);
}
