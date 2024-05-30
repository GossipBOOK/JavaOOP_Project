import java.sql.*;
public class conn {
    Connection c;
    Statement s;

    public conn(){
        try {

            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");
    
            s = c.createStatement();

            s.executeUpdate("Create Database if not exists chat");
            s.executeUpdate("Use chat");
            
        } catch(Exception e){
            System.out.println(e);
        }


    }


}
