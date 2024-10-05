package model;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

	private InventoryItems products;
	private ObservableList<PurchaseOrder> purchaseOrders;

	public Inventory() {
		this.products = new InventoryItems(this);
		this.purchaseOrders = FXCollections.<PurchaseOrder>observableArrayList();
		//this.products.insertDummyData();	
		loadInventoryData();
	}


	private void loadInventoryData() {
		try {
			CSVReader reader = new CSVReader(new FileReader(new File("products.csv")));
			List<String[]> allData = reader.readAll();

			for (String[] row : allData) {
				int id = Integer.parseInt(row[0]);
				String name = row[1];
				String desc = row[2];
				int onHand =  Integer.parseInt(row[3]);
				int onOrder =Integer.parseInt(row[4]);
				int minStock = Integer.parseInt(row[5]);
				int maxStock =  Integer.parseInt(row[6]);
				InventoryItem product = new InventoryItem(id, name, desc, onHand, onOrder, minStock, maxStock);
				this.products.getProducts().add(product);
			}
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			CSVReader reader = new CSVReader(new FileReader(new File("purchase_orders.csv")));
			List<String[]> allData = reader.readAll();

			for (String[] row : allData) {
				int id = Integer.parseInt(row[0]);
				int qty =  Integer.parseInt(row[1]);
				String date = row[2];
				PurchaseOrder po = new PurchaseOrder(id, date, qty);
				purchaseOrders.add(po);
			}
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}


	public InventoryItems getProducts() {
		return products;
	}

	public void addPO(PurchaseOrder po) {
		purchaseOrders.add(po);
	}

	public ObservableList<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}
}
