package Administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAll extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewAll(int vendorID) {
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(new BorderLayout());
		
		
		
		String purchases = purchases(vendorID);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 63, 560, 272);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		textArea.setEditable(false);
		textArea.setText(purchases);
		contentPane.add(textArea);
		
		/*
		JLabel lblTransactions = new JLabel("Transactions :");
		lblTransactions.setFont(new Font("Sylfaen", Font.PLAIN, 17));

		lblTransactions.setBounds(10, 10, 116, 24);
		contentPane.add(lblTransactions);
		*/
		
		/*
		JLabel lblAllPayments = new JLabel("All Payments :");
		lblAllPayments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblAllPayments.setBounds(336, 26, 116, 14);
		contentPane.add(lblAllPayments);
		*/
	}

	
	public String purchases(int vendorID) {
		String info = "Unique ID:\t Time:\t Vendor:\t\t Description:\t Amount:\n\n";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM purchases where vendor_id = " + vendorID + " Order by date desc;";
			
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
}