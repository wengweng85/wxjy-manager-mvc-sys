package com.insigma.mvc.serviceimp.sysmanager.userrole;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.MD5Util;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.userrole.SysUserRoleMapper;
import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;
import com.insigma.mvc.service.sysmanager.userrole.SysUserRoleService;

/**
 * �û���ɫ����
 * @author wengsh
 *
 */
@Service
public class SysUserRoleServiceImpl extends MvcHelper implements SysUserRoleService {

	@Resource
    private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * ��ȡ������Ϣ��
	 */
	@Override
	public List<SysGroup> getAllGroupList(String parentid) {
		 return sysUserRoleMapper.getAllGroupList(parentid);
	}
	
	
	/**
	 * ��ȡ������Ϣ
	 */
	@Override
	public AjaxReturnMsg getGroupDataById(String groupid) {
		return this.success(sysUserRoleMapper.getGroupDataById(groupid));
	}
	
	
	/**
	 * ��ȡ��������Ա��Ϣ
	 */
	@Override
	public HashMap<String,Object> getUserListByGroupid(SysGroup sgroup) {
		PageHelper.offsetPage(sgroup.getOffset(), sgroup.getLimit());
		List<SysUser> list=sysUserRoleMapper.getUserListByGroupid(sgroup.getGroupid());
		PageInfo<SysUser> pageinfo = new PageInfo<SysUser>(list);
		return this.success_hashmap_response(pageinfo);
	}


	/**
	 * ͨ����Աid��ȡ��ɫѡ��״̬
	 */
	@Override
	public HashMap<String,Object> getUserRoleByUserId(SysRole srole) {
		PageHelper.offsetPage(srole.getOffset(), srole.getLimit());
		List<SysRole> list=sysUserRoleMapper.getUserRoleByUserId(srole.getUserid());
		PageInfo<SysRole> pageinfo = new PageInfo<SysRole>(list);
		return this.success_hashmap_response(pageinfo);
	}


	/**
	 * �����û���ɫ
	 */
	@Override
	public AjaxReturnMsg saveUserRole(SysRole srole) {
		sysUserRoleMapper.deleteUserRoleByUserId(srole.getUserid());
		String[] selectnodes= srole.getSelectnodes().split(",");
		for(int i=0;i<selectnodes.length;i++){
			SysRole temp=new SysRole();
			temp.setUserid(srole.getUserid());
			temp.setRoleid(selectnodes[i]);
			sysUserRoleMapper.saveUserRole(temp);
		}
		return this.success("�û���Ȩ�ɹ�");
	}
	
	/**
	 * �����û���Ϣ
	 */
	@Override
	public AjaxReturnMsg saveAddSysUser(SysUser suser) {
		if(!"".equals(suser.getUsername())||suser.getUsername()!=null){
			//��ѯ��������û���
			SysUser suser_ = sysUserRoleMapper.getSysUserInfoById(suser.getUsername());
		}
		suser.setPassword(MD5Util.MD5Encode("123456"));
		suser.setEnabled("1");
		suser.setUsertype("2");
		//����ϵͳ�û���
		int insertNum = sysUserRoleMapper.saveSysUserInfo(suser);
		//д���û��û��������
		SysUser suserGroup  = new SysUser();
		suserGroup.setUserid(suser.getUserid());
		suserGroup.setGroupid(suser.getGroupid());
		int insertGroupUser = sysUserRoleMapper.saveSysGroupUser(suserGroup);
		
		if(insertNum==1 && insertGroupUser == 1){
			return this.success("ϵͳ�û������ɹ�");
		}else {
			return this.error("ϵͳ�û�����ʧ��");
		}
		
	}

	/**
	 * ����ϵͳ������Ϣ
	 */
	@Override
	public AjaxReturnMsg saveAddSysAgency(SysGroup sgroup) {
		if(!"".equals(sgroup.getName())||sgroup.getName()!=null){
			SysGroup sgroup_ = sysUserRoleMapper.getSysAgencyById(sgroup.getName());
			if(sgroup_!=null){
				return this.error("��ϵͳ���������Ѵ���,�뻻һ������");
			}
		}
		sgroup.setType("2");
		sgroup.setStatus("1");
		int insertNum = sysUserRoleMapper.saveSysAgency(sgroup);
		if(insertNum ==1){
			return this.success("ϵͳ���������ɹ�");
		}else {
			return this.error("ϵͳ��������ʧ��");
		}
	}
	
	/**
	 * ɾ��ϵͳ������Ϣ
	 */
	@Override
	public AjaxReturnMsg deleteSysAgency(String groupid) {
		int deleteNum =0;
		List<SysGroup> list = sysUserRoleMapper.getSysAgencyForChildById(groupid);
		if(list.size()>0){
			return this.error("�û��������ӽڵ㣬����ɾ��!");
		}else {
			List<SysUser> listUser = sysUserRoleMapper.getUserListByGroupid(groupid);
			if(listUser.size()>0){
				return this.error("�û��������û���Ϣ������ɾ�������µ��û�!");
			}else {
				deleteNum =sysUserRoleMapper.deleteSysAgencyInfoByGroupId(groupid);
			}
		}
		if(deleteNum==1){
			return this.success("ϵͳ����ɾ���ɹ�!");
		}else {
			return this.error("ϵͳ����ɾ��ʧ��!");
		}
	}
}
