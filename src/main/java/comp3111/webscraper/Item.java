package comp3111.webscraper;

/**
 * Item class handles the information of a result item scrapped by WebScraper
 * It acts as an database for an item
 * @author zach1king
 *
 */

public class Item implements Comparable<Item> {
	private String title ; 
	private String portal;
	private double price ;
	private String url ;
	private String date ;
	
	/**
	 * gets the title of the item
	 * @return String	title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * sets the title of the item
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * gets the Portal of the item
	 * @return String	portal
	 */
	public String getPortal() {
		return portal;
	}
	/**
	 * sets the portal of the item
	 */
	public void setPortal(String portal) {
		this.portal = portal;
	}
	/**
	 * gets the price of the item
	 * @return String	price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * sets the price of the item
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * gets the url of the item
	 * @return String title
	 */
	public String getUrl() {
		return url;
	}
	/** 
	 * sets the url of the item
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * gets the date of the item
	 * @return String date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * sets the date of the item
	 * @param date
	 */
	public void setDate(String date) {	
		this.date = date;
	}
	/**
	 * compare the price with the given item
	 * @param Item to be compared
	 */
	public int compareTo(Item a) {
		double result = this.price-a.price;
		if (result<0)
		{
			return -1;
		}
		else if (result==0) {
			return this.portal.compareTo(a.portal);
		}
		else
		{
			return 1;
		}
	}

}
