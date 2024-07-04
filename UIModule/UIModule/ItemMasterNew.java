package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BusinessLayer.ProductBusiness;
import BusinessLayer.CategoryBusiness;

public class ItemMasterNew extends JFrame  {

	private JPanel contentPane;
	private JTextField txtItem;
	private JTextField txtPrice;
	private JTextField txtDesc;
	private JComboBox<String> comboBox;
	private JLabel lblMsg;

	private ProductBusiness objProd = new ProductBusiness();
	private CategoryBusiness objCat = new CategoryBusiness();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemMasterNew frame = new ItemMasterNew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ItemMasterNew() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200)); // Updated background color
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout()); // Use BorderLayout for main content pane

		JPanel mainPanel = new JPanel(new GridBagLayout()); // Panel for centering components
		mainPanel.setBackground(Color.white); // Match main content pane background
		contentPane.add(mainPanel, BorderLayout.CENTER);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 22));
		GridBagConstraints gbcMsg = new GridBagConstraints();
		gbcMsg.gridx = 0;
		gbcMsg.gridy = 0;
		gbcMsg.gridwidth = 2;
		gbcMsg.insets = new Insets(0, 0, 20, 0); // Bottom margin
		mainPanel.add(lblMsg, gbcMsg);

		JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for internal panel
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65)); // Updated panel background color
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.insets = new Insets(20, 20, 20, 20); // Padding around panel
		mainPanel.add(panel, gbcPanel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblCreateItem = new JLabel("Create Item");
		lblCreateItem.setFont(new Font("Calibri", Font.BOLD, 26));
		lblCreateItem.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(lblCreateItem, gbc);

		JLabel lblItemName = new JLabel("Product");
		lblItemName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemName.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblItemName, gbc);

		txtItem = new JTextField();
		txtItem.setColumns(20); // Increased width to match DeleteItem
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(txtItem, gbc);

		JLabel lblSKUCode = new JLabel("Price");
		lblSKUCode.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSKUCode.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblSKUCode, gbc);

		txtPrice = new JTextField();
		txtPrice.setColumns(20); // Increased width to match DeleteItem
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(txtPrice, gbc);


		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCategory.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblCategory, gbc);

		comboBox = new JComboBox<>();
		comboBox.setPreferredSize(new Dimension(150, 28));
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboBox, gbc);

		JButton btnCreate = new JButton("Create Item");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});
		btnCreate.setFont(new Font("Calibri", Font.BOLD, 20));
		btnCreate.setBackground(new Color(43, 43, 43));
		btnCreate.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(btnCreate, gbc);


		populateComboBox();
	}

	public void populateComboBox(){
		/* java.util.List<java.util.List<Object>> categories = objCat.readCategories();
		for (List<Object> category : categories) {
			comboBox.addItem((String) category.get(1));
		} */

	}

	public void Insert(){
		String prod = txtItem.getText();
		String priceTxt = txtPrice.getText();
		String ctg = comboBox.getSelectedItem().toString();
		Double price = Double.parseDouble(priceTxt);
		String out = objProd.insertProducts(prod,price,ctg);

		if(out.equals("1")){
			JOptionPane.showMessageDialog(null, "Product added successfully");
		}
		else if(out.equals("-1")){
			JOptionPane.showMessageDialog(null, "Product already exists");
		}
		else{
			JOptionPane.showMessageDialog(null, "Something went wrong. Please try again");
		}

	}

}



