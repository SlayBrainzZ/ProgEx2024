package UIModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import BusinessLayer.OrderDetailsBusiness;

public class UpdateSalesMaster extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblMsg;

	private OrderDetailsBusiness objOrd = new OrderDetailsBusiness();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSalesMaster frame = new UpdateSalesMaster();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UpdateSalesMaster() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1545, 800); // Adjusted the size to match UpdateCustomer
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(0, 0, 5, 0);
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;
		gbcContent.anchor = GridBagConstraints.CENTER;

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 22));
		contentPane.add(lblMsg, gbcContent);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(43, 43, 43));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblUpdateSales = new JLabel("Update Sales");
		lblUpdateSales.setFont(new Font("Calibri", Font.BOLD, 26));
		lblUpdateSales.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblUpdateSales, gbcPanel);

		tableModel = new DefaultTableModel(new Object[]{"Order ID", "Product", "Quantity", "Customer", "Order Date", "Status"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Allow editing for all columns except Order ID
				return column != 0;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Integer.class;
				} else if (columnIndex == 2) {
					return Integer.class;
				} else {
					return String.class;
				}
			}
		};
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
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

		// Set preferred width for each column
		TableColumn column;
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(200);
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(100);
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(200);
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(150);
		column = table.getColumnModel().getColumn(5);
		column.setPreferredWidth(100);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1400, 400)); // Adjusted preferred size
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.gridwidth = 2;
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.weightx = 1.0;
		gbcPanel.weighty = 1.0;
		panel.add(scrollPane, gbcPanel);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedRow();
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 20));
		btnUpdate.setBackground(new Color(43, 43, 43));
		btnUpdate.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnUpdate, gbcPanel);

		contentPane.add(panel, gbcPanelCenter);

		loadOrderDetails();
	}

	private void loadOrderDetails() {
        /*List<List<Object>> orders = objOrd.getDetailsToUpdate(); // Use the getDetailsToUpdate method
        tableModel.setRowCount(0); // Clear existing rows
        for (List<Object> order : orders) {
            tableModel.addRow(new Object[]{order.get(0), order.get(1), order.get(2), order.get(3), order.get(4), order.get(5)});
        }*/
	}

	private void updateSelectedRow() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int orderId = (int) tableModel.getValueAt(selectedRow, 0);
			String product = tableModel.getValueAt(selectedRow, 1).toString();
			int quantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
			String customer = tableModel.getValueAt(selectedRow, 3).toString();
			String orderDate = tableModel.getValueAt(selectedRow, 4).toString();
			String status = tableModel.getValueAt(selectedRow, 5).toString();

			String updateResult = objOrd.updateOrderDetails(orderId, product, quantity, customer, orderDate, status);

			if (updateResult.equals("1")) {
				lblMsg.setForeground(Color.GREEN);
				JOptionPane.showMessageDialog(null, "Update successful.");

			} else {
				lblMsg.setForeground(Color.RED);
				JOptionPane.showMessageDialog(null, "Failed to update order details.");

			}

			// Reload order details to reflect changes
			loadOrderDetails();
		} else {
			lblMsg.setForeground(Color.RED);
			JOptionPane.showMessageDialog(this, "Please select a row to update.");
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
