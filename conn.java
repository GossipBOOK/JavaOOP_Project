import java.sql.*;
public class conn {
    Connection c;
    Statement s;

    public conn(){
        try {
            c = DriverManager.getConnection("jdbc:mysql:///chat", "root", "password");
            s = c.createStatement();


        } catch(Exception e){
            System.out.println(e);
        }

    }
}
