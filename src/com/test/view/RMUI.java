package com.test.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RMUI extends JFrame{
	String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static void main(String[] args) {
		new RMUI();
	}

	JLabel welcome = new JLabel("Welcome Relationship Manager!");
	//show assests and provide buy or sell button
	JPanel panel = new JPanel();
	JButton addAsset = new JButton("ADD ASSET");
	JButton remAsset = new JButton("REMOVE ASSET");
	RMUI(){
		super("Virtual Trade");
		setSize(240,250);
		setLocation(50,80);
		panel.setLayout (null); 
		

		welcome.setBounds(10,10,300,10);
		addAsset.setBounds(10,40,200,30);
		remAsset.setBounds(10,90,200,30);
		panel.add(welcome);
		panel.add(addAsset);
		panel.add(remAsset);


	getContentPane().add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}

	
}
