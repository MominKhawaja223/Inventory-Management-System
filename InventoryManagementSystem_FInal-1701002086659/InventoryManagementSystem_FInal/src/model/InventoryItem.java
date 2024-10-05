package model;

import javafx.beans.property.*;

public class InventoryItem {
	private IntegerProperty productID;
    private StringProperty productName;
    private StringProperty productDescription;
    private IntegerProperty onHandQty;
    private IntegerProperty onOrderQty;
    private IntegerProperty minStockLevel;
    private IntegerProperty maxStockLevel;
    
	public InventoryItem() {
		
	}
	
	public InventoryItem(int productID, String productName, String productDescription, int onHandQty, int onOrderQty,
			int minStockLevel, int maxStockLevel) {
		this.productID = new SimpleIntegerProperty(productID);
		this.productName = new SimpleStringProperty(productName);
		this.productDescription = new SimpleStringProperty(productDescription);
		this.onHandQty = new SimpleIntegerProperty(onHandQty);
		this.onOrderQty = new SimpleIntegerProperty(onOrderQty);
		this.minStockLevel = new SimpleIntegerProperty(minStockLevel);
		this.maxStockLevel = new SimpleIntegerProperty(maxStockLevel);
	}
	
	public int getProductID() {
		return productID.get();
	}

	public void setProductID(IntegerProperty productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName.get();
	}

	public void setProductName(StringProperty productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription.get();
	}

	public void setProductDescription(StringProperty productDescription) {
		this.productDescription = productDescription;
	}

	public int getOnHandQty() {
		return onHandQty.get();
	}

	public void setOnHandQty(IntegerProperty onHandQty) {
		this.onHandQty = onHandQty;
	}

	public int getOnOrderQty() {
		return onOrderQty.get();
	}

	public void setOnOrderQty(IntegerProperty onOrderQty) {
		this.onOrderQty = onOrderQty;
	}

	public int getMinStockLevel() {
		return minStockLevel.get();
	}

	public void setMinStockLevel(IntegerProperty minStockLevel) {
		this.minStockLevel = minStockLevel;
	}

	public Integer getMaxStockLevel() {
		return maxStockLevel.get();
	}

	public void setMaxStockLevel(IntegerProperty maxStockLevel) {
		this.maxStockLevel = maxStockLevel;
	}

	public String getCSVString() {
		return  productID.get() + "," + productName.get() + ","
				+ productDescription.get() + "," + onHandQty.get() + "," + onOrderQty.get() + ","
				+ minStockLevel.get() + "," + maxStockLevel.get();
	}
	
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productDescription="
				+ productDescription + ", onHandQty=" + onHandQty + ", onOrderQty=" + onOrderQty + ", minStockLevel="
				+ minStockLevel + ", maxStockLevel=" + maxStockLevel + "]";
	}
}
