package Administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Vendor.DepartmentStore;
import Vendor.OnlineBookStore;
import Vendor.Restaurant;
import Vendor.SuperMarket;

public class ViewAllVendors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewAllVendors() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton departmentStore = new JButton("Department Store");
		departmentStore.setBounds(201, 56, 176, 52);
		departmentStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens Department Store
				 */
				ViewAll vw = new ViewAll(1);
				vw.setVisible(true);
				//dispose();
			}
		});
		contentPane.add(departmentStore);
		
		JButton restaurant = new JButton("Restaurant");
		restaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens restaurant
				 */
				ViewAll vw = new ViewAll(3);
				vw.setVisible(true);
				//dispose();
			}
		});
		restaurant.setBounds(201, 156, 176, 45);
		contentPane.add(restaurant);
		
		JButton superMarket = new JButton("Super Market");
		superMarket.setBounds(201, 256, 176, 45);
		superMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens super market
				 */
				ViewAll vw = new ViewAll(4);
				vw.setVisible(true);
				//dispose();
			}
		});
		contentPane.add(superMarket);
		
		JButton onlineBookStore = new JButton("Online Book Store");
		onlineBookStore.setBounds(201, 356, 176, 45);
		onlineBookStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens online book store
				 */
				ViewAll vw = new ViewAll(2);
				vw.setVisible(true);
				//dispose();
			}
		});
		contentPane.add(onlineBookStore);
		
		JLabel lblEnjoyShopping = new JLabel("Enjoy Shopping!");
		lblEnjoyShopping.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblEnjoyShopping.setBounds(411, 501, 128, 24);
		contentPane.add(lblEnjoyShopping);
		
		
	}
	
	public String view(int vendorID) {
		String info = "VENDORS: \n\n Vendor Name:\t Description:\t\n\n";

		info = info + "Department Store\t Clothing shop\n";	 
		info = info + "Online Bookstore\t Books store\n";
		info = info + "Reataurant Bar\t Food & Drink\n";
		info = info + "Supermarket store\t Groceries shop\n";
		
				     
			    
		return info;
	}

}
