package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import BusinessLayer.ItemBusiness;
import CommonInterface.CRUDInterface;

public class UpdateItemMaster extends JFrame implements CRUDInterface {
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	JLabel lblMsg;
	private JPanel contentPane;
	private JTextField txtSKU;
	private JTextField txtNewName;
	private JTextField txtDesc;
	private JComboBox<String> comboBox;

	ItemBusiness objItem = new ItemBusiness();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateItemMaster frame = new UpdateItemMaster();
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
	public UpdateItemMaster() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 0, 1545, 870);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200)); // Match background color
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		panel.setBackground(new Color(60, 63, 65)); // Match background color
		panel.setBounds(569, 215, 480, 379);

		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Item Name");
		lblNewLabel.setForeground(Color.WHITE); // Match font color
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel.setBounds(77, 103, 117, 33);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SKU Code");
		lblNewLabel_1.setForeground(Color.WHITE); // Match font color
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(77, 191, 93, 26);
		panel.add(lblNewLabel_1);

		txtSKU = new JTextField();
		txtSKU.setBounds(244, 192, 190, 26);
		panel.add(txtSKU);
		txtSKU.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New Item Name");
		lblNewLabel_2.setForeground(Color.WHITE); // Match font color
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(77, 151, 157, 26);
		panel.add(lblNewLabel_2);

		txtNewName = new JTextField();
		txtNewName.setBounds(244, 151, 190, 26);
		panel.add(txtNewName);
		txtNewName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setForeground(Color.WHITE); // Match font color
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_3.setBounds(77, 233, 106, 26);
		panel.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update();
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(43, 43, 43)); // Set background color to grey
		btnNewButton.setForeground(Color.WHITE); // Set foreground (text) color to white
		btnNewButton.setBounds(168, 311, 144, 33);
		panel.add(btnNewButton);

		txtDesc = new JTextField();
		txtDesc.setBounds(244, 233, 190, 26);
		panel.add(txtDesc);
		txtDesc.setColumns(10);

		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getItemDetails();
			}
		});
		comboBox.setBounds(244, 103, 187, 28);
		panel.add(comboBox);

		JLabel lblUpdateItemName = new JLabel("Update Item Name");
		lblUpdateItemName.setForeground(Color.WHITE); // Match font color
		lblUpdateItemName.setFont(new Font("Dubai", Font.BOLD, 24));
		lblUpdateItemName.setBounds(141, 21, 211, 33);
		panel.add(lblUpdateItemName);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMsg.setBounds(471, 124, 691, 44);
		contentPane.add(lblMsg);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\JBG3.jpg")); // Update with correct image path
		lblNewLabel_4.setBounds(0, 0, 1545, 875);
		contentPane.add(lblNewLabel_4);

		populateCombobox();
	}

	public void Insert() {
	}

	public void Delete() {
	}

	public void Read() {
	}

	public void populateCombobox() {
		String[] Items = objItem.populateCombobox_Update();
		comboBox.removeAllItems();
		for (String item : Items) {
			comboBox.addItem(item);
		}
	}

	public void getItemDetails() {
		String Item = (String) comboBox.getSelectedItem();
		String[][] ItemDetails = objItem.getItemDetails(Item);
		for (String[] detail : ItemDetails) {
			txtSKU.setText(detail[0]);
			txtDesc.setText(detail[1]);
		}
	}

	public void Update() {
		String Oldname = (String) comboBox.getSelectedItem();
		String Name = txtNewName.getText();
		String SKU = txtSKU.getText();
		String Desc = txtDesc.getText();

		if (Name.isEmpty()) {
			lblMsg.setText("Enter the item name");
		} else if (SKU.isEmpty()) {
			lblMsg.setText("Enter the SKU Code");
		} else {
			objItem.UpdateItem(Oldname, Name, SKU, Desc);
			lblMsg.setText("Item details were updated successfully");

			txtNewName.setText("");
			txtSKU.setText("");
			txtDesc.setText("");
		}
	}
}
