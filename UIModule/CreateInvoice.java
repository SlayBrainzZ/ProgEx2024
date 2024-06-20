package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.InvoiceBusiness;
import ExceptionHandling.ExceptionMaster;

public class CreateInvoice extends JFrame {

	ExceptionMaster objEx = new ExceptionMaster();
	DefaultTableModel objTableModel = new DefaultTableModel();
	Dictionary Details = new Hashtable();
	Dictionary SalesDetails = new Hashtable();
	InvoiceBusiness objInvoice = new InvoiceBusiness();

	JComboBox cmbBoxAccID;
	JComboBox cmbBoxSalesID;
	JComboBox cmbBoxTranID;

	JLabel lblMsg;

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblSalesid;
	private JLabel lblTransactionId;
	private JTable table;
	private JTextField txtName;
	private JTextField txtAdd1;
	private JTextField txtAdd2;
	private JTextField txtAdd3;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateInvoice frame = new CreateInvoice();
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
	public CreateInvoice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Create Invoice");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(43, 43, 43));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileWriter();
				JOptionPane.showMessageDialog(null, "Invoice created");
			}
		});
		btnNewButton.setBounds(747, 656, 118, 34);
		contentPane.add(btnNewButton);

		cmbBoxAccID = new JComboBox();
		cmbBoxAccID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String var = (String) cmbBoxAccID.getSelectedItem();
				int AccID = Integer.parseInt(var);
				getSalesID(AccID);
				getDetails(AccID);
				getArrDetails(AccID);
			}
		});
		cmbBoxAccID.setBounds(548, 154, 151, 27);
		contentPane.add(cmbBoxAccID);

		cmbBoxSalesID = new JComboBox();
		cmbBoxSalesID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxSalesID.getSelectedItem() != null) {
					int SalesID = Integer.parseInt(cmbBoxSalesID.getSelectedItem().toString());
					getTranID(SalesID);
				}
			}
		});
		cmbBoxSalesID.setBounds(747, 154, 151, 27);
		contentPane.add(cmbBoxSalesID);

		cmbBoxTranID = new JComboBox();
		cmbBoxTranID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTableData();
			}
		});
		cmbBoxTranID.setBounds(951, 154, 151, 27);
		contentPane.add(cmbBoxTranID);

		JPanel panel2 = new JPanel();
		panel2.setBounds(499, 83, 648, 112);
		panel2.setBackground(new Color(43, 43, 43));
		contentPane.add(panel2);
		panel2.setLayout(null);

		lblNewLabel = new JLabel("AccountID");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel.setBounds(80, 40, 118, 26);
		panel2.add(lblNewLabel);

		lblSalesid = new JLabel("SalesID");
		lblSalesid.setForeground(Color.white);
		lblSalesid.setFont(new Font("Calibri", Font.BOLD, 21));
		lblSalesid.setBounds(290, 40, 118, 26);
		panel2.add(lblSalesid);

		lblTransactionId = new JLabel("Transaction ID");
		lblTransactionId.setForeground(Color.white);
		lblTransactionId.setFont(new Font("Calibri", Font.BOLD, 21));
		lblTransactionId.setBounds(470, 40, 152, 26);
		panel2.add(lblTransactionId);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Item");
		tableModel.addColumn("Unit Price");
		tableModel.addColumn("Quantity");
		tableModel.addColumn("Total Amount");
		tableModel.addColumn("Order Date");

		table = new JTable(tableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Calibri", Font.PLAIN, 15));
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.setGridColor(new Color(200, 200, 200));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(500, 403, 650, 231);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBounds(499, 210, 648, 172);
		panel.setBackground(new Color(43, 43, 43));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Customer Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1.setBounds(235, 10, 173, 22);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Address Line 1");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_2.setBounds(35, 104, 130, 22);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Address Line 2");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(353, 62, 130, 22);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Address Line 3");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_2_2.setBounds(352, 104, 130, 22);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Customer Name");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_2_3.setBounds(32, 61, 153, 22);
		panel.add(lblNewLabel_2_3);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(181, 61, 143, 27);
		panel.add(txtName);
		txtName.setColumns(10);

		txtAdd1 = new JTextField();
		txtAdd1.setEditable(false);
		txtAdd1.setColumns(10);
		txtAdd1.setBounds(181, 101, 143, 27);
		panel.add(txtAdd1);

		txtAdd2 = new JTextField();
		txtAdd2.setEditable(false);
		txtAdd2.setColumns(10);
		txtAdd2.setBounds(495, 61, 143, 27);
		panel.add(txtAdd2);

		txtAdd3 = new JTextField();
		txtAdd3.setEditable(false);
		txtAdd3.setColumns(10);
		txtAdd3.setBounds(495, 99, 143, 27);
		panel.add(txtAdd3);

		// Adding example rows to the table model
		tableModel.addRow(new Object[]{"Example Item 1", 10.0, 2, 20.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 2", 15.0, 1, 15.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 3", 5.0, 3, 15.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 4", 8.0, 4, 32.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 5", 12.0, 2, 24.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 6", 7.0, 5, 35.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 7", 6.0, 2, 12.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 8", 20.0, 1, 20.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 9", 18.0, 3, 54.0, "2024-06-14"});
		tableModel.addRow(new Object[]{"Example Item 10", 25.0, 2, 50.0, "2024-06-14"});

		/* JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CreateInvoice.class.getResource("/BackgroundImage/aqua BG.png")));
		lblNewLabel_3.setBounds(0, 0, 1545, 800);
		contentPane.add(lblNewLabel_3); */

		getAccIDs();

	}

	public void getAccIDs() {
		String[] AccId = new String[] {};

		AccId = objInvoice.getAccIDs();
		int Len = AccId.length;

		for(int i=0;i<Len;i++) {
			cmbBoxAccID.addItem(AccId[i]);


		}
	}

	public void getSalesID(int AccID) {
		try {
			cmbBoxSalesID.removeAllItems();
			int[] SalesID = new int[] {};
			SalesID = objInvoice.getSalesID(AccID);
			int Len = SalesID.length;
			for (int i = 0; i < Len; i++) {
				cmbBoxSalesID.addItem(SalesID[i]);
			}
		} catch (Exception Ex) {
			JOptionPane.showMessageDialog(null, Ex);
		}
	}

	public void getTranID(int SalesID) {
		cmbBoxTranID.removeAllItems();
		SalesDetails = objInvoice.getTranDetails(SalesID);
		cmbBoxTranID.addItem(SalesDetails.get("TranID"));
	}

	public void FileWriter() {
		int AccID = Integer.parseInt((String) cmbBoxAccID.getSelectedItem());
		try {
			String ID = cmbBoxSalesID.getSelectedItem().toString();
			int SalesID = Integer.parseInt(ID);
			FileWriter objFW = new FileWriter("C://School/Invoice.txt");

			objFW.write(objInvoice.CompanyInfo(SalesID, AccID, Details.get("InvoiceDate"), Details.get("Name"),
					Details.get("Add1"), Details.get("Add2"), Details.get("Add3"), SalesDetails.get("OrderDate"),
					SalesDetails.get("Rate"), SalesDetails.get("Qtty"), SalesDetails.get("Item"),
					SalesDetails.get("Amt")).toString());

			objFW.close();
		} catch (Exception Ex) {
			objEx.InsertException(Ex.getMessage(), "CreateInvoice", "FileWriter");
			JOptionPane.showMessageDialog(null, "FileWriter: " + Ex);
		}
	}

	public void getDetails(int AccID) {
		Details = objInvoice.getAddress1(AccID);
		txtName.setText("" + Details.get("Name"));
		txtAdd1.setText("" + Details.get("Add1"));
		txtAdd2.setText("" + Details.get("Add2"));
		txtAdd3.setText("" + Details.get("Add3"));
	}

	public void getArrDetails(int AccID) {
		String[][] ArrDetails = new String[][] {};
		ArrDetails = objInvoice.getDetails2DArr(AccID);
		int Len = ArrDetails.length;

		for (int i = 0; i < Len; i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0) {
					txtName.setText(ArrDetails[i][j]);
				} else if (j == 1) {
					txtAdd1.setText(ArrDetails[i][j]);
				} else if (j == 2) {
					txtAdd2.setText(ArrDetails[i][j]);
				} else if (j == 3) {
					txtAdd3.setText(ArrDetails[i][j]);
				}
			}
		}
	}

	public void getTableData() {
		int Rows = objTableModel.getRowCount();
		for (int i = 0; i < Rows; i++) {
			objTableModel.removeRow(i);
		}
		objTableModel.insertRow(0, new Object[] { SalesDetails.get("Item"), SalesDetails.get("Rate"),
				SalesDetails.get("Qtty"), SalesDetails.get("Amt"), SalesDetails.get("OrderDate") });
	}

	// Custom TableCellRenderer for table customization
	class CustomTableCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
													   boolean hasFocus, int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// Set foreground and background colors
			cell.setForeground(Color.BLACK);
			cell.setBackground(Color.WHITE);

			// Set font
			cell.setFont(new Font("Calibri", Font.PLAIN, 15));

			// Set alignment
			if (column == 0) { // Align first column to the left
				setHorizontalAlignment(JLabel.LEFT);
			} else { // Align other columns to the center
				setHorizontalAlignment(JLabel.CENTER);
			}

			return cell;
		}
	}

	// Custom renderer for table header
	class HeaderRenderer extends DefaultTableCellRenderer {
		public HeaderRenderer() {
			setOpaque(true);
			setFont(new Font("Calibri", Font.BOLD, 15));
			setBackground(new Color(43, 43, 43));
			setForeground(Color.WHITE);
			setHorizontalAlignment(JLabel.CENTER); // Align headers to the center
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
													   boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// Set font for headers
			setFont(new Font("Calibri", Font.BOLD, 15));

			return this;
		}
	}
}