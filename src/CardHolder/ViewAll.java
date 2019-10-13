package CardHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ViewAll extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewAll(String card) {
		setBounds(200, 200, 601, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(null);
		//setLayout(new BorderLayout());
		
		String purchases = purchases(card);
		String payments = payments(card);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 63, 560, 272);
		textArea.setEditable(false);
		textArea.setText(purchases);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(336, 63, 560, 272);
		textArea_1.setText(payments);
		contentPane.add(new JScrollPane(textArea_1));
	
	}
	
	public String payments(String card) {
		String info = "Payment ID:\t Time:\t Amount:\n";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM payments where card_number = " + card + " Order by date desc;";
			
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
	
	public String purchases(String card) {
		String info = "Unique ID:\t Time:\t Vendor:\t\t Description:\t Amount:\n\n";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM purchases where card_number = " + card + " Order by date desc;";
			
			//String query = "SELECT * FROM purchases where date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE();";
			
		     ResultSet rs = myStmt.executeQuery(query);
		     if (rs.next()) {
			     do {
			    	 info = info + ""+ rs.getString("purchase_id") + "\t";
				     info = info + ""+ rs.getString("date") + "\t";
				     info = info + ""+ rs.getString("vendor") + "\t";
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