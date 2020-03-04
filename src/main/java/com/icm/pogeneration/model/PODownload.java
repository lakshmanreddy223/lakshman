package com.icm.pogeneration.model;

import java.io.Serializable;

import com.icm.pogeneration.util.DBConstant;

public class PODownload implements Serializable{

	private static final long serialVersionUID = 1L;

	private int poId = 0;
	
    private int delete = 0;
    
	private String token;
	
	private int downloadCount = 0;
	
    private String fileName;
    
    private String date;
    
    private String expirationDate;
    
    private String poNumber;
        
    private boolean status=false;

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getDelete() {
		return delete;
	}

	public static String getPath() {
		return System.getProperty("catalina.base")+"/webapps/"+DBConstant.PO_Folder_Name;

	}
	public void setDelete(int delete) {
		this.delete = delete;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
    
}
