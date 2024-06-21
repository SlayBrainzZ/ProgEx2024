package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import BusinessLayer.ItemBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

public class DeleteItem extends JFrame implements CRUDInterface {
	private JPanel contentPane;
	private JTextField txtSKU;
	private JTextField txtDesc;
	private JComboBox<String> comboBox;
	private JLabel lblMsg;

	private ItemBusiness objItem = new ItemBusiness();
	private ExceptionMaster obj = new ExceptionMaster();

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
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200)); // Updated background color
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 22));
		lblMsg.setBounds(603, 197, 690, 39);
		contentPane.add(lblMsg);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65)); // Updated panel background color
		panel.setBounds(538, 260, 400, 293); // Adjusted position and size
		contentPane.add(panel);
		panel.setLayout(new GridBagLayout()); // Changed to GridBagLayout

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblDeleteItem = new JLabel("Delete Item");
		lblDeleteItem.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteItem.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(lblDeleteItem, gbc);

		JLabel lblItemName = new JLabel("Item name");
		lblItemName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemName.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblItemName, gbc);

		comboBox = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		comboBox.setPreferredSize(new Dimension(150, 28));
		panel.add(comboBox, gbc);

		JLabel lblSKUCode = new JLabel("SKU Code");
		lblSKUCode.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSKUCode.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblSKUCode, gbc);

		txtSKU = new JTextField();
		txtSKU.setEditable(false);
		txtSKU.setColumns(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(txtSKU, gbc);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Calibri", Font.BOLD, 20));
		lblDescription.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblDescription, gbc);

		txtDesc = new JTextField();
		txtDesc.setEditable(false);
		txtDesc.setColumns(10);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(txtDesc, gbc);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
				JOptionPane.showMessageDialog(btnDelete, "Item Successfully deleted");
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDelete.setBackground(new Color(43, 43, 43));
		btnDelete.setBounds(114, 237, 171, 30);
		btnDelete.setForeground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(btnDelete, gbc);

		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\GradBG1 (3).jpg"));
		lblBG.setBounds(0, 0, 1545, 870);
		contentPane.add(lblBG);

		populateComboBox();
		setupListeners();
	}

	public void populateComboBox() {
		comboBox.removeAllItems();
		String[] items = objItem.populateComboBox_Delete();
		for (String item : items) {
			comboBox.addItem(item);
		}
	}

	public void GetItemDetails(String itemName) {
		Dictionary<String, String> itemDetails = objItem.GetItemDetails(itemName);
		txtSKU.setText(itemDetails.get("SKU"));
		txtDesc.setText(itemDetails.get("Desc"));
	}

	public void Update() {
	}

	public void Insert() {
	}

	public void Read() {
	}

	public void Delete() {
		String itemName = (String) comboBox.getSelectedItem();
		objItem.DeleteFromDB(itemName);
		populateComboBox();
	}

	private void setupListeners() {
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = (String) comboBox.getSelectedItem();
				GetItemDetails(itemName);
			}
		});
	}
}
