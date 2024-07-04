package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BusinessLayer.CustomerBusiness;

public class CustomerMaster extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtAddr;
    private JTextField txtPhone;
    private JTextField txtCity;
    private JLabel lblMsg;

    private CustomerBusiness objCus = new CustomerBusiness();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerMaster frame = new CustomerMaster();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CustomerMaster() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(-5, 60, 1545, 810);
        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblMsg = new JLabel("");
        lblMsg.setForeground(Color.RED);
        lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblMsg.setBounds(267, 62, 701, 60);
        contentPane.add(lblMsg);

        JLabel lblNewLabel = new JLabel("Create Customer");
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

        JLabel lblNewLabel_3 = new JLabel("Phone");
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 23));
        lblNewLabel_3.setBounds(124, 172, 105, 33);
        panel.add(lblNewLabel_3);

        txtPhone = new JTextField();
        txtPhone.setBounds(244, 175, 262, 28);
        panel.add(txtPhone);
        txtPhone.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Address");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 23));
        lblNewLabel_4.setBounds(548, 75, 111, 33);
        panel.add(lblNewLabel_4);

        txtAddr = new JTextField();
        txtAddr.setBounds(670, 75, 262, 28);
        panel.add(txtAddr);
        txtAddr.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("City");
        lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 23));
        lblNewLabel_5.setBounds(548, 127, 197, 32);
        panel.add(lblNewLabel_5);

        txtCity = new JTextField();
        txtCity.setBounds(670, 125, 262, 28);
        panel.add(txtCity);
        txtCity.setColumns(10);

        JButton btnCreateCus = new JButton("Create Customer");
        btnCreateCus.setVerticalAlignment(SwingConstants.TOP);
        btnCreateCus.setForeground(Color.WHITE);
        btnCreateCus.setBackground(new Color(43, 43, 43));
        btnCreateCus.setFont(new Font("Calibri", Font.BOLD, 24));
        btnCreateCus.setBounds(292, 340, 214, 38);
        panel.add(btnCreateCus);

        btnCreateCus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Insert();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setVerticalAlignment(SwingConstants.TOP);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(43, 43, 43));
        btnCancel.setFont(new Font("Calibri", Font.BOLD, 24));
        btnCancel.setBounds(591, 340, 214, 35);
        panel.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResetTxtFieldValues();
                lblMsg.setText("");
            }
        });
    }

    public void ResetTxtFieldValues() {
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddr.setText("");
        txtCity.setText("");
    }

    public void Insert() {
        try {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String addr = txtAddr.getText();
            String phone = txtPhone.getText();
            String city = txtCity.getText();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.contains("@") || !email.endsWith(".com")) {
                JOptionPane.showMessageDialog(this, "Email invalid. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (addr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phone cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (phone.length() != 10) {
                JOptionPane.showMessageDialog(this, "Phone number invalid. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(this, "City cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String result = objCus.insertCusDetails(name, email, addr, phone, city);

            if ("1".equals(result)) {
                lblMsg.setText("Customer created successfully!");
            } else {
                lblMsg.setText("Error creating customer. Please try again.");
                System.out.println();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
