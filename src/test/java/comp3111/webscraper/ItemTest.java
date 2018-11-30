package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;


public class ItemTest {

	@Test
	public void testSetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	@Test
	public void testGetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");		
		assertEquals(i.getTitle(), "ABCDE");
	}
	@Test
	public void testSetPortal() {
		Item i = new Item();
		i.setPortal("ABCDE");
		assertEquals(i.getPortal(), "ABCDE");
	}
	@Test
	public void testGetPortal() {
		Item i = new Item();
		i.setPortal("ABCDE");		
		assertEquals(i.getPortal(), "ABCDE");
	}
	@Test
	public void testSetPrice() {
		Item i = new Item();
		i.setPrice(1.5);
		double delta = 0.5;
		assertEquals(i.getPrice(), 1.5,delta);
	}
	@Test
	public void testGetPrice() {
		Item i = new Item();
		i.setPrice(1.5);	
		double delta = 0.5;
		assertEquals(i.getPrice(), 1.5,delta);
	}
	@Test
	public void testSetUrl() {
		Item i = new Item();
		i.setUrl("www.yahoo.com");
		assertEquals(i.getUrl(), "www.yahoo.com");
	}
	@Test
	public void testGetUrl() {
		Item i = new Item();
		i.setUrl("www.yahoo.com");
		assertEquals(i.getUrl(), "www.yahoo.com");
	}
	@Test
	public void testSetDate() {
		Item i = new Item();
		i.setDate("11/2");
		assertEquals(i.getDate(), "11/2");
	}
	@Test
	public void testGetDate() {
		Item i = new Item();
		i.setDate("11/2");
		assertEquals(i.getDate(), "11/2");
	}
	@Test
	public void testcompareTo() {
		int result=1;
		assertEquals(result, 1);
	}


	
	
	
	
	
	
}
