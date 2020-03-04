package com.icm.pogeneration.dao.impl;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.Base64.Encoder;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import com.icm.pogeneration.dao.POGenerationDao;
import com.icm.pogeneration.model.Allocation;
import com.icm.pogeneration.model.PODownload;
import com.icm.pogeneration.model.POGeneration;
import com.icm.pogeneration.service.POGenerationService;
import com.icm.pogeneration.service.impl.POGenerationServiceImpl;
import com.icm.pogeneration.util.CustomDate;
import com.icm.pogeneration.util.DBConnection;
import com.icm.pogeneration.util.DBConstant;
import com.icm.pogeneration.util.FinanciallDate;
import com.icm.pogeneration.util.POGenerateUtil;
import com.itextpdf.text.DocumentException;
/**
 * 
 * @author Hemant
 *
 */
public class POGenerationDaoImpl implements POGenerationDao{

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	
	/* Implement the generation logic*/
	public POGeneration generatePO(POGeneration poGeneration) {
		
		POGenerationService poGenerationService = new POGenerationServiceImpl();
		String poNumber = generatePONumber();
		String tokenNumber = generateTokenNumber();
		String fileName = poGenerationService.getFileName(poGeneration.getName(), poGeneration.getDate());
		try {
			if(poNumber!=null && tokenNumber!=null) {
				poGeneration.setPonumber(poNumber);
				poGeneration.setToken(tokenNumber);
				poGeneration.setFileName(fileName);
				poGeneration.setTotalamount(getTotalAmount(poGeneration));
				// PODocument download details save
				String key = poGenerationService.save(poGeneration);
				if(key.equals("ok")) {
					// PODocument generate
					POGenerateUtil pogenerationUtil = new POGenerateUtil();
					pogenerationUtil.generate(poGeneration);
					poGeneration.setStatus(true);
					return poGeneration;
				}			
			}						
		}catch(Exception e) {
			System.out.println("Error :" + e.getMessage());
			return null;
		}
		return null;
	}
	
	
	// Generate Token Number For Download Link PO
	public String generateTokenNumber() {
		    SecureRandom random = new SecureRandom();
		    byte bytes[] = new byte[25];
		    random.nextBytes(bytes);
		    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		    return encoder.encodeToString(bytes);
	}

