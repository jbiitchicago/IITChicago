package com.test.view;

import javax.swing.*;

public class AdminUI extends JFrame{
	public static void main(String[] args) {
		new AdminUI();
	}

	JLabel welcome = new JLabel("Welcome Admin!");
	//List users, portfolios, can create/delete/update a user/portfolio
	JPanel panel = new JPanel();
	JButton addUser = new JButton("ADD USER");
	JButton remUser = new JButton("REMOVE USER");

	AdminUI(){
	super("Virtual Trade");
	setSize(230,250);
	setLocation(50,80);
	panel.setLayout (null); 
	

	welcome.setBounds(10,10,300,10);
	addUser.setBounds(10,40,200,30);
	remUser.setBounds(10,90,200,30);
	panel.add(welcome);
	panel.add(addUser);
	panel.add(remUser);

	getContentPane().add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}
}
