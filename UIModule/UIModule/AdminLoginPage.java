package UIModule;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import BusinessLayer.UserBusiness;


public class AdminLoginPage extends JFrame {

    private JPanel contentPane;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    JLabel lblPwd;
    JLabel lblMsg;
    static AdminLoginPage frame;
    UserBusiness objUser = new UserBusiness();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AdminLoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminLoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Adjusted for better centering
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(400, 450)); // Adjust the size as needed

        lblMsg = new JLabel("");
        lblMsg.setBounds(50, 20, 300, 30);
        panel.add(lblMsg);
        lblMsg.setForeground(Color.RED);
        lblMsg.setFont(new Font("Calibri", Font.BOLD, 20));

        JLabel lblSignIn = new JLabel("Admin Sign In");
        lblSignIn.setFont(new Font("Dubai", Font.BOLD, 25));
        lblSignIn.setBounds(130, 50, 200, 30);
        panel.add(lblSignIn);

        JLabel lblEmailLabel = new JLabel("Email ID");
        lblEmailLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        lblEmailLabel.setBounds(50, 100, 100, 30);
        panel.add(lblEmailLabel);

        txtEmail = new JTextField();
        txtEmail.setText("");
        txtEmail.setOpaque(false);
        txtEmail.setFont(new Font("Calibri", Font.BOLD, 20));
        txtEmail.setBorder(null);
        txtEmail.setBounds(50, 130, 300, 30);
        panel.add(txtEmail);

        JSeparator separatorEmail = new JSeparator();
        separatorEmail.setForeground(Color.BLACK);
        separatorEmail.setBounds(50, 160, 300, 10);
        panel.add(separatorEmail);

        lblPwd = new JLabel("Password");
        lblPwd.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPwd.setBounds(50, 190, 100, 30);
        panel.add(lblPwd);

        pwdPassword = new JPasswordField();
        pwdPassword.setOpaque(false);
        pwdPassword.setBorder(null);
        pwdPassword.setBounds(50, 220, 300, 30);
        pwdPassword.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(pwdPassword);

        JSeparator separatorPwd = new JSeparator();
        separatorPwd.setForeground(Color.BLACK);
        separatorPwd.setBounds(50, 250, 300, 10);
        panel.add(separatorPwd);

        JButton btnLogin = new JButton("Admin Sign in");
        btnLogin.setForeground(Color.white);
        btnLogin.setBackground(new Color(43, 43, 43));
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
        btnLogin.setBounds(50, 300, 300, 40);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(pwdPassword.getPassword());

                // Validate the email and password using validateUser method
                String validationResult = objUser.validateAdmin(email, password);

                if (validationResult.equals("1")) {
                    JOptionPane.showMessageDialog(frame, "Admin login successful!");
                    AdminWindow adminWindow = new AdminWindow();
                    adminWindow.setVisible(true);
                    frame.dispose(); // Close the admin login window
                } else {
                    lblMsg.setText("Invalid email or password.");
                    lblMsg.setForeground(Color.BLACK);
                }
            }
        });
        panel.add(btnLogin);

        // Center the panel within the contentPane
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(panel, gbc);
    }


}
