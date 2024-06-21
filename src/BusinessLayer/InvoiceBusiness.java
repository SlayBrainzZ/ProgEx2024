package BusinessLayer;
import java.lang.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import ExceptionHandling.ExceptionMaster;

public class InvoiceBusiness {
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster objEx = new ExceptionMaster();
	

	StringBuilder sb;
	
	public InvoiceBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "Constructor");
			
		}
		
	}
	
	public StringBuilder CompanyInfo(int SalesID, int AccID, Object InvoiceDate, Object CustomerName, Object Address1, Object Address2, Object Address3, Object OrderDate, Object Rate, Object Qtty, Object Item, Object Amt) {
		
		
		sb  = new StringBuilder();
		sb.append("						Invoice");
		sb.append("\n");
		
		sb.append("Sold by                                                                           Billing Address:");
		sb.append("\n");
		
		
		sb.append("Viola" + CalculateLen("Viola", (String) CustomerName) + CustomerName );
		sb.append("\n245E, Sushant Shopping Arcade" +  CalculateLen("245E, Sushant Shopping Arcade", (String) Address1) + Address1);
		sb.append("\nSushant Lok Phase 1, Sector 29" + CalculateLen( "Sushant Lok Phase 1, Sector 29", (String) Address2) + Address2  ); 
		sb.append("\nGurgaon, Haryana, 122001" + CalculateLen("Gurgaon, Haryana, 122001" , (String) Address3) + Address3);
		
		sb.append("\n");
		sb.append("\nGST Registration Number:                                                             ");
		sb.append("\nACUPJ0101763I                                                                ");
		
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		
		
		
		sb.append("\nOrder Number:"+ SalesID + CalculateLen("Order Number:"+SalesID, "Invoice Number: "+ "V"+ SalesID) + "Invoice Number: "+ "V"+ SalesID );
		
		sb.append("\nOrder date: "+OrderDate + CalculateLen("Order date: "+ OrderDate, "Invoice Date: "+ InvoiceDate ) + "Invoice Date: "+InvoiceDate);
		
		sb.append("\n__________________________________________________________________________________________________");
		sb.append("\nS.No.|Description                                   |Unit Price| Quantity|Net Amount|Total Amount|");
		sb.append("\n__________________________________________________________________________________________________");
		sb.append("\n1| " + Item+"                                            "+"|"+Rate+ "    |"+Qtty+"        |"+ Amt +"    |");
		
		sb.append("\nTotal:						                                  |    "+Amt);
		
		return sb;
		
	}
	
	public String CalculateLen(String Left_Detail, String Right_Detail) {
		
		String Space = "";
		
		int MaxLen = 98; 
		int FinalLen = 0;
		int ExtraLen = 0;
		
		int UsedLen = Left_Detail.length() + Right_Detail.length();
		
		int LenInBetween = MaxLen - UsedLen ;
		
		if(LenInBetween + Right_Detail.length() > MaxLen) {
			ExtraLen = MaxLen - (LenInBetween + Right_Detail.length());
			FinalLen = Right_Detail.length() - ExtraLen;
		}
		
		else {
			ExtraLen = (MaxLen - Left_Detail.length()) - (LenInBetween + Right_Detail.length()) ;
			FinalLen = ExtraLen + LenInBetween;
		}
		
		for(int i=0;i<FinalLen;i++) {
			Space = Space + " ";
		}
		
		return Space;

	}
	
	public String[] getAccIDs() {
		
		String[] AccIDs = new String[] {};
		int count = 0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetIDs }");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(count==0) {
					AccIDs = new String[rs.getInt("RowCount")];
					
				}
				AccIDs[count] = rs.getString("AccountID");
				count++;
				
			}
			
			
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "getAccIDs");
			JOptionPane.showMessageDialog(null, ex);
			
		}
		
		
		return AccIDs;
	}
	
	public int[] getSalesID(int AccID) {
		
		
		int[] SalesID = new int[] {};
		
		int count=0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMasterInvoice_GetSalesID_Address(?) }");
			stmt.setInt(1, AccID);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(count==0) {
					SalesID = new int[rs.getInt("RowCount")];
				}
				
				SalesID[count] = rs.getInt("SalesID");
				count++;
				
			}
			
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "getSalesID");
			JOptionPane.showMessageDialog(null, ex);
			
			
		}
		
		return SalesID;
	}
	
	public Dictionary getAddress1(int AccID) {
		
		Dictionary Details = new Hashtable();
		
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMasterInvoice_GetSalesID_Address(?) }");
			stmt.setInt(1, AccID);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Details.put("Name", rs.getString("Name"));
				Details.put("Add1", rs.getString("Address1"));
				Details.put("Add2", rs.getString("Address2"));
				Details.put("Add3", rs.getString("Address3"));
				Details.put("InvoiceDate", rs.getString("Date"));
				
				
				}
			
			
				
			}
			
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "getAddress1");
			JOptionPane.showMessageDialog(null, ex);
			
			
		}
		
		return Details;
		
	}
	
	public String[][] getDetails2DArr(int AccID) {
		
		String[][] ArrDetails = new String[][] {};  
		int Count =0;
		int Len = 0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMasterInvoice_GetSalesID_Address(?) }");
			stmt.setInt(1, AccID);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(Count==0) {
					Len = rs.getInt("RowCount");
					ArrDetails = new String[Len][5];
					
				}
				
				for(int i=0;i<Len;i++) {
					for(int j=0;j<5;j++) {
						
						if(j==0) {
							ArrDetails[i][j] = rs.getString("Name");
						}
						else if(j==1) {
							ArrDetails[i][j] = rs.getString("Address1");
						}
						else if(j==2) {
							ArrDetails[i][j] = rs.getString("Address2");
						}
						else if(j==3) {
							ArrDetails[i][j] = rs.getString("Address3");
						}
						else if(j==4) {
							ArrDetails[i][j] = rs.getString("Date");
						}
						
						
					}
				}
				
				
			}
			
			
				
		}
			
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "getDetails2DArr");
			JOptionPane.showMessageDialog(null, ex);
			
			
		}
		
		return ArrDetails;
		
		
	}
	
	
	public Dictionary getTranDetails(int SalesID) {
		
		Dictionary SalesDetails = new Hashtable();
		
		double Amt = 0.0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_TransactionMaster_GetDetails(?)}");
			stmt.setInt(1, SalesID);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				SalesDetails.put("TranID", rs.getInt("TransactionID"));
				SalesDetails.put("OrderDate", rs.getString("Date"));
				SalesDetails.put("Qtty", rs.getInt("Quantity"));
				SalesDetails.put("Rate", rs.getDouble("Rate"));
				SalesDetails.put("Item", rs.getString("Item_Name"));
				Amt = rs.getInt("Quantity") * rs.getDouble("Rate");
				SalesDetails.put("Amt", Amt);
			}
			
		}
		catch(Exception ex) {
			objEx.InsertException(ex.getMessage(), "InvoiceBusiness", "getTranID");
			JOptionPane.showMessageDialog(null, ex);
		}
		
		return SalesDetails;
	}
	

}

