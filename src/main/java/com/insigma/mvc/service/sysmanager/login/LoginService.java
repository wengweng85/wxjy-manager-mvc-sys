package com.insigma.mvc.service.sysmanager.login;


import java.util.List;

import com.insigma.mvc.model.SysLoginInf;
import com.insigma.mvc.model.SysPermission;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;


/**
 * ��¼service�ӿ�
 * @author wengsh
 *
 */
public interface LoginService {
	/**
	 * ͨ���û�����ȡ��Ա����Ϣ
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public SysUser getUserAndGroupInfo(String loginname) ;
	
	/**
	 * ͨ���û�id��ȡ�û���ɫ����
	 * @param userid
	 * @return ��ɫ����
	 * @throws Exception 
	 */
	public List<SysRole> findRolesStr(String loginname) ;
	
	/**
	 * ͨ���û�id��ȡ�û�Ȩ�޼���
	 * @param userid
	 * @return Ȩ�޼���
	 * @throws Exception 
	 */
	public List<SysPermission> findPermissionStr(String loginname) ;
	
	
	public void saveLoginHashInfo(SysLoginInf inf);
	
	
	public List<SysLoginInf> findLoginInfoByhashcode(String loginhash);
	

}
