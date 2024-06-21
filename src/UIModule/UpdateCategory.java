package UIModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;

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
import CommonInterface.CRUDInterface;

public class UpdateCategory extends JFrame implements CRUDInterface, ItemListener, ActionListener {
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;

	CategoryBusiness objCat = new CategoryBusiness();

	JLabel lblMsg;
	private JPanel contentPane;
	private JTextField txtNewCtg;
	JComboBox<ComboBoxDetails> comboBox;
	String[] Categories = new String[] {};

	int CtgID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCategory frame = new UpdateCategory();
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
		//panel.setOpaque(false);
		//panel.setBorder(new LineBorder(new Color(153, 51, 255)));

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
		//comboBox.setBorder(new LineBorder(new Color(169, 169, 169)));
		comboBox.setBounds(175, 69, 198, 28);
		panel.add(comboBox);

		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Dubai", Font.BOLD, 20));
		lblMsg.setBounds(625, 142, 663, 54);
		contentPane.add(lblMsg);

		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon("C:\\Users\\devan\\Downloads\\jbg2 (1).jpg"));
		lblBG.setBounds(0, 0, 1545, 870);
		contentPane.add(lblBG);

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception ex) {
			lblMsg.setText(ex.getMessage());
		}

		populateComboBox();
	}

	public void actionPerformed(ActionEvent A) {
		Update();
		resetComboBox();
		populateComboBox();
	}

	public void itemStateChanged(ItemEvent item) {
		JComboBox combo = (JComboBox) item.getSource();

		ComboBoxDetails cmbBox = (ComboBoxDetails) combo.getSelectedItem();

		if (cmbBox != null) {
			CtgID = cmbBox.getID();

			String Ctg = cmbBox.getCtg();
			txtNewCtg.setText(Ctg);
		}
	}

	public void populateComboBox() {
		Object[][] CtgDetails = objCat.populateComboBox_Update();

		int Rows = CtgDetails.length;
		for (int i = 0; i < Rows; i++) {
			comboBox.addItem(new ComboBoxDetails(Integer.parseInt(CtgDetails[i][0].toString()), CtgDetails[i][1].toString()));
		}
	}

	public void resetComboBox() {
		comboBox.removeAllItems();
	}

	public void Insert() {
	}

	public void Delete() {
	}

	public void Read() {
	}

	public void Update() {
		String NewName = txtNewCtg.getText();
		String Ctg = "";
		if (comboBox != null) {
			Ctg = comboBox.getSelectedItem().toString();
		}

		objCat.UpdateCtgDB(CtgID, NewName);
		populateComboBox();
		JOptionPane.showMessageDialog(null, "Category updated successfully");

		txtNewCtg.setText("");
	}
}

class ComboBoxDetails {
	int CtgID;
	String Ctg;

	public ComboBoxDetails(int ID, String Ctg) {
		CtgID = ID;
		this.Ctg = Ctg;
	}

	public void setID(int ID) {
		CtgID = ID;
	}

	public int getID() {
		return CtgID;
	}

	public void setCtg(String Cat) {
		Ctg = Cat;
	}

	public String getCtg() {
		return Ctg;
	}

	@Override
	public String toString() {
		return Ctg;
	}
}
