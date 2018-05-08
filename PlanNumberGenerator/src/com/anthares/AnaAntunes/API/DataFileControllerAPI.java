package com.anthares.AnaAntunes.API;

import com.anthares.AnaAntunes.FileContent;
import com.anthares.AnaAntunes.GenerateNumber;

public class DataFileControllerAPI implements DataFileController {

	private static DataFileControllerAPI dataFileControllerAPI = null;
	private static GenerateNumber generateNumber = null;
	
	private static FileContent fileContent;

	private DataFileControllerAPI() {}
	
	//Contructor
	public static DataFileController getCurrentInstance() {
		if ( null == dataFileControllerAPI ) {
			dataFileControllerAPI = new DataFileControllerAPI();
			generateNumber = new GenerateNumber("DataTest");
			fileContent = new FileContent();
		}
		return dataFileControllerAPI;
	}
	
	
	
	@Override
	public void startApplication() {
		getCurrentInstance();
		fileContent = generateNumber.generateData();
	}
	
	@Override
	public void updateFile(FileContent fileContent) {
		
		generateNumber.updateFile(fileContent);
		
		
		DataFileControllerAPI.fileContent = fileContent;
	}

	@Override
	public FileContent getCurrentFileContent() {
		return fileContent;
	}


}
