package BusinessLayer;
import java.sql.*;

import ExceptionHandling.ExceptionMaster;



public class CreateUserBusiness {
	
	ExceptionMaster obj = new ExceptionMaster(); 
	
	String connectionUrl = "jdbc:sqlserver://LAPTOP-9HEOT6R2\\SQLEXPRESS01;databaseName=StockManagement;user=Devansh;password=devansh21";
	Connection con;
	
	public CreateUserBusiness() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "CreateNewUser", "constructor");
	
		}
		
	}
	
	public String InsertInUserMaster(String Name, String Email, String PhNum, String Address, String Username, String Pass) {
		String output= "-1";
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_userMaster_CreateNewuser(?,?,?,?,?,?) }");
			stmt.setString(1, Name);
			stmt.setString(2, Email);
			stmt.setString(3, PhNum);
			stmt.setString(4,Address);
			stmt.setString(5, Username);
			stmt.setString(6, Pass);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			output = rs.getString("output");
			
		}
		catch(Exception Ex) {
			obj.InsertException(Ex.getMessage(), "CreateNewUser", "InsertInUserMaster");
			
		}
		
		return output;
	}
	
	// --------------------- Update User -------------------------------- //
	
	public String[][] GetUserDetails(String Uname) {
		
		String[][] UserDetails = new String[1][4] ;
		
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_GetDetails(?)}");
			stmt.setString(1, Uname);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				for(int i=0;i<1;i++) {
					for(int j=0;j<4;j++) {
						
						if(j==0) {
							UserDetails[i][j] = rs.getString("Name");
						}
						else if(j==1) {
							UserDetails[i][j] = rs.getString("Email_ID");
						}
						else if(j==2) {
							UserDetails[i][j] = rs.getString("Phone_Number");
						}
						else if(j==3) {
							UserDetails[i][j] = rs.getString("Address");
						}
						
					}
				}

			}
			
			stmt.close();
			
			
		}
		catch(Exception e) {
			obj.InsertException(e.getMessage(), "UpdateUser", "GetUserDetails");
			
		}
		
		return UserDetails;
		
	}
	
	public String[] getUsernames() {
		
		String[] Usernames = new String[] {};
		
		int Index = 0;
		int ArrLen = 0;
		try {
			
			CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_GetUsers }");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(Index == 0) {
					ArrLen = rs.getInt("RowCount");
					Usernames = new String[ArrLen];
				}
				
				Usernames[Index] = rs.getString("Username");
				Index++;
				
			}
			
			//comboBox.removeAllItems();
			
			for(int i=0;i<ArrLen;i++) {
				//comboBox.addItem(Usernames[i]);
			}
			
		}
		catch(Exception e) {
			obj.InsertException(e.getMessage(), "CreateUserBusiness", "getUsernames");
			
		}
		return Usernames;
	}
	
	public String UpdateUserDB(String Uname, String NewUser, String Name, String Email, String Phone, String Address, String Pass) {
		String Out= "-1";
		try {
			CallableStatement stmt = con.prepareCall("{call Proc_UserMaster_Update(?,?,?,?,?,?,?)}");
			stmt.setString(1, Uname);
			stmt.setString(2, NewUser);
			stmt.setString(3, Name);
			stmt.setString(4, Email);
			stmt.setString(5, Phone);
			stmt.setString(6, Address);
			stmt.setString(7, Pass);
			
			ResultSet rs= stmt.executeQuery();
			rs.next();
			Out = rs.getString("Output");
			//populateComboBox();
								
			
		}
		catch(Exception ex) {
			obj.InsertException(ex.getMessage(), "UpdateUser", "UpdateUserDB");
		
		}
		
		return Out; 
	}

}
