package com.anthares.AnaAntunes;

import com.anthares.AnaAntunes.API.DataFileControllerAPI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	public static void main(String[] args) {
		
		DataFileControllerAPI.getCurrentInstance().startApplication();
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			primaryStage.setTitle("Plan Number Generator");
			primaryStage.setScene(new Scene(root, 600, 400));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
