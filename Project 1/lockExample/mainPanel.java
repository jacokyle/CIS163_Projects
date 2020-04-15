package project1.lockExample;

import java.awt.GridLayout;

import javax.swing.*;

public class mainPanel extends JPanel {

	private LockGUIPanel p1;
	private LockGUIPanel p2;
	private LockGUIPanel p3;
	
	private JButton master;

	
	public mainPanel () {
		
		setLayout(new GridLayout(4, 1));
		
		p1 = new LockGUIPanel();
		p2 = new LockGUIPanel();
		p3 = new LockGUIPanel();
		master = new JButton("Master Not functioning yet!");
		
		add (p1);
		add (p2);
		add (p3);
		add (master);
	}
}
