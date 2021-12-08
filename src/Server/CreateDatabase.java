package Server;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
/**
 *
 * @author Andres Caicedo <a
 * href="mailto: acaicedo@ucalgary.ca">acaicedo@ucalgary.ca</a>
 */

public class CreateDatabase {

    
    public static void createProjectDatabase(){
        String url = "jdbc:sqlite:C:/Users/andca/Documents/GitHub/ENSF480_Project/src/Server/ProjectDatabase.db" ;
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                addPropertyData(conn);
                addUserData(conn);
                conn.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    

    public static void addPropertyData(Connection c) {
        java.sql.Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE Property " + "(ID TEXT PRIMARY KEY	NOT NULL," + " type TEXT     NOT NULL," + "Bedrooms INT     NOT NULL," 
            + "Bathrooms INT  NOT NULL," + "Furnished BOOLEAN   NOT NULL,"+ " Address TEXT     NOT NULL,"+ " State TEXT     NOT NULL," + "Fee INT     NOT NULL," 
            + " feePeriodStart TEXT		NOT NULL," + " feePeriodEnd TEXT		NOT NULL," + " landlordName TEXT		NOT NULL," +" landlordEmail TEXT	NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Property Table created successfully");
    }

    
    public static void addUserData(Connection c){
        java.sql.Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE User " + "(userName TEXT PRIMARY KEY   NOT NULL," + "type TEXT     NOT NULL," + 
            " firstName TEXT    NOT NULL," + " lastName TEXT      NOT NULL," + " email TEXT    NOT NULL," + " password TEXT		NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("User Table created successfully");
    }

    public static void main(String[] args){
        createProjectDatabase();
    }
}
