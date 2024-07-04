package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import BusinessLayer.UserBusiness;


public class CreateNewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtUname;
	private JPasswordField pwdPass;
	private JPasswordField pwdConfirmPass;
	private JTextField txtAddress;

	private JLabel lblMsg;
	String out = "-1";
	LoginPageNew objLogin = new LoginPageNew();
	UserBusiness objUser = new UserBusiness();


	String url = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;Database=InventoryManagement;user=Devansh;password=devansh;encrypt=true;trustServerCertificate=true;";;
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewUser frame = new CreateNewUser();
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
	public CreateNewUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 810);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMsg.setBounds(267, 62, 701, 60);
		contentPane.add(lblMsg);

		JLabel lblNewLabel = new JLabel("Create an account");
		lblNewLabel.setBounds(650, 132, 267, 37);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 34));

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(43, 43, 43)));
		panel.setBounds(267, 178, 1022, 400);
		panel.setBackground(new Color(43, 43, 43));
		contentPane.add(panel);
		panel.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(244, 75, 262, 28);
		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_1.setBounds(124, 75, 96, 33);
		panel.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(244, 125, 262, 28);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_2.setBounds(124, 127, 85, 32);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone ");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_3.setBounds(124, 172, 105, 33);
		panel.add(lblNewLabel_3);

		txtPhone = new JTextField();
		txtPhone.setBounds(244, 175, 262, 28);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_4.setBounds(548, 124, 111, 33);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Confirm Password");
		lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_5.setBounds(548, 172, 197, 32);
		panel.add(lblNewLabel_5);

		JButton btnCreateAcc = new JButton("Create Account");
		btnCreateAcc.setVerticalAlignment(SwingConstants.TOP);
		btnCreateAcc.setForeground(Color.WHITE);
		btnCreateAcc.setBackground(new Color(43, 43, 43));
		btnCreateAcc.setFont(new Font("Calibri", Font.BOLD, 24));
		btnCreateAcc.setBounds(292, 304, 214, 38);
		panel.add(btnCreateAcc);

		JLabel lblNewLabel_3_1 = new JLabel("Username");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_3_1.setBounds(548, 80, 145, 28);
		panel.add(lblNewLabel_3_1);

		txtUname = new JTextField();
		txtUname.setColumns(10);
		txtUname.setBounds(740, 75, 261, 28);
		panel.add(txtUname);

		pwdPass = new JPasswordField();
		pwdPass.setBounds(739, 122, 262, 28);
		panel.add(pwdPass);

		pwdConfirmPass = new JPasswordField();
		pwdConfirmPass.setBounds(740, 172, 261, 28);
		panel.add(pwdConfirmPass);

		JLabel lblNewLabel_3_2 = new JLabel("Address");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Calibri", Font.BOLD, 23));
		lblNewLabel_3_2.setBounds(124, 224, 96, 36);
		panel.add(lblNewLabel_3_2);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(244, 225, 262, 28);
		panel.add(txtAddress);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(43, 43, 43));
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 24));
		btnCancel.setBounds(591, 304, 214, 35);
		panel.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetTxtFieldValues();
				lblMsg.setText("");
			}
		});

		btnCreateAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});

		// Initialize database connection
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
			System.out.println("Connected to the database.");
		} catch(Exception ex) {
			System.out.println("Error connecting to the database: " + ex.getMessage());
			ex.printStackTrace();
			// Handle the exception appropriately
		}
	}

	public void ResetTxtFieldValues() {
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtPhone.setText("");
		txtUname.setText("");
		pwdPass.setText("");
		pwdConfirmPass.setText("");
	}

	public void Insert() {
		String Name = txtName.getText();
		String Email = txtEmail.getText();
		String PhNum = txtPhone.getText();
		String Address = txtAddress.getText();
		String Uname = txtUname.getText();
		String Pass = new String(pwdPass.getPassword());
		String ConfirmPass = new String(pwdConfirmPass.getPassword());
		String Out = "";
		boolean flag = false;

		lblMsg.setForeground(Color.BLACK);

		if (Name.length() == 0) {
			lblMsg.setText("Please enter your name");
			flag = true;
		} else if (Email.length() == 0) {
			lblMsg.setText("Please enter your email");
			flag = true;
		} else if (Email.indexOf('@') < 0 || Email.indexOf('.') < 0) {
			lblMsg.setText("Please enter valid email");
			flag = true;
		} else if (PhNum.length() > 10 || PhNum.length() < 10) {
			lblMsg.setText("Please enter valid 10 digit phone number");
			flag = true;
		} else if (Address.length() == 0) {
			lblMsg.setText("Please enter your address");
			flag = true;
		} else if (Uname.length() == 0) {
			lblMsg.setText("Please enter your username");
			flag = true;
		} else if (Pass.length() == 0) {
			lblMsg.setText("Please enter your password");
			flag = true;
		} else if (ConfirmPass.length() == 0) {
			lblMsg.setText("Please enter your confirm password");
			flag = true;
		} else if (!ConfirmPass.equals(Pass)) {
			lblMsg.setText("Passwords do not match");
			flag = true;
		}
		else {

			try{
				out = objUser.insertUser(Uname, Pass, Email, PhNum, Address, Name);
				JOptionPane.showMessageDialog(null,"User inserted successfully");
			}

			catch (Exception e) {
				lblMsg.setText(e.getMessage());
			}
		}


	}
}
