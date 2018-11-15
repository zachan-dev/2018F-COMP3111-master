/**
 * 
 */
package comp3111.webscraper;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    
    @FXML
    private TableView<TableItem> table;
    
    @FXML
    private TableColumn<TableItem,String> itemTitle;
    
    @FXML
    private TableColumn<TableItem,Double> itemPrice;
    
    @FXML
    private TableColumn<TableItem,String> itemURL;
    
    @FXML
    private TableColumn<TableItem,String> itemDate;
    
    
    
    
    private WebScraper scraper;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	
    }
    
    /**
     * Called when the search button is pressed.
     * @throws ParseException 
     */
    @FXML
    private void actionSearch() throws ParseException {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\t" + item.getDate() + "\n";
    	}
    	textAreaConsole.setText(output);

    	//labelCount.setText("Hi");
    	summary(result);
    	fillTable(result);
    	
    	
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
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
    

    
    private void fillTable (List<Item> result) {
    	table.getItems().clear();
		itemTitle.setCellValueFactory(new PropertyValueFactory<TableItem, String>("title"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<TableItem, Double>("price"));
        itemURL.setCellValueFactory(new PropertyValueFactory<TableItem, String>("url"));
        itemDate.setCellValueFactory(new PropertyValueFactory<TableItem, String>("date"));
        
        
        //List<Item> property = new List<Item>
        ArrayList<TableItem> tableItem = new ArrayList<TableItem>();
        for(Item item: result) {
        	//tableItem.add(new TableItem(item.getTitle(),item.getPrice(),item.getTitle(),item.getDate()));
        	//System.out.println(item.getTitle() + '\t' + item.getUrl());
        	TableItem temp = new TableItem(item.getTitle(),item.getPrice(),item.getTitle(),item.getDate());
        	temp.setUrl(item.getUrl());
        	tableItem.add(temp);
        }
        final ObservableList<TableItem> data = FXCollections.observableArrayList(tableItem);
        /*for(TableItem item: data) {
        	System.out.println(item.getTitle() + '\t' + item.getURL());
        }*/
        
        //table.setItems(data);
        //table.getColumns().setAll();
        table.getItems().addAll(data);
        //table.getColumns().addAll(itemTitle, itemPrice, itemURL,itemDate);

    }
	


}

	


