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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class delete extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */

	public delete() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateANew = new JLabel("Delete credit card");
		lblCreateANew.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblCreateANew.setBounds(139, 64, 294, 45);
		contentPane.add(lblCreateANew);
		
		JLabel lblCreditcardNumber = new JLabel("CreditCard Number:");
		lblCreditcardNumber.setBounds(38, 249, 134, 14);
		contentPane.add(lblCreditcardNumber);
	
		textField_1 = new JTextField();
		textField_1.setBounds(204, 249, 161, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		
		JLabel Msg = new JLabel("");
		Msg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		Msg.setForeground(Color.red);
		Msg.setBounds(100, 369, 400, 26);
		getContentPane().add(Msg);
		
		JButton create = new JButton("Delete");
		create.setBounds(333, 404, 89, 23);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("c");
				String card = textField_1.getText();
				
				if(cardExist(card)) {
					del(card);
					dispose();
				} else 
					Msg.setText("Card doesn't exist in the system");
	
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
	
	public boolean cardExist(String card) {
		System.out.print("b");
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
	
	public void del(String card) {
		System.out.print("a");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			String query = "";
			query = query + "delete from cardholders where cardnumber = " + card + ";";
			//query = query + "delete from users where card_number = " + card + ";";
			//query = query + "delete from purchases where card_number = " + card + ";";
			//query = query + "delete from payments where card_number = " + card + ";";
			myStmt.executeUpdate(query);

		
			/*myStmt.executeQuery("delete from cardholders where cardnumber = " + card + ";");
			myStmt2.executeQuery("delete from users where cardnumber = " + card + ";");
			myStmt3.executeQuery("delete from purchases where cardnumber = " + card + ";");
			myStmt4.executeQuery("delete from payments where cardnumber = " + card + ";");
			*/
			
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	    }
	}

}
