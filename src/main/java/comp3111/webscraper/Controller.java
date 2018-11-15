/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;


import java.util.List;


/**
 * 
 * @author kevinw
 *
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller{

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
    private TextField textFieldRefineKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    @FXML
    private Button RefineButton;
    
    private WebScraper scraper;
    
    private int search;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    	search=0;
    	//RefineButton.setDisable(true);
    	
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	RefineButton.setDisable(true);
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPortal() + "\t" + item.getPrice() + "\t" +item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	RefineButton.setDisable(false);
    	labelCount.setText("Hi");  
    }

    
    @FXML
    private void actionRefine() {

    	System.out.println("actionRefine: " + textFieldRefineKeyword.getText());
    	
    	List<Item> refine = scraper.refine(textFieldRefineKeyword.getText());
    	String output = "";
    	
    	for (Item item : refine) {
    		output += item.getTitle() + "\t" + item.getPortal() + "\t" + item.getPrice() + "\t" +item.getUrl() + "\n";
    	}
    	RefineButton.setDisable(true);
    	textAreaConsole.setText(output);    	
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    }
}

