package com.icm.pogeneration.service.impl;

import com.icm.pogeneration.dao.PODownloadDao;
import com.icm.pogeneration.dao.impl.PODownloadDaoImpl;
import com.icm.pogeneration.model.PODownload;
import com.icm.pogeneration.service.PODownloadService;

public class PODownloadServiceImpl implements PODownloadService {

	PODownloadDao poDownloadDao;

	public PODownload downloadPO(String tokenId) {
		poDownloadDao = new PODownloadDaoImpl();
		return poDownloadDao.downloadPO(tokenId);
	}

}
