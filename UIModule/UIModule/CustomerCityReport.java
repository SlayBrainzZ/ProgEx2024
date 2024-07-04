package UIModule;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import BusinessLayer.ReportsBusiness;

public class CustomerCityReport extends JFrame {

	JLabel lblMsg;
	ReportsBusiness objReport = new ReportsBusiness();
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel objTableModel = new DefaultTableModel();
	private JTextField txtCityFilter;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		objTableModel.addColumn("Customer Name");
		objTableModel.addColumn("City");
		objTableModel.addColumn("Orders Placed");

		table = new JTable(objTableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(new Color(200, 200, 200));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(20, 20, 10, 20);
		contentPane.add(scrollPane, gbc_scrollPane);

		JPanel filterPanel = new JPanel();
		filterPanel.setBackground(new Color(200, 200, 200));
		GridBagConstraints gbc_filterPanel = new GridBagConstraints();
		gbc_filterPanel.gridx = 0;
		gbc_filterPanel.gridy = 1;
		gbc_filterPanel.insets = new Insets(10, 20, 10, 20);
		contentPane.add(filterPanel, gbc_filterPanel);
		filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 20));
		filterPanel.add(lblCity);

		txtCityFilter = new JTextField();
		txtCityFilter.setFont(new Font("Tahoma", Font.BOLD, 20));
		filterPanel.add(txtCityFilter);
		txtCityFilter.setColumns(20);

		JButton btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setBackground(new Color(43, 43, 43));
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByCity();
			}
		});
		filterPanel.add(btnFilter);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.gridx = 0;
		gbc_lblMsg.gridy = 2;
		gbc_lblMsg.insets = new Insets(10, 20, 20, 20);
		contentPane.add(lblMsg, gbc_lblMsg);

		// loadReportData();

		setLookAndFeel();
	}

	private void loadReportData() {
		/* List<List<Object>> reportData = objReport.cusCityReport();
		for (List<Object> row : reportData) {
			objTableModel.addRow(new Object[]{row.get(0), row.get(1), row.get(2)});
		} */
	}

	private void filterTableByCity() {
		/* String cityFilter = txtCityFilter.getText().trim();
		if (cityFilter.isEmpty()) {
			lblMsg.setText("Please enter a city name.");
			return;
		}

		List<List<Object>> reportData = objReport.cusCityReport();
		List<List<Object>> filteredData = reportData.stream()
				.filter(row -> row.get(1).toString().equalsIgnoreCase(cityFilter))
				.collect(Collectors.toList());

		objTableModel.setRowCount(0); // Clear existing rows

		if (filteredData.isEmpty()) {
			lblMsg.setText("No records found for the city: " + cityFilter);
		} else {
			for (List<Object> row : filteredData) {
				objTableModel.addRow(new Object[]{row.get(0), row.get(1), row.get(2)});
			}
			lblMsg.setText(filteredData.size() + " record(s) found.");
		} */
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
