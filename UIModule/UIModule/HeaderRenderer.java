package UIModule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class HeaderRenderer extends DefaultTableCellRenderer {

    public HeaderRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true); // Ensure background color is painted
        setBackground(new Color(43, 43, 43)); // Example background color
        setForeground(Color.WHITE); // Example foreground color
        setFont(new Font("Calibri", Font.BOLD, 16)); // Example font
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Example: set specific rendering for headers here

        return this;
    }
}
