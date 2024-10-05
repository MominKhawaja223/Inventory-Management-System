package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.InventoryItem;
import model.InventoryItems;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;

public class AddProductController extends Controller<InventoryItems>{
	@FXML
	private TextField idField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField descField;

	@FXML
	private TextField qtyField;

	@FXML
	private TextField minStockField;

	@FXML
	private TextField maxStockField;

	@FXML
	private Button addButton;

	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	public AddProductController() 
	{
	}

	@FXML
	private void initialize() 
	{
		if (addButton != null) {
			addButton.disableProperty().bind(
					idField.textProperty().length().lessThan(1));
			addButton.disableProperty().bind(
					nameField.textProperty().length().lessThan(1));
			addButton.disableProperty().bind(
					descField.textProperty().length().lessThan(1));
			addButton.disableProperty().bind(
					qtyField.textProperty().length().lessThan(1));
			addButton.disableProperty().bind(
					minStockField.textProperty().length().lessThan(1));
			addButton.disableProperty().bind(
					maxStockField.textProperty().length().lessThan(1));
		}
	}

	@FXML
	private void onTextEntered() 
	{
	}

	@FXML
	private void addProductHandler() 
	{
		String id = idField.getText();
		String name = nameField.getText();
		String desc = descField.getText();
		String qty = qtyField.getText();
		String minStock = minStockField.getText();
		String maxStock = maxStockField.getText();

		int productId = Integer.parseInt(id);
		int qtyInt = Integer.parseInt(qty);
		int minStockInt = Integer.parseInt(minStock);
		int maxStockInt = Integer.parseInt(maxStock);

		InventoryItem product = new InventoryItem(productId, name, desc, qtyInt, 0, minStockInt, maxStockInt);
		try {
			model.addProduct(product);
			//System.out.println("After addition model size : " + model.getProducts().size());
			idField.setText("");
			nameField.setText("");
			descField.setText("");
			qtyField.setText("");
			minStockField.setText("");
			maxStockField.setText("");
			Stage stage = new Stage();
			stage.setX(ViewLoader.X + 601);
			stage.setY(ViewLoader.Y);
			stage.getIcons().add(new Image("/image/inventory.jpeg"));
			try {
				ViewLoader.showStage(new ErrorModel(new Exception(), "Successfully added product!!!"), "/view/Error/ErrorView.fxml", "Success View", stage);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} catch (DuplicateItemException e) {
			e.printStackTrace();
			Stage stage = new Stage();
			stage.setX(ViewLoader.X + 601);
			stage.setY(ViewLoader.Y);
			stage.getIcons().add(new Image("/image/error_icon.png"));
			try {
				ViewLoader.showStage(new ErrorModel(e, "Product already added!"), "/view/Error/ErrorView.fxml", "ErrorView", stage);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@FXML
	private void closeProductHandler() 
	{
		stage.close();
	}
}