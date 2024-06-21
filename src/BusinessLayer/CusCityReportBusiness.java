package BusinessLayer;


import java.sql.*;
import ExceptionHandling.ExceptionMaster;

public class CusCityReportBusiness {
	
	ExceptionMaster obj = new ExceptionMaster();
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public CusCityReportBusiness(){
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CustomerCityReport", "Constructor");
			
		}
		
	}
	
	public String[] getCities() {
		
		String[] Cities = new String[] {};
		int Len= 0;
		int Count= 0;
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_AccountMaster_GetCities }");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				if(Len==0) {
					Len = rs.getInt("RowCount");
					Cities = new String[Len];
				}
				
				Cities[Count] = rs.getString("City");
				Count++;
				
			}
			
			
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CustomerCityReport", "getCusNames");
		}
		
		
		return Cities;
				
		
	}
	
	

}
