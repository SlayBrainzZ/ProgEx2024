package UIModule;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ExceptionHandling.ExceptionMaster;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class LoginPageNew extends JFrame {
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	JLabel lblPwd;
	JLabel lblMsg;
	static LoginPageNew frame;

	ExceptionMaster obj = new ExceptionMaster();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public LoginPageNew() {
		//setUndecorated(true);

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);


		}
		catch(Exception ex){
			obj.InsertException(ex.getMessage(), "LoginPageNew", "Constructor");
			//JOptionPane.showMessageDialog(null, ex.getMessage());
			// lblMsg.setText("Ex in constructor: "+ ex.getMessage());
		}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1552, 870);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(100, 100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setBackground(new Color(200, 200, 200));
		lblMsg.setBounds(473, 96, 661, 42);
		contentPane.add(lblMsg);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 23));

		JLabel lblSignIn = new JLabel("");
		lblSignIn.setFont(new Font("Dubai", Font.BOLD, 25));
		lblSignIn.setBounds(728, 41, 130, 26);
		contentPane.add(lblSignIn);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(802, 148, 465, 573);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

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
		separator.setBounds(95, 158, 283, 16);
		panel_1.add(separator);

		/*JLabel lblPwdIcon = new JLabel("");
		lblPwdIcon.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\icons8-password-24.png"));
		lblPwdIcon.setBounds(55, 224, 30, 28);
		panel_1.add(lblPwdIcon);*/

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
		btnSignUp.setBounds(256, 309, 122, 28);
		panel_1.add(btnSignUp);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(95, 251, 283, 16);
		panel_1.add(separator_1);

		/* JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\icons8-user-male-30.png"));
		lblUserIcon.setBounds(55, 131, 30, 28);
		panel_1.add(lblUserIcon); */

		JButton btnForget = new JButton("Forget password?");
		btnForget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ForgotPassPage objForgotPass= new ForgotPassPage();
				objForgotPass.setVisible(true);
			}
		});
		btnForget.setBackground(new Color(139, 0, 0));
		btnForget.setOpaque(false);
		btnForget.setBorder(null);
		btnForget.setVerticalAlignment(SwingConstants.TOP);
		btnForget.setFont(new Font("Calibri", Font.BOLD, 20));
		btnForget.setBounds(139, 388, 204, 28);
		panel_1.add(btnForget);

		lblPwd = new JLabel("Password");
		lblPwd.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPwd.setBounds(95, 224, 179, 28);
		panel_1.add(lblPwd);

		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(txtUsername.getText().equals("Username")) {
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
		txtUsername.setBackground(new Color(0,206,209));
		txtUsername.setOpaque(false);
		txtUsername.setBounds(95, 131, 179, 28);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		JButton btnLogin = new JButton("Sign in");
		btnLogin.setForeground(Color.white);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ValUname= txtUsername.getText();
				String ValPass = pwdPassword.getText();
				String out= GetLoginDetailsDB(ValUname, ValPass);
				lblMsg.setText(out);
				MenuMaster objMenu = new MenuMaster();

				if(! (ValUname.length() == 0 || ValPass.length()== 0)) {
					if(GetLoginDetailsDB(ValUname, ValPass).equals("1"))  {

						objMenu.setVisible(true);
						frame.hide();

					}
					else {
						lblMsg.setText("The username or password is invalid. Please try again");

				}

			}
				else {
					lblMsg.setText("Please enter your username and password");
				}


			}
		});
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		btnLogin.setBackground(new Color(43, 43, 43));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
		btnLogin.setBounds(95, 309, 122, 28);
		panel_1.add(btnLogin);

		/* JLabel lblRightImage = new JLabel("");
		lblRightImage.setIcon(new ImageIcon("C:\\Users\\devan\\Pictures\\JBG.jpg"));
		lblRightImage.setBounds(0, 0, 465, 573);
		panel_1.add(lblRightImage); */

		JLabel lblLeftImage = new JLabel("");
		lblLeftImage.setBounds(340, 148, 465, 573);
		contentPane.add(lblLeftImage);
		lblLeftImage.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\JBG6.png"));
		panel.setBackground(new Color(200, 200, 200));
		panel.setBounds(340, 148, 465, 573);
		contentPane.add(panel);
		panel.setLayout(null);

		/* JLabel LBLbg = new JLabel("");
		LBLbg.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\JBG3.jpg"));
		LBLbg.setBounds(0, 0, 1552, 880);
		contentPane.add(LBLbg); */
	}

	public String GetLoginDetailsDB(String User, String Pass) {
		String out="0";
		try {

			CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_ValidateUser(?,?) }");
			stmt.setString(1, User);
			stmt.setString(2, Pass);


			ResultSet rs = stmt.executeQuery();
			rs.next();
			out = rs.getString("Out");
			stmt.close();


		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "LoginPageNew", "GetLoginDetailsDB");
			lblMsg.setText(ex.getMessage());


		}

		return out;

	}
}

