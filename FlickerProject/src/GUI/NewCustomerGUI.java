package GUI;
/*
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewOrderGUI {

	
	private JButton save;
	private JTextField nameText;
	private JTextField addressText;
	private JTextField phoneNumText;

	public static void main(String[] args) {
		NewOrderGUI gui = new NewOrderGUI();

	}
	public NewOrderGUI() {
		
		save = new JButton("Save");
		nameText = new JTextField("Name: ");
		addressText = new JTextField("Address: ");
		phoneNumText = new JTextField("Phone Number: ");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setTitle("New Order");
		frame.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JLabel("Enter: "));
		frame.add(north,BorderLayout.NORTH);
		JPanel center = new JPanel(new GridLayout(3,1));
		center.add(nameText);
		center.add(addressText);
		center.add(phoneNumText);
		frame.add(center,BorderLayout.CENTER);
		JPanel south = new JPanel(new FlowLayout());
		south.add(save);
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
		

	}

	

}
*/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
 * This class runs the GUI window for creating a new customer.
 * 
 * Written by: Taylor
 */
public class NewCustomerGUI extends Application{
	
	public void start(Stage primaryStage) throws Exception {
		try {
			//root gets layout from NewCustomer.fxml file, created with FX Scene Builder.
			Parent root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
}








































