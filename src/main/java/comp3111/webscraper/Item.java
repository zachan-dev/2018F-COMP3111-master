package comp3111.webscraper;



public class Item implements Comparable<Item> {
	private String title ; 
	private String portal;
	private double price ;
	private String url ;
	private String date ;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
	public double getPrice() {
		return price;
	}
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
	 * Returns the URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
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
