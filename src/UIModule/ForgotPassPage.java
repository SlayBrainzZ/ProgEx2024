package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;

//import ExceptionHandling.ExceptionMaster;

public class ForgotPassPage extends JFrame {

	// String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	// Connection con;

	// JLabel lblErrorMsg;

	// ExceptionMaster obj = new ExceptionMaster();

	private JPanel contentPane;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassPage frame = new ForgotPassPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error starting application: " + e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgotPassPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(0, 0, 5, 0);
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;
		gbcContent.anchor = GridBagConstraints.CENTER;

		// lblErrorMsg = new JLabel("");
		// lblErrorMsg.setForeground(Color.RED);
		// lblErrorMsg.setFont(new Font("Arial", Font.BOLD, 22));
		// lblErrorMsg.setBounds(503, 142, 659, 48);
		// contentPane.add(lblErrorMsg, gbcContent);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		panel.setBackground(new Color(60, 63, 65));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblForgotPass = new JLabel("Forgot Password");
		lblForgotPass.setFont(new Font("Calibri", Font.BOLD, 26));
		lblForgotPass.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblForgotPass, gbcPanel);

		JLabel lblEmail = new JLabel("Email-ID");
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 20));
		lblEmail.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblEmail, gbcPanel);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// lblErrorMsg.setText("");
			}
		});
		txtEmail.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtEmail, gbcPanel);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String Pass = ValidateEmailDB(txtEmail.getText());

				// if (!Pass.equals("-1")) {
				//     lblErrorMsg.setText("Your old password is : " + Pass);

				// } else {
				//     lblErrorMsg.setText("The email you have entered is wrong. Please try again");
				// }
				JOptionPane.showMessageDialog(null, "Continue button clicked!");
			}
		});
		btnContinue.setFont(new Font("Calibri", Font.BOLD, 20));
		btnContinue.setBackground(new Color(43, 43, 43));
		btnContinue.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnContinue, gbcPanel);

		contentPane.add(panel, gbcPanelCenter);

		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon("resources/aqua BG.png")); // Update with correct image path
		gbcContent.gridx = 0;
		gbcContent.gridy = 2;
		contentPane.add(lblBG, gbcContent);

		// try {
		//     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//     con = DriverManager.getConnection(connectionUrl);

		// } catch (ClassNotFoundException ex) {
		//     // obj.InsertException(ex.getMessage(), "ForgotPassPage", "Constructor");
		//     // lblErrorMsg.setText("Database driver not found. Contact support.");
		//     JOptionPane.showMessageDialog(null, "Database driver not found. Contact support.",
		//             "Error", JOptionPane.ERROR_MESSAGE);
		// } catch (Exception ex) {
		//     // obj.InsertException(ex.getMessage(), "ForgotPassPage", "Constructor");
		//     // lblErrorMsg.setText("Error connecting to database. Contact support.");
		//     JOptionPane.showMessageDialog(null, "Error connecting to database. Contact support.",
		//             "Error", JOptionPane.ERROR_MESSAGE);
		// }
	}

	// public String ValidateEmailDB(String EmailID) {
	//     String Output = "-1";

	//     try {
	//         CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_ValidateEmail(?)}");

	//         stmt.setString(1, EmailID);
	//         stmt.execute();

	//         ResultSet rs = stmt.getResultSet();
	//         while (rs.next()) {
	//             Output = rs.getString("Password");
	//         }

	//     } catch (Exception Ex) {
	//         // obj.InsertException(Ex.getMessage(), "ForgotPassPage", "ValidateEmailDB");
	//         // lblErrorMsg.setText("Error validating email. Contact support.");
	//         JOptionPane.showMessageDialog(null, "Error validating email. Contact support.",
	//                 "Error", JOptionPane.ERROR_MESSAGE);
	//     }

	//     return Output;
	// }
}
