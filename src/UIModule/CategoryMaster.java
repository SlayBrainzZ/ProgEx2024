package UIModule;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;

import BusinessLayer.CategoryBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

public class CategoryMaster extends JFrame implements CRUDInterface {
	String ConnectionURL = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	ExceptionMaster objEx = new ExceptionMaster();
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
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 20));
		lblMsg.setBounds(538, 206, 512, 36);
		contentPane.add(lblMsg);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		panel.setBounds(538, 266, 391, 237);
		contentPane.add(panel);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblNewLabel = new JLabel("Create category");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(lblNewLabel, gbc);

		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblNewLabel_1, gbc);

		txtCtg = new JTextField();
		txtCtg.setBackground(new Color(69, 73, 74));
		txtCtg.setForeground(Color.WHITE);
		txtCtg.setFont(new Font("Calibri", Font.PLAIN, 20));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtCtg, gbc);
		txtCtg.setColumns(10);

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
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(btnNewButton, gbc);

		/* JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CategoryMaster.class.getResource("/BackgroundImage/BG 2.jpg")));
		lblNewLabel_3.setBounds(0, 0, 391, 237);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CategoryMaster.class.getResource("/BackgroundImage/aqua BG.png")));
		lblNewLabel_2.setBounds(0, 0, 1845, 800);
		contentPane.add(lblNewLabel_2); */
	}

	public void Update() {
	}

	public void Delete() {
	}

	public void Read() {
	}

	public void Insert() {
		String Ctg = txtCtg.getText();

		if (Ctg.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Category cannot be blank");
		} else {
			objCat.InsertInCatMaster(Ctg);
			JOptionPane.showMessageDialog(null, "Category has been entered successfully");
			txtCtg.setText("");
		}
	}
}
