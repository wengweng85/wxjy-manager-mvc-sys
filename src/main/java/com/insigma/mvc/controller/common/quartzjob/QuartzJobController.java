package com.insigma.mvc.controller.common.quartzjob;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.QrtzTrigger;
import com.insigma.mvc.service.common.quartzjob.QuartzJobService;


/**
 * 任务controller
 * @author Administrator
 *
 */
@Controller
public class QuartzJobController extends MvcHelper {
	
	Log log=LogFactory.getLog(QuartzJobController.class);
	
	@Resource
	private QuartzJobService quartzJobService;
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/list")	
	
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("job/joblist");
        return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/querylist")
	@ResponseBody
	
	public  HashMap<String,Object> querylist(HttpServletRequest request,Model model,QrtzTrigger qrtztrigger) throws Exception {
		return quartzJobService.queryJobList(qrtztrigger);
	}
	
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	/*@RequestMapping("/job/querylist")
	@ResponseBody
	
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,QrtzTrigger qrtztrigger) throws Exception {
		return quartzJobService.queryJobList(qrtztrigger);
	}*/
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/toadd")
	
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("job/addjob");
		return modelAndView;
	}
	
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/saveorupdate")
	@ResponseBody
	
	public AjaxReturnMsg saveorupdate( HttpServletRequest request,Model model,@Valid QrtzTrigger qrtzTrigger,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		return quartzJobService.addJob(qrtzTrigger);
	}
	
	/**
	 * delete
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/delete/{id}")
	@ResponseBody
	
	public AjaxReturnMsg jobdelete(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return quartzJobService.deleteJob(id);
	}
	
	/**
	 * delete
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/batchdelete")
	@ResponseBody
	
	public AjaxReturnMsg batchdelete(HttpServletRequest request,Model model,QrtzTrigger qrtzTrigger) throws Exception {
		return quartzJobService.batchdeleteJob(qrtzTrigger);
	}
	
	/**
	 * pause
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/pause/{id}")
	@ResponseBody
	
	public AjaxReturnMsg jobpause(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return quartzJobService.pauseJob(id);
	}
	
	/**
	 * resume
	 * @param request
	 * @return
	 */
	@RequestMapping("/job/resume/{id}")
	@ResponseBody
	
	public AjaxReturnMsg jobresume(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return quartzJobService.resumeJob(id);
	}
	
}
