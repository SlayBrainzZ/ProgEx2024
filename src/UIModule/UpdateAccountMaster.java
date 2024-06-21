package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import ExceptionHandling.ExceptionMaster;

public class UpdateAccountMaster extends JFrame {

	private static final long serialVersionUID = 1L;
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	String[] Users = new String[]{};

	ExceptionMaster obj = new ExceptionMaster();

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JPasswordField pwdPass;
	JComboBox<String> comboBox;
	JLabel lblMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAccountMaster frame = new UpdateAccountMaster();
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
	public UpdateAccountMaster() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "UpdateAccountMaster", "Constructor");
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200)); // Match DeleteUser background
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(0, 0, 5, 0);
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;
		gbcContent.anchor = GridBagConstraints.CENTER;

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 22));
		lblMsg.setBounds(603, 197, 690, 39);
		contentPane.add(lblMsg, gbcContent);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(169, 169, 169))); // Match DeleteUser border
		panel.setBackground(new Color(60, 63, 65)); // Match DeleteUser background
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblUpdateAccount = new JLabel("Update Account");
		lblUpdateAccount.setFont(new Font("Calibri", Font.BOLD, 26));
		lblUpdateAccount.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblUpdateAccount, gbcPanel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsername.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		panel.add(lblUsername, gbcPanel);

		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = (String) comboBox.getSelectedItem();
				if (user != null) {
					getUserDetails(user);
				}
			}
		});
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		comboBox.setPreferredSize(new Dimension(150, 28));
		panel.add(comboBox, gbcPanel);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblName.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblName, gbcPanel);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtName.setEditable(false);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		txtName.setPreferredSize(new Dimension(150, 28));
		panel.add(txtName, gbcPanel);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPhoneNumber.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblPhoneNumber, gbcPanel);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPhone.setEditable(false);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		txtPhone.setPreferredSize(new Dimension(150, 28));
		panel.add(txtPhone, gbcPanel);

		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Calibri", Font.BOLD, 20));
		lblEmailId.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblEmailId, gbcPanel);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEmail.setEditable(false);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		txtEmail.setPreferredSize(new Dimension(150, 28));
		panel.add(txtEmail, gbcPanel);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAddress.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 5;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblAddress, gbcPanel);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAddress.setEditable(false);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 5;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		txtAddress.setPreferredSize(new Dimension(150, 28));
		panel.add(txtAddress, gbcPanel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPassword.setForeground(Color.WHITE); // Match DeleteUser font color
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 6;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblPassword, gbcPanel);

		pwdPass = new JPasswordField();
		pwdPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 6;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		pwdPass.setPreferredSize(new Dimension(150, 28));
		panel.add(pwdPass, gbcPanel);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 20));
		btnUpdate.setBackground(new Color(43, 43, 43)); // Match DeleteUser button color
		btnUpdate.setForeground(Color.WHITE); // Match DeleteUser button text color
		btnUpdate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(169, 169, 169))); // Match DeleteUser button border
		btnUpdate.setFocusPainted(false);
		btnUpdate.setPreferredSize(new Dimension(100, 40));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(pwdPass.getPassword());
				if (password.isEmpty()) {
					lblMsg.setText("Please enter the password");
				} else {
					String result = updateUser(password);
					lblMsg.setText(result);
					clearFields();
					JOptionPane.showMessageDialog(panel, result);
				}
			}
		});

		gbcPanel.gridx = 0;
		gbcPanel.gridy = 7;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnUpdate, gbcPanel);

		GridBagConstraints gbcMainPanel = new GridBagConstraints();
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 0;
		gbcMainPanel.insets = new Insets(20, 20, 20, 20);
		contentPane.add(panel, gbcMainPanel);
	}

	private void getUserDetails(String username) {
		try {
			CallableStatement cstmt = con.prepareCall("{call GetUserDetails(?)}");
			cstmt.setString(1, username);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				txtName.setText(rs.getString("Name"));
				txtPhone.setText(rs.getString("Phone"));
				txtEmail.setText(rs.getString("Email"));
				txtAddress.setText(rs.getString("Address"));
			}
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "UpdateAccountMaster", "getUserDetails");
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	private String updateUser(String password) {
		String result = "";
		try {
			String username = (String) comboBox.getSelectedItem();
			CallableStatement cstmt = con.prepareCall("{call UpdateUser(?, ?)}");
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("Result");
			}
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "UpdateAccountMaster", "updateUser");
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		return result;
	}

	private void clearFields() {
		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		pwdPass.setText("");
	}
}
