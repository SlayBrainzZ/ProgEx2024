package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BusinessLayer.ProductBusiness;



public class DeleteItem extends JFrame {
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JLabel lblMsg;

	private ProductBusiness objProd = new ProductBusiness();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteItem frame = new DeleteItem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteItem() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400); // Adjusted size to match CategoryMaster
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout()); // GridBagLayout for centering
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 300)); // Size adjusted to fit components

		lblMsg = new JLabel("");
		lblMsg.setBounds(50, 20, 300, 30);
		panel.add(lblMsg);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 20));

		JLabel lblDeleteItem = new JLabel("Delete Item");
		lblDeleteItem.setForeground(Color.BLACK);
		lblDeleteItem.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteItem.setBounds(120, 50, 200, 30);
		panel.add(lblDeleteItem);

		JLabel lblItemName = new JLabel("Item name");
		lblItemName.setForeground(Color.BLACK);
		lblItemName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemName.setBounds(50, 100, 100, 30);
		panel.add(lblItemName);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Calibri", Font.BOLD, 20));
		comboBox.setBounds(50, 130, 300, 30);
		panel.add(comboBox);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(43, 43, 43));
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDelete.setBounds(50, 200, 300, 40);
		panel.add(btnDelete);

		// Center the panel within the contentPane
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(panel, gbc);

		setMinimumSize(new Dimension(1300, 700));

		populateComboBox();
	}

	public void populateComboBox() {
		/* List<List<Object>> products = objProd.readProd();
		comboBox.removeAllItems(); // Clear the comboBox before populating

		for (List<Object> product : products) {
			String productName = (String) product.get(0); // Assuming product name is the first element
			comboBox.addItem(productName);
		} */
	}


	public void Delete() {
		String itemName = (String) comboBox.getSelectedItem();
		String out = objProd.deleteProd(itemName);
		if(out.equals("1")){
			JOptionPane.showMessageDialog(null, "Product deleted successfully");
		}
		else if(out.equals("-1")){
			JOptionPane.showMessageDialog(null, "Please delete the orders for the product first.");
		}
		else{
			JOptionPane.showMessageDialog(null, "Something went wrong. Please try again");
		}
		populateComboBox();
	}
}
