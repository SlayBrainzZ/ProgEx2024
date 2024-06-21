package UIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.CitySalesReportBusiness;

public class CitySalesReport extends JFrame {
	DefaultTableModel objTableModel = new DefaultTableModel();
	JComboBox<String> comboBox;
	JLabel lblMsg;

	private JPanel contentPane;
	private JTable table;

	CitySalesReportBusiness objReport = new CitySalesReportBusiness();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitySalesReport frame = new CitySalesReport();
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
	public CitySalesReport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.setBackground(new Color(200, 200, 200));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		objTableModel.addColumn("City");
		objTableModel.addColumn("No. of Customers");
		objTableModel.addColumn("Revenue");

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
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
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
		comboBox.setPreferredSize(new java.awt.Dimension(200, 30)); // Set the preferred size for the combo box
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objTableModel.setRowCount(0);

				String City = (String) comboBox.getSelectedItem();

				int totalCus = getCustomers(City);
				double Rev = getRevenue(City);

				objTableModel.insertRow(0, new Object[]{City, totalCus, Rev});
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

        /* JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(3, 2, 1545, 800);
        lblNewLabel.setIcon(new ImageIcon(CitySalesReport.class.getResource("/BackgroundImage/aqua BG.png")));
        contentPane.add(lblNewLabel); */

		populateComboBox();
		setLookAndFeel();
	}

	public void populateComboBox() {
		String[] Cities = objReport.getCities();

		for (String city : Cities) {
			comboBox.addItem(city);
		}
	}

	public double getRevenue(String City) {
		return objReport.getRevenue(City);
	}

	public int getCustomers(String City) {
		return objReport.getTotalCus(City);
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
}
