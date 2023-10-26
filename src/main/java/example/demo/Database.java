package example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/application","root","ShoppingCompanion5010");
        }catch(Exception e){ System.out.println(e);}
    }
    public String[] checkDatabase(String name, String storeName, String userSearch,String price, String url) {
        String[] Data = new String[3];
        float price_int;
        searchHistory(userSearch);

        if(name != "Item not found") {
            Data[0] = name;
            Data[1] = price;
            Data[2] = url;
            price_int = Float.parseFloat(price);

            if (checkExistence(name,userSearch, storeName)) {
                float databasePrice = Float.parseFloat(getItem(userSearch,storeName)[1]);
                if(databasePrice == price_int) {
                    updateItem(userSearch, storeName,-1);
                }
                else {
                    updateItem(userSearch,storeName,price_int);
                }
            }
            else {
                insertItem(name,storeName,userSearch,price_int,url);
            }
        }
        else {
            if (checkExistence(name,userSearch, storeName)) {
                Data = getItem(userSearch,storeName);
            }
            else {
                Data[0] = name;
                Data[1] = price;
                Data[2] = url;
            }
        }
        return Data;
    }

    public boolean checkExistence(String name, String userSearch, String storeName) {
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from frequency where ((Item= \""+name+"\" or userSearch= \""+userSearch+"\") and store= \""+storeName+"\")");

            return rs.next();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public String[] getItem(String userSearch, String storeName) {
        ResultSet rs;
        String[] result = new String[3];
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select item,price,urls from frequency where userSearch= \""+userSearch+"\" and store= \""+storeName+"\"");
            rs.next();
            result[0] = rs.getString(1);
            result[1] = Float.toString(rs.getFloat(2));
            result[2] = rs.getString(3);
        } catch (SQLException e) {
            e.printStackTrace();
            result[0] = "error";
            result[1]="0.0";
            result[2] = "error";
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
                ps.setFloat(2, price);
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
    public void insertItem(String name, String storeName, String userSearch,float price,String url) {
        try {
            String cmd = "insert into application.frequency values (?,1,?,?,?,?,NOW())";
            PreparedStatement ps = con.prepareStatement(cmd);
            ps.setString(1, name);
            ps.setFloat(2,price);
            ps.setString(3, userSearch);
            ps.setString(4, storeName);
            ps.setString(5,url);
            ps.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> displaySuggestion() {
        List<String> names = new ArrayList<String>();
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select userSearch from frequency where Count>10 order by Count desc");
            while(rs.next() && names.size() < 5) {
                if(!names.contains(rs.getString(1))) {
                    names.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
    public String[] printSearchHistory() {
        String[] names = new String[]{"","","","",""};
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select searchItem from history order by dateTime desc limit 5");
            int i = 0;
            while(rs.next()) {
                names[i] = rs.getString(1);
                i++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return names;

    }
    public void searchHistory(String name) {
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from application.history where searchItem= \""+name+"\"");
            if(rs.next()) {
                String cmd = "update application.history set DateTime = NOW() where searchItem = ?";
                PreparedStatement ps = con.prepareStatement(cmd);
                ps.setString(1,name);
                ps.execute();
            }
            else {
                String cmd = "insert into application.history values(?,NOW())";
                PreparedStatement ps = con.prepareStatement(cmd);
                ps.setString(1, name);
                ps.execute();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean SignUp(String fName, String lName, String email,String pass) {
        try {
            String cmd = "insert into application.accountinfo values (?,?,?,MD5(?))";
            PreparedStatement ps = con.prepareStatement(cmd);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, email);
            ps.setString(4,pass);
            ps.execute();
            return true;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean SignIn(String email,String pass) {
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from accountinfo where (email= \""+email+"\" and Password = MD5(\""+pass+"\"))");

            if(rs.next()) {
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
