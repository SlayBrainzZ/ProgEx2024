package BusinessLayer;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JOptionPane;

import ExceptionHandling.ExceptionMaster;



public class SalesBusiness  {
	ExceptionMaster obj = new ExceptionMaster();
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public SalesBusiness() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "constructor");
			
		}
	}
	
							// DeleteSales //
	
	public String[] GetCusNamesCmbBox_Delete() {
		int Pos = 0;
		int ArrLength = 0;
		
		String[] CusNames = new String[] {};
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetNames}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Pos == 0) {
					ArrLength= rs.getInt("RowCount");
					CusNames = new String[ArrLength+1];
					
				}
				
				CusNames[Pos] = rs.getString("Name");
				Pos++;
				
			}

			stmt.close();
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetCusNamesCmbBox");
			
		}
		return CusNames;
				
	}
	
	public Integer[] getSalesID_Delete(String CusName) {
		int ArrLen = 0;
		int Pos = 0;
		
		Integer[] SalesID = new Integer[] {};
		boolean flag = false;
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetSalesID(?)}");
			stmt.setString(1, CusName);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				flag = true;
				if(Pos == 0) {
					ArrLen = rs.getInt("RowCount");
					SalesID = new Integer[ArrLen];
					
				}
				SalesID[Pos] = rs.getInt("SalesID");
				Pos++;
			}
			

		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "getSalesID_Delete");

		}
		return SalesID;
	
	}
	
	public Dictionary GetSalesDetails(int SalesID) {
		
		Dictionary SalesDetails = new Hashtable();
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_Get_SalesDetails_Items(?)}");
			stmt.setInt(1, SalesID);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				SalesDetails.put("Item", rs.getString("Item"));
				SalesDetails.put("Amount", rs.getDouble("Amount"));
				SalesDetails.put("Date", rs.getString("TransactionDate"));

			}
			
		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetSalesDetails");
			
		}
		
		return SalesDetails;
	}
	
	public void DeleteSales(String CusName) {
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_Delete(?)}");
			stmt.setString(1, CusName);
			
			stmt.execute();
			
			stmt.close();
			

		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "DeleteSales");
			
		}
		
		
		
	}
							// SalesMaster //
	
	public void InsertInDB(String CusName, String qtty, String Rate, String Date, String Item) {
		String out= "0";
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_Salesmaster_Insert(?,?,?,?,?)}");
			stmt.setString(1, CusName);
			stmt.setString(2, qtty);
			stmt.setString(3, Rate);
			stmt.setString(4, Date);
			stmt.setString(5, Item);
			
			stmt.execute();
			
			//GetCusNamesInCmbBox();
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "InsertInDB");
			
		}
		
	
	}
	
	public String[] GetItems_Insert() {
		int Pos = 0;
		int ArrLen = 0;
		String[] Items = new String[] {};
		
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetItem}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Pos== 0) {
					ArrLen = rs.getInt("RowCount");
					Items = new String[ArrLen];
				}
				Items[Pos] = rs.getString("Item_Name");
				Pos++;
			}
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetItems");
			
		}
		return Items;
		
	}
	
	public String[] GetCusNames_Insert() {
		int Pos = 0;
		int ArrLength = 0;
		String[] CusNames = new String[] {};
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetCusNames}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Pos == 0) {
					ArrLength= rs.getInt("RowCount");
					CusNames = new String[ArrLength];
					
				}
				
				CusNames[Pos] = rs.getString("Name");
				Pos++;
				
			}
			
			stmt.close();
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetCusNames_Insert");
			
		}
		return CusNames;
	}
	
								// UpdateSales //
	
	public String[] GetCusNames_Update() {
		int Index = 0;
		int ArrLen = 0;
		String[] CusNames = new String[] {};
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetNames}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Index == 0) {
					ArrLen = rs.getInt("RowCount");
					CusNames = new String[ArrLen];
				}
				
				CusNames[Index] = rs.getString("Name");
				Index++;
			}

		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetCusNames_Update");
			
		}
		return CusNames;
	}
	
	public Integer[] GetSalesID_Update(String CusName) {
		int ArrLen = 0;
		int Pos = 0;
		
		Integer[] SalesID = new Integer[] {};
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetSalesID(?)}");
			stmt.setString(1, CusName);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Pos == 0) {
					ArrLen = rs.getInt("RowCount");
					SalesID = new Integer[ArrLen];
					
				}
				SalesID[Pos] = rs.getInt("SalesID");
				Pos++;
			}
			
			
		}
		catch(Exception e) {
			obj.InsertException(e.getMessage(), "SalesBusiness", "GetSalesID_Update");
			
		}
		
		return SalesID;
	}
	
	public Dictionary GetItemDate_Update(int SalesID) {
		
		Dictionary ItemDate = new Hashtable();
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_Get_SalesDetails_Items(?)}");
			stmt.setInt(1, SalesID);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				ItemDate.put("Item", (rs.getString("Item")));
				ItemDate.put("Date", (rs.getString("TransactionDate")));
			
			}
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetItemDate_Update");
			
			
		}
		return ItemDate;
	}
	
	public int getItemID_Update(String Item) {
		int ID = 0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_GetItemID(?)}");
			stmt.setString(1, Item);
			
			ResultSet rs = stmt.executeQuery();
			
			
			rs.next();
			ID = rs.getInt("Item_ID");
			
			
		}
		catch(Exception e) {
			obj.InsertException(e.getMessage(), "SalesBusiness", "getItemID_Update");
			
		}
		
		
		return ID;

	}
	
	public Dictionary GetRate_Qtty(int SalesID) {
		
		Dictionary Rate_Qtty = new Hashtable();
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_TransactionMaster_GetRate_Qtty(?)}");
			stmt.setInt(1, SalesID);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Rate_Qtty.put("Rate", rs.getString("Rate"));
				Rate_Qtty.put("Qtty", rs.getString("Quantity"));
				
			}
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "GetRate_Qtty");
			
			
		}
		return Rate_Qtty;
	}
	
	public void UpdateSalesDB(int SalesID, int ItemID, String Date, int Qtty, double Rate) {
		String out = "-1";
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_Update(?,?,?,?,?)}");
			stmt.setInt(1, SalesID);
			stmt.setInt(2, ItemID);
			stmt.setString(3, Date);
			stmt.setInt(4, Qtty);
			stmt.setDouble(5, Rate);
			
			stmt.execute();
			stmt.close();
			
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesBusiness", "UpdateSalesDB");
			
		}
		
		
	}

	
}
