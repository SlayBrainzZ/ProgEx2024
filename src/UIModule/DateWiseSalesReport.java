package UIModule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import ExceptionHandling.ExceptionMaster;

public class DateWiseSalesReport extends JFrame {

	ExceptionMaster obj = new ExceptionMaster();
	DefaultTableModel objTableModel = new DefaultTableModel();

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	private JPanel contentPane;
	private JTable table;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JScrollPane scrollPane;
	private JLabel lblBackground;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateWiseSalesReport frame = new DateWiseSalesReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DateWiseSalesReport() {
		initializeDatabaseConnection();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800); // Adjusted size for better fitting

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.setBackground(new Color(200, 200, 200));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		objTableModel.addColumn("Date");
		objTableModel.addColumn("Sales");

		GridBagConstraints gbc;

		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(60, 63, 65)); // Dark viewport background

		table = new JTable(objTableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(new Color(200, 200, 200)); // Light grid color
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43)); // Dark header background
		table.getTableHeader().setForeground(Color.WHITE); // White header text color
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		scrollPane.setViewportView(table);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(20, 20, 10, 20);
		contentPane.add(scrollPane, gbc);

		txtStartDate = new JTextField("YYYY-MM-DD");
		txtStartDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtStartDate.getText().equals("YYYY-MM-DD")) {
					txtStartDate.setText("");
				}
			}
		});
		txtStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartDate.setColumns(10);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 20, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		contentPane.add(txtStartDate, gbc);

		txtEndDate = new JTextField("YYYY-MM-DD");
		txtEndDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtEndDate.getText().equals("YYYY-MM-DD")) {
					txtEndDate.setText("");
				}
			}
		});
		txtEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndDate.setColumns(10);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 20);
		gbc.anchor = GridBagConstraints.WEST;
		contentPane.add(txtEndDate, gbc);

		JButton btnFetchSales = new JButton("Fetch Sales");
		btnFetchSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSales();
			}
		});
		btnFetchSales.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFetchSales.setBackground(new Color(43, 43, 43));
		btnFetchSales.setForeground(Color.WHITE);
		btnFetchSales.setFocusPainted(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 0, 10, 20);
		gbc.anchor = GridBagConstraints.WEST;
		contentPane.add(btnFetchSales, gbc);

		pack();
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

	public void getSales() {
		String StartDate = txtStartDate.getText();
		String EndDate = txtEndDate.getText();
		objTableModel.setRowCount(0);
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMasterReport_DateWiseSales(?,?)}");
			stmt.setString(1, StartDate);
			stmt.setString(2, EndDate);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				objTableModel.addRow(new Object[]{rs.getString("TransactionDate"), rs.getDouble("Amount")});
			}
		} catch (Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "getSales");
		}
	}

	private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CustomTableCellRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (isSelected) {
				renderer.setBackground(new Color(51, 153, 255));
				renderer.setForeground(Color.WHITE);
			} else {
				renderer.setBackground(row % 2 == 0 ? new Color(69, 73, 74) : new Color(60, 63, 65));
				renderer.setForeground(Color.WHITE);
			}
			return renderer;
		}
	}

	private static class HeaderRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public HeaderRenderer() {
			setOpaque(true);
			setBorder(BorderFactory.createEmptyBorder()); // Remove borders
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setBackground(new Color(43, 43, 43));
			setForeground(Color.WHITE);
			setFont(new Font("Segoe UI", Font.BOLD, 16));
			return this;
		}
	}
}
