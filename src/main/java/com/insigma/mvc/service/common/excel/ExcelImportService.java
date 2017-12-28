package com.insigma.mvc.service.common.excel;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Ac60ExcelTemp;
import com.insigma.mvc.model.ExcelExportModel;
import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.resolver.AppException;




/**
 * excel״̬service
 * @author wengsh
 *
 */
public interface ExcelImportService {
	
	public HashMap<String,Object> getList( SysExcelBatch sExcelBatch );
	
	public void executeListToTemp(List<String[]> list,SysExcelBatch sExcelBatch) throws AppException;
	
	public void executeProc(SysExcelBatch sExcelBatch)throws AppException;

	public HashMap<String,Object> queryPovertyDataTotalByexcelBatchNumber( Ac60ExcelTemp ac60ExcelTemp );
	
	public HashMap<String,Object> getPovertyImprtDataList( Ac60ExcelTemp ac60ExcelTemp );
	
	public AjaxReturnMsg<String> deleteTempDataByNumber(String number);
	
	 public List<ExcelExportModel> queryExportDataByNumber(String number);
	 
	 public AjaxReturnMsg<String> exportData(String number);
	 
	 public  Ac60ExcelTemp queryImpDataById(String aac002);
}
