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


public class twoCards extends JFrame {
	
	private JPanel contentPane;
	private JButton card_one;
	private JButton card_two;
	
	
	
	
	public twoCards(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String [] card = {"card 1", "card 2"};
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from users where user = '" + user + "';");
			myRs.next();
			card[0] = myRs.getString("card_number");
			myRs.next();
			card[1] = myRs.getString("card_number");
			
			myConn.close();
		} catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
		}
		
		
				
		card_one = new JButton(card[0]);
		card_one.setBounds(200, 200, 119, 35);
		card_one.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						Account acnt = new Account(card[0]);
						acnt.setVisible(true);
						dispose();
					}
				});
		contentPane.add(card_one);
		
		card_two = new JButton(card[1]);
		card_two.setBounds(200, 300, 119, 35);
		card_two.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						Account acnt = new Account(card[1]);
						acnt.setVisible(true);
						dispose();
					}
				});
		contentPane.add(card_two);
		
		JLabel loginMsg = new JLabel("You currently have 2 accounts. Choose to which one you would like to log in");
		loginMsg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		loginMsg.setBounds(50, 79, 500, 26);
		getContentPane().add(loginMsg);
	}
	

}
