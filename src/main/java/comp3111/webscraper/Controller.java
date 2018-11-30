/**
 * 
 */
package comp3111.webscraper;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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

import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;


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

public class Controller extends WebScraperApplication{

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
	private TableView<TableItem> table;

	@FXML
	private TableColumn<TableItem,String> itemTitle;

	@FXML
	private TableColumn<TableItem,Double> itemPrice;

	@FXML
	private TableColumn<TableItem,String> itemURL;

	@FXML
	private TableColumn<TableItem,String> itemDate;

	@FXML
	private Button RefineButton;

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
		 initProgram();
	 }

	 /**
	  * Called when the search button is pressed.
	  * @throws ParseException 
	  * enable refine button
	  */
	 @FXML
	 private void actionSearch() throws ParseException {
		 List <Item> result = search();
		 summary(result);
		 fillTable(result);

	 }
	 /**
	  * Called when the refine button is pressed.
	  * @throws ParseException 
	  * disable refine button
	  */
	 @FXML
	 private void actionRefine() throws ParseException {

		 System.out.println("actionRefine: " + textFieldRefineKeyword.getText());


		 List<Item> refine = scraper.refine(textFieldRefineKeyword.getText());
		 String output = "";

		 for (Item item : refine) {
			 output += item.getTitle() + "\t" + item.getPortal() + "\t" + item.getPrice() + "\t" +item.getUrl() + "\n";
		 }
		 RefineButton.setDisable(true);
		 textAreaConsole.setText(output); 
		 summary(refine);
		 fillTable(refine);
		 
	 }
	 /**
	  * Called when the new button is pressed. Very dummy action - print something in the command prompt.
	  */
	 @FXML
	 private void actionLast() throws ParseException {
		 System.out.println("actionLast");
		 last();
		 if (lastKeyword != "") {
			 actionSearch();
		 }
	 }

	 @FXML
	 private void actionAbout() {
		 System.out.println("actionAbout");
		 Alert dg = dialogAbout();
		 dg.show();
	 }

	 @FXML
	 private void actionQuit() {
		 System.out.println("actionQuit");
		 quit();
	 }

	 @FXML
	 private void actionClose() {
		 System.out.println("actionClose");
		 close();
	 }

	 @FXML
	 private void actionLowest() {
		 getHostServices().showDocument(labelMin.getText());
	 }
	 
	 @FXML
	 private void actionLatest() {
		 getHostServices().showDocument(labelLatest.getText());
	 }
	 
	 private void initProgram() {
		 lastSearch.setDisable(true);
		 RefineButton.setDisable(true);
	 }
	 
	 public List<Item> search() {
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
		 RefineButton.setDisable(false);
		 size = 0;
		 pages = 0;
		 
		 return result;
	 }
	 
	 private void summary(List<Item> result) throws ParseException {
		 if(result.size() > 0) {
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
				 if(item.getDate() != "-") {
					 if(simpleDateFormat.parse(item.getDate()).after(latest)) {
						 latest = simpleDateFormat.parse(item.getDate());
						 urlLatest = item.getUrl();
					 }
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
	 
	 public Alert dialogAbout() {
		 Alert dg = new Alert(Alert.AlertType.INFORMATION);
		 dg.setTitle("About the team");
		 dg.setHeaderText("COMP3111 Project Team No. 34");
		 dg.setContentText("Developers Info: "
				 + "\n CHAN, Siu Him\t ITSC: shchanam\t GitHub: https://github.com/zach1king"
				 + "\n CHANG, Hiu Tung\t ITSC: htchang\t Github: https://github.com/htchang1"
				 + "\n LEE, Yuen Nam\t ITSC: ynleeaa\t Github: https://github.com/heidileeyn");
		 return dg;
	 }
	 
	 public void last() {
		 lastSearch.setDisable(true);
		 
		 if (lastKeyword != "") {
			 textFieldKeyword.setText(lastKeyword);
		 }
	 }
	 
	 public void quit() {
		 scraper.close();
		 System.exit(0);
	 }

	 public void close() {
		 lastKeyword = "";
		 latestKeyword = "";
		 lastSearch.setDisable(true);
		 textFieldKeyword.setText("");
		 textAreaConsole.setText("");
		 labelCount.setText("<total>");
		 labelPrice.setText("<AvgPrice>");
		 labelMin.setText("<Lowest>");
		 labelLatest.setText("<Latest>");
		 table.getItems().clear();
	 }
	 
	 public boolean getStatus_isDisabledLastSearch() {
		 return lastSearch.isDisable();
	 }
	 
	 public String getTextFieldKeyword() { return textFieldKeyword.getText(); }
	 
	 public String getTextAreaConsole() { return textAreaConsole.getText(); }
	 
	 public String getLabelCount() { return labelCount.getText(); }
	 
	 public String getLabelPrice() { return labelPrice.getText(); }
	 
	 public String getLabelMin() { return labelMin.getText(); }
	 
	 public String getLabelLatest() { return labelLatest.getText(); }
	 
	 public boolean isTableClean() { return Bindings.isEmpty(table.getItems()).get(); }

}




