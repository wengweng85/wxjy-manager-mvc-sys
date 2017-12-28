package com.insigma.mvc.service.sysmanager.param;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysParam;


/**
 *  ϵͳ����service
 * @author wengsh
 *
 */
public interface SysParamService {
	
	public List<SysParam> getList();
	
	public HashMap<String, Object> getPageList(SysParam sParam);
	
	public AjaxReturnMsg<String>  updateparam(SysParam sParam);
	
	public AjaxReturnMsg<String>  deleteparambyid(String  paramid);
	
	
}
