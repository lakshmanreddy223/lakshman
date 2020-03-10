package com.icm.pogeneration.service.impl;

import java.io.IOException;

import com.icm.pogeneration.dao.POGenerationDao;
import com.icm.pogeneration.dao.impl.POGenerationDaoImpl;
import com.icm.pogeneration.model.POGeneration;
import com.icm.pogeneration.service.POGenerationService;
import com.itextpdf.text.DocumentException;

public class POGenerationServiceImpl implements POGenerationService{
    
	POGenerationDao poGenerationDao = null;
	
	public POGeneration generatePO(POGeneration poGeneration) {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.generatePO(poGeneration);
	}

	public String save(POGeneration poGeneration) {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.save(poGeneration);
	}

	public String update(POGeneration poGeneration) {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.update(poGeneration);
	}

	public POGeneration updatePO(POGeneration poGeneration) throws IOException, DocumentException {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.updatePO(poGeneration);
	}

	public String getFileName(String fileName, String date) {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.getFileName(fileName, date);
	}
	
	public POGeneration getByPONumber(String poNumber) {
		poGenerationDao = new POGenerationDaoImpl();
		return poGenerationDao.getByPONumber(poNumber);
	}

	public int delete(POGeneration poNumber) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
