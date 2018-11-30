package comp3111.webscraper;

import org.junit.Test;
/*
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
*/
import static org.junit.Assert.*;

import java.util.List;
import java.text.ParseException;
import java.util.Vector;






public class SummaryTest {

	@Test
	public void testCountItem() {
		Controller c = new Controller();
		Item i = new Item();
		Item j = new Item();
		Item k = new Item();
		List<Item> result = new Vector<Item>();
		result.add(i);
		result.add(j);
		result.add(k);
		assertEquals(c.count(result),"3");
	}

	
	@Test
	public void testAveragePrice() throws ParseException {
		Controller c = new Controller();
		Item i = new Item();
		Item j = new Item();
		Item k = new Item();
		i.setPrice(10);
		j.setPrice(20);
		k.setPrice(30);
		List<Item> result = new Vector<Item>();
		result.add(i);
		result.add(j);
		result.add(k);
		assertEquals(c.avg(result),Double.toString((double)20));
		
	}
	
	
	@Test
	public void testCheapestItem() throws ParseException {
		Controller c = new Controller();
		Item i = new Item();
		Item j = new Item();
		Item k = new Item();
		i.setPrice(10);
		j.setPrice(20);
		k.setPrice(30);
		i.setUrl("ABC");
		j.setUrl("DEF");
		k.setUrl("GHI");
		List<Item> result = new Vector<Item>();
		result.add(i);
		result.add(j);
		result.add(k);
		assertEquals(c.cheapest(result),"ABC");
	}
	
	@Test
	public void testLatestItem() throws ParseException {
		Controller c = new Controller();
		Item i = new Item();
		Item j = new Item();
		Item k = new Item();
		i.setDate("2018-09-20 00:00");
		j.setDate("2018-10-20 00:00");
		k.setDate("2018-11-20 00:00");
		i.setUrl("ABC");
		j.setUrl("DEF");
		k.setUrl("GHI");
		List<Item> result = new Vector<Item>();
		result.add(i);
		result.add(j);
		result.add(k);
		assertEquals(c.latest(result), "GHI");
	}


}
