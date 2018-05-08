package com.anthares.AnaAntunes.controller;

import java.util.function.UnaryOperator;

import com.anthares.AnaAntunes.FileContent;
import com.anthares.AnaAntunes.API.DataFileControllerAPI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.VBox;

public class Controller {

	FileContent fileContent;
	FileContent tempFileContent;
	
	@FXML
    private Button acceptLPButton;
	
	@FXML
    private Button acceptTPButton;
	
	@FXML
    private Button deleteLPButton;
	
	@FXML
    private Button deleteTPButton;
	
	@FXML
	private VBox newGeneratedNumberLPVBoxLayout;
	
	@FXML
	private VBox newGeneratedNumberTPVBoxLayout;
	
	@FXML
	private Label liftPlanLabel;
	
	@FXML
	private Label taskPlanLabel;
	
	@FXML
	private Label generatedLiftPlanLabel;
	
	@FXML
	private Label generatedTaskPlanLabel;
	
	@FXML
    public void initialize() {
		fileContent = getFileContent();
		updateLPLabel(liftPlanLabel, fileContent);
		updateTPLabel(taskPlanLabel, fileContent);
		updateSettings();
		formatTextFieldNumbers();
    }
	
	//=========================================== LIFT PLAN ===========================================
	
	public void updateLPLabel(Label label, FileContent fileContentToUpdate) {
		label.setText(fileContentToUpdate.getBoat() + "-" + fileContentToUpdate.getCurrentLiftPLabel() + "-" + fileContentToUpdate.getCurrentYear() + "-" + String.format("%03d", fileContentToUpdate.getCurrentLiftPNumber() ));
	}
	
	public void generateNewLiftPlanNumber(ActionEvent event) {
		newGeneratedNumberLPVBoxLayout.setVisible(true);
		
		fileContent = getFileContent();
		tempFileContent = new FileContent();
		
		tempFileContent = fileContentDto(fileContent);
		
		System.out.println("-------");
		System.out.println(fileContent.getCurrentLiftPNumber());
		System.out.println(tempFileContent.getCurrentLiftPNumber());
		
		tempFileContent.setCurrentLiftPNumber( tempFileContent.getCurrentLiftPNumber() + 1 );
		
		
		System.out.println(fileContent.getCurrentLiftPNumber());
		System.out.println(tempFileContent.getCurrentLiftPNumber());
		
		
		updateLPLabel(generatedLiftPlanLabel, tempFileContent);
		
		
		//System.out.println( "number " + getFileContent().getCurrentLiftPNumber() + " date " + getFileContent().getLastUpdateDate());
	}
	
	public void deleteLPButtonClick(ActionEvent event) {
		newGeneratedNumberLPVBoxLayout.setVisible(false);
	}
	
	public void acceptLPButtonClick(ActionEvent event) {
		DataFileControllerAPI.getCurrentInstance().updateFile(tempFileContent);
		updateLPLabel(liftPlanLabel, tempFileContent);
		newGeneratedNumberLPVBoxLayout.setVisible(false);
	}
	
	//=========================================== LIFT PLAN ===========================================
	
	//=========================================== TASK PLAN ===========================================
	
	public void updateTPLabel(Label label, FileContent fileContentToUpdate) {
		label.setText(fileContentToUpdate.getBoat() + "-" + fileContentToUpdate.getCurrentTaskPLabel() + "-" + fileContentToUpdate.getCurrentYear() + "-" + String.format("%03d", fileContentToUpdate.getCurrentTaskPNumber() ));
	}
	
	public void generateNewTaskPlanNumber(ActionEvent event) {
		newGeneratedNumberTPVBoxLayout.setVisible(true);
		
		fileContent = getFileContent();
		tempFileContent = new FileContent();
		
		tempFileContent = fileContentDto(fileContent);
		
		System.out.println("-------");
		System.out.println(fileContent.getCurrentTaskPNumber());
		System.out.println(tempFileContent.getCurrentTaskPNumber());
		
		tempFileContent.setCurrentTaskPNumber( tempFileContent.getCurrentTaskPNumber() + 1 );
		
		
		System.out.println(fileContent.getCurrentTaskPNumber());
		System.out.println(tempFileContent.getCurrentTaskPNumber());
		
		
		updateTPLabel(generatedTaskPlanLabel, tempFileContent);
		
		//System.out.println( "number " + getFileContent().getCurrentLiftPNumber() + " date " + getFileContent().getLastUpdateDate());
	}
	
	
	
	public void deleteTPButtonClick(ActionEvent event) {
		newGeneratedNumberTPVBoxLayout.setVisible(false);
	}
	
