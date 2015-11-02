package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This class starts and runs the main menu for the user. 
 * The user can then use buttons to link to the other GUI windows.
 *
 * Written by: An
 */
public class MainWindowGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		try {
			// root gets layout from MainWindow.fxml file, created with FX
			// Scene Builder.
			Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
