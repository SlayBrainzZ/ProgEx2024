package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BusinessLayer.CategoryBusiness;


public class UpdateCategory extends JFrame implements ItemListener, ActionListener {

	private JPanel contentPane;
	private JTextField txtNewCtg;
	private JComboBox<ComboBoxDetails> comboBox;
	private JLabel lblMsg;

	private CategoryBusiness objCat = new CategoryBusiness();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateCategory frame = new UpdateCategory();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateCategory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		panel.setBounds(567, 223, 425, 235);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNewCtg = new JTextField();
		txtNewCtg.setBackground(new Color(69, 73, 74));
		txtNewCtg.setForeground(Color.WHITE);
		txtNewCtg.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtNewCtg.setColumns(10);
		txtNewCtg.setBounds(175, 112, 198, 28);
		panel.add(txtNewCtg);

		JLabel lblNewLabel_1_1 = new JLabel("New category");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(33, 110, 120, 32);
		panel.add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(43, 43, 43));
		btnNewButton.setBounds(118, 170, 190, 36);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1_2 = new JLabel("Category name");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(33, 68, 126, 32);
		panel.add(lblNewLabel_1_2);

		JLabel lblUpdateCategory = new JLabel("Update category");
		lblUpdateCategory.setForeground(Color.WHITE);
		lblUpdateCategory.setFont(new Font("Calibri", Font.BOLD, 26));
		lblUpdateCategory.setBounds(120, 10, 198, 38);
		panel.add(lblUpdateCategory);

		comboBox = new JComboBox<>();
		comboBox.addItemListener(this);
		comboBox.setBackground(new Color(69, 73, 74));
		comboBox.setForeground(Color.WHITE);
		comboBox.setBounds(175, 69, 198, 28);
		panel.add(comboBox);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 20));
		lblMsg.setBounds(625, 142, 663, 54);
		contentPane.add(lblMsg);



		populateComboBox();
	}

	public void actionPerformed(ActionEvent A) {
		Update();
		resetComboBox();
		populateComboBox();
	}

	public void itemStateChanged(ItemEvent item) {
		JComboBox<?> combo = (JComboBox<?>) item.getSource();
		ComboBoxDetails cmbBox = (ComboBoxDetails) combo.getSelectedItem();

		if (cmbBox != null) {

			String Ctg = cmbBox.getCtg();
			txtNewCtg.setText(Ctg);
		}
	}

	public void resetComboBox() {
		comboBox.removeAllItems();
	}


	public void populateComboBox() {
		/* List<List<Object>> categories = objCat.readCategories();
		for (List<Object> category : categories) {
			int id = (int) category.get(0);
			String name = (String) category.get(1);
			comboBox.addItem(new ComboBoxDetails(id, name));
		} */
	}

	public void Update() {
		String newName = txtNewCtg.getText();
		if (comboBox != null) {
			ComboBoxDetails selectedCategory = (ComboBoxDetails) comboBox.getSelectedItem();
			if (selectedCategory != null) {
				String ctg = selectedCategory.ctg;
				objCat.updateCategory(ctg, newName);
				JOptionPane.showMessageDialog(null, "Category updated successfully");
				txtNewCtg.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "No category selected");
			}
		}
	}
}

class ComboBoxDetails {
	String ctg;

	public ComboBoxDetails(int ID, String Ctg) {

		this.ctg = Ctg;
	}


	public String getCtg() {
		return ctg;
	}

	@Override
	public String toString() {
		return ctg;
	}
}
