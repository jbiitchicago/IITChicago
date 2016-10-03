package com.test.view;

import com.test.dao.UserDAOImpl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = -3253993606370066257L;

	public static void main(String[] args) throws IOException {
		//MySQLConnector.connectToDB();
		new LoginUI();
	}

	JLabel welcome = new JLabel("Welcome to the Virtual Trade Application!!");
	
	//radioButtonMenuPanel.setLayout(new GridBagLayout());
	
	JLabel username = new JLabel("Username ");
	JLabel password = new JLabel("Password ");
	JButton blogin = new JButton("Login");
	JLabel newUser = new JLabel("First time user?");
	JButton registerBtn = new JButton("Register");
	JRadioButton urad = new JRadioButton("User",true);
    JRadioButton adminrad = new JRadioButton("Admin");
    JRadioButton rmrad = new JRadioButton("RM");
    ButtonGroup group = new ButtonGroup();
    
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);

	public LoginUI() {
		super("Virtual Trade");
		setSize(500, 300);
		setLocation(500, 280);
		panel.setLayout(null);
		
		panel1.setLayout(new GridLayout(1,3));
		group.add(urad);
	    group.add(adminrad);
	    group.add(rmrad);
	    panel1.add(urad);
	    panel1.add(adminrad);
	    panel1.add(rmrad);
		welcome.setBounds(70, 5, 300, 30);
		panel1.setBounds(70, 30, 400, 50);
		username.setBounds(100, 80, 100, 20);
		password.setBounds(100, 105, 100, 20);
		txuser.setBounds(175, 80, 150, 20);
		pass.setBounds(175, 105, 150, 20);
		blogin.setBounds(200, 140, 100, 30);
		registerBtn.setBounds(200, 180, 100, 30);
		newUser.setBounds(70, 180, 150, 20);
		panel.add(welcome);
		panel.add(panel1);
		panel.add(username);
		panel.add(password);
		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(newUser);
		panel.add(registerBtn);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = pass.getText();
				if(adminrad.isSelected()){
				if (puname.equals("admin") && ppaswd.equals("pwd")) {
					AdminUI adminFrame = new AdminUI();
					adminFrame.setVisible(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "Wrong Admin Password / Username");
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}
				}else if(rmrad.isSelected()){
					if (puname.contains("rm_")){
					
					RMUI rmui = new RMUI();
					rmui.setUsername(puname.substring(3));
					System.out.println("puname-------"+puname.substring(3));
					rmui.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Wrong Relationship Manager Password / Username");
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}
					}
				else {
					//check db for user, populate ui with info - assests, balances/add vcash, get report, accountdetails
					try {
						int rs = new UserDAOImpl().getUser(puname, ppaswd);
						System.out.println(rs); System.out.println(ppaswd);
						if(rs > 0){
							new PortfolioUI(rs);
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong Password / Username");
							txuser.setText("");
							pass.setText("");
							txuser.requestFocus();
						}
					} catch (IOException | SQLException e) {
						
						e.printStackTrace();
					}
					
				}

			}
		});

		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try{
				RegisterUI regFace = new RegisterUI();
				regFace.setVisible(true);
				dispose();
				}catch(Exception ex){
					ex.printStackTrace();
				}

			}
		});
	}
}
