package com.icm.pogeneration.model;


import java.io.Serializable;
import java.util.ArrayList;

import com.icm.pogeneration.util.CustomDate;

public class POGeneration implements Serializable{

	/**
	 * @author raju
	 */
	private static final long serialVersionUID = 1L;

	private int poId = 0;
	
    private int delete = 0;
    
	private String token;
	
	private int downloadCount = 0;
	
	private ArrayList<String> payment_terms;
	
	private ArrayList<Allocation> allocationList;
	

	public ArrayList<String> getPayment_terms() {
		return payment_terms;
	}

	public void setPayment_terms(ArrayList<String> payment_terms) {
		this.payment_terms = payment_terms;
	}
		
	public ArrayList<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(ArrayList<Allocation> allocationList) {
		this.allocationList = allocationList;
	}





	private String fileName;
    
    private String expirationDate = null;
    
	private String potype;
	
	private String ponumber;
		
	private String name;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;

	private String description;
	
	private String period;
	
	private String quantity;
	
	private String rate;
	
	private String amount;
	
	private String totalamount;
	
	
  // MM/DD/YYYY
	private String date;
	
	private String downloadUrl;
	
	private boolean status=false;
	
	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}
	
	  // MM/DD/YYYY
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getPotype() {
		return potype;
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

	public String getExpirationDate() {
		return CustomDate.getExpireDate();
	}

	public void setPotype(String potype) {
		this.potype = potype;
	}
	public String getPonumber() {
		return ponumber;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAmmount() {
		return amount;
	}
	public void setAmmount(String ammount) {
		this.amount = ammount;
	}
}
