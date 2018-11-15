package comp3111.webscraper;



public class Item implements Comparable<Item>{
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {	
		this.date = date;
	}
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
