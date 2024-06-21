package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResetPassword extends JFrame {

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	private JPanel contentPane;
	private JTextField txtUname;
	private JPasswordField txtPass;
	private JPasswordField txtNewPass;
	private JPasswordField txtConfirm;
	private JLabel lblErrorMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
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
	public ResetPassword() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		contentPane.setBackground(new Color(200, 200, 200)); // Set background color

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(10, 10, 10, 10);
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;
		gbcContent.anchor = GridBagConstraints.CENTER;

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

		JLabel lblResetPass = new JLabel("Reset Password");
		lblResetPass.setFont(new Font("Calibri", Font.BOLD, 26));
		lblResetPass.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblResetPass, gbcPanel);

		JLabel lblUname = new JLabel("Username");
		lblUname.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUname.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		panel.add(lblUname, gbcPanel);

		txtUname = new JTextField();
		txtUname.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtUname, gbcPanel);

		JLabel lblPass = new JLabel("Current Password");
		lblPass.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPass.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblPass, gbcPanel);

		txtPass = new JPasswordField();
		txtPass.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtPass, gbcPanel);

		JLabel lblNewPass = new JLabel("New Password");
		lblNewPass.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewPass.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblNewPass, gbcPanel);

		txtNewPass = new JPasswordField();
		txtNewPass.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtNewPass, gbcPanel);

		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfirm.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblConfirm, gbcPanel);

		txtConfirm = new JPasswordField();
		txtConfirm.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtConfirm, gbcPanel);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = txtUname.getText();
				String OldPassword = new String(txtPass.getPassword());
				String NewPassword = new String(txtNewPass.getPassword());
				String ConfirmPass = new String(txtConfirm.getPassword());

				if (NewPassword.equals(ConfirmPass)) {
					if (getLoginDetailsDB(Username, OldPassword, NewPassword) == 1) {
						JOptionPane.showMessageDialog(null, "Password has been updated");
					} else {
						lblErrorMsg.setText("Wrong password");
					}
				} else {
					lblErrorMsg.setText("Enter new password again and confirm password");
				}
			}
		});
		btnReset.setFont(new Font("Calibri", Font.BOLD, 20));
		btnReset.setBackground(new Color(43, 43, 43));
		btnReset.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 5;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnReset, gbcPanel);

		lblErrorMsg = new JLabel("");
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setFont(new Font("Arial", Font.BOLD, 18));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 6;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblErrorMsg, gbcPanel);

		contentPane.add(panel, gbcPanelCenter);

		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon("resources/aqua BG.png")); // Update with correct image path
		gbcContent.gridx = 0;
		gbcContent.gridy = 2;
		contentPane.add(lblBG, gbcContent);
	}

	public int getLoginDetailsDB(String Uname, String Pass, String NewPass) {
		int stmtEx = -1;
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_ChangePassword(?,?,?)}");

			stmt.setString(1, Uname);
			stmt.setString(2, Pass);
			stmt.setString(3, NewPass);

			stmtEx = stmt.executeUpdate();
			lblErrorMsg.setText("Statement executed: " + stmtEx);

			stmt.close();
		} catch (Exception ex) {
			lblErrorMsg.setText(ex.getMessage());
		}
		return stmtEx;
	}
}
