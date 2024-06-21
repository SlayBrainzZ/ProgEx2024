package DataAccessLayer;
import java.sql.*;

import javax.swing.JOptionPane;

import ExceptionHandling.ExceptionMaster;
public class SQLConnection {
	
	ExceptionMaster obj = new ExceptionMaster(); 
	
	String connectionURL = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21"; 
	public Connection con;
	
	public SQLConnection() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "SalesReportBusiness", "Constructor");
			
		}
		
		//JOptionPane.showMessageDialog(null, "c = " + connectionURL);
		
		
		
		
	}

}
