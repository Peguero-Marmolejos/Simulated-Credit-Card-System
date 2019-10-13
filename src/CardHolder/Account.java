package CardHolder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class Account extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	
public Account(String card) {
		
		//Integer card = 2255;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea top = new JTextArea();
		top.setEditable(false);
		top.setBounds(29, 53, 525, 97);
		top.setText(topInfo(card));
		contentPane.add(top);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(29, 187, 525, 228);
		textArea.setText(purchases(card));
		contentPane.add(textArea);
		
		JLabel lblAccountStatement = new JLabel("Account Statement");
		lblAccountStatement.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblAccountStatement.setBounds(29, 11, 210, 31);
		contentPane.add(lblAccountStatement);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(537, 187, 17, 229);
		contentPane.add(scrollBar);
		
		JLabel lblRecentPurchases = new JLabel("Recent Purchases :");
		lblRecentPurchases.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblRecentPurchases.setBounds(29, 155, 120, 31);
		contentPane.add(lblRecentPurchases);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(29, 454, 525, 63);
		textArea_1.setText(payments(card));
		contentPane.add(textArea_1);
		
		JLabel payments = new JLabel("Payments :");
		payments.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		payments.setBounds(29, 429, 81, 14);
		contentPane.add(payments);
		
		JButton viewAll = new JButton("View All");
		viewAll.setBounds(465, 420, 89, 23);
		viewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAll view = new ViewAll(card);
				view.setVisible(true);
				
			}
		});

		contentPane.add(viewAll);
		
		JButton makeAPayment = new JButton("Make a Payment");
		makeAPayment.setBounds(421, 526, 133, 23);
		makeAPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MakeAPayment map = new MakeAPayment(card);
				map.setVisible(true);
				dispose();
			}
		});
		contentPane.add(makeAPayment);
		
		JButton logOut = new JButton("logOut");
		logOut.setBounds(421, 026, 133, 23);
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		contentPane.add(logOut);
	}
	
	public String topInfo(String card) {
		String info = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM cardholders where cardnumber = " + card + ";";
			
		     ResultSet rs = myStmt.executeQuery(query);
		     rs.next();
		     info = "Account name: "+ rs.getString("name");
		     info = info + "\nCard number: "+ rs.getString("cardnumber");
		     info = info + "\nCredit limit: $"+ rs.getString("creditlimit");
		     info = info + "\nAmount due : $"+ rs.getString("balance");

		     myConn.close();
		     
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	        return exc.getMessage();
	    }
		
		return info;
	}
	
	public String purchases(String card) {
		String info = "Unique ID:\t Time:\t Vendor:\t\t Description:\t Amount:\n\n";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM purchases where card_number = " + card + " AND date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() Order by date desc limit 10;";
			
			//String query = "SELECT * FROM purchases where date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE();";
			
		     ResultSet rs = myStmt.executeQuery(query);
		     if (rs.next()) {
			     do {
			    	 info = info + ""+ rs.getString("purchase_id") + "\t";
				     info = info + ""+ rs.getString("date") + "\t";
				     info = info + ""+ rs.getString("vendor") + "\t";
				     if(rs.getString("vendor").contains("Restaurant")) info+= "\t";
				     info = info + ""+ rs.getString("description") + "\t";
				     info = info + "$"+ rs.getString("amount") + "\n" ;
			     }	while (rs.next()); {
			    	 
			     }
		     } else
		    	 info = "There are currently no transactions to show!";

		     
		     

		     myConn.close();
		     
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	        return exc.getMessage();
	    }
		
		return info;
	}
	
	public String payments(String card) {
		String info = "Payment ID:\t Time:\t Amount:\n";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM payments where card_number = " + card + " AND date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() Order by date desc limit 3;";
			
		     ResultSet rs = myStmt.executeQuery(query);
		     if (rs.next()) {
		    	 do {
			    	 info = info + ""+ rs.getString("pay_id") + "\t";
				     info = info + ""+ rs.getString("date") + "\t";
				     info = info + "$"+ rs.getString("amount") + "\n" ;
			     }	while (rs.next()); {
			    	 
			     }
		     } else 
		    	 info = "There are no Payments to show!";
		     
		     myConn.close();
		     
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	        return exc.getMessage();
	    }
		return info;
	}
}
