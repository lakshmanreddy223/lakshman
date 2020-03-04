package com.icm.pogeneration.model;

public class ResponsePO {

	private String ponumber;
	private String tokenURL;
	private String filename;
	private String totalamount;
	
	
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getPonumber() {
		return ponumber;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public String getTokenURL() {
		return tokenURL;
	}
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
