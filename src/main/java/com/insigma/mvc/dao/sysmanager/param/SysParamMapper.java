package com.insigma.mvc.dao.sysmanager.param;

import java.util.List;

import com.insigma.mvc.model.SysParam;



/**
 * wengsh
 * @author wengsh
 *
 */
public interface SysParamMapper {
	
	List<SysParam> getList();
	List<SysParam> getPageList(SysParam sParam);
	SysParam getParamByid(String paramid);
	int addparam(SysParam sParam);
	int updateparam(SysParam sParam);
	int deleteparambyid(String paramid);
}
