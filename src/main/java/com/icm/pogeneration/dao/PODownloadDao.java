package com.icm.pogeneration.dao;

import com.icm.pogeneration.model.PODownload;

public interface PODownloadDao {

	public PODownload downloadPO(String tokenId);
	
	public PODownload getPOByToken(String tokenId);
	
}
