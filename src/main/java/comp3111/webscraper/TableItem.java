package comp3111.webscraper;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableItem {
	private SimpleStringProperty title ; 
	private SimpleDoubleProperty price ;
	private SimpleStringProperty url ;
	private SimpleStringProperty date ;
	
	
	public TableItem(String title, double price, String url, String date) {
		this.title = new SimpleStringProperty(title);
		this.price = new SimpleDoubleProperty(price);
		this.url = new SimpleStringProperty(url);
		this.date = new SimpleStringProperty(date);
	}
	/*
	public TableItem() {
		
	}*/
	
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public void setPrice(double price) {
		this.price.set(price);
	}
	
	public String getUrl() {
		return url.get();
	}
	
	public void setUrl(String url) {
		this.url.set(url);
	}
	
	public String getDate() {
		return date.get();
	}
	
	public void setDate(String date) {
		this.date.set(date);
	}
}
