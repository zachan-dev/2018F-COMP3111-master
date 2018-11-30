package comp3111.webscraper;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * To format the result item to fill the Table
 * Use by the fillTable() in Controller.java
 * @author heidileeyn
 *
 */

public class TableItem {
	private SimpleStringProperty title ; 
	private SimpleDoubleProperty price ;
	private SimpleStringProperty url ;
	private SimpleStringProperty date ;
	
	/**
	 * Initializer of TableItem
	 * 
	 */
	public TableItem(String title, double price, String url, String date) {
		this.title = new SimpleStringProperty(title);
		this.price = new SimpleDoubleProperty(price);
		this.url = new SimpleStringProperty(url);
		this.date = new SimpleStringProperty(date);
	}
	/*
	public TableItem() {
		
	}*/
	
	/**
	 * 
	 * @return A String of the Item's title
	 */
	public String getTitle() {
		return title.get();
	}
	/**
	 * 
	 * @param title A String of the Item's title
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}
	/**
	 * 
	 * @return The Price of the item
	 */
	public double getPrice() {
		return price.get();
	}
	/**
	 * 
	 * @param price -- the price of the item
	 */
	public void setPrice(double price) {
		this.price.set(price);
	}
	/**
	 * 
	 * @return A string of the URL
	 */
	public String getUrl() {
		return url.get();
	}
	/**
	 * 
	 * @param url -- A string of the URL
	 */
	public void setUrl(String url) {
		this.url.set(url);
	}
	/**
	 * 
	 * @return A string of the date
	 */
	public String getDate() {
		return date.get();
	}
	/**
	 * 
	 * 
	 * @param date -- A string of the date
	 */
	public void setDate(String date) {
		this.date.set(date);
	}
}
