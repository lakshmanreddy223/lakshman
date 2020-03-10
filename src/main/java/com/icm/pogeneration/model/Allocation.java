package com.icm.pogeneration.model;

import java.io.Serializable;

/**
 * 
 * @author raju
 *
 */
public class Allocation implements Serializable{

	private static final long serialVersionUID = 1L;

	public String description;
    
    public String period;
	
	public String quantity;
	
	public String rate;
	
	public String amount;
	
	public String costcenter;

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	} 
	
}
