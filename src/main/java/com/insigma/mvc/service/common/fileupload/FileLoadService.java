package com.insigma.mvc.service.common.fileupload;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.SysFileRecord;




/**
 * 文件上传service
 * @author wengsh
 *
 */
public interface FileLoadService {
	 public SysFileRecord getFileInfo(String file_uuid);  
     public byte[]  download(String file_path);  
     public String upload(String originalFilename,String file_bus_id,String file_bus_type,InputStream in);  
     public String uploadexcel(String originalFilename,String excel_upload_type,String minColumns,InputStream in) throws Exception;  
 	 public HashMap<String,Object> getFileList( SysFileRecord sFileRecord );
 	 public AjaxReturnMsg<String> deleteFileByBusUuid(String file_uuid);
 	 public AjaxReturnMsg<String> batDeleteData(SysFileRecord sFileRecord) ;
 	 public AjaxReturnMsg<String> batupdateBusIdByBusUuidArray(Map<String,Object> map);
 	 public SysExcelBatch getExcelBatchByNumber(String number);
 	 public int updateExelBatchErrorFilePath(SysExcelBatch sExcelBatch);
}
