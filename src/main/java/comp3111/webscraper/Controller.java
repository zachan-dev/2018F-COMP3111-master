/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import java.util.List;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author kevinw
 *
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller {

    @FXML 
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 

    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> columnTitle;
    @FXML private TableColumn<Item, Double> columnUrl;
    @FXML private TableColumn<Item, String> columnDate;
    
    @FXML
    private MenuItem lastSearch;
    
    private WebScraper scraper;
    
    public static int size = 0;
    public static int pages = 0;
    public static String lastKeyword = "";
    public static String latestKeyword = "";
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    }

    /**
     * Default initializer.
     */
    @FXML
    private void initialize() {
    	lastSearch.setDisable(true);
    }
    
    /**
     * Called when the search button is pressed.
     * @throws ParseException 
     */
    @FXML
    private void actionSearch() throws ParseException {
    	lastKeyword = latestKeyword;
    	latestKeyword = textFieldKeyword.getText();
    	if (lastKeyword != "") {
    		System.out.println("lastSearch enabled");
    		lastSearch.setDisable(false);
    	}

    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "Total items scrapped = " + size + ".\t" + "Total pages obtained = " + pages + ".\n\n";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPortal() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\t" + item.getDate() + "\n";
    	}
    	textAreaConsole.setText(output);
    	size = 0;
    	pages = 0;
    	
    	summary(result);
    	table(result);

    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionLast() throws ParseException {
    	if (lastKeyword != "") {
    		textFieldKeyword.setText(lastKeyword);
    		actionSearch();
    	}
    	
    	System.out.println("actionLast");
	}
    
    @FXML
    private void actionAbout() {
    	Alert dg = new Alert(Alert.AlertType.INFORMATION);
    	dg.setTitle("About the team");
    	dg.setHeaderText("COMP3111 Project Team No. 34");
    	dg.setContentText("Developers Info: "
    			+ "\n CHAN, Siu Him\t ITSC: shchanam\t GitHub: https://github.com/zach1king"
    			+ "\n CHANG, Hiu Tung\t ITSC: htchang\t Github: https://github.com/htchang1"
    			+ "\n LEE, Yuen Nam\t ITSC: ynleeaa\t Github: https://github.com/heidileeyn");
    	dg.show();
    }
    
    @FXML
    private void actionQuit() {
    	System.exit(0);
    }
    
    @FXML
    private void actionClose() {
    	lastKeyword = "";
    	latestKeyword = "";
    	lastSearch.setDisable(true);
    	textFieldKeyword.setText("");
    	textAreaConsole.setText("");
    	labelCount.setText("<total>");
    	labelPrice.setText("<AvgPrice>");
    	labelMin.setText("<Lowest>");
    	labelLatest.setText("<Latest>");
    	tableView.getItems().clear();
    }
    
    private void summary(List<Item> result) throws ParseException {
		if(result != null) {
			double sum=0;
			int count=0;
			double min= Double.POSITIVE_INFINITY;
			String urlMin = "";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date latest = simpleDateFormat.parse("0000-00-00 00:00");
			String urlLatest = "";

			labelCount.setText(Integer.toString(result.size()));
			for(Item item : result) {
				sum += item.getPrice();
				if(item.getPrice()>0.0) {
					count++;
					if(item.getPrice()<min) {
						min = item.getPrice();
						urlMin = item.getUrl();
					}

				}
				if(simpleDateFormat.parse(item.getDate()).after(latest)) {
					latest = simpleDateFormat.parse(item.getDate());
					urlLatest = item.getUrl();
				}
			}

			labelPrice.setText(String.format("%f", (float)sum/count));
			labelMin.setText(urlMin);
			labelLatest.setText(urlLatest);

		}else {
			labelCount.setText("0");
			labelPrice.setText("-");
			labelMin.setText("-");
			labelLatest.setText("-");
		}

	}
    
    private void table(List<Item> result) throws ParseException {
    	
    }

}

