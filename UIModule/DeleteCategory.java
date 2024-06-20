package UIModule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.ImageIcon;

import BusinessLayer.CategoryBusiness;
import CommonInterface.CRUDInterface;
import ExceptionHandling.ExceptionMaster;

public class DeleteCategory extends JFrame implements CRUDInterface {
	private JPanel contentPane;
	String[] Category = new String[] {};
	JComboBox<String> comboBox;
	JLabel lblMsg;

	CategoryBusiness objCat = new CategoryBusiness();
	ExceptionMaster obj = new ExceptionMaster();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCategory frame = new DeleteCategory();
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
	public DeleteCategory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 60, 1545, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 20));
		lblMsg.setBounds(538, 206, 512, 36);
		contentPane.add(lblMsg);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(169, 169, 169)));
		panel.setBackground(new Color(60, 63, 65));
		panel.setBounds(538, 266, 391, 237);
		contentPane.add(panel);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblDeleteCategory = new JLabel("Delete Category");
		lblDeleteCategory.setForeground(Color.WHITE);
		lblDeleteCategory.setFont(new Font("Calibri", Font.BOLD, 26));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(lblDeleteCategory, gbc);

		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setForeground(Color.WHITE);
		lblCategoryName.setFont(new Font("Calibri", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblCategoryName, gbc);

		comboBox = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		comboBox.setPreferredSize(new java.awt.Dimension(150, 28));
		panel.add(comboBox, gbc);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		btnSubmit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(43, 43, 43));
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(btnSubmit, gbc);

		populateComboBox();
	}

	public void Insert() {
	}

	public void Update() {
	}

	public void Read() {
	}

	public void Delete() {
		String CatName = (String) comboBox.getSelectedItem();
		objCat.DeleteCatDB(CatName);
		comboBox.removeItem(CatName);
		JOptionPane.showMessageDialog(null, "Category successfully deleted");
	}

	public void populateComboBox() {
		Category = objCat.populateComboBox_Delete();
		for (String cat : Category) {
			comboBox.addItem(cat);
		}
	}
}
