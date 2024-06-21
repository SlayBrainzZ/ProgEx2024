package BusinessLayer;

import java.sql.*;

import ExceptionHandling.ExceptionMaster;

public class CitywiseCtgReportBusiness {
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster obj = new ExceptionMaster();
	
	public CitywiseCtgReportBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitywiseCtgReportBusiness", "Constructor");
			
		}
		
	}
	
	public String[] getCategories() {
		
		String[] Ctg = new String[] {};
		int count = 0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_CategoryMaster_GetCategories}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.last();
			int ArrLen = rs.getRow();
			
			rs.beforeFirst();
			
			
			
			while(rs.next()) {
				
				if(count==0) {
					Ctg = new String[ArrLen];
				}
				
				Ctg[count] = rs.getString("CategoryName");
				count++;
			}
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitywiseCtgReportBusiness", "getCategories");
		}
		return Ctg;
	}
	
	
	
}
