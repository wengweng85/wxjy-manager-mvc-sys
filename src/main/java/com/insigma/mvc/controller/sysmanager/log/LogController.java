package com.insigma.mvc.controller.sysmanager.log;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SysErrorLog;
import com.insigma.mvc.model.SysLog;
import com.insigma.mvc.service.sysmanager.log.LogService;


/**
 * ��־����controller
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/log")
public class LogController extends MvcHelper {
	
	Log log=LogFactory.getLog(LogController.class);
	
	@Resource
	private LogService logService;
	
	/**
	 * ��ת����־��ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/log/logList");
        return modelAndView;
	}
	
	/**
	 * ��ȡ��־�б�
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLogList")
	@ResponseBody
	public HashMap<String,Object> getAc01List(HttpServletRequest request,Model model,  SysLog slog ) throws Exception {
		return logService.queryLogList(slog);
	}
	
	
	/**
	 * ��ת��������־��ѯҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorlogindex")
	public ModelAndView errorlogindex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/log/errorLogList");
        return modelAndView;
	}
	
	/**
	 * ��ȡ������־�б�
	 * @param request
	 * @return
	 */
	@RequestMapping("/getErrogLogList")
	@ResponseBody
	public HashMap<String,Object> getErrogLogList(HttpServletRequest request,Model model,  SysErrorLog sErrorLog ) throws Exception {
		return logService.queryErrorLogList(sErrorLog);
	}
	
	/**
	 * ͨ����־��Ż�ȡ��־��ϸ��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLogById/{logid}")
	public ModelAndView getLogById(HttpServletRequest request,Model model,@PathVariable String logid) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/log/logview");
		SysLog slog = logService.queryLogById(logid);
		modelAndView.addObject("slog",slog);  
        return modelAndView;
	}
	
	/**
	 * ͨ����־��Ż�ȡ�쳣��־��ϸ��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/getErrorLogById/{logid}")
	public ModelAndView toview(HttpServletRequest request,Model model,@PathVariable String logid) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/log/errorlogview");
		SysErrorLog slog = logService.queryErrorLogById(logid);
		modelAndView.addObject("slog",slog);  
        return modelAndView;
	}
	
}
