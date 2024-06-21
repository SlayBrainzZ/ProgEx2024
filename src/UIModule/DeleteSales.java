package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Dictionary;

import BusinessLayer.SalesBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

public class DeleteSales extends JFrame implements ItemListener, CRUDInterface {

	private static final long serialVersionUID = 1L;

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	private JPanel contentPane;
	JLabel lblMsg;
	private JTextField txtBalAmt;
	private JTextField txtDate;
	private JTextField txtItems;
	JComboBox<String> cmbBoxCusName;
	JComboBox<Integer> cmbBoxSalesID;

	SalesBusiness objSales = new SalesBusiness();

	String[] CusNames = new String[]{};

	ExceptionMaster obj = new ExceptionMaster();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSales frame = new DeleteSales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error starting application: " + e.getMessage());
				}
			}
		});
	}

	public DeleteSales() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
			obj.InsertException(ex.getMessage(), "DeleteSales", "constructor");
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(0, 0, 5, 0);
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;
		gbcContent.anchor = GridBagConstraints.CENTER;

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 22));
		lblMsg.setBounds(603, 197, 690, 39);
		contentPane.add(lblMsg, gbcContent);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblDeleteSales = new JLabel("Delete Sales");
		lblDeleteSales.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteSales.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblDeleteSales, gbcPanel);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCustomerName.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblCustomerName, gbcPanel);

		cmbBoxCusName = new JComboBox<>();
		cmbBoxCusName.addItemListener(this);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		cmbBoxCusName.setPreferredSize(new Dimension(150, 28));
		panel.add(cmbBoxCusName, gbcPanel);

		JLabel lblSalesId = new JLabel("Sales ID");
		lblSalesId.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSalesId.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblSalesId, gbcPanel);

		cmbBoxSalesID = new JComboBox<>();
		cmbBoxSalesID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxSalesID.getSelectedItem() != null) {
					int salesID = (int) cmbBoxSalesID.getSelectedItem();
					GetSalesDetails(salesID);
				}
			}
		});
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		cmbBoxSalesID.setPreferredSize(new Dimension(150, 28));
		panel.add(cmbBoxSalesID, gbcPanel);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAmount.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblAmount, gbcPanel);

		txtBalAmt = new JTextField();
		txtBalAmt.setEditable(false);
		txtBalAmt.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 3;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtBalAmt, gbcPanel);

		JLabel lblItemsBought = new JLabel("Items Bought");
		lblItemsBought.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemsBought.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblItemsBought, gbcPanel);

		txtItems = new JTextField();
		txtItems.setEditable(false);
		txtItems.setFont(new Font("Dubai", Font.BOLD, 15));
		txtItems.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 4;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtItems, gbcPanel);

		JLabel lblTransactionDate = new JLabel("Transaction Date");
		lblTransactionDate.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTransactionDate.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 5;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		panel.add(lblTransactionDate, gbcPanel);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 5;
		gbcPanel.gridwidth = 1;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txtDate, gbcPanel);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
				JOptionPane.showMessageDialog(btnDelete, "Sales successfully deleted");
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDelete.setBackground(new Color(43, 43, 43));
		btnDelete.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 6;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnDelete, gbcPanel);

		contentPane.add(panel, gbcPanelCenter);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\GradBG1 (3).jpg"));
		gbcContent.gridx = 0;
		gbcContent.gridy = 2;
		contentPane.add(lblNewLabel_1, gbcContent);

		GetCusNamesCmbBox();
	}

	public void itemStateChanged(ItemEvent e) {
		String CusName = (String) cmbBoxCusName.getSelectedItem();
		populateCmbBoxSalesID(CusName);
	}

	public void GetCusNamesCmbBox() {
		CusNames = objSales.GetCusNamesCmbBox_Delete();
		cmbBoxCusName.removeAllItems();
		for (String name : CusNames) {
			cmbBoxCusName.addItem(name);
		}
	}

	public void populateCmbBoxSalesID(String CusName) {
		Integer[] SalesID = objSales.getSalesID_Delete(CusName);
		cmbBoxSalesID.removeAllItems();
		for (int id : SalesID) {
			cmbBoxSalesID.addItem(id);
		}
	}

	public void GetSalesDetails(int SalesID) {
		Dictionary<String, Object> SalesDetails = objSales.GetSalesDetails(SalesID);
		txtBalAmt.setText(String.valueOf(SalesDetails.get("Amount")));
		txtItems.setText((String) SalesDetails.get("Item"));
		txtDate.setText((String) SalesDetails.get("Date"));
	}

	public void Insert() {
	}

	public void Update() {
	}

	public void Read() {
	}

	public void Delete() {
		String Name = (String) cmbBoxCusName.getSelectedItem();
		objSales.DeleteSales(Name);
		GetCusNamesCmbBox();
	}
}
