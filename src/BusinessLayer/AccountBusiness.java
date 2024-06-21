package BusinessLayer;

import java.util.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ExceptionHandling.ExceptionMaster;




public class AccountBusiness extends ExceptionMaster {
	
	Dictionary Values = new Hashtable();
	
	
	String out= "-1";
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster obj = new ExceptionMaster();
	
	public AccountBusiness() {
		
		try {
			int x = 0;
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			int y = 5/x;
			
		}
		catch(Exception ex) {
			InsertException(ex.getMessage(), "AccountMaster", "Constructor");
			//obj.InsertException(ex.getMessage(), "AccountMaster", "Constructor");
			
		}
		
	}
	
	public String InsertCusDetails(String Name, String Email, String Phone, String Add1, String Add2, String Add3, String city) {
		out= "-1";
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_InsertCusDetails(?,?,?,?,?,?,?)}");
			
			stmt.setString(1, Name);
			stmt.setString(2, Email);
			stmt.setString(3, Phone);
			stmt.setString(4, Add1);
			stmt.setString(5, Add2);
			stmt.setString(6, Add3);
			stmt.setString(7, city);
			
		
			ResultSet rs = stmt.executeQuery();
			
			rs.next();			
			out = rs.getString("output");						
			stmt.close();
			
			
		}
		catch(Exception ex) {
			//obj.InsertException(ex.getMessage(), "AccountMaster", "InsertCusDetails");
			
			//lblMsg.setText(ex.getMessage());
		}
		
		
		return out;
		
	}
	
	public Dictionary GetCustomerDetails(String CusName) {
		//CusName = (String) cmbBoxCusName.getSelectedItem();
		
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetCusDetails(?)}" );
			stmt.setString(1, CusName);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Values.put("AccID", rs.getInt("AccountID"));
				Values.put("EmailID", rs.getString("EmailId"));
				Values.put("Phone", rs.getString("Phone_Number"));
				Values.put("City", rs.getString("City"));
			
			}
			
			stmt.close();
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "DeleteAccountMaster", "GetCustomerDetails");
			
		}
		
		return Values;
		
	}
	
	
	
	public String[][] GetCustomerDetailsUsingArr(int CusID) {
		
		String[][] CusDetails = new String[1][3] ;
		int Count =0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetCusDetails(?)}");
			stmt.setInt(1, CusID);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				if(Count==0) {
					
					for(int i=0;i<1;i++) {
						for(int j=0;j<3;j++) {
							
							if(j==0) {
								CusDetails[i][j] =  rs.getString("EmailID");
							}
							else if(j==1) {
								CusDetails[i][j] = rs.getString("Phone_Number");
							}
							else if(j==2) {
								CusDetails[i][j] = rs.getString("City");
							}
							
						}
					}
					
					
					
				}
				
			}
			
			stmt.close();
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "DeleteAccountMaster", "GetCustomerDetails");
			
		}
		
		return CusDetails;
		
	}
	
	
	
	public String DeleteCustomerDB(String CusName) {
		out= "-1";
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_DeleteCustomer(?)}");
			
			stmt.setString(1, CusName);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			out = rs.getString("output");
			stmt.close();
		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "DeleteAccountMaster", "DeleteCustomerDB");
			
		}
		
		
		return out;
		
	}
	
	public String[] GetCusNames_Delete() {
		int Pos = 0;
		int ArrLength = 0;
		String[] CusNames = new String[] {};
		
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetNamesToDelete}");
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
			obj.InsertException(ex.getMessage(), "DeleteAccountMaster", "populateComboBox");
			
		}
		return CusNames;
		
	}
	
	public Object[][] getCusNames_Update() {
		
		int ArrLen = 0;
		
		Object[][] CusID_Names = new Object[][] {};
		
		int Count = 0;
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetCusNames}");
			ResultSet rs = stmt.executeQuery();
			
			int i = 0;
			
			while(rs.next()) {
				ArrLen = rs.getInt("RowCount");
				 if(i==0) {
					 CusID_Names = new Object[ArrLen][2];
				 }
				 
				
				
				

					for(int j=0;j<2;j++) {
						
						CusID_Names[i][0] = rs.getString("Name");
						CusID_Names[i][1] = rs.getString("AccountID");
						
						
					}
					//JOptionPane.showMessageDialog(null, CusID_Names);
				i++;

			}
			
		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "AccountBusiness", "getCusNames_Update");
			//lblMsg.setText("Error in populateComboBox: "+ ex.getMessage());
		}
		
		
		
		return CusID_Names;
	}
	
	public void UpdateCusDetailsDB(int ID, String CusName, String NewName, String Email, String Phone, String City) {
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_UpdateCusDetails(?,?,?,?,?,?)}");
			
			stmt.setInt(1, ID);
			stmt.setString(2, CusName);
			stmt.setString(3, NewName);
			stmt.setString(4, Email);
			stmt.setString(5, Phone);
			stmt.setString(6, City);
			
			stmt.execute();
			stmt.close();
			
			//populateComboBox();
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "AccountBusiness", "UpdateCusDetailsDB");
		}
		
		
	}

}
