package com.insigma.mvc.controller.sysmanager.perm;

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
import com.insigma.mvc.model.SysPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;

/**
 * 权限管理
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/perm")
public class SysPermController extends MvcHelper {
	
	
	@Resource
	private SysPermService sysPermService;
	/**
	 * 页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/perm/sysPermIndex");
        return modelAndView;
	}
	
	
	/**
	 * 页面初始化 右键菜单模式
	 * @param request
	 * @return
	 */
	@RequestMapping("/index_contextmenu")
	
	public ModelAndView index_contextmenu(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/perm/sysPermIndex_contextmenu");
        return modelAndView;
	}
	
	
	/**
	 * 权限树数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	
	@ResponseBody
	public List<SysPermission> treedata(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		return sysPermService.getPermTreeList();
	}
	
	
	/**
	 * 通过权限编号获取权限数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPermData/{id}")
	
	@ResponseBody
	public AjaxReturnMsg getPermDataByid(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable String id) throws Exception {
		return  sysPermService.getPermDataById(id);
	}
	
	
	/**
	 * 更新或保存权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SysPermission spermission,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		return sysPermService.saveOrUpdatePermData(spermission);
		
	}
	
	/**
	 * 删除权限相关数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePermDataById/{id}")
	@ResponseBody
	
	public AjaxReturnMsg deletePermDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return   sysPermService.deletePermDataById(id);
	}
	
	
	/**
	 *批量 删除权限相关数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/batdeletePermData")
	@ResponseBody
	
	public AjaxReturnMsg batdeletePermData(HttpServletRequest request,Model model,SysPermission spermission) throws Exception {
		return   sysPermService.batdeletePermData(spermission);
	}
	
	
	/**
	 * 权限树移动排序
	 * @param request
	 * @return
	 */
	@RequestMapping("/moveNode/{id}")
	@ResponseBody
	
	public AjaxReturnMsg<String> moveNode(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return   sysPermService.moveNode(id);
	}
}
