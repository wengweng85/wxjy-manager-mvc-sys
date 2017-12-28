package com.insigma.mvc.dao.common.fileupload;

import java.util.List;
import java.util.Map;

import com.insigma.mvc.model.SysExcelBatch;
import com.insigma.mvc.model.SysFileRecord;



/**
 * �ļ���¼����
 * @author wengsh
 *
 */
public interface FileLoadMapper {
	
	/**
	 * �����ļ���¼
	 * @param sfilerecord
	 */
	public void saveFileRecord( SysFileRecord sfilerecord);
	
	/**
	 * ����ҵ���¼
	 * @param sfilerecord
	 */
	public void saveBusRecord(SysFileRecord sfilerecord);
	/**
	 * ͨ���ļ�md5��ѯ�ļ�
	 * @param file_md5
	 * @return
	 */
	public SysFileRecord getFileUploadRecordByFileMd5(String file_md5);
	
	/**
	 * ͨ��ҵ��id��ѯ�ļ�
	 * @param file_uuid
	 * @return
	 */
	public SysFileRecord getBusFileRecordByBusId(String bus_uuid);
	
	/**
	 * 
	 * @param sFileRecord
	 * @return
	 */
	public List<SysFileRecord> getBusFileRecordListByBusId(SysFileRecord sFileRecord);
	
	/**
	 * ͨ���ļ����ɾ���ļ�
	 * @param fileuuid
	 * @return
	 */
	public int deleteFileByBusUuid(String bus_uuid);
	
	/**
	 * ����ɾ������
	 * @param ids
	 * @return
	 */
	public int batDeleteData(String[] ids);
	
	/**
	 *  ͨ���ļ�id�������ҵ��id��ҵ��״̬Ϊ��Ч״̬ 
	 * @return
	 */
	public int batupdateBusIdByBusUuidArray(Map<String,Object> map);
	
	
	/**
	 * ����excel�ļ��ϴ���¼��
	 * @param sfilerecord
	 */
	public void saveExelBatch(SysExcelBatch sExcelBatch);
	
	
	/**
	 * �����ļ���¼��
	 * @param sExcelBatch
	 */
	public void updateExelBatch(SysExcelBatch sExcelBatch);
	
	/**
	 * ͨ��id��ȡ���κ�
	 * @param id
	 * @return
	 */
	public SysExcelBatch getExcelBatchById(String id);
	
	
	public SysExcelBatch getExcelBatchByNumber(String number);
	/**
	 * ��ҳ��ѯ
	 * @param sExcelBatch
	 * @return
	 */
	public List<SysExcelBatch> getExcelBatchList(SysExcelBatch sExcelBatch);
	
	/**
	 * ͨ�����κ�ɾ��������Ϣ
	 * @param number
	 * @return
	 */
	public int deleteExcelBatchByNumber(String number);
	
	
	
	public int updateExelBatchErrorFilePath(SysExcelBatch sExcelBatch);
}
