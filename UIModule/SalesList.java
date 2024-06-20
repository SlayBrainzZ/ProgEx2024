package UIModule;

import java.awt.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ExceptionHandling.ExceptionMaster;

public class SalesList extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String CONNECTION_URL = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	private Connection connection;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel messageLabel;
	private JScrollPane scrollPane;
	private ExceptionMaster exceptionHandler = new ExceptionMaster();

	public SalesList() {
		initializeComponents();
		setupLayout();
		loadData();
		setLookAndFeel();
	}

	private void initializeComponents() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(CONNECTION_URL);
		} catch (Exception ex) {
			exceptionHandler.InsertException(ex.getMessage(), "SalesList", "Constructor");
		}

		tableModel = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class; // Set column class as String to ensure proper rendering
			}
		};
		tableModel.addColumn("Sales ID");
		tableModel.addColumn("Customer Name");
		tableModel.addColumn("Amount");
		tableModel.addColumn("Date");
		tableModel.addColumn("Item bought");
		tableModel.addColumn("Quantity");
		tableModel.addColumn("Rate");

		table = new JTable(tableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Set Segoe UI font and bold
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.setGridColor(new Color(200, 200, 200));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		messageLabel = new JLabel("", SwingConstants.CENTER);
		messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Set Segoe UI font and bold
		messageLabel.setForeground(Color.WHITE);
	}

	private void setupLayout() {
		setTitle("Sales List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 500));
		getContentPane().setBackground(new Color(200, 200, 200));

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);

		scrollPane.setBounds(50, 100, 900, 400);
		contentPane.add(scrollPane);

		messageLabel.setBounds(50, 50, 900, 30);
		contentPane.add(messageLabel);

		JLabel titleLabel = new JLabel("Sales List");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Set Segoe UI font and bold
		titleLabel.setBounds(450, 20, 120, 30);
		contentPane.add(titleLabel);
	}

	private void loadData() {
		try {
			CallableStatement statement = connection.prepareCall("{call Proc_SalesMaster_Details}");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				tableModel.addRow(new Object[]{
						resultSet.getInt("SalesID"),
						resultSet.getString("Name"),
						resultSet.getDouble("Amount"),
						resultSet.getString("TransactionDate"),
						resultSet.getString("Item_Name"),
						resultSet.getInt("Quantity"),
						resultSet.getDouble("Rate")
				});
			}
		} catch (Exception e) {
			exceptionHandler.InsertException(e.getMessage(), "SalesList", "loadData");
		}
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CustomTableCellRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (isSelected) {
				setBackground(new Color(51, 153, 255));
				setForeground(Color.WHITE);
			} else {
				setBackground(row % 2 == 0 ? new Color(69, 73, 74) : new Color(60, 63, 65));
				setForeground(Color.WHITE);
			}

			return this;
		}
	}

	private static class HeaderRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public HeaderRenderer() {
			setOpaque(true);
			setHorizontalAlignment(SwingConstants.CENTER);
			setBackground(new Color(43, 43, 43));
			setForeground(Color.WHITE);
			setFont(new Font("Segoe UI", Font.BOLD, 16)); // Set Segoe UI font and bold
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			return this;
		}
	}

	public static void main(String[] args) {
		SalesList frame = new SalesList();
		frame.setVisible(true);
	}
}
