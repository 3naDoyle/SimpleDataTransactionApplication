package com.triona.data;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonValue;

public class DataTransaction {
	
    @NotEmpty
    @NotNull
    @NotBlank
	private String date;
    @NotEmpty
    @NotNull
    @NotBlank
	private String type;
    @NotEmpty
    @NotNull
    @NotBlank
	private String amount;
	
	public DataTransaction() {
		// Default constructor used for deseralization of the DataTransaction object
	}
	
	public DataTransaction(String date, String type, String amount){ 
		this.date=date;
		this.type=type;
		this.amount=amount;
		
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	public String updateAmount(String value) {
		double currentValue = Double.parseDouble(getAmount());
		double updatedValue = currentValue+Double.parseDouble(value);
		setAmount(Double.toString(updatedValue));
		return getAmount();		
	}
	
	
	/**
	 * A DataTransaction object is only equal to another DataTransaction object
	 * if their attributes date & type values are the same. (see hasCode())
	 * @param Object o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		DataTransaction t = (DataTransaction) o;
		return this.hashCode() == t.hashCode();	
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getDate(),this.getType());
	}

	 @Override
	 @JsonValue
	public String toString(){
		return "date:"+getDate() +",type:"+getType()+",amount:"+getAmount();
	}
}
