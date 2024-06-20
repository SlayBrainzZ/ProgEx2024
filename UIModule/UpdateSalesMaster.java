package UIModule;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import BusinessLayer.SalesBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class UpdateSalesMaster extends JFrame implements ItemListener, CRUDInterface {
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	private JPanel contentPane;
	private JTextField txtItem;
	private JTextField txtQtty;
	private JTextField txtRate;
	private JTextField txtDate;
	JLabel lblMsg;
	JComboBox cmbBoxCusNames;
	JComboBox cmbSalesID;

	SalesBusiness objSales = new SalesBusiness();
	ExceptionMaster obj = new ExceptionMaster();

	String[] CusNames = new String[] {};
	Integer[] SalesID = new Integer[] {};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSalesMaster frame = new UpdateSalesMaster();
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
	public UpdateSalesMaster() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		//contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Calibri", Font.BOLD, 23));
		lblMsg.setBounds(360, 149, 831, 38);
		contentPane.add(lblMsg);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(43, 43, 43)));
		panel.setBackground(new Color(43, 43, 43));
		panel.setBounds(360, 197, 831, 335);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSalesID = new JLabel("Sales ID");
		lblSalesID.setForeground(Color.WHITE);
		lblSalesID.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSalesID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSalesID.setBounds(61, 142, 113, 28);
		panel.add(lblSalesID);

		cmbSalesID = new JComboBox();
		cmbSalesID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbSalesID.getSelectedItem() != null) {
					int ID = (int) cmbSalesID.getSelectedItem();
					GetItemDate(ID);
					GetRate_Qtty(ID);
				}
			}
		});
		cmbSalesID.setBounds(207, 148, 185, 28);
		panel.add(cmbSalesID);

		cmbBoxCusNames = new JComboBox();
		cmbBoxCusNames.addItemListener(this);
		cmbBoxCusNames.setBounds(207, 100, 185, 28);
		panel.add(cmbBoxCusNames);

		JLabel lblNewLabel = new JLabel("Update Sales");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(353, 13, 150, 32);
		panel.add(lblNewLabel);

		txtItem = new JTextField();
		txtItem.setColumns(10);
		txtItem.setBounds(207, 198, 185, 28);
		panel.add(txtItem);

		JLabel lblNewLabel_1_1_1 = new JLabel("Item bought");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(61, 191, 113, 28);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Quantity");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(454, 90, 87, 32);
		panel.add(lblNewLabel_1_2);

		txtQtty = new JTextField();
		txtQtty.setColumns(10);
		txtQtty.setBounds(574, 100, 185, 28);
		panel.add(txtQtty);

		JLabel lblNewLabel_1_2_1 = new JLabel("Rate");
		lblNewLabel_1_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(454, 142, 51, 32);
		panel.add(lblNewLabel_1_2_1);

		txtRate = new JTextField();
		txtRate.setColumns(10);
		txtRate.setBounds(574, 148, 185, 28);
		panel.add(txtRate);

		JLabel lblNewLabel_1_2_2 = new JLabel("Date");
		lblNewLabel_1_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_2.setBounds(454, 191, 87, 28);
		panel.add(lblNewLabel_1_2_2);

		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(574, 196, 185, 28);
		panel.add(txtDate);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update();
			}
		});
		btnUpdate.setBackground(new Color(43, 43, 43)); // Set background color to grey
		btnUpdate.setForeground(Color.WHITE); // Set text color to white
		btnUpdate.setVerticalAlignment(SwingConstants.TOP);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(330, 277, 185, 28);
		panel.add(btnUpdate);

		JLabel lblNewLabel_1_2_3 = new JLabel("Customer Name");
		lblNewLabel_1_2_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_3.setForeground(Color.WHITE);
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_3.setBounds(61, 90, 140, 32);
		panel.add(lblNewLabel_1_2_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\GradBG1 (3).jpg"));
		lblNewLabel_4.setBounds(0, 0, 831, 335);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("\r\n");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\GradBG1 (3).jpg"));
		lblNewLabel_3.setBounds(0, 0, 1545, 875);
		contentPane.add(lblNewLabel_3);

		populateCmbBoxCusNames();

		String CusName = (String) cmbBoxCusNames.getSelectedItem();
		populateCmbSalesID(CusName);
	}

	public void itemStateChanged(ItemEvent item) {
		String CusName = (String) cmbBoxCusNames.getSelectedItem();
		populateCmbSalesID(CusName);
	}

	public void Insert() {
	}

	public void Read() {
	}

	public void Delete() {
	}

	public void Update() {
		int SalesID = (int) cmbSalesID.getSelectedItem();
		String item = txtItem.getText();
		int ItemID = getItemID(item);
		String Date = txtDate.getText();
		int Qtty = Integer.parseInt(txtQtty.getText());
		double Rate = Double.parseDouble(txtRate.getText());

		if (Date.isEmpty()) {
			lblMsg.setText("Enter the date");
		} else if (txtQtty.getText().isEmpty()) {
			lblMsg.setText("Enter the quantity");
		} else if (txtRate.getText().isEmpty()) {
			lblMsg.setText("Enter the rate");
		} else {
			objSales.UpdateSalesDB(SalesID, ItemID, Date, Qtty, Rate);
			populateCmbBoxCusNames();
			JOptionPane.showMessageDialog(null, "Details updated successfully");
		}
	}

	public void populateCmbSalesID(String CusName) {
		SalesID = objSales.GetSalesID_Update(CusName);
		int ArrLen = SalesID.length;

		cmbSalesID.removeAllItems();

		for (int i = 0; i < ArrLen; i++) {
			cmbSalesID.addItem(SalesID[i]);
		}
	}

	public void GetRate_Qtty(int SalesID) {
		Dictionary Rate_Qtty = new Hashtable();
		Rate_Qtty = objSales.GetRate_Qtty(SalesID);

		txtRate.setText((String) Rate_Qtty.get("Rate"));
		txtQtty.setText((String) Rate_Qtty.get("Qtty"));
	}

	public int getItemID(String Item) {
		return objSales.getItemID_Update(Item);
	}

	public void GetItemDate(int SalesID) {
		Dictionary ItemDate = new Hashtable();
		ItemDate = objSales.GetItemDate_Update(SalesID);

		txtItem.setText((String) ItemDate.get("Item"));
		txtDate.setText((String) ItemDate.get("Date"));
	}

	public void populateCmbBoxCusNames() {
		CusNames = objSales.GetCusNames_Update();
		int ArrLen = CusNames.length;

		cmbBoxCusNames.removeAllItems();

		for (int i = 0; i < ArrLen; i++) {
			cmbBoxCusNames.addItem(CusNames[i]);
		}
	}
}
