package BusinessLayer;

import java.sql.*;

import ExceptionHandling.ExceptionMaster;

public class MonthlyRevReportBusiness {
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster obj = new ExceptionMaster();
	
	public MonthlyRevReportBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "MonthlyRevReportBusiness", "Constructor");
			
		}
	}
	
	public String[] getMonths() {
		
		String[] Months = new String[] {};
		int Count = 0;
		try {
			
			CallableStatement stmt =  con.prepareCall("{call Proc_SalesMaster_GetMonths }");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(Count==0) {
					Months = new String[rs.getInt("RowCount")];
				}
				
				Months[Count] = rs.getString("Months");
				Count++;
			}
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "MonthlyRevReportBusiness", "getMonths");
		}
		

		
		
		return Months;
		
	}
	
	public double getRevenue(String Month) {
		
		double Rev = 0.0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_MonthlyRevReport(?)}");
			stmt.setString(1, Month);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Rev = rs.getDouble("Amt");
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "MonthlyRevReportBusiness", "getMonths");
		}
		
		return Rev;
		
	}
	
	
	
}
