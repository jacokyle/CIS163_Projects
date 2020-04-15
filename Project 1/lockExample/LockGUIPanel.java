package project1.lockExample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LockGUIPanel extends JPanel {

	private Lock myLock;  // model
	
	private JLabel label;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button0;

	private JButton clrButton;
	private JButton entButton;

	private String userInput;

	//-----------------------------------------------------------------
	//  Constructor: Sets up the main GUI components.
	//----------------------------------------------------------------
	public LockGUIPanel()
	{

		setLayout(new GridLayout(3,4));
		myLock = new Lock ("123");
		userInput = "";
		
		label = new JLabel ("Locked!!!");

		ButtonListener butListener = new ButtonListener();
		entButton = new JButton("Enter");
		entButton.addActionListener(butListener);
		
		button1 = new JButton("1");
		button1.addActionListener(butListener);
		button2 = new JButton("2");
		button2.addActionListener(butListener);

		button3 = new JButton("3");
		button3.addActionListener(butListener);
		button4 = new JButton("4");
		button4.addActionListener(butListener);

		button5 = new JButton("5");
		button5.addActionListener(butListener);
		button6 = new JButton("6");
		button6.addActionListener(butListener);

		button7 = new JButton("7");
		button7.addActionListener(butListener);
		button8 = new JButton("8");
		button8.addActionListener(butListener);

		button9 = new JButton("9");
		button9.addActionListener(butListener);
		button0 = new JButton("0");
	    button0.addActionListener(butListener);

		clrButton = new JButton("Reset");
		clrButton.addActionListener(butListener);

		add (button1);
		add (button2);
		add (button3);
		add (button4);
		add (button5);
		add (button6);
		add (button7);
		add (button8);
		add (button9);
		add (button0);

		add (clrButton);
		add (entButton);
		add (label);

		setPreferredSize (new Dimension(400,300));
		setBackground (Color.yellow);
	}

	//*****************************************************************
	//  Represents an action listener for the temperature input field.
	//*****************************************************************
	private class ButtonListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Performs the conversion when the enter key is pressed in
		//  the text field.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event)
		{

		//	System.out.println("I am happy");
			
			if (button0 == event.getSource()) {
				userInput += '0';
			}
			if (button1 == event.getSource()) {
				userInput += '1';
			}
			if (button2 == event.getSource()) {
				userInput += '2';
			}
			if (button3 == event.getSource()) {
				userInput += '3';
			}

			if (button4 == event.getSource()) {
				userInput += '4';
			}
			if (button5 == event.getSource()) {
				userInput += '5';
			}
			if (button6 == event.getSource()) {
				userInput += '6';
			}
			if (button7 == event.getSource()) {
				userInput += '7';
			}
			if (button8 == event.getSource()) {
				userInput += '8';
			}
			if (button9 == event.getSource()) {
				userInput += '9';
			}

			System.out.println(userInput);

			if (clrButton == event.getSource()) {
				userInput = "";
				label.setText("Locked!!!");
			}

			if (entButton == event.getSource()) {
				myLock.enterCode(userInput);
				if (myLock.isUnlock())
					label.setText("Unlocked!!!!");
				else
					label.setText("Locked!!!");
				userInput = "";

			}
		}
	}

}
