package com.insigma.mvc.dao.sysmanager.log;

import java.util.List;

import com.insigma.mvc.model.SysErrorLog;
import com.insigma.mvc.model.SysLog;


/**
 * 
 * ÈÕÖ¾¼ÇÂ¼mapper
 * @author wengsh
 *
 */
public interface LogMapper {
	public void saveLogInfo(SysLog slog);
	public void saveSysErrorLog(SysErrorLog sErrorLog);
	public List<SysLog> queryLogList(SysLog slog);
	public SysLog queryLogById(String logid);
	public List<SysErrorLog>  queryErrorLogList(SysErrorLog sErrorLog);
	public SysErrorLog queryErrorLogById(String logid);
}
