package com.insigma.mvc.service.sysmanager.perm;

import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysPermission;




/**
 * ������֮Ȩ�޹���service
 * @author wengsh
 *
 */
public interface SysPermService {
	
	public List<SysPermission>  getPermTreeList();
	
	public AjaxReturnMsg getPermDataById(String id);
	
	public AjaxReturnMsg saveOrUpdatePermData(SysPermission spermission);
	
    public AjaxReturnMsg getPermListDataByParentid(String parentid);
	
	public AjaxReturnMsg deletePermDataById(String id);
	
	public AjaxReturnMsg batdeletePermData(SysPermission spermission); 
	
	public AjaxReturnMsg<String> moveNode(String id);

}
