package comp3111.webscraper;

import static org.junit.Assert.*;
import java.net.URLEncoder;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;


public class WebScraperTest {
	
	

	public void PaginationInit() {
		Controller.pages = 0;
		Controller.size = 0;
	}
	
	@Test
	public void PaginationTestScrapeByStrangeKeyword() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		int expectedPage = 0;
		int expectedSize = 0;
		String strangeKeyword = "g*jpwj";
		scraperUnderTest.scrape(strangeKeyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages==expectedPage && Controller.size==expectedSize);
	}
	
	@Test
	public void PaginationTestScrapeByKeyword_leak() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		String Keyword = "leak";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages!=0 && Controller.size!=0);
	}
	
	@Test
	public void PaginationTestScrapeByKeyword_holy() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		String Keyword = "holy";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages!=0 && Controller.size!=0);
	}
	
	@Test
	public void PaginationTestScrapeByNumber() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		String Keyword = "753";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages!=0 && Controller.size!=0);
	}
	
	@Test
	public void PaginationTestScrapeByNothing() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		int expectedPage = 0;
		int expectedSize = 0;
		String Keyword = "";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages==expectedPage && Controller.size==expectedSize);
	}
	
	@Test
	public void PaginationTestScrapeBySpaceBar() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		int expectedPage = 0;
		int expectedSize = 0;
		String Keyword = " ";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages==expectedPage && Controller.size==expectedSize);
	}
	
	@Test
	public void PaginationTestScrapeByTwoSpaceBar() {
		WebScraper scraperUnderTest = new WebScraper();
		PaginationInit();
		int expectedPage = 0;
		int expectedSize = 0;
		String Keyword = "h  h";
		scraperUnderTest.scrape(Keyword);
		assertTrue(Controller.pages + ";" + Controller.size, Controller.pages==expectedPage && Controller.size==expectedSize);
	}
	@Test
	public void TestRefine() {
		WebScraper test = new WebScraper();
		test.scrape("apple+pie");
		List<Item> result= test.refine("Favourites");
		assertEquals(result.get(0).getPortal(), "Preloved");

	}
	
}
