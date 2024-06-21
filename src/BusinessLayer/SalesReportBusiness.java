package BusinessLayer;
import java.sql.*;

import javax.swing.JOptionPane;

import DataAccessLayer.SQLConnection;
import ExceptionHandling.ExceptionMaster;

public class SalesReportBusiness{
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	ExceptionMaster obj = new ExceptionMaster();
	
	public SalesReportBusiness() {
		
		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "Constructor");
			
		}
		
		
	}
	
						// DateWise Revenue Report //
	
	public double GetAmount(String DateFrom, String DateTo) {
		double Amt = 0.5;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMaster_DateWiseRevenue(?,?)}");
			stmt.setString(1, DateFrom);
			stmt.setString(2, DateTo);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			Amt = rs.getDouble("Amt");
			
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "GetAmount");
		}
		
		return Amt;
	}
	
									// DateWise Revenue Report //
	
	

									// DateWise Sales Report //
	
	public Double[] getSales(String StartDate, String EndDate) {
		Double[] Sales = new Double[] {};
		int Count=0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_SalesMasterReport_DateWiseSales(?,?)}");
			
			stmt.setString(1, StartDate);
			stmt.setString(2, EndDate);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(Count==0) {
					Sales = new Double[rs.getInt("RowCount")];
				}
				
				Sales[Count] = rs.getDouble("Amount");
				Count++;
				
				
			}
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "getSales");
		}
		
		
		return Sales;
		
		
	}
	
}
