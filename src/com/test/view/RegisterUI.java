package com.test.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

import com.test.dao.AccountDAOImpl;
import com.test.dao.PortfolioDAOImpl;
import com.test.dao.UserDAO;
import com.test.dao.UserDAOImpl;
import com.test.model.Account;
import com.test.model.Portfolio;
import com.test.model.User;

public class RegisterUI extends JFrame {

public static void main(String[] args) throws IOException, SQLException {
	new RegisterUI();
}

JLabel register = new JLabel("Fill all the fields...");
JLabel name = new JLabel("Name");
JTextField txname = new JTextField(15);
JLabel username = new JLabel("Username");
JTextField txusername = new JTextField(15);
JLabel password = new JLabel("Password");
JTextField txpass = new JTextField(15);
JLabel portfolioName = new JLabel("Portfolio Name");
JTextField txportfolio = new JTextField(15);
JLabel email = new JLabel("Email");
JTextField txemail = new JTextField(15);
JLabel mobile = new JLabel("Mobile");
JTextField txmobile = new JTextField(15);
JButton registerBtn = new JButton("Register Me!");

JPanel panel = new JPanel();

RegisterUI() throws IOException, SQLException{
super("Virtual Trade");
setSize(500,400);
setLocation(500,280);
panel.setLayout (null); 

register.setBounds(10,5,300,30);
name.setBounds(10, 40, 150, 30);
txname.setBounds(160, 40, 150, 25);
username.setBounds(10, 70, 150, 30);
txusername.setBounds(160, 70, 150, 25);
password.setBounds(10, 100, 150, 30);
txpass.setBounds(160, 100, 150, 25);
portfolioName.setBounds(10, 130, 150, 30);
txportfolio.setBounds(160, 130, 150, 25);
email.setBounds(10, 160, 150, 30);
txemail.setBounds(160, 160, 150, 25);
mobile.setBounds(10, 190, 150, 30);
txmobile.setBounds(160, 190, 150, 25);
registerBtn.setBounds(10, 240, 150, 40);


panel.add(register);
panel.add(name);
panel.add(txname);
panel.add(username);
panel.add(txusername);
panel.add(password);
panel.add(txpass);
panel.add(portfolioName);
panel.add(txportfolio);
panel.add(email);
panel.add(txemail);
panel.add(mobile);
panel.add(txmobile);
panel.add(registerBtn);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionRegister();
}

private void actionRegister() throws IOException, SQLException{
	registerBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			try{
				User user = new User();
				user.setName(txname.getText());
				user.setUsername(txusername.getText());
				user.setEmail(txemail.getText());
				user.setPassword(txpass.getText());
				user.setPortfolioName(txportfolio.getText());
				user.setMobile(txmobile.getText());
				
				UserDAOImpl U = new UserDAOImpl();
				U.persistUser(user);
				int userId = U.getUser(txusername.getText(), txpass.getText());
				
				Portfolio p = new Portfolio(txportfolio.getText());
				PortfolioDAOImpl P = new PortfolioDAOImpl();
				p.setuserId(userId);
				P.persistPortfolio(p);
				System.out.println("11111111111111111----"+userId);	
				
				int portfolioid = P.getId(userId);
				System.out.println("222222222222222222222----"+portfolioid);	
				
				AccountDAOImpl A = new AccountDAOImpl();
				Account a = new Account();
				a.setPortfolioId(portfolioid);
				int accid = A.persistAccount(a);
				System.out.println("3333333333333333----"+accid);	
				
				P.updatePortfolio(accid, portfolioid);
				U.updatePortfolioId(portfolioid, userId);
				
				LoginUI login = new LoginUI();
				login.setVisible(true);
				
				dispose();
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	});	
	
	
	
	
}

}









