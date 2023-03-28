package App_Code;
import java.sql.*;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

/*That is where the database related code will be*/
public class Database {
	private Connection con;
	private Statement stmt;		
	
	Database(){
		connect();
	}
	
	public void connect() {
		try{  
			//The class used to connect to mysql
			Class.forName("com.mysql.cj.jdbc.Driver");  
			//Database name is application, username is root and password is ShoppingCompanion5010
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/application","root","ShoppingCompanion5010");  
			}catch(Exception e){ System.out.println(e);}  
	}
	public String[] checkDatabase(String name, String storeName, String userSearch,String price) {
		String[] Data = new String[2];
		float price_int;
		if(name != "Item not found") {
			Data[0] = name;
        	Data[1] = price;
            price_int = Float.parseFloat()(price);
            
            if (checkExistence(name,userSearch, storeName)) {
                float databasePrice = Float.parseFloat()(getItem(userSearch,storeName)[1]);
                if(databasePrice == price_int) {
                    updateItem(userSearch, storeName,-1);
                }
                else {
                    updateItem(userSearch,storeName,price_int); 
                }
            }
            else {
            	insertItem(name,storeName,userSearch,price_int);
            }
        }
        else {
            Data = getItem(userSearch,storeName);
        }
        return Data;
    }

	public boolean checkExistence(String name, String userSearch, String storeName) {
		try {
		stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from frequency where ((Item= \""+name+"\" or userSearch= \""+userSearch+"\") and store= \""+storeName+"\")");
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
    public String[] getItem(String userSearch, String storeName) {
        ResultSet rs;
        String[] result = new String[2]; 
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select item,price from frequency where userSearch= \""+userSearch+"\" and store= \""+storeName+"\"");
			rs.next();
			result[0] = rs.getString(1);
			result[1] = Integer.toString(rs.getInt(2));
		} catch (SQLException e) {
			e.printStackTrace();
			result[0] = "error";
			result[1]="error";
		}
        
        
        return result;
    }

	public void updateItem(String name, String storeName, float price) {
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from frequency where userSearch= \""+name+"\" and store= \""+storeName+"\"");
			rs.next();
			int count = rs.getInt(2)+1;
			if(price!=-1) {
				String cmd = "update application.frequency set count=?, price=? where userSearch=? and store=?";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setInt(1,count);
				ps.setInt(2, price);
				ps.setString(3, name);
				ps.setString(4, storeName);
				ps.execute();
			}
			else {
				String cmd = "update application.frequency set count=? where userSearch=? and store=?";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setInt(1,count);
				ps.setString(2, name);
				ps.setString(3, storeName);
				ps.execute();
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}  	
		}
	public void insertItem(String name, String storeName, String userSearch,float price) {
		try {
			String cmd = "insert into application.frequency values (?,1,?,?,?,NOW())";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, name);
			ps.setInt(2,price);
			ps.setString(3, userSearch);
			ps.setString(4, storeName);
			ps.execute();

			}catch (SQLException e) {
				e.printStackTrace();
			}  	
	}
	public void displaySuggestion() {
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
	public void printSearchHistory() {
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
 