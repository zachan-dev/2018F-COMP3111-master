package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;



public class TableItemTest {
	
	
	@Test
	public void testInitialiseTitle() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		assertEquals(a.getTitle(), "A");
	}
	
	@Test
	public void testInitialisePrice() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		assertEquals(Double.toString(a.getPrice()), Double.toString((double)10));
	
	}
	
	@Test
	public void testInitialiseUrl() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		assertEquals(a.getUrl(), "abc");
		
	}
	
	@Test
	public void testInitialiseDate() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		assertEquals(a.getDate(), "20181130");
	}
	
	@Test
	public void testSetTitle() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		a.setTitle("B");
		assertEquals(a.getTitle(), "B");
	}
	
	@Test 
	public void testSetPrice() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		a.setPrice(20);
		assertEquals(Double.toString(a.getPrice()),Double.toString((double)20));
	}
	
	
	@Test
	public void testSetUrl() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		a.setUrl("def");
		assertEquals(a.getUrl(), "def");
	}
	
	@Test
	public void testSetDate() {
		TableItem a = new TableItem("A", 10, "abc", "20181130");
		a.setDate("20181201");
		assertEquals(a.getDate(), "20181201");
	}
	

}
