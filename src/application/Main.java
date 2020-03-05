package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	//creation of fields outside of primary stage so other methods can access them.
	GridPane mainPane;
	Button orderButton;
	Button cancelButton;
	TextField webAddressEntry;
	Label resultOfScrapper;
	Button submitButton;
	Button clearButton;

	
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
			HBox buttonsBox = new HBox(8);
			buttonsBox.getChildren().addAll(submitButton,clearButton);
			
			mainPane.add(websitePromptBox, 0, 0);
			mainPane.add(buttonsBox, 0, 1);
			

			
			Scene scene = new Scene(mainPane,700,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MacBeth Word Count");
			primaryStage.show();
			
			submitButton.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
					WebpageHandling websiteToScrape = new WebpageHandling(webAddressEntry.getText());
					websiteToScrape.scrapeWebpage(websiteToScrape.getWebAddress());
				}
			});
			
			clearButton.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
					webAddressEntry.clear();
					webAddressEntry.setPromptText("http://example.com");
					webAddressEntry.setFocusTraversable(false);
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
