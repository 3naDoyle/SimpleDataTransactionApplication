package com.triona.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Paths;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import com.triona.data.DataTransaction;
import com.triona.data.DataTransactionCollection;
import com.triona.services.FileService;

public class TestDataTransactions {
 
	private static String LOCAL_FILE_LOCATION = "src\\test\\java\\testTransactionData.json";
	FileService f = new FileService();
	DataTransaction [] fileData;
	

	@Before
	public void setup() {
		fileData = f.getDataTransactionFile(LOCAL_FILE_LOCATION);
	}
	
	
	@Test
	public void duplicatesUpdated() {
//		assertTrue(fileData.length==9);
//		for(Object o : fileData) {	
//			if()
//		}
		
	
		
		DataTransactionCollection dataCollection = new DataTransactionCollection(fileData);
		
		//check size after update and merge
		assertTrue(dataCollection.size()==7);
		
		
	}
	
}
