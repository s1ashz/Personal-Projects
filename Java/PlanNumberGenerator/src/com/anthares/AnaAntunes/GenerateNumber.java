package com.anthares.AnaAntunes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.Logger;

public class GenerateNumber {

	private final Logger LOG = Logger.getLogger(GenerateNumber.class.getName());
	
	private String fileName;
	private int currentNumber;
	
	public GenerateNumber(String fileName) {
		if( null != fileName ) {
			this.fileName = fileName;
		} else {
			this.fileName = "No_Name_Specified.txt";
		}
	}

	public FileContent generateData() {
		LOG.info("Checking if file exists");
		FileContent fileContent = null;
		if (checkFileExists()) {
			fileContent = readExistingFile();
		} else {
			fileContent = generateDefaultFileContent();
			LOG.info("Creating new file........");
			writeFile(fileContent);
		}
		return fileContent;
	}

	public void updateFile(FileContent fileContent) {
		writeFile(fileContent);
	}
		
	private void writeFile(FileContent fileContentToWrite) {

		File dataFile = new File(fileName);
		try {
			FileOutputStream fOutStream = new FileOutputStream(dataFile);
			ObjectOutputStream writer = new ObjectOutputStream(fOutStream);
			
			if ( null != fileContentToWrite ) {
				writer.writeObject(fileContentToWrite);
				LOG.info("Writting content to file........");
			}
			writer.close();
			fOutStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private FileContent readExistingFile() {
		File dataFile = new File(fileName);
		FileContent currentFileContent = null;
		try {

			FileInputStream fInStream = new FileInputStream(dataFile);
			ObjectInputStream reader = new ObjectInputStream(fInStream);
			
			currentFileContent = (FileContent) reader.readObject();
	        LOG.info("Reading content of existing file........");
	        LOG.info("File last modified at: " + currentFileContent.getLastUpdateDate());
	        
	        reader.close();
	        fInStream.close();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOG.info("File not found");
		} catch (Exception e ) {
			e.printStackTrace();
			LOG.info("Error initializing stream");
		}
		
		return currentFileContent;
	}

	private FileContent generateDefaultFileContent() {
		LOG.info("Generating default content........");
		
		FileContent newFileContent = new FileContent();
		
		Date date = new Date();
		
		newFileContent = new FileContent();
		newFileContent.setCurrentYear(18);
		newFileContent.setBoat("ANT");
		newFileContent.setCurrentLiftPNumber(1);
		newFileContent.setCurrentTaskPNumber(1);
		newFileContent.setCurrentLiftPLabel("LP");
		newFileContent.setCurrentTaskPLabel("TP");
		newFileContent.setLastUpdateDate( date.getDay() + "/" + date.getMonth() + "/" + date.getYear() );
		
		return newFileContent;
	}
	
	private boolean checkFileExists() {
		File myFile = new File(fileName);
		return myFile.exists();
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

}
