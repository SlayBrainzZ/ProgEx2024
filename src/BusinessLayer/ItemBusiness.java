package BusinessLayer;
import java.util.*;

import ExceptionHandling.ExceptionMaster;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;



public class ItemBusiness {
	
	ExceptionMaster obj= new ExceptionMaster();
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public ItemBusiness() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "Constructor");
			
		}
		
	}
	
							//  ---  DeleteItem --- //
	
	public String[] populateComboBox_Delete() {
		int Pos = 0;
		int ArrLen = 0;
		String[] Items = new String[] {};

		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_GetName}");
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
			obj.InsertException(ex.getMessage(), "ItemBusiness", "populateComboBox_Delete");
			
		}
		
		return Items;
	}
	
	// Used in UpdateItem and DeleteItem 
	
	public Dictionary GetItemDetails(String ItemName) {
		
		Dictionary ItemDetails = new Hashtable();
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_GetData(?)}");
			stmt.setString(1, ItemName);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ItemDetails.put("SKU", rs.getString("SKU_Code"));
				ItemDetails.put("Desc", rs.getString("Description"));
				
				
			}
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "GetItemDetails_Delete");
			
		}
		
		return ItemDetails;
	}
	
	public void DeleteFromDB(String ItemName) {
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_Delete(?)}");
			
			stmt.setString(1, ItemName);
			
			stmt.execute();
			
			stmt.close();
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "DeleteFromDB");
			
		}
		
		
	}
									//  ---  ItemMasterNew (Insert) --- //
	
	public String DBInsert(String Item, String SKU, String Desc, String CatName) {
		String out = "-1";
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_Insert(?,?,?,?)}");
			
			stmt.setString(1, Item);
			stmt.setString(2, SKU);
			stmt.setString(3, Desc);
			stmt.setString(4, CatName);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			out = rs.getString("out");
			stmt.close();
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "DBInsert");
		}
		
		return out;
	}
	
	public String[] GetCategory_Insert() {
		int Pos = 0;
		int Index = 0;
		String[] Category = new String[] {};
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_GetCategory()}");
			ResultSet rs = stmt.executeQuery();
			
		
			while(rs.next()) {
				if(Pos==0) {
					Index = rs.getInt("RowCount");
					Category = new String[Index];
				}

				Category[Pos] = rs.getString("CategoryName");
				Pos++;
				
			}
			
			stmt.close();

		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "GetCategory_Insert");
		}
		
		return Category;
	}
	
						/// UpdateItemMaster ///
	
	public String[]  populateCombobox_Update() {
		
		String[] Items = new String[] {};
		int ArrLen = 0;
		int Index=0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_Display}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Index == 0) {
					ArrLen = rs.getInt("RowCount");
					Items = new String[ArrLen];
				}
				
				Items[Index] = rs.getString("Item_Name");
				Index++;
				
			}
			
			
		}
		catch(Exception e) {
			obj.InsertException(e.getMessage(), "ItemBusiness", "populateCombobox_Update");
		}
		return Items;
	}
	
	public void UpdateItem(String OldName, String NewName, String SKU, String Desc) {
		
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_Update(?,?,?,?)}");
			stmt.setString(1, OldName);
			stmt.setString(2, NewName);
			stmt.setString(3, SKU);
			stmt.setString(4, Desc);
			
			stmt.execute();
		
			stmt.close();
			
			//populateCombobox();
			
			
		}
		
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "UpdateItem");
		}
		
		
	}
	
	public String[][] getItemDetails(String ItemName) {
		String[][] ItemDetails = new String[1][2];
		
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_ItemMaster_GetData(?)}");
			stmt.setString(1, ItemName);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				for(int r=0;r<1;r++) {
					for(int c = 0;c<2;c++) {
						
						if(c==0) {
							ItemDetails[r][c] = rs.getString("SKU_Code");
						}
						else {
							ItemDetails[r][c] = rs.getString("Description");
						}
						
					}
				}

			}
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "ItemBusiness", "GetItemDetails_Delete");
			
		}
		
		return ItemDetails;
	}
	

	
}
