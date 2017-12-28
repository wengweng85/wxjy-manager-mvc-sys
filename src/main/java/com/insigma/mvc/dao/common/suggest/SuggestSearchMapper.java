package com.insigma.mvc.dao.common.suggest;

import java.util.List;

import com.insigma.mvc.model.SysSuggestKey;

public interface SuggestSearchMapper {
	
	List<SysSuggestKey> searchByKey(SysSuggestKey key);
    
}