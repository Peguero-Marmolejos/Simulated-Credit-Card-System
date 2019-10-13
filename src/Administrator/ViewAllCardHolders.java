package Administrator;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CardHolder.Account;
import CardHolder.ViewAll;

public class ViewAllCardHolders extends JFrame {
	private JTextField card;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewAllCardHolders() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 560, 372);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		textArea.setEditable(false);
		textArea.setText(view());
		contentPane.add(textArea);
		
		card = new JTextField();
		card.setBounds(250, 390, 79, 35);
		getContentPane().add(card);
		card.setColumns(10);
		
		JLabel Msg = new JLabel("");
		Msg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		Msg.setForeground(Color.red);
		Msg.setBounds(5, 390, 200, 26);
		getContentPane().add(Msg);
		
		JButton viewAccount = new JButton("View Account");
		viewAccount.setBounds(365, 390, 119, 33);
		viewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String c = card.getText();
				if (!isNumeric(c))
					Msg.setText("Credit card not numeric!");
				else if (c.length() > 4 || c.length() < 4) 
					Msg.setText("Card should be 4 digits!");
				else if (!cardExist(c)) 
					Msg.setText("Credit card doesn't exist!");
				else {
					Account acnt = new Account(c);
					acnt.setVisible(true);
				}
				
				
			}
		});

		contentPane.add(viewAccount);
	}
	
	public String view() {
		String info = "Full Name:\t\t Card num:\t limit:\t balance:\n\n";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM cardholders order by name;";
			
			//String query = "SELECT * FROM purchases where date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE();";
			
		     ResultSet rs = myStmt.executeQuery(query);
		     if (rs.next()) {
			     do {
			    	 info = info + ""+ rs.getString("name") + "\t\t";
			    	 info = info + ""+ rs.getString("cardnumber") + "\t";
				     info = info + ""+ rs.getString("creditlimit") + "\t";
				     info = info + ""+ rs.getString("balance") + "\n";
				     
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
	
	public boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
	
	public boolean cardExist(String card) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from cardholders");
			while (myRs.next()) {
				if (myRs.getString("cardNumber").equals(card)) {
					System.out.println("card exists");
					return true;
				}
			} 
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	    }
		return false;
	}
	

}
