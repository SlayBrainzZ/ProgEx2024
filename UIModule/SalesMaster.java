package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

import BusinessLayer.SalesBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

public class SalesMaster extends JFrame implements CRUDInterface {
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	private JPanel contentPane;
	private JTextField txtQtty;
	private JTextField txtRate;
	private JTextField txtAmt;
	private JTextField txtDate;
	JLabel lblMsg;
	SalesBusiness objSales = new SalesBusiness();
	ExceptionMaster obj = new ExceptionMaster();

	JComboBox<String> cmbBoxItemName;
	JComboBox<String> cmbBoxCusName;
	String[] Items = new String[]{};
	String[] CusNames = new String[]{};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMaster frame = new SalesMaster();
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
	public SalesMaster() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700); // Adjust frame size
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Increased top inset
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.insets = new Insets(0, 0, 10, 0); // Increase bottom inset of lblMsg for spacing
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 0;
		gbc_lblMsg.anchor = GridBagConstraints.CENTER;
		contentPane.add(lblMsg, gbc_lblMsg);

		initializeDatabaseConnection();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(30, 30, 30, 30); // Increased insets for spacing around the panel
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		gbc_panel.fill = GridBagConstraints.BOTH;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridBagLayout());

		JLabel lblSales = new JLabel("Sales");
		lblSales.setForeground(Color.WHITE);
		lblSales.setFont(new Font("Calibri", Font.BOLD, 26));
		GridBagConstraints gbc_lblSales = new GridBagConstraints();
		gbc_lblSales.insets = new Insets(0, 0, 20, 0);
		gbc_lblSales.gridx = 0;
		gbc_lblSales.gridy = 0;
		gbc_lblSales.insets = new Insets(20, 0, 20, 0); // Adjust top and bottom insets for spacing
		gbc_lblSales.anchor = GridBagConstraints.EAST;
		panel.add(lblSales, gbc_lblSales);

		JLabel lblCusName = new JLabel("Customer Name");
		lblCusName.setForeground(Color.WHITE);
		lblCusName.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblCusName = new GridBagConstraints();
		gbc_lblCusName.anchor = GridBagConstraints.EAST;
		gbc_lblCusName.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblCusName.gridx = 0;
		gbc_lblCusName.gridy = 1;
		panel.add(lblCusName, gbc_lblCusName);

		cmbBoxCusName = new JComboBox<>();
		GridBagConstraints gbc_cmbBoxCusName = new GridBagConstraints();
		gbc_cmbBoxCusName.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_cmbBoxCusName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBoxCusName.gridx = 1;
		gbc_cmbBoxCusName.gridy = 1;
		gbc_cmbBoxCusName.anchor = GridBagConstraints.WEST;
		gbc_cmbBoxCusName.weightx = 1.0;
		panel.add(cmbBoxCusName, gbc_cmbBoxCusName);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.anchor = GridBagConstraints.EAST;
		gbc_lblItemName.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 2;
		panel.add(lblItemName, gbc_lblItemName);

		cmbBoxItemName = new JComboBox<>();
		GridBagConstraints gbc_cmbBoxItemName = new GridBagConstraints();
		gbc_cmbBoxItemName.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_cmbBoxItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBoxItemName.gridx = 1;
		gbc_cmbBoxItemName.gridy = 2;
		gbc_cmbBoxItemName.anchor = GridBagConstraints.WEST;
		gbc_cmbBoxItemName.weightx = 1.0;
		panel.add(cmbBoxItemName, gbc_cmbBoxItemName);

		JLabel lblQtty = new JLabel("Quantity");
		lblQtty.setForeground(Color.WHITE);
		lblQtty.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblQtty = new GridBagConstraints();
		gbc_lblQtty.anchor = GridBagConstraints.EAST;
		gbc_lblQtty.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblQtty.gridx = 0;
		gbc_lblQtty.gridy = 3;
		panel.add(lblQtty, gbc_lblQtty);

		txtQtty = new JTextField();
		GridBagConstraints gbc_txtQtty = new GridBagConstraints();
		gbc_txtQtty.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtQtty.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtty.gridx = 1;
		gbc_txtQtty.gridy = 3;
		gbc_txtQtty.anchor = GridBagConstraints.WEST;
		gbc_txtQtty.weightx = 1.0;
		panel.add(txtQtty, gbc_txtQtty);
		txtQtty.setColumns(10);

		JLabel lblRate = new JLabel("Rate");
		lblRate.setForeground(Color.WHITE);
		lblRate.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblRate = new GridBagConstraints();
		gbc_lblRate.anchor = GridBagConstraints.EAST;
		gbc_lblRate.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblRate.gridx = 0;
		gbc_lblRate.gridy = 4;
		panel.add(lblRate, gbc_lblRate);

		txtRate = new JTextField();
		GridBagConstraints gbc_txtRate = new GridBagConstraints();
		gbc_txtRate.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRate.gridx = 1;
		gbc_txtRate.gridy = 4;
		gbc_txtRate.anchor = GridBagConstraints.WEST;
		gbc_txtRate.weightx = 1.0;
		panel.add(txtRate, gbc_txtRate);
		txtRate.setColumns(10);

		JLabel lblAmt = new JLabel("Amount");
		lblAmt.setForeground(Color.WHITE);
		lblAmt.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblAmt = new GridBagConstraints();
		gbc_lblAmt.anchor = GridBagConstraints.EAST;
		gbc_lblAmt.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblAmt.gridx = 0;
		gbc_lblAmt.gridy = 5;
		panel.add(lblAmt, gbc_lblAmt);

		txtAmt = new JTextField();
		txtAmt.setEditable(false);
		GridBagConstraints gbc_txtAmt = new GridBagConstraints();
		gbc_txtAmt.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtAmt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmt.gridx = 1;
		gbc_txtAmt.gridy = 5;
		gbc_txtAmt.anchor = GridBagConstraints.WEST;
		gbc_txtAmt.weightx = 1.0;
		panel.add(txtAmt, gbc_txtAmt);
		txtAmt.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 6;
		panel.add(lblDate, gbc_lblDate);

		txtDate = new JTextField();
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.insets = new Insets(10, 0, 10, 10); // Adjusted insets for spacing
		gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDate.gridx = 1;
		gbc_txtDate.gridy = 6;
		gbc_txtDate.anchor = GridBagConstraints.WEST;
		gbc_txtDate.weightx = 1.0;
		panel.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(new Color(75, 75, 75)); // Dark grey button color
		btnSave.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 7;
		gbc_btnSave.anchor = GridBagConstraints.CENTER;
		gbc_btnSave.gridwidth = 2; // Span across two columns
		panel.add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(75, 75, 75)); // Dark grey button color
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 8;
		gbc_btnCancel.anchor = GridBagConstraints.CENTER;
		gbc_btnCancel.gridwidth = 2; // Span across two columns
		panel.add(btnCancel, gbc_btnCancel);

		JButton btnCalAmt = new JButton("Calculate Amount");
		btnCalAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetAmount();
			}
		});
		btnCalAmt.setForeground(Color.WHITE);
		btnCalAmt.setBackground(new Color(75, 75, 75)); // Dark grey button color
		btnCalAmt.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btnCalAmt = new GridBagConstraints();
		gbc_btnCalAmt.insets = new Insets(10, 10, 10, 10); // Adjusted insets for spacing
		gbc_btnCalAmt.gridx = 0;
		gbc_btnCalAmt.gridy = 9;
		gbc_btnCalAmt.anchor = GridBagConstraints.CENTER;
		gbc_btnCalAmt.gridwidth = 2; // Span across two columns
		panel.add(btnCalAmt, gbc_btnCalAmt);
	}

	public void GetAmount() {
		int Qtty = Integer.parseInt(txtQtty.getText());
		double Rate = Double.parseDouble(txtRate.getText());
		double Amt = Qtty * Rate;
		txtAmt.setText(String.valueOf(Amt));
	}

	public void Insert() {
        /* try {
            int i = objSales.insertSalesRecord(cmbBoxCusName.getSelectedItem().toString(),
                    cmbBoxItemName.getSelectedItem().toString(), Integer.parseInt(txtQtty.getText()),
                    Double.parseDouble(txtRate.getText()), Double.parseDouble(txtAmt.getText()),
                    txtDate.getText());
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Saved");
                lblMsg.setText("Data Saved");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not Saved");
                lblMsg.setText("Data Not Saved");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } */
	}

	@Override
	public void Update() {

	}

	@Override
	public void Delete() {

	}

	@Override
	public void Read() {

	}

	public void initializeDatabaseConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			lblMsg.setText("Database connected");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
