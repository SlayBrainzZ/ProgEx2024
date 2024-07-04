package UIModule;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import BusinessLayer.CategoryBusiness;



public class CategoryMaster extends JFrame {

	CategoryBusiness objCat = new CategoryBusiness();

	private JPanel contentPane;
	private JTextField txtCtg;
	JLabel lblMsg;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryMaster frame = new CategoryMaster();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryMaster() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400); // Size 600x400 pixels
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout()); // GridBagLayout for centering
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 300)); // Size 400x300 pixels

		lblMsg = new JLabel("");
		lblMsg.setBounds(50, 20, 300, 30);
		panel.add(lblMsg);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Create Category");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel.setBounds(120, 50, 200, 30);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(50, 100, 100, 30);
		panel.add(lblNewLabel_1);

		txtCtg = new JTextField();
		txtCtg.setText("");
		txtCtg.setOpaque(false);
		txtCtg.setFont(new Font("Calibri", Font.BOLD, 20));
		txtCtg.setBorder(null);
		txtCtg.setBounds(50, 130, 300, 30);
		panel.add(txtCtg);

		JSeparator separatorEmail = new JSeparator();
		separatorEmail.setForeground(Color.BLACK);
		separatorEmail.setBounds(50, 160, 300, 10);
		panel.add(separatorEmail);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(43, 43, 43));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(50, 200, 300, 40); // Adjusted position
		panel.add(btnNewButton);

		// Center the panel within the contentPane
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(panel, gbc);

		setMinimumSize(new Dimension(1300, 700));
	}

	public void Insert() {
		String Ctg = txtCtg.getText();
		String out = objCat.insertCategories(Ctg);

		if (Ctg.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Category cannot be blank");
		} else if(out.equals("-1")) {
			JOptionPane.showMessageDialog(null, "The category already exists");
		} else {
			JOptionPane.showMessageDialog(null, "Category has been entered successfully");
			txtCtg.setText("");
		}
	}
}