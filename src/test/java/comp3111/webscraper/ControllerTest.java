package comp3111.webscraper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import javafx.scene.control.Alert;

public class ControllerTest {
	@Test
	public void testCloseByStatus_isDisabledLastSearch() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getStatus_isDisabledLastSearch());
	}
	
	@Test
	public void testLastSearchByStatus_isDisabledLastSearch() {
		Controller Sys = new Controller();
		Sys.last();
		assertTrue(Sys.getStatus_isDisabledLastSearch());
	}
	
	@Test
	public void testSearchByStatus_isDisabledLastSearch() {
		Controller Sys = new Controller();
		Controller.lastKeyword = "testing";
		List<Item> results = Sys.search(); //dummy
		assertTrue(!Sys.getStatus_isDisabledLastSearch());
	}
	
	@Test
	public void testCloseByTextFieldKeyword() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getTextFieldKeyword() == "");
	}
	
	@Test
	public void testCloseByTextAreaConsole() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getTextAreaConsole() == "");
	}
	
	@Test
	public void testCloseByLabelCount() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getLabelCount() == "<total>");
	}
	
	@Test
	public void testCloseByLabelPrice() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getLabelPrice() == "<AvgPrice>");
	}
	
	@Test
	public void testCloseByLabelMin() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getLabelMin() == "<Lowest>");
	}
	
	@Test
	public void testCloseByLabelLatest() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.getLabelLatest() == "<Latest>");
	}
	
	@Test
	public void testCloseByTableCleaned() {
		Controller Sys = new Controller();
		Sys.close();
		assertTrue(Sys.isTableClean());
	}
}
