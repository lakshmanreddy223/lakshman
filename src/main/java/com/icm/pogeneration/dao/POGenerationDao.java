package com.icm.pogeneration.dao;

import java.io.IOException;

import com.icm.pogeneration.model.POGeneration;
import com.icm.pogeneration.persistance.PersistanceDao;
import com.itextpdf.text.DocumentException;

public interface POGenerationDao extends PersistanceDao<POGeneration>{

	public POGeneration generatePO(POGeneration poGeneration);
	
	public POGeneration updatePO(POGeneration poGeneration) throws IOException, DocumentException;
		
	public int getPOId();
	
	public String getFileName(String fileName, String date);
}
 