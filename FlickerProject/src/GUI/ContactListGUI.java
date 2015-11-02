package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactListGUI extends Application {

	
	public void start(Stage primaryStage) throws Exception {
		try {
			//root gets layout from OrderViewer.fxml file, created with FX Scene Builder.
			Parent root = FXMLLoader.load(getClass().getResource("ContactList.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		launch(args);

	}

}
