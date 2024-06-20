package UIModule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Customize the rendering here
        // Example: change background color based on some condition
        if (row % 2 == 0) {
            cellComponent.setBackground(Color.LIGHT_GRAY);
        } else {
            cellComponent.setBackground(Color.WHITE);
        }

        // Return the customized component
        return cellComponent;
    }
}
