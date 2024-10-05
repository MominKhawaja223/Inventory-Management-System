package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PurchaseOrder {
	private IntegerProperty productID;
	private StringProperty orderDate;
	private IntegerProperty quantity;

	public PurchaseOrder() {

	}

	public PurchaseOrder(int productID, String orderDate,  int quantity) {
		this.productID = new SimpleIntegerProperty(productID);
		this.orderDate = new SimpleStringProperty(orderDate);
		this.quantity = new SimpleIntegerProperty(quantity);
	}

	public Integer getProductID() {
		return productID.get();
	}

	public void setProductID(IntegerProperty productID) {
		this.productID = productID;
	}

	public String getOrderDate() {
		return orderDate.get();
	}

	public void setOrderDate(StringProperty orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(IntegerProperty quantity) {
		this.quantity = quantity;
	}
	
	public String getCSVString() {
		return  productID.get() + "," + quantity.get() + ","
				+ orderDate.get();
	}
}
