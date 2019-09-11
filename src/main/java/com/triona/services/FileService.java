package com.triona.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.triona.data.DataTransaction;
import com.triona.data.DataTransactionCollection;
import com.triona.data.DataTransactionSerializer;

@Service
public class FileService {
	private ObjectMapper objectMapper = new ObjectMapper();
	private File dataTransactionFile;
	private static String LOCAL_FILE_LOCATION = "C:\\dataTransactions\\transcations.json";

	public FileService() {
		// create empty directory (where persistence file will be located)
		dataTransactionFile = new File(LOCAL_FILE_LOCATION);
		if (!dataTransactionFile.exists()) {
			dataTransactionFile.getParentFile().mkdirs(); // does nothing if already created
		}
		// register Serializer with Object Mapper
		registerDataTransactionSerializer();
	}
	
	private void registerDataTransactionSerializer() {
		//register DataTransaction serializer with Object Mapper
		DataTransactionSerializer serializer = new DataTransactionSerializer(DataTransaction.class);
		SimpleModule module = new SimpleModule("DataTransactionSeralizer", new Version(2, 1, 3, null, null, null));
		module.addSerializer(DataTransaction.class, serializer);
		objectMapper.registerModule(module);	
	}

	/**
	 * Adds new transactions and updates existing transactions in persistence file
	 * @param postTransactionData
	 */
	public String addDataTransaction(List<Object> postTransactionData) {
		DataTransactionCollection transactionData = new DataTransactionCollection(postTransactionData);
		String postTransactions = getDataAsJson(transactionData);

		if (!persistanceFileHasData()) {
			createPersitanceFile(transactionData);
		} else {
			DataTransactionCollection persistantData = getPersistantFileData();
			persistantData.addAll(transactionData); //handles duplicates
			updatePersistenceFile(persistantData);
		}

		return postTransactions;
	}

	public String getSavedTransactions() {
		return getDataAsJson(getPersistantFileData());
	}

	/**
	 * Rewrites the local transaction file with updated transaction data
	 */
	private void updatePersistenceFile(DataTransactionCollection persistanceData) {
		try {
			objectMapper.writeValue(dataTransactionFile, persistanceData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if persistence file contains any data (includes existence check)
	 * @return boolean - has data?
	 */
	private boolean persistanceFileHasData() {
		dataTransactionFile = new File(LOCAL_FILE_LOCATION);
		System.out.println(dataTransactionFile.exists() + "  " + dataTransactionFile.length());
		return dataTransactionFile.exists() && !(dataTransactionFile.length() == 0) ? true : false;
	}

	/**
	 * Get data from persistence file (Mapped to DataTransaction Object)
	 * @return DataTransactionCollection - all DataTransaction objects in
	 *         persistence file or empty object if no data
	 */
	public DataTransactionCollection getPersistantFileData() {
		DataTransaction[] fileContents = null;

		try {
			byte[] jsonData = Files.readAllBytes(Paths.get(LOCAL_FILE_LOCATION));
			fileContents = objectMapper.readValue(jsonData, DataTransaction[].class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return !(fileContents == null) ? new DataTransactionCollection(fileContents) : new DataTransactionCollection();
	}

	
	private void createPersitanceFile(DataTransactionCollection dt) {

		if (!dataTransactionFile.exists()) {
			dataTransactionFile.getParentFile().mkdirs();
		}
		try {
			objectMapper.writeValue(dataTransactionFile, dt);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getDataAsJson(DataTransactionCollection dt) {
		String json = "";

		try {
			json = objectMapper.writeValueAsString(dt);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
	
	public DataTransaction[] getDataTransactionFile(String path) {
		DataTransaction[] fileContents = null;

		try {
			byte[] jsonData = Files.readAllBytes(Paths.get(path));
			fileContents = objectMapper.readValue(jsonData, DataTransaction[].class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContents;
	}

}
