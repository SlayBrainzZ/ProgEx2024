package UIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
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
import javax.swing.table.JTableHeader;

import ExceptionHandling.ExceptionMaster;

public class AccountMasterList extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String CONNECTION_URL = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	private Connection connection;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel messageLabel;
	private JScrollPane scrollPane;
	private ExceptionMaster exceptionHandler = new ExceptionMaster();

	public AccountMasterList() {
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
			exceptionHandler.InsertException(ex.getMessage(), "AccountMasterList", "Constructor");
		}

		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("City");
		tableModel.addColumn("Email ID");
		tableModel.addColumn("Phone Number");
		tableModel.addColumn("Balance Amount");

		table = new JTable(tableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.setGridColor(new Color(200, 200, 200));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());  // Remove borders
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		messageLabel = new JLabel("", SwingConstants.CENTER);
		messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	}

	private void setupLayout() {
		setTitle("Account Master List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 500));
		getContentPane().setBackground(new Color(200, 200, 200));

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(BorderFactory.createEmptyBorder());  // Remove borders
		setContentPane(contentPane);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(20, 20, 10, 20);
		constraints.anchor = GridBagConstraints.CENTER;
		contentPane.add(messageLabel, constraints);

		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(10, 20, 20, 20);
		contentPane.add(scrollPane, constraints);
	}

	private void loadData() {
		try {
			CallableStatement statement = connection.prepareCall("{call Proc_AccountMaster_Get}");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				tableModel.addRow(new Object[]{
						resultSet.getInt("AccountID"),
						resultSet.getString("Name"),
						resultSet.getString("City"),
						resultSet.getString("EmailID"),
						resultSet.getString("Phone_Number"),
						resultSet.getDouble("Balance_Amount")
				});
			}
		} catch (Exception e) {
			exceptionHandler.InsertException(e.getMessage(), "AccountMasterList", "loadData");
		}
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("TitlePane.background", Color.BLACK);
			UIManager.put("TitlePane.foreground", Color.WHITE);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
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

	public static void main(String[] args) {
		AccountMasterList frame = new AccountMasterList();
		frame.setVisible(true);
	}
}
