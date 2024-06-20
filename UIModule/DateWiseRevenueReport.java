package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.SalesReportBusiness;

public class DateWiseRevenueReport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JLabel lblNewLabel;
	private DefaultTableModel objTableModel = new DefaultTableModel();
	private SalesReportBusiness objSales = new SalesReportBusiness();
	private Connection con;
	private String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateWiseRevenueReport frame = new DateWiseRevenueReport();
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
	public DateWiseRevenueReport() {
		initializeDatabaseConnection();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.setBackground(new Color(200, 200, 200));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		objTableModel.addColumn("Date From");
		objTableModel.addColumn("Date To");
		objTableModel.addColumn("Revenue");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		table = new JTable(objTableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(new Color(200, 200, 200));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		scrollPane.setViewportView(table);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(20, 20, 10, 20);
		contentPane.add(scrollPane, gbc_scrollPane);

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

		GridBagConstraints gbc_txtStartDate = new GridBagConstraints();
		gbc_txtStartDate.gridx = 0;
		gbc_txtStartDate.gridy = 1;
		gbc_txtStartDate.insets = new Insets(10, 20, 10, 20);
		gbc_txtStartDate.anchor = GridBagConstraints.WEST;
		contentPane.add(txtStartDate, gbc_txtStartDate);

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

		GridBagConstraints gbc_txtEndDate = new GridBagConstraints();
		gbc_txtEndDate.gridx = 0;
		gbc_txtEndDate.gridy = 2;
		gbc_txtEndDate.insets = new Insets(10, 20, 20, 20);
		gbc_txtEndDate.anchor = GridBagConstraints.WEST;
		contentPane.add(txtEndDate, gbc_txtEndDate);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startDate = txtStartDate.getText();
				String endDate = txtEndDate.getText();

				if (startDate.equals("YYYY-MM-DD") || endDate.equals("YYYY-MM-DD") || startDate.isEmpty() || endDate.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter the date in the correct format");
					return;
				}

				getRevenue(startDate, endDate);
			}
		});
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCalculate.setBackground(new Color(43, 43, 43));
		btnCalculate.setForeground(Color.WHITE);
		btnCalculate.setFocusPainted(false);
		btnCalculate.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.gridx = 0;
		gbc_btnCalculate.gridy = 3;
		gbc_btnCalculate.insets = new Insets(10, 20, 20, 20);
		gbc_btnCalculate.anchor = GridBagConstraints.CENTER;
		contentPane.add(btnCalculate, gbc_btnCalculate);

		/* lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DateWiseRevenueReport.class.getResource("/BackgroundImage/aqua BG.png")));
		lblNewLabel.setBounds(0, 0, 1545, 800);
		contentPane.add(lblNewLabel); */

		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}

	private void initializeDatabaseConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void getRevenue(String startDate, String endDate) {
		try {
			double amount = objSales.GetAmount(startDate, endDate);
			objTableModel.setRowCount(0);
			objTableModel.addRow(new Object[]{startDate, endDate, amount});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error fetching revenue: " + e.getMessage());
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
