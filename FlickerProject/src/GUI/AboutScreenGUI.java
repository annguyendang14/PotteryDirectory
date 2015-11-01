package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This class runs the "About" window for credits.
 * 
 * Written by: Taylor
 */
public class AboutScreenGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		try {
			//root gets layout from AboutScreen.fxml file, created with FX Scene Builder.
			Parent root = FXMLLoader.load(getClass().getResource("AboutScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
