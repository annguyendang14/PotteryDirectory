import java.awt.*;

import javax.swing.*;



public class MenuWindow {
	public static void main(String[] args){
		showGUI();
	}
	public static void showGUI(){
		//many of these components need to be private
		JFrame frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setTitle("Pottery Directory");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(150, 150));
		JPanel north = new JPanel(new GridLayout(3,1));
		Font myFont = new Font("Verdana", Font.PLAIN, 25);
		JLabel intro1 = new JLabel("Welcome to the Sondahl Pottery Directory!", JLabel.CENTER);
		intro1.setFont(myFont);
		JLabel intro2 = new JLabel("An Order Tracking Software", JLabel.CENTER);
		intro2.setFont(myFont);
		JLabel intro3 = new JLabel("What do you want to do?", JLabel.CENTER);
		intro3.setFont(myFont);
		north.add(intro1);
		north.add(intro2);
		north.add(intro3);
		frame.add(north, BorderLayout.NORTH);
		JPanel center = new JPanel(new GridLayout(3,2));
		center.add(new JButton("Search for and Edit a current Customer"));
		center.add(new JButton("Add a Customer/Order"));
		center.add(new JButton("Remove a Customer/Order"));
		center.add(new JButton("Generate a Shipping Label"));
		center.add(new JButton("Generate a Contact List"));
		center.add(new JButton("Quit"));
		frame.add(center, BorderLayout.CENTER);
		JPanel west = new JPanel();
		frame.add(west, BorderLayout.WEST);
		JPanel east = new JPanel();
		frame.add(east, BorderLayout.EAST);
		JPanel south = new JPanel();
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	

}
