package com.insigma.mvc.dao.sysmanager.login;


import java.util.List;

import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysLoginInf;
import com.insigma.mvc.model.SysPermission;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;


/**
 * ��¼service�ӿ�
 * @author wengsh
 *
 */
public interface LoginMapper {
	/**
	 * ͨ���û�����ȡ��Ա����Ϣ
	 * @param username
	 * @return �û���
	 * @ 
	 */
	public SysUser getUserAndGroupInfo(String loginname)  ;
	
	
	/**
	 * ��ȡ��ǰ����������������Ϣ
	 * @param groupid
	 * @return
	 * @
	 */
	public List<SysGroup> getGroupAreaInfo(String groupid)   ;
	
	
	/**
	 * ͨ���û�id��ȡ�û���ɫ����
	 * @param userid
	 * @return ��ɫ����
	 * @ 
	 */
	public List<SysRole> findRolesStr(String loginname) ;
	
	
	/**
	 * ͨ���û�id��ȡ�û�Ȩ�޼���
	 * @param userid
	 * @return Ȩ�޼���
	 * @ 
	 */
	public List<SysPermission> findPermissionStr(String loginname) ;
	
	
	/**
	 * ����hashinfo
	 * @param hashinfo
	 */
	public void saveLoginHashInfo(SysLoginInf inf);
	
	
	public List<SysLoginInf> findLoginInfoByhashcode(String loginhash);

}
