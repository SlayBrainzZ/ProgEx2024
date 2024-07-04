package UIModule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;

import BusinessLayer.UserBusiness;


public class UserList extends JFrame {


	UserBusiness objUser = new UserBusiness();


	DefaultTableModel objTableModel;

	private JPanel contentPane;
	private JTable table;
	private JLabel messageLabel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList frame = new UserList();
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
	public UserList() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 600); // Increased width for better visibility
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 500));
		setTitle("User List");

		contentPane = new JPanel(new GridBagLayout());
		contentPane.setBackground(Color.white);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		messageLabel = new JLabel("", SwingConstants.CENTER);
		messageLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		messageLabel.setForeground(Color.RED);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(20, 20, 10, 20);
		constraints.anchor = GridBagConstraints.CENTER;
		contentPane.add(messageLabel, constraints);

		objTableModel = new DefaultTableModel();
		objTableModel.addColumn("Name");
		objTableModel.addColumn("Username");
		objTableModel.addColumn("Email ID");
		objTableModel.addColumn("Phone Number");
		objTableModel.addColumn("Address");

		table = new JTable(objTableModel);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(43, 43, 43));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.setRowHeight(35); // Increased row height for better visibility

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(60, 63, 65));

		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(10, 20, 20, 20);
		contentPane.add(scrollPane, constraints);

		// loadUserData(); // Load data into the table

		pack(); // Adjust frame size
	}

	private void loadUserData() {
		/* List<List<Object>> users = objUser.readUsers();
		objTableModel.setRowCount(0); // Clear existing rows

		for (List<Object> user : users) {
			// Add user data to table model (excluding password)
			objTableModel.addRow(new Object[]{
					user.get(0), // Name
					user.get(1), // Username
					user.get(3), // Email ID
					user.get(4), // Phone Number
					user.get(5)  // Address
			});
		} */
	}

	private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CustomTableCellRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
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
			setFont(new Font("Calibri", Font.BOLD, 18));
			return this;
		}
	}
}
