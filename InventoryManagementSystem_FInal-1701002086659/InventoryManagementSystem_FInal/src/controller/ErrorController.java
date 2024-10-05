package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Exceptions.ErrorModel;

public class ErrorController  extends Controller<ErrorModel> {
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	@FXML
	private Label error1Label;

	@FXML
	private Label error2Label;
	
	public ErrorController() 
	{

	}

	@FXML
	private void initialize() 
	{
		String label1 = model.getException().getClass().getSimpleName();
		String label2 = model.getMessage();
		setLabels(label1, label2);
	}
	
	@FXML
	private void errorCloseHandler() 
	{
		stage.close();
	}
	
	public void setLabels(String label1, String label2) {
		error1Label.setText(label1);
		error2Label.setText(label2);
	}
    
}
