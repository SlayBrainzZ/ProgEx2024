package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import BusinessLayer.UserBusiness;

public class ForgotPassPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtNewPassword;
	UserBusiness objUser = new UserBusiness(); // Create an instance of UserBusiness

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 450)); // Adjust the size as needed

		JLabel lblMsg = new JLabel("");
		lblMsg.setBounds(50, 20, 300, 30);
		panel.add(lblMsg);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 20));

		JLabel lblForgotPass = new JLabel("Forgot Password");
		lblForgotPass.setFont(new Font("Dubai", Font.BOLD, 25));
		lblForgotPass.setBounds(130, 50, 200, 30);
		panel.add(lblForgotPass);

		JLabel lblEmail = new JLabel("Email-ID");
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 20));
		lblEmail.setBounds(50, 100, 100, 30);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setOpaque(false);
		txtEmail.setFont(new Font("Calibri", Font.BOLD, 20));
		txtEmail.setBorder(null);
		txtEmail.setBounds(50, 130, 300, 30);
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lblMsg.setText("");
			}
		});
		panel.add(txtEmail);

		JSeparator separatorEmail = new JSeparator();
		separatorEmail.setForeground(Color.BLACK);
		separatorEmail.setBounds(50, 160, 300, 10);
		panel.add(separatorEmail);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewPassword.setBounds(50, 190, 150, 30);
		panel.add(lblNewPassword);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setOpaque(false);
		txtNewPassword.setBorder(null);
		txtNewPassword.setBounds(50, 220, 300, 30);
		txtNewPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		panel.add(txtNewPassword);

		JSeparator separatorPwd = new JSeparator();
		separatorPwd.setForeground(Color.BLACK);
		separatorPwd.setBounds(50, 250, 300, 10);
		panel.add(separatorPwd);

		JButton btnContinue = new JButton("Continue");
		btnContinue.setForeground(Color.white);
		btnContinue.setBackground(new Color(43, 43, 43));
		btnContinue.setFont(new Font("Calibri", Font.BOLD, 20));
		btnContinue.setBounds(50, 300, 300, 40);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String newPassword = new String(txtNewPassword.getPassword());
				if (email.isEmpty() || newPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter both email and new password.");
				} else {
					String result = objUser.updateUserPassword(email, newPassword);
					if ("1".equals(result)) {
						JOptionPane.showMessageDialog(null, "Password updated successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update password. Please try again.");
					}
				}
			}
		});
		panel.add(btnContinue);

		// Center the panel within the contentPane
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(panel, gbc);
	}
}
