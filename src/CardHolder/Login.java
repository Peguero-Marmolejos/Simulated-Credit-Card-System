package CardHolder;
import java.awt.Color;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton forgotPassword;
	private JButton newUser;
	private JButton loginBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200, 200, 601, 599);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel username = new JLabel("Username");
			username.setFont(new Font("Sylfaen", Font.PLAIN, 15));
			username.setBounds(82, 198, 61, 26);
			getContentPane().add(username);
			
			JLabel password = new JLabel("Password");
			password.setFont(new Font("Sylfaen", Font.PLAIN, 15));
			password.setBounds(82, 259, 61, 26);
			getContentPane().add(password);
			
			textField = new JTextField();
			textField.setBounds(183, 194, 179, 35);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(183, 255, 179, 35);
			getContentPane().add(passwordField);
			
			JLabel title = new JLabel("Login");
			title .setFont(new Font("Sylfaen", Font.PLAIN, 35));
			title .setBounds(183, 24, 251, 56);
			getContentPane().add(title );
			
			//forgotPassword = new JButton("Forgot password?");
			//forgotPassword.setBounds(183, 301, 151, 26);
			//contentPane.add(forgotPassword);
			
			newUser = new JButton("New User");
			newUser.setBounds(183, 338, 119, 35);
			newUser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent click) {
							SignUp signup = new SignUp();
							signup.setVisible(true);
							dispose();
						}
					});
			contentPane.add(newUser);
			
			JLabel loginMsg = new JLabel("");
			loginMsg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
			loginMsg.setForeground(Color.red);
			loginMsg.setBounds(200, 479, 200, 26);
			getContentPane().add(loginMsg);
			
			
			loginBtn = new JButton("Login");
			loginBtn.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String user = textField.getText();
					String pWord = passwordField.getText();
					// checking if user field is empty. then checking if user exists. 
					if (user.equals("")) {
						loginMsg.setText("Please enter user name!");
					} else if (!checkUserExist(user)) {
							loginMsg.setText("No such user exist!");
					}  else if(pWord.equals("")) {
						loginMsg.setText("Please enter password!");
					} else if (!checkPWord(pWord)) {
						loginMsg.setText("That password is incorrect!");
					} else {
						loginMsg.setText("Logged in successfully!");
						if (getCardCount(user) == 1) {
							String cCard = getCardFromUser(user);
							Account acnt = new Account(cCard);
							acnt.setVisible(true);
							dispose();
						} else {
							System.out.println("new windows for 2 cards is needed");
							twoCards acnt = new twoCards(user);
							acnt.setVisible(true);
							dispose();
						}

						
					}
			
				}
								
			});
			
			loginBtn.setFont(new Font("Sylfaen", Font.PLAIN, 16));
			loginBtn.setBounds(412, 449, 151, 75);
			contentPane.add(loginBtn);
		
		
			

	}
	
	public boolean checkPWord(String f) {
		boolean status = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from users");
			while (myRs.next()) {
				if (myRs.getString("password").equals(f)) {
					status = true;
					myConn.close();
					break;
				};
			}
			myConn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
 
		}
		
		
		return status;
	}
	
	public boolean checkUserExist(String user) {
		boolean status = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from users");
			while (myRs.next()) {
				if (myRs.getString("user").equals(user)) {
					status = true;
					myConn.close();
					break;
				};
			}
			myConn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}
	
	public String getCardFromUser(String user) {
		String card = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from users where user = '" + user + "';");
			myRs.next();
			card = myRs.getString("card_number");
			myConn.close();
			return card;
		} catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
		}
		return card;
	}
	
	public Integer getCardCount(String user) {
		Integer count = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select COUNT(card_number) from users where user = '" + user + "';");
			myRs.next();
			count = Integer.parseInt(myRs.getString("COUNT(card_number)"));
			myConn.close();
			return count;
		} catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
		}
		
		
		
		return count;
	}
	
	
	
}
