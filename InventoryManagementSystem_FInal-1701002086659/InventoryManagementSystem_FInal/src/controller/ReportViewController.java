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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Inventory;
import model.InventoryItem;
import model.PurchaseOrder;
import model.Exceptions.ErrorModel;

public class ReportViewController extends Controller<Inventory> implements Initializable{
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;


	@FXML
	private TableView<InventoryItem> inventoryTable;
	
	@FXML
	private TableView<PurchaseOrder> poTable;
	
	@FXML
	private TableView<InventoryItem> salesTable;

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
	public TableColumn<PurchaseOrder, Integer> poProductId;
	

	@FXML
	public TableColumn<PurchaseOrder, Integer> poQuantity;
	

	@FXML
	public TableColumn<PurchaseOrder, String> orderDate;
	
	
	
	
	public ReportViewController() 
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
		
		
		poProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
		poQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
	}

	@FXML
	private void closeReportHandler() 
	{
		stage.close();
	}
	
	@FXML
	private void exportInventoryHandler() 
	{
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		try {
			ViewLoader.showStage(new ErrorModel(new Exception(), "Successfully exported inventories!!!"), "/view/Error/ErrorView.fxml", "Export View", stage);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
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
	}
	
	@FXML
	private void exportPOHandler() 
	{
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		try {
			ViewLoader.showStage(new ErrorModel(new Exception(), "Successfully exported Purchase Orders!!!"), "/view/Error/ErrorView.fxml", "Export View", stage);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(new File("purchase_orders.csv"), true));
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
	private void exportSalesHandler() 
	{
		Stage stage = new Stage();
		stage.setX(ViewLoader.X + 601);
		stage.setY(ViewLoader.Y);
		stage.getIcons().add(new Image("/image/inventory.jpeg"));
		try {
			ViewLoader.showStage(new ErrorModel(new Exception(), "Sorry no sales exist!!!"), "/view/Error/ErrorView.fxml", "Export View", stage);
		} catch (IOException ex) {
			ex.printStackTrace();
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

		
		poProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
		poQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
	    	
		ObservableList<InventoryItem> products = model.getProducts().getProducts();
		Property<ObservableList<InventoryItem>> productListProperty = new SimpleObjectProperty<>(products);
		inventoryTable.itemsProperty().bind(productListProperty);
		
		Property<ObservableList<PurchaseOrder>> poListProperty = new SimpleObjectProperty<>(model.getPurchaseOrders());
		poTable.itemsProperty().bind(poListProperty);
		
		//allFlightsTable.setItems(flights);
		
	}
	
}