	public void acceptTPButtonClick(ActionEvent event) {
		DataFileControllerAPI.getCurrentInstance().updateFile(tempFileContent);
		updateTPLabel(taskPlanLabel, tempFileContent);
		newGeneratedNumberTPVBoxLayout.setVisible(false);
	}
	
	//=========================================== TASK PLAN ===========================================
	
	//=========================================== SETTINGS ===========================================
	
		@FXML
		private TextField settingsBoatLabel;
		
		@FXML
		private TextField settingsYearLabel;
		
		@FXML
		private TextField settingsLPLabel;
		
		@FXML
		private TextField settingsLPNumberLabel;
		
		@FXML
		private TextField settingsTPLabel;
		
		@FXML
		private TextField settingsTPNumberLabel;
		
		@FXML
		private Button updateSettingsBtn;
		
		
		
		public void setUpdateBtnVisible() {
			updateSettingsBtn.setVisible(true);
		}
		
		
		public void updateSettingsClick(ActionEvent event) {
			
			FileContent currentFileContent = getFileContent();
			
			currentFileContent.setBoat( settingsBoatLabel.getText() );
			currentFileContent.setCurrentYear( Long.valueOf( settingsYearLabel.getText() ) );
			currentFileContent.setCurrentLiftPLabel( settingsLPLabel.getText() );
			currentFileContent.setCurrentLiftPNumber( Long.valueOf(  settingsLPNumberLabel.getText() ) );
			currentFileContent.setCurrentTaskPLabel( settingsTPLabel.getText() );
			currentFileContent.setCurrentTaskPNumber( Long.valueOf(  settingsTPNumberLabel.getText() ) );
			
			
			DataFileControllerAPI.getCurrentInstance().updateFile(currentFileContent);
			
			updateLabelsWithNewFile();
			
			updateSettingsBtn.setVisible(false);
		}
		
		
		
		
		
		
		private void updateSettings() {
			
			
			
			
			
			
			FileContent updatedFileContent = getFileContent(); 
			
			settingsBoatLabel.setText(updatedFileContent.getBoat());
			settingsYearLabel.setText(Long.toString( updatedFileContent.getCurrentYear() ));
			settingsLPLabel.setText(updatedFileContent.getCurrentLiftPLabel());
			settingsLPNumberLabel.setText(Long.toString( updatedFileContent.getCurrentLiftPNumber() ));
			settingsTPLabel.setText(updatedFileContent.getCurrentTaskPLabel());
			settingsTPNumberLabel.setText(Long.toString( updatedFileContent.getCurrentTaskPNumber() ));
			
			updateSettingsBtn.setVisible(false);
		}

		private void formatTextFieldNumbers() {
			UnaryOperator<Change> filter = change -> {
				String text = change.getText();
				
				if (text.matches("[0-9]*")) {
					return change;
				}
				
				return null;
			};
			TextFormatter<String> settingsYearLabelFormatter = new TextFormatter<>(filter);
			TextFormatter<String> settingsLPNumberLabelFormatter = new TextFormatter<>(filter);
			TextFormatter<String> settingsTPNumberLabelFormatter = new TextFormatter<>(filter);
			
			settingsYearLabel.setTextFormatter(settingsYearLabelFormatter);
			settingsLPNumberLabel.setTextFormatter(settingsLPNumberLabelFormatter);
			settingsTPNumberLabel.setTextFormatter(settingsTPNumberLabelFormatter);
			
		}
		
		//=========================================== SETTINGS ===========================================
		
		
		
	private void updateLabelsWithNewFile() {
		
		FileContent currentFileContent = getFileContent();
		updateLPLabel(liftPlanLabel, currentFileContent);
		updateTPLabel(taskPlanLabel, currentFileContent);
		
	}
		
		
		
		
		
	
	private FileContent getFileContent() {
		return DataFileControllerAPI.getCurrentInstance().getCurrentFileContent();
	}
	
	private FileContent fileContentDto(FileContent existingFileContent) {
		
		FileContent tmp = new FileContent();

		tmp.setBoat(existingFileContent.getBoat());
		tmp.setCurrentLiftPLabel(existingFileContent.getCurrentLiftPLabel());
		tmp.setCurrentLiftPNumber(existingFileContent.getCurrentLiftPNumber());
		tmp.setCurrentTaskPLabel(existingFileContent.getCurrentTaskPLabel());
		tmp.setCurrentTaskPNumber(existingFileContent.getCurrentTaskPNumber());
		
		tmp.setCurrentTaskPNumber(existingFileContent.getCurrentTaskPNumber());
		tmp.setCurrentYear(existingFileContent.getCurrentYear());
		tmp.setLastUpdateDate(existingFileContent.getLastUpdateDate());
		
		return tmp;
	}
}
