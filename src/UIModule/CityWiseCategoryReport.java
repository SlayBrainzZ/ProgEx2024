package UIModule;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.CitywiseCtgReportBusiness;
import ExceptionHandling.ExceptionMaster;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;

import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CityWiseCategoryReport extends JFrame {

	int Rows=0;
	JLabel lblMsg;

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	ExceptionMaster obj = new ExceptionMaster();

	CitywiseCtgReportBusiness objReport = new CitywiseCtgReportBusiness();

	private JPanel contentPane;
	private JTable table;

	JComboBox comboBox;

	DefaultTableModel objTableModel = new DefaultTableModel();
	private JTextField txtQtty;
	private JLabel lblMsg1;
	private JLabel lblMsg2;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityWiseCategoryReport frame = new CityWiseCategoryReport();
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
	public CityWiseCategoryReport() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);


		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitywiseCtgReportBusiness", "Constructor");

		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		objTableModel.addColumn("Items Sold");
		objTableModel.addColumn("Quantity");
		objTableModel.addColumn("City");

		lblMsg2 = new JLabel("");
		lblMsg2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMsg2.setBounds(336, 563, 142, 43);
		contentPane.add(lblMsg2);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 271, 723, 281);
		contentPane.add(scrollPane);

		table = new JTable(objTableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Ctg = (String) comboBox.getSelectedItem();

				getReport(Ctg);
				//RemovePrevRows(Rows);
			}
		});
		comboBox.setBounds(336, 272, 155, 29);
		contentPane.add(comboBox);

		txtQtty = new JTextField();
		txtQtty.setBounds(722, 582, 114, 24);
		contentPane.add(txtQtty);
		txtQtty.setColumns(10);

		lblMsg = new JLabel("Total items sold");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMsg.setBounds(557, 576, 155, 29);
		contentPane.add(lblMsg);

		JLabel lblNewLabel = new JLabel("Select Category");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(338, 235, 155, 29);
		contentPane.add(lblNewLabel);

		lblMsg1 = new JLabel("");
		lblMsg1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMsg1.setBounds(285, 677, 163, 54);
		contentPane.add(lblMsg1);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CityWiseCategoryReport.class.getResource("/BackgroundImage/aqua BG.png")));
		lblNewLabel_1.setBounds(3, 2, 1545, 800);
		contentPane.add(lblNewLabel_1);

		getCategories();
	}

	public void getCategories() {
		String[] Ctg = new String[] {};

		Ctg = objReport.getCategories();
		int Len = Ctg.length ;

		for(int i=0;i<Len;i++) {

			comboBox.addItem(Ctg[i]);

		}

	}

	public void getReport(String Ctg) {
		String var = "";
		int Flag = 0;
		Rows = objTableModel.getRowCount();

		lblMsg.setText("R1 = "+ Rows);
		int TotalQtty =0;
		try {



			CallableStatement stmt = con.prepareCall("{ call Proc_CategoryMaster_CategoryCityReport(?) }");
			stmt.setString(1, Ctg);

			ResultSet rs = stmt.executeQuery();
			objTableModel.setRowCount(0);


			while(rs.next()) {

				objTableModel.insertRow(0, new Object[] { rs.getString("Item_Name"), rs.getInt("TotalQtty"), rs.getString("City") });
				TotalQtty += rs.getInt("TotalQtty");

			}

			Rows = objTableModel.getRowCount();
			txtQtty.setText(""+ TotalQtty);

		}

		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CityWiseCategoryReport", "getReport");
		}

	}


}
