package App_Code;
import java.sql.*;  
import java.net.*;
import java.io.*;

/*That is where the database related code will be*/
public class Database {
	static Connection con;
	static Statement stmt;
	public static void main(String args[]) throws MalformedURLException{  
			try {
		       
				userInterface user = new userInterface();
				connect();
				boolean check = checkExistence("Test4");
				if(check) {
					updateItem("Test4",-1);
				}
				else {
					insertItem("Test4",20);
				}
				check = checkExistence("Test3");
				if(check) {
					updateItem("Test3",23);
				}
				else {
					insertItem("Test3",20);
				}
				check = checkExistence("Test11");
				if(check) {
					updateItem("Test5",-1);
				}
				else {
					insertItem("Test11",33);
				}
				con.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	}		
	
	private static void connect() {
		try{  
			//The class used to connect to mysql
			Class.forName("com.mysql.cj.jdbc.Driver");  
			//Database name is application, username is root and password is ShoppingCompanion5010
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/application","root","ShoppingCompanion5010");  
			}catch(Exception e){ System.out.println(e);}  
	}
	
	private static boolean checkExistence(String name) {
		try {
		stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from frequency where Item= \""+name+"\"");
		if(rs.next()) {
			return true;
		}
		else {
			return false;
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  	
	}
	private static void updateItem(String name, int price) {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from frequency where Item= \""+name+"\"");
			rs.next();
			int count = rs.getInt(2)+1;
			if(price!=-1) {
				String cmd = "update application.frequency set count=?, price=? where Item=?";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setInt(1,count);
				ps.setInt(2, price);
				ps.setString(3, name);
				ps.execute();
			}
			else {
				String cmd = "update application.frequency set count=? where Item=?";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setInt(1,count);
				ps.setString(2, name);
				ps.execute();
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}  	
		}
	private static void insertItem(String name, int price) {
		try {
			String cmd = "insert into application.frequency values (?,1,?,NOW())";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, name);
			ps.setInt(2,price);
			ps.execute();

			}catch (SQLException e) {
				e.printStackTrace();
			}  	
	}
	private static void displaySuggestion() {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from frequency where Count>10");
			while(rs.next()) {  
				/*//int price = [updated price function]
				 if (rs.getInt(3)!= price)
				//updateItem(rs.getString(1), price);
				//ResultSet temp = stmt.executeQuery("select * from frequency where Item= \""+rs.getString(1)+"\"");
				System.out.println(temp.getString(1)+"  "+temp.getInt(3)); */ 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static void printSearchHistory() {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select name from history order by DateTime desc limit 5");
			while(rs.next()) {  
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
 