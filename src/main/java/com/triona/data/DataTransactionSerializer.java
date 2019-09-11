package com.triona.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class DataTransactionSerializer extends StdSerializer<DataTransaction> { 
	
	 public DataTransactionSerializer(Class<DataTransaction> t) {
	        super(t);
	    }

	@Override
	public void serialize(DataTransaction dataTransaction, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("date", dataTransaction.getDate());
		gen.writeStringField("type", dataTransaction.getType());
		gen.writeStringField("amount", dataTransaction.getAmount());
        gen.writeEndObject();
		
	}


}
