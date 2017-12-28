package com.insigma.mvc.serviceimp.sysmanager.param;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.param.SysParamMapper;
import com.insigma.mvc.model.SysParam;
import com.insigma.mvc.service.sysmanager.param.SysParamService;

/**
 * 系统参数service
 * @author wengsh
 *
 */
@Service
public class SysParamServiceImpl extends MvcHelper implements SysParamService {

	@Resource
	private SysParamMapper sysParamMapper;

	@Override
	public List<SysParam> getList(){
		return sysParamMapper.getList();
	}
	
	
	@Override
	public HashMap<String, Object> getPageList(SysParam sParam) {
		PageHelper.offsetPage(sParam.getOffset(), sParam.getLimit());
		List<SysParam> list=sysParamMapper.getPageList(sParam);
		PageInfo<SysParam> pageinfo = new PageInfo<SysParam>(list);
		return this.success_hashmap_response(pageinfo);
	}


	@Override
	public AjaxReturnMsg<String> updateparam(SysParam sParam) {
		SysParam issparamexist=sysParamMapper.getParamByid(sParam.getParamid());
		//更新
		if(issparamexist!=null){
			sysParamMapper.updateparam(sParam);
			return this.success("更新成功");
		}
	    //新增
		{
			int addnum=sysParamMapper.addparam(sParam);
			return this.success("新增成功");
		}
	}


	@Override
	public AjaxReturnMsg<String> deleteparambyid(String paramid) {
		// TODO Auto-generated method stub
		int deletenum=sysParamMapper.deleteparambyid(paramid);
		return this.success("删除成功");
	}

}