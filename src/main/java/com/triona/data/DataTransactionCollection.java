package com.triona.data;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTransactionCollection extends ArrayList<DataTransaction> {

	private static final long serialVersionUID = 1L; //unique id
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public DataTransactionCollection() {
		
	}
	
	public DataTransactionCollection(List<Object> data) {
		serializeData(data).forEach(t-> this.add(t)); //handles duplicates
	}
	
	
	public DataTransactionCollection(DataTransaction[] data) {
		Arrays.asList(data).forEach(dt -> this.add(dt));
	}
	
	
	private List<DataTransaction> serializeData(List<Object> data){
		DataTransaction[] objectData = null;
		try {
			String listToJson = objectMapper.writeValueAsString(data);
			objectData = objectMapper.readValue(listToJson, DataTransaction[].class);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.asList(objectData);
	}
	
	
	public DataTransaction get(String date, String type){
		return this.stream()
				.filter(t -> t.hashCode() == Objects.hash(date, type))
				.findFirst()
				.get();
	}
	

	/** 
	 * adds a single transaction to the existing collection. Performs check to to see if transaction 
	 * exists already - if so, sum the amounts and update the existing transaction 
	 * else, add as new transaction
	 */
	@Override
	public boolean add(DataTransaction t) {
		if(this.contains(t)){ 
			DataTransaction existing = this.get(this.indexOf(t));
			existing.updateAmount(t.getAmount());
		}else {
			super.add(t);
		}
		
		return true;
	}
	
	

	@Override
	public boolean addAll(Collection<? extends DataTransaction> transactions) {
		transactions.stream()
			.forEach(t -> this.add(t));
		return true;
	}



	
	

}
