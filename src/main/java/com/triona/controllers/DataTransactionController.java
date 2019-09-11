package com.triona.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.triona.services.FileService;

@RestController
public class DataTransactionController {
	
    @Autowired
	FileService fileService = new FileService();

	@PostMapping(value = "/addTransaction", consumes = "application/json", produces = "application/json")
	public String add(@RequestBody List<Object> dataTransactions) {
		String addedTransactions = fileService.addDataTransaction(dataTransactions);
	    return addedTransactions; // duplicates handled (merged & values summed)
	}
	
	 @GetMapping(value="/getSavedTransactions",produces = "application/json")
	 @ResponseBody
	    public String getPersistentData() {
		 String json = fileService.getDataAsJson(fileService.getPersistantFileData());
	        return json;
	 }
	 

	
}
