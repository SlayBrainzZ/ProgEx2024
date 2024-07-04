package UIModule;

import java.awt.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import BusinessLayer.CategoryBusiness;



public class DeleteCategory extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JLabel lblMsg;

	private CategoryBusiness objCat = new CategoryBusiness();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DeleteCategory frame = new DeleteCategory();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DeleteCategory() {
		initialize();
		populateComboBox();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400); // Adjusted size to 600x400 pixels
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 300)); // Adjusted panel size to 400x300 pixels

		lblMsg = new JLabel("");
		lblMsg.setBounds(50, 20, 300, 30);
		panel.add(lblMsg);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 20)); // Matched font to CategoryMaster

		JLabel lblDeleteCategory = new JLabel("Delete Category");
		lblDeleteCategory.setForeground(Color.BLACK); // Matched color to CategoryMaster
		lblDeleteCategory.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDeleteCategory.setBounds(120, 50, 250, 30); // Adjusted position
		panel.add(lblDeleteCategory);

		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setForeground(Color.BLACK); // Matched color to CategoryMaster
		lblCategoryName.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCategoryName.setBounds(50, 100, 150, 30); // Adjusted position
		panel.add(lblCategoryName);

		comboBox = new JComboBox<>();
		comboBox.setBounds(210, 100, 150, 30); // Adjusted position
		comboBox.setPreferredSize(new Dimension(150, 28));
		panel.add(comboBox);

		JButton btnSubmit = new JButton("Delete");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		btnSubmit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(43, 43, 43));
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
		btnSubmit.setBounds(120, 160, 150, 40); // Adjusted position
		panel.add(btnSubmit);

		// Center the panel within the contentPane
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(panel, gbc);

		setMinimumSize(new Dimension(1300, 700));
	}


	public void populateComboBox() {
		/*List<List<Object>> categories = objCat.readCategories();
		for (List<Object> category : categories) {
			comboBox.addItem((String) category.get(1));
		}*/
	}

	public void Delete() {
		String CatName = (String) comboBox.getSelectedItem();
		String out = objCat.deleteCategories(CatName);

		if (out.equals("-1")) {
			JOptionPane.showMessageDialog(null, "Please delete the products under the category first");
		} else {
			comboBox.removeItem(CatName);
			JOptionPane.showMessageDialog(null, "Category deleted");
		}
	}
}
