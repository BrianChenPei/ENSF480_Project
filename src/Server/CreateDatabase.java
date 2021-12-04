package Server;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.DriverManager;

public class CreateDatabase {

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;

    public CreateDatabase(String DBURL, String USERNAME, String PASSWORD) {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public static void CreateProjectDatabase(){
        CreateDatabase SQL = new CreateDatabase("jdbc:mysql://localhost:3306/prms", "group4", "ensf480");
        SQL.initializeConnection();
        System.out.println("A new database has been created.");
        
        addPropertyData(SQL.dbConnect);
        addUserData(SQL.dbConnect);
        try { 
            SQL.dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            System.out.println("Connection unsucessful");
            System.out.println("Good bye!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void addPropertyData(Connection c) {
        java.sql.Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE Property " + "(ID INT PRIMARY KEY	NOT NULL," + " type TEXT     NOT NULL," + "Bedrooms INT     NOT NULL," + 
            "Bathrooms INT  NOT NULL," + "Furnished BOOLEAN   NOT NULL," + " cityQuadrant TEXT  NOT NULL," + 
            " listingState TEXT     NOT NULL," + " fee REAL      NOT NULL," + " feePeriodStart TEXT		NOT NULL," + " feePeriodEnd TEXT		NOT NULL," + " landlordName TEXT		NOT NULL," +
            " landlordEmail TEXT	NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    
    public static void addUserData(Connection c){
        java.sql.Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE User " + "(ID INT PRIMARY KEY	NOT NULL," + " type TEXT     NOT NULL," + " userName TEXT    NOT NULL," + 
            " firstName TEXT    NOT NULL," + " lastName TEXT      NOT NULL," + " email TEXT    NOT NULL," + " password TEXT		NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void main(String[] args){
        CreateProjectDatabase();
    }
}
