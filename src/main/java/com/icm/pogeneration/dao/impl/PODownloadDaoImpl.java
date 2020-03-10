package com.icm.pogeneration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import com.icm.pogeneration.dao.PODownloadDao;
import com.icm.pogeneration.model.PODownload;
import com.icm.pogeneration.util.CustomDate;
import com.icm.pogeneration.util.DBConnection;
import com.icm.pogeneration.util.DBConstant;

/**
 * 
 * @author raju
 *
 */
public class PODownloadDaoImpl implements PODownloadDao{

	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	public PODownload downloadPO(String tokenId) {
		boolean status = false;
		PODownload poDownload = getPOByToken(tokenId);
		if(poDownload!=null) {
			try {
				status = validateTokenId(poDownload, tokenId);
				if(status) {
					poDownload.setStatus(true);
					return poDownload;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else
			return null;
		
		return null;
	}

	public PODownload getPOByToken(String tokenId) {
		PODownload poDownload = null;
		try {
		    		
			connection = DBConnection.getConnection();
			String query = "SELECT * from "+DBConstant.TABLE_NAME+" where "+DBConstant.PO_TOKEN+"=? and "+DBConstant.PO_DELETE+"=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, tokenId);
			preparedStatement.setInt(2, 0);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				poDownload = new PODownload();
				poDownload.setDate(resultSet.getString(DBConstant.PO_DATE));
				poDownload.setFileName(resultSet.getString(DBConstant.PO_FILENAME));
				poDownload.setToken(resultSet.getString(DBConstant.PO_TOKEN));
				poDownload.setExpirationDate(resultSet.getString(DBConstant.PO_EXPIRATION));
				poDownload.setDownloadCount(Integer.parseInt(resultSet.getString(DBConstant.PO_DOWNLOAD_COUNT)));				 
			}
		}catch(Exception e){
			System.out.println(e);
			return poDownload = null;
		}
		finally{
			DBConnection.closeResources(resultSet, preparedStatement, connection);
		}
		return poDownload;
	}
	
	public boolean validateTokenId(PODownload poDownload, String tokenId) throws ParseException {
	    if(tokenId.equals(poDownload.getToken())) {
	    	String expDate = poDownload.getExpirationDate();
	    	String status = CustomDate.validateDate(expDate);
	    	if(status.equals("val") || status.equals("eql")) {
	    		System.out.println("Valid Token");
	    		return true;
	    	}else {
	    		System.out.println("Expire Token");
	    		return false;
	    	}
	    }	 
		return false;
	}
}
