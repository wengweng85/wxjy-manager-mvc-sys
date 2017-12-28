package com.insigma.mvc.controller.sysmanager.role;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SysRole;
import com.insigma.mvc.service.sysmanager.role.SysRoleService;

/**
 * ��ɫ������ɫ��ɫ�������
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends MvcHelper  {
	
	@Resource
	private SysRoleService sysRoleService;
	/**
	 * ҳ���ʼ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/role/sysRoleIndex");
        return modelAndView;
	}
	
	/**
	 * ��ɫ�б��ѯ
	 * @param request
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	
	public HashMap<String,Object> querylist(HttpServletRequest request,Model model,SysRole srole) throws Exception {
		return sysRoleService.getAllRoleList(srole);
	}
	
	
	/**
	 * ͨ����ɫ��Ż�ȡ��ɫ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleData/{id}")
	
	@ResponseBody
	public AjaxReturnMsg getPermDataByid(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable String id) throws Exception {
		return sysRoleService.getRoleDataById(id);
	}
	
	/**
	 * ɾ����ɫ�������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoleDataById/{id}")
	@ResponseBody
	public AjaxReturnMsg deleteRoleDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return  sysRoleService.deleteRoleDataById(id);
	}
	
	/**
	 * ���»򱣴��ɫ
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SysRole srole,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		return sysRoleService.saveOrUpdateRoleData(srole);
	}
	
	/**
	 * ��ɫ-Ȩ��������
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	@ResponseBody
	public  List<SysRole> treedata(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id=request.getParameter("id");
		return sysRoleService.getRolePermTreeData(id);
	}
	
	
	/**
	 * ���»򱣴�Ȩ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveroleperm")
	@ResponseBody
	public AjaxReturnMsg saveroleperm(HttpServletRequest request,Model model,SysRole srole) throws Exception {
		return sysRoleService.saveRolePermData(srole);
	}
}
