package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Import your classes here
import ExceptionHandling.ExceptionMaster;
import java.sql.Connection;
import java.sql.DriverManager;
import UIModule.CreateInvoice;
import UIModule.CreateNewUser;
import UIModule.UpdateUser;
import UIModule.DeleteUser;
import UIModule.AccountMasterList;
import UIModule.UpdateAccountMaster;
import UIModule.DeleteAccountMaster;
import UIModule.CategoryMaster;
import UIModule.UpdateCategory;
import UIModule.DeleteCategory;
import UIModule.ItemMasterNew;
import UIModule.UpdateItemMaster;
import UIModule.DeleteItem;
import UIModule.SalesMaster;
import UIModule.DeleteSales;
import UIModule.UpdateSalesMaster;
import UIModule.CitySalesReport;
import UIModule.CustomerCityReport;
import UIModule.CityWiseCategoryReport;
import UIModule.DateWiseRevenueReport;
import UIModule.DateWiseSalesReport;
import UIModule.MonthlyRevenueReport;

public class MenuMaster extends JFrame implements ActionListener {

	JLabel lblUserText;
	JPanel panelUser;
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	private JPanel contentPane;
	JButton btnMaster, btnInput, btnReport, btnInvoice;
	String Username;
	ExceptionMaster obj = new ExceptionMaster();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuMaster frame = new MenuMaster();
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
	public MenuMaster() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "LoginPageNew", "Constructor");
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\devan\\Pictures\\JViola (2).jpg"));
		setTitle("REPORTING SOFTWARE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome to the Inventory Management System");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcome.setForeground(Color.LIGHT_GRAY);
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);  // Center the text
		lblWelcome.setBounds(100, 50, 600, 30);  // Adjust the bounds to be centered within the frame
		contentPane.add(lblWelcome);

		btnMaster = new JButton("Master");
		btnMaster.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMaster.setBackground(Color.LIGHT_GRAY);
		btnMaster.setForeground(Color.DARK_GRAY);
		btnMaster.setBounds(300, 150, 200, 40);
		btnMaster.addActionListener(this);
		contentPane.add(btnMaster);

		btnInput = new JButton("Input");
		btnInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInput.setBackground(Color.LIGHT_GRAY);
		btnInput.setForeground(Color.DARK_GRAY);
		btnInput.setBounds(300, 210, 200, 40);
		btnInput.addActionListener(this);
		contentPane.add(btnInput);

		btnReport = new JButton("Report");
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReport.setBackground(Color.LIGHT_GRAY);
		btnReport.setForeground(Color.DARK_GRAY);
		btnReport.setBounds(300, 270, 200, 40);
		btnReport.addActionListener(this);
		contentPane.add(btnReport);

		btnInvoice = new JButton("Invoice");
		btnInvoice.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInvoice.setBackground(Color.LIGHT_GRAY);
		btnInvoice.setForeground(Color.DARK_GRAY);
		btnInvoice.setBounds(300, 330, 200, 40);
		btnInvoice.addActionListener(this);
		contentPane.add(btnInvoice);
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == btnMaster) {
			String[] options = {"User Master", "Update User", "Delete User", "Customer Master", "Update Customer", "Delete Customer", "Category Master", "Update Category", "Delete Category", "Item Master", "Update Item", "Delete Item"};
			String choice = (String) JOptionPane.showInputDialog(null, "Choose an action", "Master Actions", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			handleMasterActions(choice);
		}

		if (button == btnInput) {
			String[] options = {"Sales", "Delete Sales", "Update Sales"};
			String choice = (String) JOptionPane.showInputDialog(null, "Choose an action", "Input Actions", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			handleInputActions(choice);
		}

		if (button == btnReport) {
			String[] options = {"Citywise Sales Report", "Citywise Customer Report", "Datewise Revenue Report", "Datewise Sales Report", "Monthly Revenue Report"};
			String choice = (String) JOptionPane.showInputDialog(null, "Choose an action", "Report Actions", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			handleReportActions(choice);
		}

		if (button == btnInvoice) {
			CreateInvoice invoice = new CreateInvoice();
			invoice.setVisible(true);
		}
	}

	private void handleMasterActions(String choice) {
		switch (choice) {
			case "User Master":
				CreateNewUser userMaster = new CreateNewUser();
				userMaster.setVisible(true);
				break;
			case "Update User":
				UpdateUser updateUser = new UpdateUser();
				updateUser.setVisible(true);
				break;
			case "Delete User":
				DeleteUser delUser = new DeleteUser();
				delUser.setVisible(true);
				break;
			case "Customer Master":
				AccountMasterList AccMaster = new AccountMasterList();
				AccMaster.setVisible(true);
				break;
			case "Update Customer":
				UpdateAccountMaster updateAcc = new UpdateAccountMaster();
				updateAcc.setVisible(true);
				break;
			case "Delete Customer":
				DeleteAccountMaster delAcc = new DeleteAccountMaster();
				delAcc.setVisible(true);
				break;
			case "Category Master":
				CategoryMaster Ctg = new CategoryMaster();
				Ctg.setVisible(true);
				break;
			case "Update Category":
				UpdateCategory updateCtg = new UpdateCategory();
				updateCtg.setVisible(true);
				break;
			case "Delete Category":
				DeleteCategory delCtg = new DeleteCategory();
				delCtg.setVisible(true);
				break;
			case "Item Master":
				ItemMasterNew ItemMaster = new ItemMasterNew();
				ItemMaster.setVisible(true);
				break;
			case "Update Item":
				UpdateItemMaster updateItem = new UpdateItemMaster();
				updateItem.setVisible(true);
				break;
			case "Delete Item":
				DeleteItem delItem = new DeleteItem();
				delItem.setVisible(true);
				break;
		}
	}

	private void handleInputActions(String choice) {
		switch (choice) {
			case "Sales":
				SalesMaster sales = new SalesMaster();
				sales.setVisible(true);
				break;
			case "Delete Sales":
				DeleteSales deleteSales = new DeleteSales();
				deleteSales.setVisible(true);
				break;
			case "Update Sales":
				UpdateSalesMaster updateSales = new UpdateSalesMaster();
				updateSales.setVisible(true);
				break;
		}
	}

	private void handleReportActions(String choice) {
		switch (choice) {
			case "Citywise Sales Report":
				CitySalesReport citySales = new CitySalesReport();
				citySales.setVisible(true);
				break;
			case "Citywise Customer Report":
				CustomerCityReport cusCityReport = new CustomerCityReport();
				cusCityReport.setVisible(true);
				break;
			/* case "Category City Report":
				CityWiseCategoryReport cityCtg = new CityWiseCategoryReport();
				cityCtg.setVisible(true);
				break; */
			case "Datewise Revenue Report":
				DateWiseRevenueReport datewiseRev = new DateWiseRevenueReport();
				datewiseRev.setVisible(true);
				break;
			case "Datewise Sales Report":
				DateWiseSalesReport datewiseSales = new DateWiseSalesReport();
				datewiseSales.setVisible(true);
				break;
			case "Monthly Revenue Report":
				MonthlyRevenueReport monthlyRev = new MonthlyRevenueReport();
				monthlyRev.setVisible(true);
				break;
		}
	}
}
