package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

//http://shakespeare.mit.edu/macbeth/full.html
public class Main extends Application {
	//creation of fields outside of primary stage so other methods can access them.
	GridPane mainPane;
	Button orderButton;
	Button cancelButton;
	TextField webAddressEntry;
	TextArea viewResults;
	Label resultOfClearButton;
	Label resultOfSubmitButton;
	Button submitButton;
	Button clearButton;
	//TODO Implement clear all when lables start to overlap
	//One fix is to implement a clear all button.
	Button clearAllButton;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			mainPane = new GridPane();
			mainPane.setPadding(new Insets(10,10,10,10));
			mainPane.setMinSize(500, 200);
			mainPane.setVgap(5);
			mainPane.setHgap(5);
			
			//Label creation to guide user.
			Label entryPrompt = new Label("Please enter the web address here:");
			webAddressEntry = new TextField();
			//prompt text. Did not work at first until I setFocusTraversable to Bool value false.
			webAddressEntry.setPromptText("http://example.com");
			webAddressEntry.setPrefColumnCount(20);
			webAddressEntry.setFocusTraversable(false);
			//HBox allows for two items in one row.
			HBox websitePromptBox = new HBox(8);
			websitePromptBox.getChildren().addAll(entryPrompt,webAddressEntry);
			
			//Button creation to submit or clear
			submitButton = new Button("Submit");
			clearButton = new Button("Clear");
			//Will create a clear all option as a kind of reset to the GUI. Unsure
			//why some elements are overlapping. Well I am sure that it's because I haven't met
			//all conditional statements.
			//clearAllButton = new Button("Clear All");
			HBox buttonsBox = new HBox(8);
			buttonsBox.getChildren().addAll(submitButton,clearButton);
			
			mainPane.add(websitePromptBox, 0, 0);
			mainPane.add(buttonsBox, 0, 1);
			

			
			Scene scene = new Scene(mainPane,500,300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MacBeth Word Count");
			primaryStage.show();
			//TODO Implement better conditional testing.
			//Not all cases are covered and the program can easily fall in to an overlap of labels.
			submitButton.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
				
					if(webAddressEntry.getText().isEmpty()) {
						resultOfSubmitButton = new Label();
						resultOfSubmitButton.setText("No website entered.");
						mainPane.add(resultOfSubmitButton, 0, 2);
					}
					else if(resultOfClearButton != null && webAddressEntry.getText() != null) {
						mainPane.getChildren().remove(resultOfClearButton);
						WebpageHandling websiteToScrape = new WebpageHandling(webAddressEntry.getText());
						viewResults = new TextArea();
						viewResults.setText(websiteToScrape.scrapeWebpage(websiteToScrape.getWebAddress()));
						mainPane.add(viewResults, 0, 2);
					}
					else if(resultOfClearButton != null ) {
						mainPane.getChildren().remove(resultOfClearButton);
						resultOfSubmitButton = new Label();
						resultOfSubmitButton.setText("No website entered.");
						mainPane.add(resultOfSubmitButton, 0, 2);
					}
					else if(resultOfSubmitButton != null) {
						mainPane.getChildren().remove(resultOfSubmitButton);
						WebpageHandling websiteToScrape = new WebpageHandling(webAddressEntry.getText());
						viewResults = new TextArea();
						viewResults.setText(websiteToScrape.scrapeWebpage(websiteToScrape.getWebAddress()));
						mainPane.add(viewResults, 0, 2);
					}
					else {
					WebpageHandling websiteToScrape = new WebpageHandling(webAddressEntry.getText());
					viewResults = new TextArea();
					viewResults.setText(websiteToScrape.scrapeWebpage(websiteToScrape.getWebAddress()));
					mainPane.add(viewResults, 0, 2);
					}
					
				}
			});
			//TODO Conditions that will need to be implemented later for better UX and UI clarity.
			clearButton.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
					webAddressEntry.clear();
					webAddressEntry.setPromptText("http://example.com");
					webAddressEntry.setFocusTraversable(false);
					
					if(viewResults != null) {
						viewResults.clear();
						mainPane.getChildren().remove(viewResults);
						resultOfClearButton = new Label("Information Cleared");
						mainPane.add(resultOfClearButton, 0, 2);
					}
					else if(viewResults != null && resultOfClearButton != null) {
						mainPane.getChildren().remove(resultOfClearButton);
						viewResults.clear();
						mainPane.getChildren().remove(viewResults);
						resultOfClearButton = new Label("Information Cleared");
						mainPane.add(resultOfClearButton, 0, 2);
					}
					else {
						mainPane.getChildren().remove(resultOfSubmitButton);
						resultOfClearButton = new Label("No Information to Clear");
						mainPane.add(resultOfClearButton, 0, 2);
					}
					
					//So far they do not work properly.
					/*if(resultOfClearButton != null) {
						resultOfClearButton = new Label("");
						
					}
					else if(viewResults != null) {
						viewResults.clear();
						mainPane.getChildren().remove(viewResults);
						resultOfClearButton.setText("Information Cleared");
						mainPane.add(resultOfClearButton, 0, 2);
					}
					
					
					else if(webAddressEntry.getText().isEmpty()) {
						resultOfClearButton = new Label();
						resultOfClearButton.setText("No website entered to be cleared.");
						mainPane.add(resultOfClearButton, 0, 2);
					}
					else {
						resultOfClearButton = new Label();
						resultOfClearButton.setText("Website cleared.");
						mainPane.add(resultOfClearButton, 0, 2);
					}*/
				}
			});
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
