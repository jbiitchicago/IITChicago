package com.test.view;

import javax.swing.*;

import com.test.model.DBBootstrap;

import java.awt.event.*;

public class MainClassDBUI extends JFrame{
	private static final long serialVersionUID = -3253993606370066257L;
	
	public static void main(String[] args) {
		new MainClassDBUI();
	}
	
	JLabel welcome = new JLabel("Please enter your MySQL credentials");
	JLabel username = new JLabel("Username ");
	JLabel password = new JLabel("Password ");
	JButton blogin = new JButton("Submit");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	
	public MainClassDBUI() {
	
		super("Virtual Trade");
		setSize(500, 200);
		setLocation(500, 280);
		panel.setLayout(null);
		
		welcome.setBounds(70, 5, 300, 30);
		username.setBounds(100, 40, 100, 20);
		txuser.setBounds(200,40,100,20);
		password.setBounds(100, 65, 100, 20);
		pass.setBounds(200,65,100,20);
		blogin.setBounds(200, 100, 100, 30);
		panel.add(welcome);
		panel.add(username);
		panel.add(password);
		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionSubmit();
	}
		
	public void actionSubmit() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				DBBootstrap.USER=puname;
				
				String ppaswd = pass.getText();
				DBBootstrap.PASS=ppaswd;
				String[] args={""};
				DBBootstrap.main(args);

			}
		});
	}
		
}
