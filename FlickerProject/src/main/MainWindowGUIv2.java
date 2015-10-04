package main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainWindowGUIv2 extends Application{

	Button createNewCustomer, createNewOrder;
	Scene welcomeWindowScene, newCustomerScene, newOrderScene;
	Stage welcomeWindowStage, newCustomerStage, newOrderStage;
	Parent root;
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		welcomeWindowStage = primaryStage;
		
	
		
		try {
			root = FXMLLoader.load(getClass().getResource("WelcomeWindow.fxml"));
			welcomeWindowScene = new Scene(root);
			primaryStage.setScene(welcomeWindowScene);
			primaryStage.show();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}

}
