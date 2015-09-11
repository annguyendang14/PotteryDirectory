import java.awt.*;

import javax.swing.*;



public class MenuWindow {
	public static void main(String[] args){
		showGUI();
	}
	public static void showGUI(){
		JFrame frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setTitle("Pottery Directory");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JLabel("What do you want to do?"));
		frame.add(north, BorderLayout.NORTH);
		JPanel center = new JPanel(new GridLayout(3,2));
		center.add(new JButton("Search"));
		center.add(new JButton("Add Order"));
		center.add(new JButton("Remove"));
		center.add(new JButton("List"));
		center.add(new JButton("Contact"));
		center.add(new JButton("Quit"));
		frame.add(center, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
	

}
