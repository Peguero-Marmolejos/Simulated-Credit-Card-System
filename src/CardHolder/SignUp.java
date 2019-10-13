package CardHolder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JFrame {
	private JTextField textFieldUN;//username textfield
	private JTextField textFieldCC;//creditcard textfield
	private JPasswordField passwordField;//password
	private JPasswordField passwordField_1;//retype
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SignUp() {
		getContentPane().setFont(new Font("Sylfaen", Font.PLAIN, 30));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		getContentPane().setLayout(null);
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		username.setBounds(82, 198, 61, 26);
		getContentPane().add(username);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		password.setBounds(82, 336, 61, 26);
		getContentPane().add(password);
		
		JLabel retype = new JLabel("Re-Type Password");
		retype.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		retype.setBounds(23, 397, 120, 26);
		getContentPane().add(retype);
		
		JLabel creditCard = new JLabel("Credit Card");
		creditCard.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		creditCard.setBounds(71, 268, 72, 14);
		getContentPane().add(creditCard);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(183, 194, 179, 35);
		getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		textFieldCC = new JTextField();
		textFieldCC.setBounds(183, 258, 179, 35);
		getContentPane().add(textFieldCC);
		textFieldCC.setColumns(10);
		String creditcard = textFieldCC.getText();

		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 332, 179, 35);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(183, 393, 179, 35);
		getContentPane().add(passwordField_1);
		
		JLabel loginMsg = new JLabel("");
		loginMsg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		loginMsg.setForeground(Color.red);
		loginMsg.setBounds(120, 450, 500, 26);
		getContentPane().add(loginMsg);
		
		JButton activate = new JButton("Activate Account!");
		activate.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		activate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		activate.setBounds(361, 478, 192, 56);
		activate.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String pWord = new String(passwordField.getPassword());
				String pWord1 = new String(passwordField_1.getPassword());
				String uName = textFieldUN.getText();
				String cCard = textFieldCC.getText();
				
				
				System.out.println(pWord+pWord1+uName+cCard);
				if(!checkCC(cCard)) 
					loginMsg.setText("Card number doesn't exist or is not 4 digits.");//first check if creditcard is already in DB? check if that is a reasonable 
				else if (checkUserExist(uName))
					loginMsg.setText("That user is already exists, pick another one!");
				else if(!pWord.equals(pWord1))
					loginMsg.setText("Passwords don't match.");
				else if(!checkPWord(pWord))
					loginMsg.setText("That password is less than 6 characters.");
				else if (!isAlphaNumeric(pWord))
					loginMsg.setText("That password must include letters and numbers.");
				else if (isActivated(cCard)) 
					loginMsg.setText("Account already exist for that credit card number");
				else if (activate(Integer.valueOf(cCard), uName, pWord)) {
					loginMsg.setText("Account created successfully");
					Account acnt = new Account(cCard);
					acnt.setVisible(true);
					dispose();
				} else {
					loginMsg.setText("There was a problem creating account. see log for information");
				}			
			}
	
		});
		getContentPane().add(activate);
		
		JLabel title = new JLabel("Sign Up Today");
		title .setFont(new Font("Sylfaen", Font.PLAIN, 35));
		title .setBounds(141, 24, 251, 56);
		getContentPane().add(title );
		
		JButton haveAnAccount = new JButton("Returning User");
		haveAnAccount.setBackground(SystemColor.menu);
		haveAnAccount.setBounds(361, 91, 120, 23);
		haveAnAccount.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						Login lgn = new Login();
						lgn.setVisible(true);
						dispose();						
					}
			
		});
		getContentPane().add(haveAnAccount);
	}
	public boolean checkPWord(String f) {
		if(f.length() < 6) {
			return false;
		}else 			
		return true;
	}
	
	public boolean checkCC(String f) {
		if(f.length() == 4) {
			
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
					} 
				} 
		    } catch (Exception exc) {
		        System.err.println("Got an exception!");
		        System.err.println(exc.getMessage());
		    }
			
			
			return false;
		}else return false;
			
	}
	
	public boolean isActivated(String f) {			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection myConn = DriverManager.getConnection(
						"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			               "cali8332", "23058332");
				Statement myStmt = myConn.createStatement();
				
				
				ResultSet myRs = myStmt.executeQuery("select * from cardholders where cardNumber =" + f +";");
				
				
				myRs.next();
					if (myRs.getString("activated").equals("1")) {
						return true;
					}

		    } catch (Exception exc) {
		        System.err.println("Got an exception!");
		        System.err.println(exc.getMessage());
		    }

			return false;
	}
	
	public boolean activate(Integer cCard, String user, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
	//		ResultSet myRs = myStmt.executeQuery("select * from cardholders");
	//		myRs.next();
//			if my 
			
			String query = "insert into users values (?, ?, ?)";
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
		     preparedStmt.setInt (1, cCard);
		     preparedStmt.setString (2, user);
		     preparedStmt.setString   (3, pass);
		     preparedStmt.execute();
		     myStmt.executeUpdate("update cardholders set activated = 1 where cardNumber =" + cCard +";");
		     myConn.close();
		     return true;

		     
		     
			
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	        return false;
	    }
		
	}
	
	public boolean checkUserExist(String f) {			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection myConn = DriverManager.getConnection(
						"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			               "cali8332", "23058332");
				Statement myStmt = myConn.createStatement();
				
				
				ResultSet myRs = myStmt.executeQuery("select * from users");
				while (myRs.next()) {
					if (myRs.getString("user").equals(f)) {
						System.out.println("such user exists");
						return true;
					} 
				} 
		    } catch (Exception exc) {
		        System.err.println("Got an exception!");
		        System.err.println(exc.getMessage());
		    }
			
			
			return false;

			
	}
	
	public static boolean isAlphaNumeric(String s) {
		return s.matches("^(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]+$");
	}

}
