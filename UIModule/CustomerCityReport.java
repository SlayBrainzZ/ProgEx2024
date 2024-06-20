package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.CusCityReportBusiness;

public class CustomerCityReport extends JFrame {

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	JLabel lblMsg;
	CusCityReportBusiness objReport = new CusCityReportBusiness();

	JComboBox<String> comboBox;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel objTableModel = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCityReport frame = new CustomerCityReport();
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
	public CustomerCityReport() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		//contentPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.setBackground(new Color(200, 200, 200));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		objTableModel.addColumn("Customer Name");
		objTableModel.addColumn("City");

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

		JScrollPane scrollPane = new JScrollPane(table);
		//scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(20, 20, 10, 20);
		contentPane.add(scrollPane, gbc_scrollPane);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setPreferredSize(new Dimension(200, 30)); // Set the preferred size for the combo box
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objTableModel.setRowCount(0);
				getCusNames();
			}
		});

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		gbc_comboBox.insets = new Insets(10, 20, 20, 20);
		gbc_comboBox.anchor = GridBagConstraints.CENTER;
		contentPane.add(comboBox, gbc_comboBox);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 2;
		gbc_lblMsg.insets = new Insets(10, 20, 20, 20);
		contentPane.add(lblMsg, gbc_lblMsg);

		populateComboBox();
		setLookAndFeel();
	}

	public void populateComboBox() {
		String[] CusNames = objReport.getCities();
		for (String city : CusNames) {
			comboBox.addItem(city);
		}
	}

	public void getCusNames() {
		String City = (String) comboBox.getSelectedItem();
		objTableModel.setRowCount(0);

		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_CityReport(?)}");
			stmt.setString(1, City);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				objTableModel.addRow(new Object[] { rs.getString("Name"), City });
			}

		} catch (Exception ex) {
			lblMsg.setText(ex.getMessage());
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
			//setBorder(BorderFactory.createEmptyBorder()); // Remove borders
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
