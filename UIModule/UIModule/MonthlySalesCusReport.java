package UIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import BusinessLayer.ReportsBusiness;

public class MonthlySalesCusReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel messageLabel;
	private JScrollPane scrollPane;
	ReportsBusiness objReport = new ReportsBusiness();

	public MonthlySalesCusReport() {
		initializeComponents();
		setupLayout();
		loadReports(); // Load data into the table
	}

	private void initializeComponents() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Customer");
		tableModel.addColumn("Month");
		tableModel.addColumn("Total Sales");

		table = new JTable(tableModel);
		table.setRowHeight(35);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
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
		messageLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		messageLabel.setForeground(Color.RED);
	}

	private void setupLayout() {
		setTitle("Monthly Sales Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 500));
		getContentPane().setBackground(new Color(200, 200, 200));

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBackground(Color.white);
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

	private void loadReports() {
		/* List<List<Object>> reports = objReport.getMonthCusReports();
		for (List<Object> report : reports) {
			tableModel.addRow(new Object[]{report.get(0), report.get(1), report.get(2)});
		} */
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
			setFont(new Font("Calibri", Font.BOLD, 18));
			return this;
		}
	}

	public static void main(String[] args) {
		MonthlySalesCusReport frame = new MonthlySalesCusReport();
		frame.setVisible(true);
	}
}
