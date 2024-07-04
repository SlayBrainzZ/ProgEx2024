package UIModule;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import BusinessLayer.UserBusiness;


public class LoginPageNew extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	JLabel lblPwd;
	JLabel lblMsg;
	static LoginPageNew frame;
	UserBusiness objUser = new UserBusiness();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginPageNew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPageNew() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1552, 870);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		lblMsg = new JLabel("");
		lblMsg.setBackground(new Color(200, 200, 200));
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 23));
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.insets = new Insets(0, 0, 20, 0);
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 0;
		gbc_lblMsg.anchor = GridBagConstraints.CENTER;
		contentPane.add(lblMsg, gbc_lblMsg);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(400, 500)); // Set preferred size for visibility
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 20, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		gbc_panel_1.anchor = GridBagConstraints.CENTER;
		contentPane.add(panel_1, gbc_panel_1);

		pwdPassword = new JPasswordField();
		pwdPassword.setOpaque(false);
		pwdPassword.setBorder(null);
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblPwd.setText("");
			}
		});
		pwdPassword.setBounds(95, 220, 167, 23);
		panel_1.add(pwdPassword);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(55, 158, 283, 16);
		panel_1.add(separator);

		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setForeground(Color.white);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewUser objCreateUser = new CreateNewUser();
				objCreateUser.setVisible(true);
			}
		});
		btnSignUp.setVerticalAlignment(SwingConstants.TOP);
		btnSignUp.setOpaque(true);
		btnSignUp.setFont(new Font("Calibri", Font.BOLD, 20));
		btnSignUp.setBackground(new Color(43, 43, 43));
		btnSignUp.setBounds(200, 309, 122, 28);
		panel_1.add(btnSignUp);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(55, 251, 283, 16);
		panel_1.add(separator_1);

		JButton btnForget = new JButton("Forget password?");
		btnForget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Please contact the admin in case you have forgotten your password");
			}
		});
		btnForget.setForeground(Color.white);
		btnForget.setOpaque(true);
		btnForget.setFont(new Font("Calibri", Font.BOLD, 20));
		btnForget.setBackground(new Color(43, 43, 43));
		btnForget.setBounds(60, 388, 283, 28);
		panel_1.add(btnForget);

		lblPwd = new JLabel("Password");
		lblPwd.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPwd.setBounds(95, 224, 179, 28);
		panel_1.add(lblPwd);

		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
				}
			}
		});
		txtUsername.setOpaque(false);
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText("");
			}
		});

		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Calibri", Font.BOLD, 20));
		txtUsername.setText("Username");
		//txtUsername.setBackground(new Color(0, 206, 209));
		txtUsername.setOpaque(false);
		txtUsername.setBounds(95, 131, 179, 28);
		panel_1.add(txtUsername);
		txtUsername.setColumns(20);

		JButton btnLogin = new JButton("Sign in");
		btnLogin.setForeground(Color.white);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ValUname = txtUsername.getText();
				String ValPass = new String(pwdPassword.getPassword());

				String validationResult = objUser.validateUser(ValUname, ValPass);

				if (validationResult.equals("1")) {
					JOptionPane.showMessageDialog(frame, "Login successful!");
					// Navigate to the next screen
					MenuMaster objMenu = new MenuMaster();
					objMenu.setVisible(true);
					frame.dispose(); // Close the login window
				} else {
					lblMsg.setText("Invalid username or password.");
					lblMsg.setForeground(Color.black);
				}
			}
		});
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		btnLogin.setBackground(new Color(43, 43, 43));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
		btnLogin.setBounds(85, 309, 122, 28);
		panel_1.add(btnLogin);

		// Add Admin Login Button
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setForeground(Color.white);
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLoginPage adminLoginPage = new AdminLoginPage();
				adminLoginPage.setVisible(true);
			}
		});
		btnAdminLogin.setVerticalAlignment(SwingConstants.TOP);
		btnAdminLogin.setBackground(new Color(43, 43, 43));
		btnAdminLogin.setFont(new Font("Calibri", Font.BOLD, 20));
		btnAdminLogin.setBounds(60, 448, 283, 28);
		panel_1.add(btnAdminLogin);
	}

}

