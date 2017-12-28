package com.insigma.mvc.serviceimp.sysmanager.perm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.perm.SysPermMapper;
import com.insigma.mvc.model.SysPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;
import com.mysql.jdbc.StringUtils;


/**
 * ������֮Ȩ�޹���service impl 
 * @author wengsh
 *
 */

@Service
public class SysPermServiceImpl extends MvcHelper implements SysPermService {

	@Resource
	private SysPermMapper sysPermMapper;
	
	
	/**
	 * ��ȡȨ������
	 */
	@Override
	public List<SysPermission> getPermTreeList() {
		// TODO Auto-generated method stub
		return  sysPermMapper.getPermTreeList();
	}

	/**
	 * ͨ��Ȩ��id��ȡȨ������
	 */
	@Override
	public AjaxReturnMsg getPermDataById(String id) {
		return this.success(sysPermMapper.getPermDataById(id));
	}

	
    /**
     * ��������Ȩ������
     */
	@Override
	@Transactional
	public AjaxReturnMsg saveOrUpdatePermData(SysPermission spermission) {
		   SysPermission isPermsionCodeexist=sysPermMapper.isPermCodeExist(spermission);
		   if(isPermsionCodeexist!=null){
			   return this.error("��Ȩ��"+spermission.getCode()+"����Ѿ�����,����������һ���µ�Ȩ�ޱ��");
		   }
		   
		   SysPermission isPermsionUrlexist=sysPermMapper.isPermUrlExist(spermission);
		   if(isPermsionUrlexist!=null){
			   return this.error("��Ȩ��"+spermission.getUrl()+"·����ַ�Ѿ�����,����������һ���µ�·����ַ");
		   }
		   
		  //�ж��Ƿ���²���
		  if(StringUtils.isNullOrEmpty(spermission.getPermissionid())){
				 int insertnum=sysPermMapper.savePermissionData(spermission);
				 if(insertnum==1){
					 return this.success("�����ɹ�");
				 }else{
					 return this.error("����ʧ��");
				 }
		 }else{
				 int updatenum=sysPermMapper.updatePermissionData(spermission);
				 if(updatenum==1){
					 return this.success("���³ɹ�");
				 }else{
					 return this.error("����ʧ��");
				 }
		  }
	}

	/**
	 * ͨ�����ڵ��ȡȨ���ӽڵ�����
	 */
	@Override
	public AjaxReturnMsg getPermListDataByParentid(String parentid) {
		return this.success( sysPermMapper.getPermListDataByParentid(parentid));
	}

	
	/**
	 * ͨ��Ȩ��idɾ��Ȩ���������
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deletePermDataById(String id) {
		if(sysPermMapper.getPermListDataByParentid(id).size()>0){
			return this.error("��ǰȨ�޻�������Ȩ������,����ɾ����Ȩ������");
		}else{
			int deletenum=sysPermMapper.deletePermDataById(id);
			if(deletenum==1){
				return this.success("ɾ���ɹ�");
			}else{
				return this.success("ɾ��ʧ��");
			}
			
		}
	}

	
	/**
	 * �ڵ��ƶ�
	 */
	@Override
	public AjaxReturnMsg<String> moveNode(String id) {
		String[]  ids=id.split("_");
		//Ҫ�ƶ��Ľڵ�id
		String movetreenodeid=ids[0];
		//Ŀ��ڵ�id
		String targettreenodeid=ids[1];
		//Ҫ�ƶ��Ľڵ�
		SysPermission moveSpermission=sysPermMapper.getPermDataById(movetreenodeid);
		//Ŀ��ڵ�
		SysPermission targetSpermission=sysPermMapper.getPermDataById(targettreenodeid);
		
		//ͬ���ƶ�,ֻ�޸������
		if(moveSpermission.getParentid().equals(targetSpermission.getParentid())){
			moveSpermission.setSortnum(targetSpermission.getSortnum());
		}
		//��ͬ���ƶ����޸ĵ�ǰ�ڵ�ĸ��ڵ�ΪĿ��ڵ�id
		else{
			moveSpermission.setParentid(targetSpermission.getPermissionid());
		}
		sysPermMapper.updatePermissionData(moveSpermission);
		return this.success("�ɹ�");
	}

	/**
	 * ����ɾ��Ȩ��
	 */
	@Override
	public AjaxReturnMsg batdeletePermData(SysPermission spermission) {
		// TODO Auto-generated method stub
		String[] selectnodes= spermission.getSelectnodes().split(",");
		sysPermMapper.batdeletePermData(selectnodes);
		return this.success("�ɹ�");
	}
}
