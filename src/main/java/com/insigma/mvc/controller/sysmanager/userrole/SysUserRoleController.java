package com.insigma.mvc.controller.sysmanager.userrole;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.insigma.common.util.EhCacheUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SysGroup;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.model.SysUser;
import com.insigma.mvc.service.sysmanager.userrole.SysUserRoleService;
import com.insigma.shiro.realm.SysUserUtil;

/**
 * �û������û�����
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/userrole")
public class SysUserRoleController extends MvcHelper {
	
	@Resource
	private SysUserRoleService sysUserRoleService;
	/**
	 * ҳ���ʼ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/userrole/sysUserRoleIndex");
        return modelAndView;
	}
	
	
	/**
	 * ����������
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	
	@ResponseBody
	public  List<SysGroup>  getGroupTreeData(HttpServletRequest request,Model model) throws Exception {
		String parentid=request.getParameter("id");
		if(parentid.equals("")){
			//parentid="G001";
			parentid=SysUserUtil.getCurrentUser().getAab301();
		}
		return sysUserRoleService.getAllGroupList(parentid);
	}
	
	
	/**
	 * ͨ��������Ż�ȡ������Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getgroupdatabyid/{id}")
	
	@ResponseBody
	public AjaxReturnMsg getgroupdata(HttpServletRequest request,Model model,@PathVariable String id ) throws Exception {
		return sysUserRoleService.getGroupDataById(id);
	}
	
	/**
	 * ��ȡ��Ա��Ϣ�б�
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserListDataByid")
	
	@ResponseBody
	public HashMap<String,Object> getUserListByGroupid(HttpServletRequest request,Model model,SysGroup sgroup ) throws Exception {
		if(StringUtils.isEmpty(sgroup.getGroupid())){
			String groupid="G001";
			Object object=EhCacheUtil.getParamFromCache("ROOTGROUPID");
			if(object!=null){
				groupid=(String)object;
			}
			sgroup.setGroupid(groupid);
		}
		return sysUserRoleService.getUserListByGroupid(sgroup);
	}
	
	
	/**
	 * ͨ���û�id��ȡ��ɫ�б�ѡ��״̬
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleByUserId")
	
	@ResponseBody
	public HashMap<String,Object> getRoleByUserId(HttpServletRequest request,Model model,SysRole srole ) throws Exception {
		if(StringUtils.isEmpty(srole.getUserid())){
			srole.setUserid("");
		}
		return sysUserRoleService.getUserRoleByUserId(srole);
	}
	
	
	/**
	 * �����û���Ӧ��ɫ
	 * @param request
	 * @param model
	 * @param srole
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUserRole")
	
	@ResponseBody
	public AjaxReturnMsg saveUserRole(HttpServletRequest request,Model model,SysRole srole ) throws Exception {
		return sysUserRoleService.saveUserRole(srole);
	}
	
	
	/**
	 * ����ϵͳ�û�
	 * @param request
	 * @return
	 */
	@RequestMapping("/addSysUser/{groupid}")
	
	public ModelAndView addSysUser(HttpServletRequest request,Model model,@PathVariable String groupid ) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/userrole/addSysUser");
		modelAndView.addObject("groupid", groupid);
        return modelAndView;
	}
	
	/**
	 * ����ϵͳ�û���Ϣ
	 * @param request
	 * @param model
	 * @param suser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAddSysUser")
	
	@ResponseBody
	public AjaxReturnMsg saveAddSysUser(HttpServletRequest request,Model model,SysUser suser) throws Exception {
		return sysUserRoleService.saveAddSysUser(suser);
	}
	/**
	 * ����ϵͳ�������
	 * @param request
	 * @return
	 */
	@RequestMapping("/addSysAgency/{groupid}")
	
	public ModelAndView addSysAgency(HttpServletRequest request,Model model,@PathVariable String groupid ) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/userrole/addSysAgency");
		modelAndView.addObject("parentid", groupid);
        return modelAndView;
	}
	
	/**
	 * ����ϵͳ������Ϣ
	 * @param request
	 * @param model
	 * @param suser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAddSysAgency")
	
	@ResponseBody
	public AjaxReturnMsg saveAddSysAgency(HttpServletRequest request,Model model,SysGroup sgroup) throws Exception {
		return sysUserRoleService.saveAddSysAgency(sgroup);
	}
	
	/**
	 * ͨ���������ɾ��������Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteSysAgency/{groupid}")
	
	@ResponseBody
	public AjaxReturnMsg deleteSysAgency(HttpServletRequest request,Model model,@PathVariable String groupid ) throws Exception {
		return sysUserRoleService.deleteSysAgency(groupid);
	}
}
