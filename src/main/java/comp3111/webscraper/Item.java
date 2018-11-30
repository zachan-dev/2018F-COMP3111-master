package comp3111.webscraper;



public class Item implements Comparable<Item> {
	private String title ; 
	private String portal;
	private double price ;
	private String url ;
	private String date ;
	/**
	 * Returns the title
	 * @return String of title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Set the title
	 *@param title - the title you want to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Returns the portal
	 * @return String of portal
	 */
	public String getPortal() {
		return portal;
	}
	/**
	 * Set the portal
	 *@param portal - the portal you want to set
	 */
	public void setPortal(String portal) {
		this.portal = portal;
	}
	/**
	 * Returns the price
	 * @return double of price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Set the price
	 *@param price - the price you want to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Returns the URL
	 * @return String of url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Set the URL
	 *@param url - the url you want to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Returns the date
	 * @return String of date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Set the date
	 *@param date - the date you want to set
	 */
	public void setDate(String date) {	
		this.date = date;
	}
	/**
	 * Compare value
	 * @param a - the item you want to compare to
	 * @return return 1 if current value is greater than comparing value;return -1 if current value is greater than comparing value;return 0 if they are the same
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
