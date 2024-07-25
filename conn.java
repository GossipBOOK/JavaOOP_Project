import java.sql.*;
public class conn {
    Connection c;
    Statement s;

    public conn(){
        try {

            //sql workbench username and password
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");
    
            s = c.createStatement();

            s.executeUpdate("Create Database if not exists chat");
            s.executeUpdate("Use chat");
            s.executeUpdate("Create table if not exists credentials (userID varchar(255) primary key,email varchar(255),password varchar(255))");
            
        } catch(Exception e){
            System.out.println(e);
        }

    }


}
