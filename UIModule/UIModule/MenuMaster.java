package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Import your classes here

public class MenuMaster extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton btnMaster, btnInput, btnReport;

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

	public MenuMaster() {
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
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);
		lblWelcome.setBounds(100, 50, 600, 30);
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
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == btnMaster) {
			String[] options = {
					"Customer Master", "Update Customer", "Delete Customer",
					"Category Master", "Update Category", "Delete Category",
					"Item Master", "Update Item", "Delete Item",
					"Customer List", "Category List", "Item List", "Sales List"
			};
			String choice = (String) JOptionPane.showInputDialog(
					null, "Choose an action", "Master Actions",
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]
			);
			handleMasterActions(choice);
		} else if (button == btnInput) {
			String[] options = {"Sales", "Delete Sales", "Update Sales"};
			String choice = (String) JOptionPane.showInputDialog(
					null, "Choose an action", "Input Actions",
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]
			);
			handleInputActions(choice);
		} else if (button == btnReport) {
			String[] options = {
					"Citywise Customer Report", "Orders by Month Report",
					"Monthly Sales With Customer Report"
			};
			String choice = (String) JOptionPane.showInputDialog(
					null, "Choose an action", "Report Actions",
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]
			);
			handleReportActions(choice);
		}
	}

	private void handleMasterActions(String choice) {
		switch (choice) {
			case "Customer Master":
				new CustomerMaster().setVisible(true);
				break;
			case "Update Customer":
				new UpdateCustomer().setVisible(true);
				break;
			case "Delete Customer":
				new DeleteCustomer().setVisible(true);
				break;
			case "Category Master":
				new CategoryMaster().setVisible(true);
				break;
			case "Update Category":
				new UpdateCategory().setVisible(true);
				break;
			case "Delete Category":
				new DeleteCategory().setVisible(true);
				break;
			case "Item Master":
				new ItemMasterNew().setVisible(true);
				break;
			case "Update Item":
				new UpdateItemMaster().setVisible(true);
				break;
			case "Delete Item":
				new DeleteItem().setVisible(true);
				break;
			case "Customer List":
				new CustomerList().setVisible(true);
				break;
			case "Category List":
				new CategoryList().setVisible(true);
				break;
			case "Item List":
				new ItemList().setVisible(true);
				break;
			case "Sales List":
				new SalesList().setVisible(true);
				break;
			default:
				break;
		}
	}

	private void handleInputActions(String choice) {
		switch (choice) {
			case "Sales":
				new SalesMaster().setVisible(true);
				break;
			case "Delete Sales":
				new DeleteSales().setVisible(true);
				break;
			case "Update Sales":
				new UpdateSalesMaster().setVisible(true);
				break;
			default:
				break;
		}
	}

	private void handleReportActions(String choice) {
		switch (choice) {
			case "Citywise Customer Report":
				new CustomerCityReport().setVisible(true);
				break;
			case "Orders by Month Report":
				new MonthOrderCountReport().setVisible(true);
				break;
			case "Monthly Sales With Customer Report":
				new MonthlySalesCusReport().setVisible(true);
				break;
			default:
				break;
		}
	}
}
