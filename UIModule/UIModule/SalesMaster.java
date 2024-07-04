package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import BusinessLayer.OrderDetailsBusiness;
import BusinessLayer.CustomerBusiness;
import BusinessLayer.ProductBusiness;



public class SalesMaster extends JFrame {

	private JPanel contentPane;
	private JTextField txtQtty;
	private JTextField txtRate;
	private JTextField txtStatus;
	private JTextField txtDate;
	JLabel lblMsg;
	OrderDetailsBusiness objOrd = new OrderDetailsBusiness();
	CustomerBusiness objCus = new CustomerBusiness();
	ProductBusiness objPro = new ProductBusiness();



	JComboBox<String> cmbBoxItemName;
	JComboBox<String> cmbBoxCusName;
	List<List<Object>> productList;

	String[] Items = new String[]{};
	String[] CusNames = new String[]{};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMaster frame = new SalesMaster();
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
	public SalesMaster() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700); // Adjust frame size
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Increased top inset
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.insets = new Insets(0, 0, 10, 0); // Increase bottom inset of lblMsg for spacing
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 0;
		gbc_lblMsg.anchor = GridBagConstraints.CENTER;
		contentPane.add(lblMsg, gbc_lblMsg);


		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(30, 30, 30, 30); // Increased insets for spacing around the panel
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		gbc_panel.fill = GridBagConstraints.BOTH;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridBagLayout());

		JLabel lblSales = new JLabel("Sales");
		lblSales.setForeground(Color.WHITE);
		lblSales.setFont(new Font("Calibri", Font.BOLD, 26));
		GridBagConstraints gbc_lblSales = new GridBagConstraints();
		gbc_lblSales.insets = new Insets(0, 0, 20, 0);
		gbc_lblSales.gridx = 0;
		gbc_lblSales.gridy = 0;
		gbc_lblSales.insets = new Insets(20, 0, 20, 0); // Adjust top and bottom insets for spacing
		gbc_lblSales.anchor = GridBagConstraints.EAST;
		panel.add(lblSales, gbc_lblSales);

		JLabel lblCusName = new JLabel("Customer Name");
		lblCusName.setForeground(Color.WHITE);
		lblCusName.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblCusName = new GridBagConstraints();
		gbc_lblCusName.anchor = GridBagConstraints.EAST;
		gbc_lblCusName.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblCusName.gridx = 0;
		gbc_lblCusName.gridy = 1;
		panel.add(lblCusName, gbc_lblCusName);

		cmbBoxCusName = new JComboBox<>();
		GridBagConstraints gbc_cmbBoxCusName = new GridBagConstraints();
		gbc_cmbBoxCusName.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_cmbBoxCusName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBoxCusName.gridx = 1;
		gbc_cmbBoxCusName.gridy = 1;
		gbc_cmbBoxCusName.anchor = GridBagConstraints.WEST;
		gbc_cmbBoxCusName.weightx = 1.0;
		panel.add(cmbBoxCusName, gbc_cmbBoxCusName);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.anchor = GridBagConstraints.EAST;
		gbc_lblItemName.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 2;
		panel.add(lblItemName, gbc_lblItemName);

		cmbBoxItemName = new JComboBox<>();
		cmbBoxItemName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillRateField();
			}
		});
		GridBagConstraints gbc_cmbBoxItemName = new GridBagConstraints();
		gbc_cmbBoxItemName.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_cmbBoxItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBoxItemName.gridx = 1;
		gbc_cmbBoxItemName.gridy = 2;
		gbc_cmbBoxItemName.anchor = GridBagConstraints.WEST;
		gbc_cmbBoxItemName.weightx = 1.0;
		panel.add(cmbBoxItemName, gbc_cmbBoxItemName);

		JLabel lblQtty = new JLabel("Quantity");
		lblQtty.setForeground(Color.WHITE);
		lblQtty.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblQtty = new GridBagConstraints();
		gbc_lblQtty.anchor = GridBagConstraints.EAST;
		gbc_lblQtty.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblQtty.gridx = 0;
		gbc_lblQtty.gridy = 3;
		panel.add(lblQtty, gbc_lblQtty);

		txtQtty = new JTextField();
		GridBagConstraints gbc_txtQtty = new GridBagConstraints();
		gbc_txtQtty.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtQtty.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtty.gridx = 1;
		gbc_txtQtty.gridy = 3;
		gbc_txtQtty.anchor = GridBagConstraints.WEST;
		gbc_txtQtty.weightx = 1.0;
		panel.add(txtQtty, gbc_txtQtty);
		txtQtty.setColumns(10);

		JLabel lblRate = new JLabel("Rate");
		lblRate.setForeground(Color.WHITE);
		lblRate.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblRate = new GridBagConstraints();
		gbc_lblRate.anchor = GridBagConstraints.EAST;
		gbc_lblRate.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblRate.gridx = 0;
		gbc_lblRate.gridy = 4;
		panel.add(lblRate, gbc_lblRate);

		txtRate = new JTextField();
		txtRate.setEditable(false);
		GridBagConstraints gbc_txtRate = new GridBagConstraints();
		gbc_txtRate.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRate.gridx = 1;
		gbc_txtRate.gridy = 4;
		gbc_txtRate.anchor = GridBagConstraints.WEST;
		gbc_txtRate.weightx = 1.0;
		panel.add(txtRate, gbc_txtRate);
		txtRate.setColumns(10);

		JLabel lblAmt = new JLabel("Status");
		lblAmt.setForeground(Color.WHITE);
		lblAmt.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblAmt = new GridBagConstraints();
		gbc_lblAmt.anchor = GridBagConstraints.EAST;
		gbc_lblAmt.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblAmt.gridx = 0;
		gbc_lblAmt.gridy = 5;
		panel.add(lblAmt, gbc_lblAmt);

		txtStatus = new JTextField();
		txtStatus.setEditable(true);
		GridBagConstraints gbc_txtAmt = new GridBagConstraints();
		gbc_txtAmt.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtAmt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmt.gridx = 1;
		gbc_txtAmt.gridy = 5;
		gbc_txtAmt.anchor = GridBagConstraints.WEST;
		gbc_txtAmt.weightx = 1.0;
		panel.add(txtStatus, gbc_txtAmt);
		txtStatus.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 6;
		panel.add(lblDate, gbc_lblDate);

		txtDate = new JTextField();
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDate.gridx = 1;
		gbc_txtDate.gridy = 6;
		gbc_txtDate.anchor = GridBagConstraints.WEST;
		gbc_txtDate.weightx = 1.0;
		panel.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(new Color(75, 75, 75)); // Dark grey button color
		btnSave.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 7;
		gbc_btnSave.anchor = GridBagConstraints.CENTER;
		gbc_btnSave.gridwidth = 2; // Span across two columns
		panel.add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(75, 75, 75)); // Dark grey button color
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 8;
		gbc_btnCancel.anchor = GridBagConstraints.CENTER;
		gbc_btnCancel.gridwidth = 2; // Span across two columns
		panel.add(btnCancel, gbc_btnCancel);

		populateCusComboBox(); // Populate customer names in combo box
		populateItemComboBox(); // Populate item names in combo box
	}

	public void populateCusComboBox() {
		/* List<List<Object>> customers = objCus.readCusDetails();
		for (List<Object> customer : customers) {
			cmbBoxCusName.addItem((String) customer.get(0)); // Add customer names to combo box
		} */
	}

	public void populateItemComboBox() {
		/* productList = objPro.readProd();
		for (List<Object> product : productList) {
			cmbBoxItemName.addItem((String) product.get(0)); // Add product names to combo box
		}*/
	}

	private void fillRateField() {
		/* String selectedItem = (String) cmbBoxItemName.getSelectedItem();
		for (List<Object> product : productList) {
			if (product.get(0).equals(selectedItem)) {
				txtRate.setText(String.valueOf(product.get(1)));
				break;
			}
		} */
	}


	public void Insert() {
		try {

			String customerName = (String) cmbBoxCusName.getSelectedItem();
			String itemName = (String) cmbBoxItemName.getSelectedItem();
			int quantity = Integer.parseInt(txtQtty.getText());
			String date = txtDate.getText();
			String status = txtStatus.getText();


			String out = objOrd.insertOrderDetails(itemName, quantity, customerName, date, status);

			if (out.equals("1")) {
				JOptionPane.showMessageDialog(null, "Inserted Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Insertion Failed");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occured. Please try again later");
			e.printStackTrace();
		}
	}


}
