package com.icm.pogeneration.service;

import java.io.IOException;

import com.icm.pogeneration.model.POGeneration;
import com.icm.pogeneration.persistance.PersistanceDao;
import com.itextpdf.text.DocumentException;

public interface POGenerationService extends PersistanceDao<POGeneration> {

   public POGeneration generatePO(POGeneration poGeneration);
   
   public POGeneration updatePO(POGeneration poGeneration) throws IOException, DocumentException;
	
   public String getFileName(String filename, String date);
}
