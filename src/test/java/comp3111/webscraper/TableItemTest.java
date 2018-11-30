package comp3111.webscraper;


import org.junit.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import static org.junit.Assert.*;

public class TableItemTest {
	@Test
	public void testSetTitle() {
		TableItem i = new TableItem(); 
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	@Test
	public void testGetTitle() {
		TableItem i = new TableItem();
		i.setTitle("ABCDE");		
		assertEquals(i.getTitle(), "ABCDE");
	}

	@Test
	public void testSetPrice() {
		TableItem i = new TableItem();
		i.setPrice(1.5);
		double delta = 0.5;
		assertEquals(i.getPrice(), 1.5,delta);
	}
	@Test
	public void testGetPrice() {
		TableItem i = new TableItem();
		i.setPrice(1.5);	
		double delta = 0.5;
		assertEquals(i.getPrice(), 1.5,delta);
	}
	@Test
	public void testSetUrl() {
		TableItem i = new TableItem();
		i.setUrl("www.yahoo.com");
		assertEquals(i.getUrl(), "www.yahoo.com");
	}
	@Test
	public void testGetUrl() {
		TableItem i = new TableItem();
		i.setUrl("www.yahoo.com");
		assertEquals(i.getUrl(), "www.yahoo.com");
	}
	@Test
	public void testSetDate() {
		TableItem i = new TableItem();
		i.setDate("11/2");
		assertEquals(i.getDate(), "11/2");
	}
	@Test
	public void testGetDate() {
		TableItem i = new TableItem();
		i.setDate("11/2");
		assertEquals(i.getDate(), "11/2");
	}

}
