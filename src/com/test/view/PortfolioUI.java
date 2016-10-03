package com.test.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import com.test.dao.AccountDAOImpl;
import com.test.dao.EquityAssetDAOImpl;
import com.test.dao.PortfolioDAOImpl;
import com.test.dao.TransactionDAOImpl;
import com.test.dao.UserDAOImpl;
import com.test.model.EquityAsset;
import com.test.model.Stock;

public class PortfolioUI extends JFrame {

	public static void main(String[] args) throws SQLException, IOException {
		new PortfolioUI(1);
	}

	JLabel welcome = new JLabel("Welcome to your Portfolio");
	JPanel panel = new JPanel();
	JButton getAssets = new JButton("SHOW EQUITY ASSETS");
	JButton buyAsset = new JButton("BUY EQUITY PRODUCT");
	JButton sellAsset = new JButton("SELL EQUITY ASSET");
	JButton loginAgain = new JButton("LOGIN AS A DIFFERENT USER");
	JButton deletePortfolio = new JButton("DELETE PORTFOLIO");
	JButton getAccountDetails = new JButton("GET ACCOUNT REPORT");
	JButton transactionReport = new JButton("GET TRANSACTION DETAILS");
	JButton addCash = new JButton("ADD VIRTUAL CASH");
	DefaultComboBoxModel stocksList = new DefaultComboBoxModel();
	JTextField units = new JTextField();
	DefaultComboBoxModel stocksList1 = new DefaultComboBoxModel();
	JTextField units1 = new JTextField();
	final JComboBox stockList, stockList1;
	int portfolioId, account_id;

	PortfolioUI(int userid) throws SQLException, IOException {
		
		super("Virtual Trade");
		System.out.println("User id"+userid);
		setSize(600, 800);
		setLocation(400, 50);
		panel.setLayout(null);
		ArrayList<String> stocks = new Stock().getStocks();
		for(String stock:stocks){
			stocksList.addElement(stock);
		}
		portfolioId = new UserDAOImpl().getPortfolioId(userid);
		account_id = new PortfolioDAOImpl().getAccountIdOfPortfolio(portfolioId);
		ArrayList<String> rs = new EquityAssetDAOImpl().getAssetNames(portfolioId);
		for(String sym: rs){
			stocksList.addElement(sym);
		}
		stockList = new JComboBox(stocksList);    
		stockList.setSelectedIndex(0);
		stockList1 = new JComboBox(stocksList1);   
		if(rs.size()>0)
		stockList1.setSelectedIndex(0);
		
		welcome.setBounds(200, 20, 300, 30);
		getAssets.setBounds(150, 90, 300, 30);
		stockList.setBounds(150,150,150,30); units.setBounds(330,150,100,30);
		buyAsset.setBounds(150, 200, 300, 30);
		
		stockList1.setBounds(150,260,150,30); units1.setBounds(330,260,100,30);
		sellAsset.setBounds(150, 310, 300, 30);
		getAccountDetails.setBounds(150, 370, 300, 30);
		transactionReport.setBounds(150, 430, 300, 30);
		loginAgain.setBounds(150, 490, 300,30);
		deletePortfolio.setBounds(150,550, 300,30);
		addCash.setBounds(150,610,300,30);

		panel.add(welcome);
		panel.add(buyAsset);
		panel.add(stockList);
		panel.add(units1);
		panel.add(stockList1);
		panel.add(units);
		panel.add(sellAsset);
		panel.add(getAccountDetails);
		panel.add(transactionReport);
		panel.add(loginAgain);
		panel.add(deletePortfolio);
		panel.add(getAssets);
		panel.add(addCash);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		final int u = userid;
		actionListerners(u);
	}
	
	public void actionListerners(final int u) {
		loginAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				LoginUI login = new LoginUI();
				login.setVisible(true);
				dispose();
				
			}
		});
		
		deletePortfolio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					new PortfolioDAOImpl().deletePortfolio(portfolioId);
					new AccountDAOImpl().deleteAccount(account_id);
					new TransactionDAOImpl().deleteTransactions(account_id);
					
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});

		buyAsset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String assetName = (String)stockList.getSelectedItem();
				int num = Integer.parseInt(units.getText());
				try {
					float currentBal = new AccountDAOImpl().currentBalance(account_id);
					float price = Stock.getPriceForStock(assetName);
					float purchaseAmt = price*num;
					if( purchaseAmt > currentBal){
						JOptionPane.showMessageDialog(null, "You do not have sufficient balance to purchase this equity product."
								+ " Please reduce the number of units or fund you account with virtual cash...");
					}
					else {
						EquityAsset eq = new EquityAsset();
						eq.setSymbol(assetName); eq.setUnits(num); eq.setInvestmentAmt(purchaseAmt);
						eq.setPurchasePrice(price); eq.setPortfolioId(portfolioId);
						eq.setCurrentValue(purchaseAmt);
						new EquityAssetDAOImpl().buyAsset(eq);
						new AccountDAOImpl().updateAccount(currentBal-purchaseAmt);
					}
				} catch (IOException | SQLException e1) {
					
					e1.printStackTrace();
				}
			
			}
			
			});
			
			
		sellAsset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String assetName = (String)stockList1.getSelectedItem();
				int num = Integer.parseInt(units1.getText());
				float currentPriceofAsset = Stock.getPriceForStock(assetName);
				float currentBal = 0F;
				try {
					currentBal = new AccountDAOImpl().currentBalance(account_id);
				} catch (IOException | SQLException e2) {
					e2.printStackTrace();
				}
				try {
					int availableUnits = new EquityAssetDAOImpl().getBalanceUnits(u, assetName);
					
					if( num > availableUnits){
						JOptionPane.showMessageDialog(null, "You do not have sufficient "
								+ "units.");
								
					}
					else {
						
						new AccountDAOImpl().updateAccount(currentBal+currentPriceofAsset*num);
						
						new EquityAssetDAOImpl().sellAsset(assetName,availableUnits-num,portfolioId);
					}
				} catch (IOException | SQLException e1) {
					
					e1.printStackTrace();
				}
			
			}
			
			});
			
		addCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					new AccountDAOImpl().updateAccount(portfolioId);
				}catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	}			
			
}
