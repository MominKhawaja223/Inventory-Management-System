package model;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;

import javafx.collections.*;
import model.Exceptions.*;

public class InventoryItems {
	private ObservableList<InventoryItem> products;
	private Inventory inventory;

	public InventoryItems(Inventory inventory) {
		this.inventory = inventory;
		this.products = FXCollections.<InventoryItem>observableArrayList();
	}

	public ObservableList<InventoryItem> getProducts() {
		return this.products;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void addProduct(InventoryItem product) throws DuplicateItemException {
		if (hasProduct(product.getProductName())) { throw new DuplicateItemException(); }
		this.products.add(product);
	}

	public void removeProduct(InventoryItem product) throws ItemNotFoundException {
		if (!hasProduct(product.getProductName())) { throw new ItemNotFoundException(); }
		this.products.remove(product);
	}

	public boolean hasProduct(String name) {
		for (InventoryItem f : this.products) { 
			if (f.getProductName().equals(name)) 
			{ 
				return true; 
			} 
		}
		return false;
	}

	public InventoryItem getProduct(String name) throws ItemNotFoundException {
		for (InventoryItem f : this.products) { 
			if (f.getProductName().equals(name)) 
			{ 
				return f; 
			} 
		}
		throw new ItemNotFoundException();
	}

	public ObservableList<InventoryItem> getFilteredProducts(String name) {
		LinkedList<InventoryItem> filtered = new LinkedList<InventoryItem>();
		for (InventoryItem f : products) { 
			if (f.getProductName().toLowerCase().contains(name.toLowerCase())) 
			{ 
				filtered.add(f); 
			} 
		}
		return FXCollections.<InventoryItem>observableArrayList(filtered);
	}

	public void insertDummyData() {
		/*	products.add(new Product(1, "Pen", "A Pilot pen", 10, 15,  8 , 20));
		products.add(new Product(2, "Pencil", "A natraj pencil", 11, 14,  8 , 20));
		products.add(new Product(3, "Eraser", "Eraser", 10, 15,  8 , 20));
		products.add(new Product(4, "Sharpner", "Sharpner", 10, 15,  8 , 20));
		products.add(new Product(5, "Whitener", "Whitener", 10, 15,  8 , 20));
		products.add(new Product(6, "Scale", "Scale", 10, 15,  8 , 20));
		products.add(new Product(7, "Compass", "Compass", 10, 15,  8 , 20));
		products.add(new Product(8, "Notebook", "Notebook", 10, 15,  8 , 20));
		products.add(new Product(9, "Color Pencil", "Color Pencil", 10, 15,  8 , 20));
		products.add(new Product(10, "Writing Pad", "Writing", 10, 15,  8 , 20));
		 */

	}

}