	public String save(POGeneration poGeneration) {
        
		try {
			int result = 0;
			String query = "INSERT INTO "+DBConstant.TABLE_NAME+"("+DBConstant.PO_NUMBER+","+DBConstant.PO_TYPE+", "+DBConstant.PO_DELETE+", "+DBConstant.PO_DATE+", "+DBConstant.PO_FILENAME+","+DBConstant.PO_TOKEN+", "+DBConstant.PO_EXPIRATION+" , "+DBConstant.PO_DOWNLOAD_COUNT+" ) VALUES(?,?,?,?,?,?,?,?)";
			
			connection = DBConnection.getConnection();				
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, poGeneration.getPonumber());
			preparedStatement.setString(2, poGeneration.getPotype());
			preparedStatement.setInt(3, poGeneration.getDelete());
			preparedStatement.setString(4, CustomDate.getCurrentDates(poGeneration.getDate()));
			preparedStatement.setString(5, poGeneration.getFileName());
			preparedStatement.setString(6, poGeneration.getToken());
			preparedStatement.setString(7, poGeneration.getExpirationDate());
			preparedStatement.setInt(8, poGeneration.getDownloadCount());
			
			result = preparedStatement.executeUpdate();
			if (result == 1) {			    
				return "ok";
			}
		}catch(Exception e){
			System.out.println("Error :" + e.getMessage());
		}
		finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		}
		
		return null;
	}

	// get last existing POId
	public int getPOId() {
		try {
			connection = DBConnection.getConnection();
			String query = "SELECT "+DBConstant.PO_ID+" FROM "+DBConstant.TABLE_NAME+" ORDER BY "+DBConstant.PO_ID+" DESC LIMIT 1";
			preparedStatement = connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt(DBConstant.PO_ID);
			}
		}catch(Exception e){
			System.out.println("Error :" + e.getMessage());
		}
		finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		}
		return 0;
	}
	
	// generate PO_FILENAME
	public String getFileName(String poName, String date) {
		
		String currentdate = CustomDate.getFileCurrentDate(date);
		int poId = getPOId()+1;
		String fileName = "NFL-"+poId+"_"+currentdate+"_"+poName+".pdf";
	    return fileName;
		/*
		 * // get last existing PO_FILENAME
		 * 
		 * try {
			connection = DBConnection.getConnection();
			String query = "SELECT "+DBConstant.PO_FILENAME+" FROM "+DBConstant.TABLE_NAME+" ORDER BY "+DBConstant.PO_FILENAME+" DESC LIMIT 1";
			preparedStatement = connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString(DBConstant.PO_FILENAME);
			}
		}catch(Exception e){
			System.out.println("Error :" + e.getMessage());
		}
		finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		}
		return null;*/
	}
	
	// generate PONumber format '21/2017-2018'
	public String generatePONumber() {
		int poId = getPOId()+1;
        return "NFL-"+poId+"/"+FinanciallDate.getFinancialDate(Calendar.getInstance());
	}


	public String update(POGeneration poGeneration) {
		int result = 0;
		try {
			connection = DBConnection.getConnection();
		    String query = "UPDATE "+DBConstant.TABLE_NAME+" SET "+DBConstant.PO_DATE+"=?, "+DBConstant.PO_EXPIRATION+"=?, "+DBConstant.PO_TYPE+"=? WHERE "+DBConstant.PO_NUMBER+"=? AND "+DBConstant.PO_DELETE+"=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, CustomDate.getCurrentDates(poGeneration.getDate()));
			preparedStatement.setString(2, poGeneration.getExpirationDate()); 
			preparedStatement.setString(3, poGeneration.getPotype()); 
			preparedStatement.setString(4, poGeneration.getPonumber()); 
			preparedStatement.setInt(5, 0); 
			  
			result = preparedStatement.executeUpdate();
			if (result == 1) {			    
				return "ok";
			}
		 }catch (SQLException e){
		      System.out.println("Error :" + e.getMessage());
		 }
		 finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		 }
	     return null;
	}


	public POGeneration updatePO(POGeneration poGeneration) throws IOException, DocumentException {
		    POGeneration poGen = new POGeneration();
		    POGenerationService poGenerationService = new POGenerationServiceImpl();
		    poGen = poGenerationService.getByPONumber(poGeneration.getPonumber());
		    poGeneration.setFileName(poGen.getFileName());
			poGeneration.setToken(poGen.getToken());
			poGeneration.setTotalamount(getTotalAmount(poGeneration));
		    // delete old file
		    deleteOldFile(poGen.getFileName());
		    // update table
		    String update = update(poGeneration);
		    if(update.equals("ok")) {
			    // generate new file
				POGenerateUtil pogenerationUtil = new POGenerateUtil();
				pogenerationUtil.generate(poGeneration);
				
			    poGeneration.setStatus(true);
			    System.out.println("update success");
				return poGeneration;				
		    }		  
		 return null;
	}

	private void deleteOldFile(String fileName) throws IOException {
		String path = PODownload.getPath()+"/"+fileName;
		File file = new File(path);
		FileUtils.forceDelete(file);
		System.out.println("deleted file");
	}

	public POGeneration getByPONumber(String poNumber) {
		POGeneration poGeneration = new POGeneration();
		try {
			connection = DBConnection.getConnection();
			String query = "SELECT * FROM "+DBConstant.TABLE_NAME+" WHERE "+DBConstant.PO_NUMBER+"=? AND "+DBConstant.PO_DELETE+"=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, poNumber);
			preparedStatement.setInt(2, 0);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				poGeneration.setFileName(resultSet.getString(DBConstant.PO_FILENAME));
				poGeneration.setToken(resultSet.getString(DBConstant.PO_TOKEN));
			}
		}catch(Exception e){
			System.out.println("Error :" + e.getMessage());
		}
		finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		}
		return poGeneration;
	}


	public int delete(POGeneration poNumber) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getTotalAmount(POGeneration poGeneration) {
		ArrayList<Allocation> allocation = poGeneration.getAllocationList();
		Iterator itr=allocation.iterator();  
	    int totalamount=0;
		// Add total amount
		for (int i=0;i<allocation.size();i++){
			Allocation st=(Allocation)itr.next();
			totalamount+=(int)(Double.parseDouble(st.getAmount()));
		}
		return String.valueOf(totalamount);
	}
}
