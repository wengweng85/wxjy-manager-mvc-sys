package com.insigma.mvc.dao.common.quartzjob;


import java.util.List;

import com.insigma.mvc.model.QrtzTrigger;


/**
 * µÇÂ¼service½Ó¿Ú
 * @author wengsh
 *
 */
public interface QuartzJobMapper {
	
	public List<QrtzTrigger> queryJobList( QrtzTrigger qrtztrigger);
	

}
