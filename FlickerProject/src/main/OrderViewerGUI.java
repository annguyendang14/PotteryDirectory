package main;

import controller.OrderViewerControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderViewerGUI extends Application {

	
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("OrderViewer.fxml"));
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
