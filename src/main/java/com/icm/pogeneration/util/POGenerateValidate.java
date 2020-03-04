package com.icm.pogeneration.util;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.icm.pogeneration.model.Allocation;

public class POGenerateValidate {

	public static boolean validate(String ponumber,String podate, String potype, String name, /*String address,*/ String city, String state, String pincode, ArrayList<Allocation> allocationList) {
		   if(StringUtils.isBlank(ponumber) || StringUtils.isBlank(podate) || StringUtils.isBlank(potype) || StringUtils.isBlank(name) /*|| StringUtils.isBlank(address)*/ || StringUtils.isBlank(city) || 
				   StringUtils.isBlank(state) || StringUtils.isBlank(pincode) || allocationList.isEmpty()){
		    return false; 
		   }
		return true;
		}
}
 