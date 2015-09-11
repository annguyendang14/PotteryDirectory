import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class NewOrderGUI {

	private JLabel enter;
	private JLabel name;
	private JLabel address;
	private JLabel phoneNum;
	private JButton save;
	private JTextField nameText;
	private JTextField addressText;
	private JTextField phoneNumText;

	public void NewOrder() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		enter = new JLabel("Enter: ");
		name = new JLabel("Name: ");
		address = new JLabel("Address: ");
		phoneNum = new JLabel("phoneNum: ");
		save = new JButton("Save");
		nameText = new JTextField();
		addressText = new JTextField();
		phoneNumText = new JTextField();
		
		
		
		// overall frame
		frame.setLayout(new GridLayout(4, 2));
		frame.add(enter);
		frame.add()
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 300));
		frame.setTitle("New Order");
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		NewOrderGUI gui = new NewOrderGUI();

	}

}
