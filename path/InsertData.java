package path;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class InsertData {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://192.168.1.124:5432/pio", "postgres", "postgres");
         stmt = c.createStatement();
         String sql = "CREATE TABLE zhoutest " +
                      "(MID INT PRIMARY KEY     NOT NULL," +
                      " SID           TEXT    NOT NULL, " +
                      " PAGE            TEXT     NOT NULL)";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (1,1,'cherokee')";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (2,1,'wranger')";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (3,1,'cherokee')";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (4,1,'1500')";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (5,2,'cherokee')";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO zhoutest (MID,SID,PAGE) VALUES (6,3,'2500')";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
     }
}