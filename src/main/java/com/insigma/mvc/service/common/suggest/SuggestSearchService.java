package com.insigma.mvc.service.common.suggest;

import java.util.HashMap;
import java.util.List;

import com.insigma.mvc.model.SysSuggestKey;


/**

 * @author wengsh
 *
 */
public interface SuggestSearchService {
	
	HashMap<String,List<SysSuggestKey>> searchByKey(SysSuggestKey key);
	HashMap<String,List<SysSuggestKey>> searchByKeyBySolr(SysSuggestKey key);
	
}
