package ExceptionHandling;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ExceptionMaster {
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public ExceptionMaster() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			//lblMsg.setText(ex.getMessage());
		}
		
	}
	
	public void InsertException(String Exception, String Form, String Method) {
		
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_ExceptionMaster_GetException(?,?,?)}");
			
			stmt.setString(1, Exception);
			stmt.setString(2, Form);
			stmt.setString(3, Method);
			stmt.execute();
			
			
		}
		catch(Exception e) {
			
		}
		
	}
	

}
