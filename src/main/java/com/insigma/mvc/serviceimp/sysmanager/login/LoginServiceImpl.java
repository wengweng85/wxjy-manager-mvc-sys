package com.insigma.mvc.serviceimp.sysmanager.login;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.mvc.dao.sysmanager.login.LoginMapper;
import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysLoginInf;
import com.insigma.mvc.model.SysPermission;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;
import com.insigma.mvc.service.sysmanager.login.LoginService;


/**
 * 登录service接口
 * @author wengsh
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	//登录dao
	@Resource
	private LoginMapper loginmapper;
	
	@Override
	public SysUser getUserAndGroupInfo(String loginname)  {
		SysUser suser=loginmapper.getUserAndGroupInfo(loginname);
		if(suser==null){
			return null;
		}else {
			List<SysGroup> list=loginmapper.getGroupAreaInfo(suser.getGroupid());
			for (SysGroup sgroup : list) {
				Object type =sgroup.getType();
				if("1".equals(type)){//当前数据为行政区划
					suser.setAab301(sgroup.getGroupid());
					suser.setAab301name(sgroup.getName());
				    break;
				}
			}
			return suser;
		}
	}

	@Override
	public List<SysRole> findRolesStr(String loginname)  {
		List<SysRole> list= loginmapper.findRolesStr(loginname);
		return list;
	}

	@Override
	public List<SysPermission>  findPermissionStr(String loginname)  {
		List<SysPermission> list=loginmapper.findPermissionStr(loginname);
		return list;
	}

	@Override
	public void saveLoginHashInfo(SysLoginInf inf) {
		loginmapper.saveLoginHashInfo(inf);
	}

	@Override
	public List<SysLoginInf> findLoginInfoByhashcode(String loginhash) {
		return loginmapper.findLoginInfoByhashcode(loginhash);
	}


}
