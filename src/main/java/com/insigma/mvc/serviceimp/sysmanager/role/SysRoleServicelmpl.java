package com.insigma.mvc.serviceimp.sysmanager.role;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.role.SysRoleMapper;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.service.sysmanager.role.SysRoleService;
import com.mysql.jdbc.StringUtils;


/**
 * 管理功能之角色管理service impl 
 * @author wengsh
 *
 */

@Service
public class SysRoleServicelmpl extends MvcHelper  implements SysRoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;

	/**
	 * 获取所有角色数据
	 */
	@Override
	public HashMap<String,Object> getAllRoleList( SysRole srole) {
		PageHelper.offsetPage(srole.getOffset(), srole.getLimit());
		List<SysRole> list=sysRoleMapper.getAllRoleList();
		PageInfo<SysRole> pageinfo = new PageInfo<SysRole>(list);
		return this.success_hashmap_response (pageinfo);
	}
 
	/**
	 * 通过角色id获取角色数据
	 */
	@Override
	public AjaxReturnMsg getRoleDataById(String id) {
		return this.success(sysRoleMapper.getRoleDataById(id));
	}

	/**
	 * 保存或更新角色数据
	 */
	@Override
	@Transactional
	public AjaxReturnMsg saveOrUpdateRoleData(SysRole srole) {
		SysRole ispermsionexist=sysRoleMapper.isRoleCodeExist(srole);
		if(ispermsionexist!=null){
			   return this.error("此角色"+srole.getCode()+"编号已经存在,请重新输入一个新的角色编号");
		}else{
			//判断是否更新操作
			if(StringUtils.isNullOrEmpty(srole.getRoleid())){
				sysRoleMapper.saveRoleData(srole);
				 return this.success("新增成功");
			}else{
				sysRoleMapper.updateRoleData(srole);
				return this.success("更新成功");
			}
		}
	}

	/**
	 * 通过角色id删除角色数据
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deleteRoleDataById(String id) {
		if(sysRoleMapper.isRoleUsedbyUser(id)!=null){
			return this.error("当前角色已经被用户绑定使用，不允许删除,请确认");
		}else{
			sysRoleMapper.deleteRoleDataById(id);
			sysRoleMapper.deleteRolePermbyRoleid(id);
			return this.success("删除成功");
		}
	}
	

	/**
	 * 获取角色权限树
	 */
	@Override
	public List<SysRole> getRolePermTreeData(String roleid) {
		return sysRoleMapper.getRolePermTreeData(roleid);
	}

	/**
	 * 保存角色权限树数据
	 */
	@Transactional
	@Override
	public AjaxReturnMsg saveRolePermData(SysRole srole) {
		//先删除角色对应历史数据
		sysRoleMapper.deleteRolePermbyRoleid(srole.getRoleid());
		String[] selectnodes= srole.getSelectnodes().split(",");
		for(int i=0;i<selectnodes.length;i++){
			SysRole temp=new SysRole();
			temp.setRoleid(srole.getRoleid());
			temp.setPermissionid(selectnodes[i]);
			sysRoleMapper.saveRolePermData(temp);
		}
		return this.success("角色增加权限成功");
	}
}
