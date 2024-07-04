package UIModule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import BusinessLayer.CustomerBusiness;

public class CustomerList extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel objTableModel;
    private CustomerBusiness objCus = new CustomerBusiness();

    public CustomerList() {
        initializeComponents();
        loadData();
        setLookAndFeel();
        setupLayout();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeComponents() {
        objTableModel = new DefaultTableModel();
        objTableModel.addColumn("Customer Name");
        objTableModel.addColumn("Email");
        objTableModel.addColumn("Address");
        objTableModel.addColumn("Phone");
        objTableModel.addColumn("City");
        objTableModel.addColumn("Orders Placed");

        JTable table = new JTable(objTableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Tahoma", Font.BOLD, 18));
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        table.setGridColor(new Color(200, 200, 200));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.getTableHeader().setBackground(new Color(43, 43, 43));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Customer Name
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Email
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Address
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Phone
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // City
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Orders Placed

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(60, 63, 65));

        JLabel lblItems = new JLabel("Customer List", SwingConstants.CENTER);
        lblItems.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblItems.setForeground(new Color(43, 43, 43));

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(200, 200, 200));
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 20, 10, 20);
        contentPane.add(lblItems, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 20, 20);
        contentPane.add(scrollPane, gbc);

        setContentPane(contentPane);
    }

    private void loadData() {
        /* List<List<Object>> details = objCus.readCusDetails();
        for (List<Object> cus : details) {
            objTableModel.addRow(cus.toArray());
        } */
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void setupLayout() {
        setTitle("Customer List");
        setSize(1100, 700); // frame
        setMinimumSize(new Dimension(1300, 700));
        getContentPane().setBackground(Color.white);
    }

    private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        public CustomTableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                setBackground(new Color(51, 153, 255));
                setForeground(Color.WHITE);
            } else {
                setBackground(row % 2 == 0 ? new Color(69, 73, 74) : new Color(60, 63, 65));
                setForeground(Color.WHITE);
            }

            return this;
        }
    }

    private static class HeaderRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        public HeaderRenderer() {
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder());
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(new Color(43, 43, 43));
            setForeground(Color.WHITE);
            setFont(new Font("Calibri", Font.BOLD, 18));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CustomerList frame = new CustomerList();
            frame.setVisible(true);
        });
    }
}
