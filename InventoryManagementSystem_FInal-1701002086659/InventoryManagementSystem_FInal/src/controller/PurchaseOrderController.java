package controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.POItem;
import model.PurchaseOrder;
import model.Exceptions.ErrorModel;

public class PurchaseOrderController extends Controller<POItem>{
	@FXML
	private TextField idField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField descField;

	@FXML
	private TextField qtyField;

	@FXML
	private TextField newQtyField;

	@FXML
	private Label currentDate;

	@FXML
	private Button createButton;

	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	public PurchaseOrderController() 
	{
	}

	@FXML
	private void initialize() 
	{
		if (createButton != null) {
			createButton.disableProperty().bind(
					newQtyField.textProperty().length().lessThan(1));
		}

		idField.setText(String.valueOf(model.product.getProductID()));
		nameField.setText(model.product.getProductName());
		descField.setText(model.product.getProductDescription());
		qtyField.setText(String.valueOf(model.product.getOnHandQty()));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		currentDate.setText(dtf.format(now));
	}

	@FXML
	private void onTextEntered() 
	{
	}

	@FXML
	private void createHandler() 
	{
		
		int existing = model.product.getOnHandQty();
		int qtyInt = Integer.parseInt(newQtyField.getText());
		
		if (qtyInt <=0) {
			return;
		}
		
		PurchaseOrder po = new PurchaseOrder(model.product.getProductID(), currentDate.getText(), qtyInt);
		model.inventory.addPO(po);
		try {
			IntegerProperty ip = new SimpleIntegerProperty(existing + qtyInt);
			model.product.setOnHandQty(ip);
			idField.setText("");
			nameField.setText("");
			descField.setText("");
			qtyField.setText("");
			newQtyField.setText("");	
			currentDate.setText("");
			Stage stage = new Stage();
			stage.setX(ViewLoader.X + 601);
			stage.setY(ViewLoader.Y);
			stage.getIcons().add(new Image("/image/inventory.jpeg"));
			try {
				ViewLoader.showStage(new ErrorModel(new Exception(), "Successfully create PO!!!"), "/view/Error/ErrorView.fxml", "Success View", stage);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void closePurchaseHandler() 
	{
		stage.close();
	}
}