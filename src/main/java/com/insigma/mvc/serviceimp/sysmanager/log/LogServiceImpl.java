package com.insigma.mvc.serviceimp.sysmanager.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.IPUtil;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.log.LogMapper;
import com.insigma.mvc.model.SysErrorLog;
import com.insigma.mvc.model.SysLog;
import com.insigma.mvc.service.sysmanager.log.LogService;

/**
 * 
 * 日志管理
 * @author wengsh
 *
 */

@Service
public class LogServiceImpl extends MvcHelper implements LogService {

	
	@Resource
	private LogMapper logMapper;
	
	//@Resource
	//private LogDao logdao;

	@Override
	@Transactional
	public String saveLogInfo(SysLog slog){
		logMapper.saveLogInfo(slog);
		return slog.getLogid();
	}
	
	

    
	
	@Override
	public String sysErrorLog(Exception e, HttpServletRequest request) {
		// TODO Auto-generated method stub
		SysErrorLog sysLog=new SysErrorLog();
        if(e.getMessage()!=null){
        	 sysLog.setMessage(e.getMessage().length()>500?e.getMessage().substring(0,499):e.getMessage()); 
        }
        sysLog.setStackmsg(getStackMsg(e));
        sysLog.setExceptiontype(e.getClass().getName());
        String ip=IPUtil.getClientIpAddr(request);
        /*IPSeekerUtil util=new IPSeekerUtil();*/
        sysLog.setIpaddr(ip);
        /*String country=util.getIpCountry(ip);
        sysLog.setIpaddr(country+"("+ip+")");*/
        sysLog.setUsergent(request.getHeader("user-agent"));
        sysLog.setReferer(request.getHeader("Referer"));
        StringBuffer url=request.getRequestURL();
        if(request.getQueryString()!=null&&!("").equals(request.getQueryString())){
            url.append("?"+request.getQueryString());
        }
        sysLog.setUrl(url.toString());
        String cookie="";
        if(request.getCookies()!=null){
            Cookie[] cookies=request.getCookies();
            for(int i=0;i<cookies.length;i++){
                Cookie tempcookie=cookies[i];
                cookie+=tempcookie.getName()+":"+tempcookie.getValue();
            }
            sysLog.setCookie(cookie.length() >500?cookie.substring(0,499):cookie);
        }
        logMapper.saveSysErrorLog(sysLog);
		return sysLog.getLogid();
	}
	
	
	  /**
     * 将异常打印出来
     * @param e
     * @return
     */
    private static String getStackMsg(Exception e) {
    	if(e!=null){
    		StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        e.printStackTrace(pw);
	        return sw.toString();
    	}else{
    		return "";
    	}
    }


	@Override
	public HashMap<String, Object> queryLogList(SysLog slog) {
		PageHelper.offsetPage(slog.getOffset(), slog.getLimit());
		List<SysLog> list=logMapper.queryLogList(slog);
		PageInfo<SysLog> pageinfo = new PageInfo<SysLog>(list);
		return this.success_hashmap_response(pageinfo);
	}


	@Override
	public HashMap<String, Object> queryErrorLogList(SysErrorLog sErrorLog) {
		// TODO Auto-generated method stub
		PageHelper.offsetPage(sErrorLog.getOffset(), sErrorLog.getLimit());
		List<SysErrorLog> list=logMapper.queryErrorLogList(sErrorLog);
		PageInfo<SysErrorLog> pageinfo = new PageInfo<SysErrorLog>(list);
		return this.success_hashmap_response(pageinfo);
		
		
	}


	@Override
	public SysLog queryLogById(String logid) {
		// TODO Auto-generated method stub
		return logMapper.queryLogById(logid);
	}


	@Override
	public SysErrorLog queryErrorLogById(String logid) {
		// TODO Auto-generated method stub
		return logMapper.queryErrorLogById(logid);
	}

	
}