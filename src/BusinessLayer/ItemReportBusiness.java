package BusinessLayer;

import java.awt.BorderLayout;

import java.util.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ExceptionHandling.ExceptionMaster;


import javax.swing.JLabel;
import java.awt.Font;

public class ItemReportBusiness extends JFrame {
	JLabel lblNewLabel;
	ExceptionMaster obj = new ExceptionMaster();

	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemReportBusiness frame = new ItemReportBusiness();
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
	public ItemReportBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemReportBusiness", "Constructor");
			
		}
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(115, 64, 237, 51);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	public String[] getCategories() {
		String[] Categories = new String[] {};
		int Len = 0;
		int Count = 0;
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_Read}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(Count==0) {
					Len = rs.getInt("RowCount");
					Categories= new String[Len];
				}
				
				Categories[Count] = rs.getString("CategoryName");
				Count++;
			}

			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemReportBusiness", "getCategories");
		}
		
		return Categories;
		
	}
	
	public Dictionary getItemDetails(String Category) {
		Dictionary ItemDetails = new Hashtable();
		
		try {
			
			
			
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_ItemReport(?)}");
			stmt.setString(1, Category);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				
				
				obj.InsertException(rs.getString("Item_Name"), "ItemReportBusiness", "getItemDetails");
				
				ItemDetails.put("Item", rs.getString("Item_Name"));
				ItemDetails.put("SKU", rs.getString("SKU_Code"));
				ItemDetails.put("Desc", rs.getString("Description"));
				
			}
			
			
			
		}
		catch(Exception ex) { 
			obj.InsertException(ex.getMessage(), "ItemReportBusiness", "getItemDetails");
		}
		
		
		return ItemDetails;
	}
	
}
