package com.icm.pogeneration.model;

import java.util.ArrayList;

import com.icm.pogeneration.dao.impl.POGenerationDaoImpl;

public class TestPO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		POGenerationDaoImpl poGenDaoImp = new POGenerationDaoImpl();
		
		POGeneration pogen= new POGeneration();
		ArrayList<Allocation> allocationList = new ArrayList<Allocation>();
		Allocation allocation = new Allocation();
		
		allocation.setAmount("15000");
		allocation.setCostcenter("Business_Development");
		allocation.setDescription("dsfdf");
		allocation.setPeriod("2018-2019");
		allocation.setQuantity("1");
		allocation.setRate("15000");
		
		allocationList.add(allocation);
		
		pogen.setPonumber("new");
		pogen.setPotype("subscription");
		pogen.setName("Hemant Kumar");
		pogen.setAddress("Jaipur");
		pogen.setCity("Jaipur");
		pogen.setPincode("111111");
		pogen.setState("Rajasthan");
		pogen.setDate("12/12/2018");
		pogen.setAllocationList(allocationList);
		
		
		POGeneration output = poGenDaoImp.generatePO(pogen);
		System.out.println(output.getToken());
		
	}

}
