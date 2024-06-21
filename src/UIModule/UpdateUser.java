package UIModule;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

import BusinessLayer.CreateUserBusiness;
import ExceptionHandling.ExceptionMaster;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class UpdateUser extends JFrame {

	CreateUserBusiness objUpdate = new CreateUserBusiness();
	ExceptionMaster obj = new ExceptionMaster();

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	JLabel lblMsg;
	private JPanel contentPane;
	private JTextField txtNewUname;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField txtAddress;
	JComboBox<String> comboBox;
	String Usernames[] = {};

	private JPasswordField pwdPass;
	private JLabel lblPanelBG;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUser frame = new UpdateUser();
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
	public UpdateUser() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "UpdateUser", "constructor");

		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(43, 43, 43)));
		panel.setBackground(new Color(43, 43, 43));
		panel.setBounds(343, 206, 910, 360);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNewUname = new JTextField();
		txtNewUname.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNewUname.setBounds(230, 132, 206, 28);
		panel.add(txtNewUname);
		txtNewUname.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(66, 85, 112, 25);
		panel.add(lblNewLabel);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(66, 181, 112, 19);
		panel.add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtName.setBounds(230, 178, 206, 28);
		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Email ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(466, 88, 87, 19);
		panel.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEmail.setBounds(636, 87, 206, 28);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(466, 125, 140, 25);
		panel.add(lblNewLabel_2);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPhone.setBounds(636, 135, 206, 28);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		lblNewLabel_3 = new JLabel("Update User Details");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(353, 8, 203, 31);
		panel.add(lblNewLabel_3);

		JButton btnConfirm = new JButton("Update");
		//btnConfirm.setBackground(Color.GRAY); // Set background color to grey
		btnConfirm.setForeground(Color.WHITE); // Set text color to white
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirm.setBounds(389, 304, 135, 31);
		btnConfirm.setBackground(new Color(43, 43, 43));
		panel.add(btnConfirm);

		lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(466, 178, 157, 22);
		panel.add(lblNewLabel_4);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAddress.setBounds(636, 178, 206, 28);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Uname = (String) comboBox.getSelectedItem();
				getUserDetails(Uname);
			}
		});
		comboBox.setBounds(230, 87, 206, 28);
		panel.add(comboBox);

		JLabel lblNewUsername = new JLabel("New Username");
		lblNewUsername.setForeground(Color.WHITE);
		lblNewUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewUsername.setBounds(66, 133, 154, 26);
		panel.add(lblNewUsername);

		JLabel lblNewLabel_4_1 = new JLabel("Password");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(466, 220, 114, 32);
		panel.add(lblNewLabel_4_1);

		pwdPass = new JPasswordField();
		pwdPass.setBounds(636, 225, 206, 28);
		panel.add(pwdPass);

		lblPanelBG = new JLabel("");
		lblPanelBG.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\Green.jpg")); // Placeholder, update with your image path
		lblPanelBG.setBounds(-29, 0, 939, 360);
		panel.add(lblPanelBG);

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Username = (String) comboBox.getSelectedItem();
				String NewUser = txtNewUname.getText();
				String Name = txtName.getText();
				String Email = txtEmail.getText();
				String Phone= txtPhone.getText();
				String Address= txtAddress.getText();
				String Pass = new String(pwdPass.getPassword());
				String out = "-1";

				if (NewUser.isEmpty()) {
					lblMsg.setText("Please enter the username");
				} else if (Name.isEmpty()) {
					lblMsg.setText("Please enter the name");
				} else if (Email.isEmpty()) {
					lblMsg.setText("Please enter the email ID");
				} else if (Phone.isEmpty()) {
					lblMsg.setText("Please enter the phone number");
				} else if (Address.isEmpty()) {
					lblMsg.setText("Please enter the address");
				} else if (Pass.isEmpty()) {
					lblMsg.setText("Please enter the password");
				} else {
					out = objUpdate.UpdateUserDB(Username, NewUser, Name, Email, Phone, Address, Pass);
					populateComboBox();

					if (out.equals("1")) {
						lblMsg.setText("Your details have been successfully changed");
					} else if (out.equals("0")) {
						lblMsg.setText("Password incorrect.");
					}
				}
			}
		});

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.BLACK);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 22));
		lblMsg.setBounds(564, 154, 670, 41);
		contentPane.add(lblMsg);

		populateComboBox();

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\Grd BG.jpg")); // Placeholder, update with your image path
		lblNewLabel_5.setBounds(0, 0, 1924, 1048);
		contentPane.add(lblNewLabel_5);
	}

	public void populateComboBox() {
		Usernames = objUpdate.getUsernames();

		comboBox.removeAllItems();

		for (int i = 0; i < Usernames.length; i++) {
			comboBox.addItem(Usernames[i]);
		}
	}

	public void getUserDetails(String UName) {
		String[][] UserDetails = objUpdate.GetUserDetails(UName);

		if (UserDetails.length > 0) {
			txtName.setText(UserDetails[0][0]);
			txtEmail.setText(UserDetails[0][1]);
			txtPhone.setText(UserDetails[0][2]);
			txtAddress.setText(UserDetails[0][3]);
		}
	}
}
