package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLayer.MonthlyRevReportBusiness;

public class MonthlyRevenueReport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBox;
	private DefaultTableModel objTableModel;

	MonthlyRevReportBusiness objReport = new MonthlyRevReportBusiness();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlyRevenueReport frame = new MonthlyRevenueReport();
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
	public MonthlyRevenueReport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(new Color(200, 200, 200)); // Set background color

		objTableModel = new DefaultTableModel();
		objTableModel.addColumn("Month");
		objTableModel.addColumn("Revenue");

		table = new JTable(objTableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
		table.setGridColor(new Color(200, 200, 200));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(69, 73, 74)); // Dark grey color
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(200, 200, 200));
		contentPane.add(panel, BorderLayout.SOUTH);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setPreferredSize(new Dimension(200, 30));
		panel.add(comboBox);

		populateComboBox();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String month = (String) comboBox.getSelectedItem();
				if (month != null) {
					getRevenue(month);
				}
			}
		});

		setLookAndFeel();
	}

	public void populateComboBox() {
		String[] months = objReport.getMonths();
		for (String month : months) {
			comboBox.addItem(month);
		}
	}

	public void getRevenue(String month) {
		double rev = objReport.getRevenue(month);
		objTableModel.setRowCount(0);
		objTableModel.addRow(new Object[]{month, rev});
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
