package BusinessLayer;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import ExceptionHandling.ExceptionMaster;



public class CategoryBusiness {
	
	String[] Categories = new String[] {};
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster objEx = new ExceptionMaster();
	
	public CategoryBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "CategoryMaster", "Constructor");
			
		}
	}
	
	public void InsertInCatMaster(String ctg) {
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_Insert(?)}");
			stmt.setString(1, ctg);
			stmt.execute();
			
			stmt.close();
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "CategoryMaster", "InsertInCatMaster");
			
		}
	}
	
	public void DeleteCatDB(String CatName) {
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_Delete(?)}");
			
			stmt.setString(1, CatName);
			
			stmt.execute();
			
			stmt.close();
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "DeleteCategory", "DeleteCatNameDB");
			
		}
		
		
	}
	
	public String[] populateComboBox_Delete() {
		
		String[] Category = new String[] {};
		int ArrLen = 0;
		int Pos = 0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_GetCategory}");
			ResultSet rs= stmt.executeQuery();
			
			while(rs.next()) {
				if(Pos == 0) {
					ArrLen = rs.getInt("RowCount"); 
					
					Category = new String[ArrLen];
				}
				
				Category[Pos] = rs.getString("CategoryName");
				Pos++;
				
			}
			
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "DeleteCategory", "populateComboBox");
			
		}
		
		return Category;
		
	}
	
	public Object[][] populateComboBox_Update() {
		
		Object[][] CtgDetails = new Object[][] {};
		
		 int ArrLen = 0;
		 
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_GetCategories}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			ResultSet rs = stmt.executeQuery();
			
			rs.last();
			ArrLen = rs.getRow();
			
			rs.beforeFirst();
			CtgDetails = new Object[ArrLen][2];
			
			int Row = 0;
			
			while(rs.next()) {
					
				CtgDetails[Row][0] = rs.getInt("CategoryID");
				CtgDetails[Row][1] = rs.getString("CategoryName");
				Row++;
				
			}

		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "CategoryBusiness", "populateComboBox_Update");
		}
		
		return CtgDetails;
	}
	
	
	public void UpdateCtgDB(int CtgID, String NewCtg) {
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_Update(?,?)}");
			stmt.setInt(1, CtgID);
			stmt.setString(2, NewCtg);
			
			stmt.execute();
			
			stmt.close();

		}
		catch(Exception ex) {
			
		}
		
		
		
	}
	
	
	

}
