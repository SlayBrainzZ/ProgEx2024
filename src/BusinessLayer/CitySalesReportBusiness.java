package BusinessLayer;
import java.sql.*;

import ExceptionHandling.ExceptionMaster;



public class CitySalesReportBusiness {
	
	ExceptionMaster obj = new ExceptionMaster();
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public CitySalesReportBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitySalesReportBusiness", "Constructor");
			
		}
		
	}
	
	public String[] getCities() {
		String[] Cities = new String[] {};
		int Count= 0;
		
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_CitySalesReport}");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Count==0) {
					Cities = new String[rs.getInt("RowCount")];
				}
				
				Cities[Count] = rs.getString("City");
				Count++;
				
			}
			
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitySalesReportBusiness", "getCities");
		}
		return Cities;
	}
	
	public double getRevenue(String City) {
		double Revenue = 1.0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_CityRevenueReport(?)}");
			stmt.setString(1, City);
			
			ResultSet rs = stmt.executeQuery();
			
			
			
			while(rs.next()) {
				
				Revenue = rs.getDouble("Amt");
				
			}
			
			
		}
		
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitySalesReportBusiness", "getRevenue");
		}
		
		return Revenue;
	
	}
	
	public int getTotalCus(String City) {
		
		int totalCus = 0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_CityCusReport(?)}");
			stmt.setString(1, City);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			totalCus = rs.getInt("TotalCus");
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CitySalesReportBusiness", "getTotalCus");
		}
		
		
		return totalCus;
	}

}
