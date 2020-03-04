package com.icm.pogeneration.persistance;

public interface PersistanceDao<T>{
	
	public String save(T poGeneration);
	
	public String update(T poGeneration);
	
	public int delete(T poNumber);
	
	public T getByPONumber(String poNumber);

}
