package Administrator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateNewAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CreateNewAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateANew = new JLabel("Create a new Credit Card User Account");
		lblCreateANew.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblCreateANew.setBounds(139, 64, 294, 45);
		contentPane.add(lblCreateANew);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(66, 169, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblCreditcardNumber = new JLabel("CreditCard Number :");
		lblCreditcardNumber.setBounds(38, 249, 134, 14);
		contentPane.add(lblCreditcardNumber);
		
		JLabel lblCreditLimit = new JLabel("Credit Limit :");
		lblCreditLimit.setBounds(66, 329, 68, 14);
		contentPane.add(lblCreditLimit);
		
		textField = new JTextField();
		textField.setBounds(204, 169, 161, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 249, 161, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(204, 329, 161, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel loginMsg = new JLabel("");
		loginMsg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		loginMsg.setForeground(Color.red);
		loginMsg.setBounds(100, 369, 400, 26);
		getContentPane().add(loginMsg);
		
		JButton create = new JButton("Create");
		create.setBounds(333, 404, 89, 23);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Create an account with  activated set to false? in DB
				 */

				String user = textField.getText();
				String cardNum = textField_1.getText();
				String creditLimit = textField_2.getText();

				if(user.length() <= 0 || user.length() > 45)
					loginMsg.setText("Name cannot be more then 45 characters or 0 characters!");
				else if (cardNum.length() <= 0 || cardNum.length() > 4)
					loginMsg.setText("Please put credit card number (4 numbers)!");
				else if (creditLimit.length() < 1)
					loginMsg.setText("Credit limit cannot be empty!");
				else if (checkCC(cardNum))
					loginMsg.setText("The card number is already exist! Choose another one.");
				else {
					create(user, cardNum, creditLimit);
					dispose();
				}
					
				
			}
			
		});
		contentPane.add(create);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(333, 458, 89, 23);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(cancel);
	}
	public boolean create(String name, String card, String limit) {
		java.util.Date date=new java.util.Date();	
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from cardholders where name ='" + name +"';");
			String existingName = "";
			String existingCard = "";
			while(myRs.next()) {
				existingName = myRs.getString("name");
				existingCard = myRs.getString("cardnumber");
			}
			
			if (existingName.equals(name)) {
				
				
				myRs = myStmt.executeQuery("select * from users where card_number ='" + existingCard +"';");
				myRs.next();
				String user = myRs.getString("user");
				String pass = myRs.getString("password");
				
				
				String query = "insert into cardholders values (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = myConn.prepareStatement(query);
				preparedStmt.setInt (1, Integer.parseInt(card));
			     preparedStmt.setString (2, limit);
			     preparedStmt.setDate   (3, sqlDate);
			     preparedStmt.setInt (4, 1);
			     preparedStmt.setBigDecimal(5, new BigDecimal(0.00));
			     preparedStmt.setString   (6, name);
			     preparedStmt.execute();
			     
			     
				
			     String query1 = "insert into users values (?, ?, ?)";
			     PreparedStatement preparedStmt1 = myConn.prepareStatement(query1);
			     preparedStmt1.setInt (1, Integer.parseInt(card));
			     preparedStmt1.setString (2, user);
			     preparedStmt1.setString   (3, pass);
			     preparedStmt1.execute();
			} else {
			
			String query = "insert into cardholders values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			preparedStmt.setInt (1, Integer.parseInt(card));
		     preparedStmt.setString (2, limit);
		     preparedStmt.setDate   (3, sqlDate);
		     preparedStmt.setInt (4, 0);
		     preparedStmt.setBigDecimal(5, new BigDecimal(0.00));
		     preparedStmt.setString   (6, name);
		     preparedStmt.execute();
			
			}
	

		     myConn.close();
		     return true;			
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	        return false;
	    }
		
	}
	
	public boolean checkCC(String f) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection myConn = DriverManager.getConnection(
						"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			               "cali8332", "23058332");
				Statement myStmt = myConn.createStatement();
				
				
				ResultSet myRs = myStmt.executeQuery("select * from cardholders");
				while (myRs.next()) {
					if (myRs.getString("cardNumber").equals(f)) {
						System.out.println("card exists");
						return true;
					} else return false;
				} 
		    } catch (Exception exc) {
		        System.err.println("Got an exception!");
		        System.err.println(exc.getMessage());
		    }
			
			
			return false;
	}

}
