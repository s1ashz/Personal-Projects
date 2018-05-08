package com.anthares.AnaAntunes.API;

import com.anthares.AnaAntunes.FileContent;

public interface DataFileController {

	public void startApplication();
	public void updateFile(FileContent fileContent);
	
	public FileContent getCurrentFileContent(); 

	
}
