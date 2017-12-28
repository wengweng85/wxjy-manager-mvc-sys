package com.insigma.mvc.service.sysmanager.log;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.insigma.mvc.model.SysErrorLog;
import com.insigma.mvc.model.SysLog;


/**
 * 日志服务
 * @author wengsh
 *
 */
public interface LogService  {
	
	public String saveLogInfo(SysLog slog);
	
	public String sysErrorLog(Exception e, HttpServletRequest request);
	
	public HashMap<String, Object> queryLogList(SysLog slog);
	
	public HashMap<String, Object> queryErrorLogList(SysErrorLog sErrorLog);
	
	public SysLog queryLogById(String logid);
	
	public SysErrorLog queryErrorLogById(String logid);
}
