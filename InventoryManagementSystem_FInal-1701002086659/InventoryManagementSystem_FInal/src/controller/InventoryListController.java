package controller;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.opencsv.CSVWriter;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Inventory;
import model.POItem;
import model.PurchaseOrder;
import model.InventoryItem;
import model.Exceptions.ErrorModel;

public class InventoryListController extends Controller<Inventory> implements Initializable{
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;


	@FXML
	private TableView<InventoryItem> allProductsTable;

	@FXML
	public TableColumn<InventoryItem, String> productID;

	@FXML
	public TableColumn<InventoryItem, Integer> productName;

	@FXML
	public TableColumn<InventoryItem, String> productDescription;
	
	@FXML
	public TableColumn<InventoryItem, Integer> onHandQty;
	
	@FXML
	public TableColumn<InventoryItem, Integer> onOrderQty;
	

	@FXML
	public TableColumn<InventoryItem, Integer> minStockLevel;
	
	@FXML
	public TableColumn<InventoryItem, Integer> maxStockLevel;

	
	@FXML
	private TextField filterProducts;

	
	
	public InventoryListController() 
	{

	}

	@FXML
	private void initialize() 
	{
		productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
		onHandQty.setCellValueFactory(new PropertyValueFactory<>("onHandQty"));
		onOrderQty.setCellValueFactory(new PropertyValueFactory<>("onOrderQty"));
		minStockLevel.setCellValueFactory(new PropertyValueFactory<>("minStockLevel"));
		maxStockLevel.setCellValueFactory(new PropertyValueFactory<>("maxStockLevel"));
	}

	@FXML
	private void closeHandler() 
	{
	    saveInventoryData();
		stage.close();
	}
	
	private void saveInventoryData() {
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(new File("products.csv")));
			for(InventoryItem p : model.getProducts().getProducts()) {
				String[] record = p.getCSVString().split(",");
				writer.writeNext(record, false);
			}
		    writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writer = null;
		try {
			writer = new CSVWriter(new FileWriter(new File("purchase_orders.csv")));
			for(PurchaseOrder p : model.getPurchaseOrders()) {
				String[] record = p.getCSVString().split(",");
				writer.writeNext(record, false);
			}
		    writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	private void addNewInventoryItem() 
	{
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		try {
			ViewLoader.showStage(model.getProducts(), "/view/Products/AddProductView.fxml", "Add Products", stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void createPurchaseOrder() 
	{
		InventoryItem product =  allProductsTable.getSelectionModel().getSelectedItem();
		if (product == null) {
			Stage stage = new Stage();
			stage.setX(ViewLoader.X + 601);
			stage.setY(ViewLoader.Y);
			stage.getIcons().add(new Image("/image/error_icon.png"));
			try {
				ViewLoader.showStage(new ErrorModel(new Exception(), "Please select a product to make PO!"), "/view/Error/ErrorView.fxml", "ErrorView", stage);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		POItem poItem = new POItem();
		poItem.inventory = model;
		poItem.product = product;
		try {
			ViewLoader.showStage(poItem, "/view/Products/PurchaseOrder.fxml", "Purchase Order", stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void generateReports() 
	{
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		try {
			ViewLoader.showStage(model, "/view/ReportView.fxml", "View Reports", stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
		onHandQty.setCellValueFactory(new PropertyValueFactory<>("onHandQty"));
		onOrderQty.setCellValueFactory(new PropertyValueFactory<>("onOrderQty"));
		minStockLevel.setCellValueFactory(new PropertyValueFactory<>("minStockLevel"));
		maxStockLevel.setCellValueFactory(new PropertyValueFactory<>("maxStockLevel"));
	    viewInventoryItemDetails();	
	}
	
	
	private void viewInventoryItemDetails() {
		ObservableList<InventoryItem> products = model.getProducts().getProducts();
		Property<ObservableList<InventoryItem>> productListProperty = new SimpleObjectProperty<>(products);
		allProductsTable.itemsProperty().bind(productListProperty);
	}
	
	public void refreshTable() {
		allProductsTable.refresh();
	}
	
	@FXML
	private void searchInventoryItems() 
	{
		String name = filterProducts.getText();
		ObservableList<InventoryItem> flights = model.getProducts().getFilteredProducts(name);
		Property<ObservableList<InventoryItem>> flightListProperty = new SimpleObjectProperty<>(flights);
		allProductsTable.itemsProperty().bind(flightListProperty);
	}

}