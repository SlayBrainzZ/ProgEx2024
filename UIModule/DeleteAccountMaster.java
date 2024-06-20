package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Dictionary;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BusinessLayer.AccountBusiness;
import ExceptionHandling.ExceptionMaster;

public class DeleteAccountMaster extends JFrame {

	private JPanel contentPane;
	private JLabel lblMsg;
	private JComboBox<String> cmbBoxCusName;
	private JTextField txtBalAmt;
	private JTextField txtCity;
	private JTextField txtPhNum;
	private JTextField txtEmail;

	AccountBusiness objAcc = new AccountBusiness();
	ExceptionMaster obj = new ExceptionMaster();

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccountMaster frame = new DeleteAccountMaster();
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
	public DeleteAccountMaster() {
		initializeDatabaseConnection();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 810); // Set dimensions to match CreateNewUser
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMsg.setBounds(267, 62, 701, 60); // Adjusted width to fit within the frame
		contentPane.add(lblMsg);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(43, 43, 43)));
		panel.setBounds(471, 205, 602, 400);// Adjusted position and size to match CreateNewUser
		panel.setBackground(new Color(43, 43, 43));
		contentPane.add(panel);
		panel.setLayout(null);

		// Centering content within the panel
		int panelWidth = panel.getWidth();
		int panelHeight = panel.getHeight();

		JLabel lblNewLabel = new JLabel("Delete Account");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel.setBounds((panelWidth - 200) / 2, 10, 200, 42); // Centered
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(43, 43, 43));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.setBounds((panelWidth - 185) / 2, 332, 185, 28); // Centered
		panel.add(btnNewButton);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds((panelWidth - 102) / 2 - 150, 93, 153, 36); // Adjusted position
		panel.add(lblNewLabel_1_1);

		cmbBoxCusName = new JComboBox<String>();
		cmbBoxCusName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CusName = (String) cmbBoxCusName.getSelectedItem();
				Dictionary<?, ?> Values = objAcc.GetCustomerDetails(CusName);
				txtEmail.setText((String) Values.get("EmailID"));
				txtPhNum.setText((String) Values.get("Phone"));
				txtCity.setText((String) Values.get("City"));
			}
		});
		cmbBoxCusName.setBounds((panelWidth - 152) / 2 + 50, 100, 188, 28); // Adjusted position
		panel.add(cmbBoxCusName);

		JLabel lblNewLabel_1 = new JLabel("Balance Amount");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds((panelWidth - 102) / 2 - 150, 139, 162, 28); // Adjusted position
		panel.add(lblNewLabel_1);

		txtBalAmt = new JTextField();
		txtBalAmt.setText("0");
		txtBalAmt.setEditable(false);
		txtBalAmt.setColumns(10);
		txtBalAmt.setBounds((panelWidth - 148) / 2 + 50, 139, 184, 28); // Adjusted position
		panel.add(txtBalAmt);

		JLabel lblNewLabel_1_2 = new JLabel("City");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds((panelWidth - 102) / 2 - 150, 181, 162, 28); // Adjusted position
		panel.add(lblNewLabel_1_2);

		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setColumns(10);
		txtCity.setBounds((panelWidth - 148) / 2 + 50, 181, 184, 28); // Adjusted position
		panel.add(txtCity);

		JLabel lblNewLabel_1_2_1 = new JLabel("Phone Number");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds((panelWidth - 102) / 2 - 150, 221, 162, 28); // Adjusted position
		panel.add(lblNewLabel_1_2_1);

		txtPhNum = new JTextField();
		txtPhNum.setEditable(false);
		txtPhNum.setColumns(10);
		txtPhNum.setBounds((panelWidth - 148) / 2 + 50, 225, 184, 28); // Adjusted position
		panel.add(txtPhNum);

		JLabel lblNewLabel_1_2_2 = new JLabel("Email ID");
		lblNewLabel_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_2_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2_2.setBounds((panelWidth - 102) / 2 - 150, 262, 162, 28); // Adjusted position
		panel.add(lblNewLabel_1_2_2);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds((panelWidth - 148) / 2 + 50, 266, 184, 28); // Adjusted position
		panel.add(txtEmail);

		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}

	private void initializeDatabaseConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "Constructor");
		}
	}

	public void Delete() {
		String CusName = (String) cmbBoxCusName.getSelectedItem();
		objAcc.DeleteCustomerDB(CusName);
		JOptionPane.showMessageDialog(null, "Customer information was successfully deleted");

		populateComboBox();
	}

	public void populateComboBox() {
		cmbBoxCusName.removeAllItems();
		String[] CusNames = objAcc.GetCusNames_Delete();
		for (String cusName : CusNames) {
			cmbBoxCusName.addItem(cusName);
		}
	}
}
