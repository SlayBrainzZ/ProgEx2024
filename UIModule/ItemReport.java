package UIModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.ItemReportBusiness;

public class ItemReport extends JFrame {
	JComboBox<String> comboBox;
	DefaultTableModel objTableModel;
	JLabel lblMsg;

	private JPanel contentPane;
	private JTable table;

	ItemReportBusiness objItem = new ItemReportBusiness();
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemReport frame = new ItemReport();
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
	public ItemReport() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			// Handle database connection error
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		contentPane.setBackground(new Color(200, 200, 200)); // Set background color

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblMsg.setForeground(Color.WHITE); // Set text color
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.anchor = GridBagConstraints.CENTER;
		gbc_lblMsg.insets = new Insets(10, 0, 10, 0);
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 0;
		contentPane.add(lblMsg, gbc_lblMsg);

		objTableModel = new DefaultTableModel();
		table = new JTable(objTableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
		table.setGridColor(new Color(200, 200, 200));

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(69, 73, 74)); // Dark grey color
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 20, 20, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		contentPane.add(scrollPane, gbc_scrollPane);

		objTableModel.addColumn("Category");
		objTableModel.addColumn("Item");
		objTableModel.addColumn("Description");
		objTableModel.addColumn("SKU Code");

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setPreferredSize(new java.awt.Dimension(200, 30));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objTableModel.setRowCount(0);
				String Ctg = (String) comboBox.getSelectedItem();
				getItemDetailsDB(Ctg);
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(10, 20, 10, 20);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		gbc_comboBox.anchor = GridBagConstraints.CENTER;
		contentPane.add(comboBox, gbc_comboBox);

		getCategories();

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\devan\\Pictures\\BG 2.jpg")); // Replace with your image path
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		setLookAndFeel();
	}

	public void getCategories() {
		String[] Categories = objItem.getCategories();
		for (String category : Categories) {
			comboBox.addItem(category);
		}
	}

	public void getItemDetailsDB(String Ctg) {
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_ItemReport(?)}");
			stmt.setString(1, Ctg);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				objTableModel.addRow(new Object[]{Ctg, rs.getString("Item_Name"), rs.getString("Description"), rs.getString("SKU_Code")});
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
			setBorder(BorderFactory.createEmptyBorder());
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
