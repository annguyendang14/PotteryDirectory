package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This class runs the GUI window for editing customers.
 * 
 * Written by: An
 */
public class EditCustomerGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		try {
			//root gets layout from EditCustomer.fxml file, created with FX Scene Builder.
			Parent root = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
