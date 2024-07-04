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
import BusinessLayer.CustomerBusiness;
import BusinessLayer.OrderDetailsBusiness;

public class DeleteSales extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JLabel lblMsg;
	private JTable table;
	private DefaultTableModel tableModel;

	OrderDetailsBusiness objOrd = new OrderDetailsBusiness();
	CustomerBusiness objCus = new CustomerBusiness();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSales frame = new DeleteSales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error starting application: " + e.getMessage());
				}
			}
		});
	}

	public DeleteSales() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1545, 800); // Adjusted the size to match DeleteCustomer
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
		panel.setBackground(new Color(60, 63, 65));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints gbcPanelCenter = new GridBagConstraints();
		gbcPanelCenter.gridx = 0;
		gbcPanelCenter.gridy = 1;
		gbcPanelCenter.insets = new Insets(5, 5, 5, 5);
		gbcPanelCenter.anchor = GridBagConstraints.CENTER;

		JLabel lblDeleteSales = new JLabel("Delete Sales");
		lblDeleteSales.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteSales.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(lblDeleteSales, gbcPanel);

		tableModel = new DefaultTableModel(new Object[]{"OrderDetailID", "Product", "Quantity", "Customer Name", "Total Amount", "Order Date", "Status"}, 0);
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
		column.setPreferredWidth(150);
		column = table.getColumnModel().getColumn(6);
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

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDelete.setBackground(new Color(43, 43, 43));
		btnDelete.setForeground(Color.WHITE);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 2;
		gbcPanel.gridwidth = 2;
		gbcPanel.anchor = GridBagConstraints.CENTER;
		panel.add(btnDelete, gbcPanel);

		contentPane.add(panel, gbcPanelCenter);

		loadOrderDetails();
	}

	private void loadOrderDetails() {
        /* List<List<Object>> orders = objOrd.readOrderDetails(); // Use the appropriate method
        tableModel.setRowCount(0); // Clear existing rows
        for (List<Object> order : orders) {
            tableModel.addRow(new Object[]{order.get(0), order.get(1), order.get(2), order.get(3), order.get(4), order.get(5), order.get(6)});
        } */
	}

	public void Delete() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int orderDetailID = (int) tableModel.getValueAt(selectedRow, 0);

			// Assuming deleteOrderDetail method returns a success/failure message
			String out = objOrd.deleteOrderDetail(orderDetailID);

			if (out.equals("1")) {
				JOptionPane.showMessageDialog(this, "Sales successfully deleted");
				tableModel.removeRow(selectedRow);
			} else if (out.equals("-1")) {
				JOptionPane.showMessageDialog(this, "Please delete the customer's orders first.");
			} else {
				JOptionPane.showMessageDialog(this, "An error occurred. Please try again.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Please select a record to delete");
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
