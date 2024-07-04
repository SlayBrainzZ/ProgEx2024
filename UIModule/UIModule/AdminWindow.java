package UIModule;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BusinessLayer.UserBusiness;

public class AdminWindow extends JFrame {

    private JPanel contentPane;
    private UserBusiness objUser = new UserBusiness();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminWindow frame = new AdminWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminWindow() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.insets = new Insets(10, 10, 10, 10);
        gbcContent.gridx = 0;
        gbcContent.gridy = 0;
        gbcContent.anchor = GridBagConstraints.CENTER;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(10, 10, 10, 10);

        JLabel lblAdmin = new JLabel("Admin Dashboard");
        lblAdmin.setFont(new Font("Calibri", Font.BOLD, 26));
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        gbcPanel.gridwidth = 2;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        panel.add(lblAdmin, gbcPanel);

        JButton btnUpdateUserDetails = new JButton("Update User Details");
        btnUpdateUserDetails.setFont(new Font("Calibri", Font.BOLD, 20));
        btnUpdateUserDetails.setBackground(new Color(43, 43, 43));
        btnUpdateUserDetails.setForeground(Color.WHITE);
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;
        gbcPanel.gridwidth = 2;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        btnUpdateUserDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateUser updateUser = new UpdateUser();
                updateUser.setVisible(true);
            }
        });
        panel.add(btnUpdateUserDetails, gbcPanel);

        JButton btnForgotPassword = new JButton("Update Password");
        btnForgotPassword.setFont(new Font("Calibri", Font.BOLD, 20));
        btnForgotPassword.setBackground(new Color(43, 43, 43));
        btnForgotPassword.setForeground(Color.WHITE);
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 2;
        gbcPanel.gridwidth = 2;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        btnForgotPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ForgotPassPage forgotPassPage = new ForgotPassPage();
                forgotPassPage.setVisible(true);
            }
        });
        panel.add(btnForgotPassword, gbcPanel);

        JButton btnUserList = new JButton("User List");
        btnUserList.setFont(new Font("Calibri", Font.BOLD, 20));
        btnUserList.setBackground(new Color(43, 43, 43));
        btnUserList.setForeground(Color.WHITE);
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 3;
        gbcPanel.gridwidth = 2;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        btnUserList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserList userList = new UserList();
                userList.setVisible(true);
            }
        });
        panel.add(btnUserList, gbcPanel);

        JButton btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setFont(new Font("Calibri", Font.BOLD, 20));
        btnDeleteUser.setBackground(new Color(43, 43, 43));
        btnDeleteUser.setForeground(Color.WHITE);
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 4;
        gbcPanel.gridwidth = 2;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteUser deleteUser = new DeleteUser();
                deleteUser.setVisible(true);
            }
        });
        panel.add(btnDeleteUser, gbcPanel);

        contentPane.add(panel, gbcContent);
    }
}
