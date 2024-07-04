package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BusinessLayer.ProductBusiness;
import BusinessLayer.CategoryBusiness;


public class UpdateItemMaster extends JFrame {

	private JLabel lblMsg;
	private JPanel contentPane;

	private JTextField txtNewName;
	private JTextField txtPrice;
	private JComboBox<String> comboBoxProduct;
	private JComboBox<String> comboBoxCategory;

	private ProductBusiness objProd = new ProductBusiness();
	private CategoryBusiness objCat = new CategoryBusiness();

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 0, 1545, 870);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white); // Match background color
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

		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setForeground(Color.WHITE); // Match font color
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(77, 191, 93, 26);
		panel.add(lblNewLabel_1);

		comboBoxCategory = new JComboBox<>();
		comboBoxCategory.setBounds(244, 192, 190, 26);
		panel.add(comboBoxCategory);

		JLabel lblNewLabel_2 = new JLabel("New Item Name");
		lblNewLabel_2.setForeground(Color.WHITE); // Match font color
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(77, 151, 157, 26);
		panel.add(lblNewLabel_2);

		txtNewName = new JTextField();
		txtNewName.setBounds(244, 151, 190, 26);
		panel.add(txtNewName);
		txtNewName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Price");
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

		txtPrice = new JTextField();
		txtPrice.setBounds(244, 233, 190, 26);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		comboBoxProduct = new JComboBox<>();
		comboBoxProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		comboBoxProduct.setBounds(244, 103, 187, 28);
		panel.add(comboBoxProduct);

		JLabel lblUpdateItemName = new JLabel("Update Item Name");
		lblUpdateItemName.setForeground(Color.WHITE); // Match font color
		lblUpdateItemName.setFont(new Font("Dubai", Font.BOLD, 24));
		lblUpdateItemName.setBounds(141, 21, 211, 33);
		panel.add(lblUpdateItemName);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMsg.setBounds(471, 124, 691, 44);
		contentPane.add(lblMsg);

		populateProductComboBox();
		populateCategoryComboBox();
	}


	public void populateProductComboBox() {
		/*List<List<Object>> products = objProd.readProd();
		comboBoxProduct.removeAllItems(); // Clear comboBox before populating

		for (List<Object> product : products) {
			String productName = (String) product.get(0);
			comboBoxProduct.addItem(productName);
		}*/
	}

	public void populateCategoryComboBox() {
		/* List<List<Object>> categories = objCat.readCategories();
		comboBoxCategory.removeAllItems(); // Clear comboBox before populating

		for (List<Object> category : categories) {
			String categoryName = (String) category.get(1);
			comboBoxCategory.addItem(categoryName);
		} */
	}

	public void Update() {

		String prod = comboBoxProduct.getSelectedItem().toString();
		String newProd = txtNewName.getText();
		String category = comboBoxCategory.getSelectedItem().toString();
		String priceText = txtPrice.getText();
		double price = 0;

		price = Double.parseDouble(priceText);
		String out = objProd.updateProd(prod, newProd, price, category);

		if(out.equals("1")) {
			JOptionPane.showMessageDialog(null, "Product Updated");
		}
		else{
			JOptionPane.showMessageDialog(null, "Something went wrong. Please try again");
		}

	}
}
