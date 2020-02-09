package com.techelevator;

public class Product {
	
	private String id;
	private String name;
	private float price;
	private String type;
	
	private int quantity = 50;
	
	
	//Getters && Setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//logic for if quantity is 0 to show SOLD OUT
	public String getQuantityToDisplay() {
		String quantityString = Integer.toString(quantity);
		if(quantity < 1) {
			quantityString = "SOLD OUT";
		}
		return  quantityString;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void subtractFromCurrentQuantity(int subtractAmount) {
		this.quantity -= subtractAmount;
		
	}
	public int getQuantity() {
		
		return quantity;
	}
	

}
