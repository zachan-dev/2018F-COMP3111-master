package comp3111.webscraper;

import java.net.URLEncoder;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.Vector;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;


/**
 * WebScraper provide a sample code that scrape web content. After it is constructed, you can call the method scrape with a keyword, 
 * the client will go to the default url and parse the page by looking at the HTML DOM.  
 * <br/>
 * In this particular sample code, it access to craigslist.org. You can directly search on an entry by typing the URL
 * <br/>
 * https://newyork.craigslist.org/search/sss?sort=rel&amp;query=KEYWORD
 *  <br/>
 * where KEYWORD is the keyword you want to search.
 * <br/>
 * Assume you are working on Chrome, paste the url into your browser and press F12 to load the source code of the HTML. You might be freak
 * out if you have never seen a HTML source code before. Keep calm and move on. Press Ctrl-Shift-C (or CMD-Shift-C if you got a mac) and move your
 * mouse cursor around, different part of the HTML code and the corresponding the HTML objects will be highlighted. Explore your HTML page from
 * body &rarr; section class="page-container" &rarr; form id="searchform" &rarr; div class="content" &rarr; ul class="rows" &rarr; any one of the multiple 
 * li class="result-row" &rarr; p class="result-info". You might see something like this:
 * <br/>
 * <pre>
 * {@code
 *    <p class="result-info">
 *        <span class="icon icon-star" role="button" title="save this post in your favorites list">
 *           <span class="screen-reader-text">favorite this post</span>
 *       </span>
 *       <time class="result-date" datetime="2018-06-21 01:58" title="Thu 21 Jun 01:58:44 AM">Jun 21</time>
 *       <a href="https://newyork.craigslist.org/que/clt/d/green-star-polyp-gsp-on-rock/6596253604.html" data-id="6596253604" class="result-title hdrlnk">Green Star Polyp GSP on a rock frag</a>
 *       <span class="result-meta">
 *               <span class="result-price">$15</span>
 *               <span class="result-tags">
 *                   pic
 *                   <span class="maptag" data-pid="6596253604">map</span>
 *               </span>
 *               <span class="banish icon icon-trash" role="button">
 *                   <span class="screen-reader-text">hide this posting</span>
 *               </span>
 *           <span class="unbanish icon icon-trash red" role="button" aria-hidden="true"></span>
 *           <a href="#" class="restore-link">
 *               <span class="restore-narrow-text">restore</span>
 *               <span class="restore-wide-text">restore this posting</span>
 *           </a>
 *       </span>
 *   </p>
 *}
 *</pre>
 * <br/>
 * The code 
 * <pre>
 * {@code
 * List<?> items = (List<?>) page.getByXPath("//li[@class='result-row']");
 * }
 * </pre>
 * extracts all result-row and stores the corresponding HTML elements to a list called items. Later in the loop it extracts the anchor tag 
 * &lsaquo; a &rsaquo; to retrieve the display text (by .asText()) and the link (by .getHrefAttribute()). It also extracts  
 * 
 *
 */
public class WebScraper {

	private static final String DEFAULT_URL = "https://newyork.craigslist.org/";
	private static final String DEFAULT_URL1 = "https://www.preloved.co.uk/";
	private WebClient client;
	protected List<Item> result;

	/**
	 * Default Constructor 
	 */
	public WebScraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}

	/**
	 * The only method implemented in this class, to scrape web content from the craigslist
	 * 
	 * @param keyword - the keyword you want to search
	 * @return A list of Item that has found. A zero size list is return if nothing is found. Null if any exception (e.g. no connectivity)
	 */
	public List<Item> scrape(String keyword) {
		
		
		try {
			Vector<Item> result = new Vector<Item>();		
			/*
			do {
				int scrappingPage = Controller.pages + 1;
				System.out.println("Scrapping page number " + scrappingPage);
				
				String searchUrl = DEFAULT_URL + "search/sss?s=" + Controller.pages + "&sort=rel&query=" + URLEncoder.encode(keyword, "UTF-8");
				
				HtmlPage page = client.getPage(searchUrl);
			
				List<?> items = (List<?>) page.getByXPath("//li[@class='result-row']");
			
				for (int i = 0; i < items.size(); i++) {
					HtmlElement htmlItem = (HtmlElement) items.get(i);
					HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));
					HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']"));
					HtmlElement spanDate = ((HtmlElement) htmlItem.getFirstByXPath(".//time[@class='result-date']"));

					// It is possible that an item doesn't have any price, we set the price to 0.0
					// in this case
					String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
					//String itemDate = spanDate.asText();
					String itemDate = spanDate.getAttribute("datetime");

					Item item = new Item();
					item.setTitle(itemAnchor.asText());
					item.setPortal("Craglist");
					item.setUrl(itemAnchor.getHrefAttribute());

					item.setPrice(new Double(itemPrice.replace("$", "")));
					item.setDate(itemDate);

					result.add(item);
				}
				
			if (items.size() != 0) Controller.pages ++;
				
			Controller.size += items.size();
				
			} while (Controller.size % 120 == 0 && Controller.size != 0);			
			client.close();
			*/
			String searchUrl1 = DEFAULT_URL1 + "search?keyword=" + URLEncoder.encode(keyword, "UTF-8");
			Document doc = Jsoup.connect(searchUrl1).get();
			Elements ele=doc.select("li.search-result");
			Elements a=ele.select("li[data-test-element='search-result']");

			for (int i = 0; i < a.size(); i++) {
				String name=a.get(i).select("span[itemprop='name']").text();
				String price=a.get(i).select("span[itemprop='price']").text();				
				try {
				price=price.substring(1,price.length());	
				price=price.replace(",","");
				}
				catch (Exception e) {
					price="0";
				}
				String link=a.get(i).select("a").attr("href");
				Item item = new Item();
				item.setTitle(name);
				item.setPortal("Preloved");
				item.setUrl(link);
				item.setPrice(new Double(price));
				result.add(item);				
				}
			
			Collections.sort(result);	
			this.result=result;
			return result;			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
