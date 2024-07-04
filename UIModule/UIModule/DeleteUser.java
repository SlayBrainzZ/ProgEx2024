package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BusinessLayer.UserBusiness;


public class DeleteUser extends JFrame {

	private static final long serialVersionUID = 1L;
	UserBusiness objUser = new UserBusiness();


	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JPasswordField pwdPass;
	private JComboBox<String> comboBox;
	private JLabel lblMsg;
	private List<List<Object>> users;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
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
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblDeleteUser = new JLabel("Delete User");
		lblDeleteUser.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteUser.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblDeleteUser, gbcPanel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsername.setForeground(Color.WHITE);
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
		lblName.setForeground(Color.WHITE);
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
		lblPhoneNumber.setForeground(Color.WHITE);
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
		lblEmailId.setForeground(Color.WHITE);
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
		lblAddress.setForeground(Color.WHITE);
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
		lblPassword.setForeground(Color.WHITE);
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

		JButton btnDel = new JButton("Delete");
		btnDel.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDel.setBackground(new Color(43, 43, 43));
		btnDel.setForeground(Color.WHITE);
		btnDel.setBorder(new LineBorder(new Color(169, 169, 169)));
		btnDel.setFocusPainted(false);
		btnDel.setPreferredSize(new Dimension(100, 40));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(pwdPass.getPassword());
				if (password.isEmpty()) {
					lblMsg.setText("Please enter the password");
					lblMsg.setForeground(Color.BLACK);
				} else {
					deleteUser();
					clearFields();
					populateComboBox(); // Refresh the combo box after deletion
				}
			}
		});

		gbcPanel.gridx = 0;
		gbcPanel.gridy = 7;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnDel, gbcPanel);

		gbcContent.gridy = 1;
		contentPane.add(panel, gbcContent);

		populateComboBox(); // Populate the combo box with usernames
	}

	public void populateComboBox() {
		// users = objUser.readUsers();
		comboBox.removeAllItems(); // Clear comboBox before populating

		/* for (List<Object> user : users) {
			String username = (String) user.get(1); // Username is the second element
			comboBox.addItem(username);
		} */
	}

	public void getUserDetails(String username) {
		/* for (List<Object> user : users) {
			if (user.get(1).equals(username)) {
				txtName.setText((String) user.get(0)); // Name
				txtPhone.setText((String) user.get(4)); // Phone Number
				txtEmail.setText((String) user.get(3)); // Email ID
				txtAddress.setText((String) user.get(5)); // Address
				pwdPass.setText((String) user.get(2)); // Password (assuming you want to display it)
				break;
			}
		} */
	}

	public void deleteUser() {
		/* String uname = comboBox.getSelectedItem().toString();
		String out = objUser.deleteUser(uname);
		if(out.equals("1")){
			JOptionPane.showMessageDialog(null,"User deleted successfully");
		}
		else{
			JOptionPane.showMessageDialog(null,"Something went wrong");
		} */
	}

	private void clearFields() {
		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		pwdPass.setText("");
	}
}
