package comp3111.webscraper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.application.Application;
import javafx.scene.control.Alert;

public class ControllerTest {
	@Before
	public void setUpClass() throws InterruptedException { //it should only called once
	    // Initialise Java FX

	    System.out.printf("About to launch FX App\n");
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(Controller.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    System.out.printf("FX App thread started\n");
	    Thread.sleep(500);
	}
	
	
	@Test
	public void testControllerByInitLastSearchButton() {
		Controller Sys = new Controller();
		assertTrue(Sys.getStatus_isDisabledLastSearch());
	}
	
	@Test
	public void testAboutAlertType() {
		Controller Sys = new Controller();
		Alert dg = Sys.dialogAbout();
		assertEquals(dg.getAlertType(), Alert.AlertType.INFORMATION);
	}

	@Test
	public void testAboutTitle() {
		Controller Sys = new Controller();
		Alert dg = Sys.dialogAbout();
		assertEquals(dg.getTitle(), "About the team");
	}
	
	@Test
	public void testAboutHeader() {
		Controller Sys = new Controller();
		Alert dg = Sys.dialogAbout();
		assertEquals(dg.getHeaderText(), "COMP3111 Project Team No. 34");
	}
	
	@Test
	public void testAboutContent() {
		Controller Sys = new Controller();
		Alert dg = Sys.dialogAbout();
		assertEquals(dg.getContentText(), "Developers Info: "
				 + "\n CHAN, Siu Him\t ITSC: shchanam\t GitHub: https://github.com/zach1king"
				 + "\n CHANG, Hiu Tung\t ITSC: htchang\t Github: https://github.com/htchang1"
				 + "\n LEE, Yuen Nam\t ITSC: ynleeaa\t Github: https://github.com/heidileeyn");
	}
	
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
