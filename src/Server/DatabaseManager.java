package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import PRMS.Report;
import Property.Property;
import Users.*;

public class DatabaseManager{

    public Connection getConn() throws ClassNotFoundException, SQLException{
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/andca/Documents/GitHub/ENSF480_Project/src/Server/ProjectDatabase.db");
        return c;
    }

    public boolean addProperty(Property p){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addProperty = null;
    	String addPropertyString = "INSERT INTO Property values (?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addProperty = conn.prepareStatement(addPropertyString);
    			addProperty.setString(1, p.getID());
				addProperty.setString(2, p.getType());
    			addProperty.setInt(3, p.getBedRoom());
				addProperty.setInt(4, p.getBathroom());
    			addProperty.setBoolean(5, p.getFurnish());
				addProperty.setString(6, p.getAddress());
    			addProperty.setString(7, p.getState());
    			addProperty.setInt(8, p.getFee());
    			addProperty.setString(9, p.getFeePeriodStart());
    			addProperty.setString(10, p.getFeePeriodEnd());
    			addProperty.setString(11, p.getLandlordName());
    			addProperty.setString(12, p.getLandlordEmail());
    			addProperty.executeUpdate();
    			System.out.println("Added new property");
    		}
    		conn.close();
    	} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }

    public boolean removeProperty(Property p){
    	Connection conn = null;
    	PreparedStatement deleteProperty = null;
    	String deletePropertyString = "DELETE FROM Property WHERE ID = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			deleteProperty = conn.prepareStatement(deletePropertyString);
    			deleteProperty.setString(1, p.getID());
    			deleteProperty.executeUpdate();
    			conn.close();
    			return true;
    		}
    		//conn.close();
    	} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    //only manager and landlord should be able to do this
    public boolean changeState(String newState, int ID) {
    	Connection conn = null;
    	PreparedStatement changeState = null;
    	String changeStateString = "UPDATE Property SET listingState = ? WHERE ID = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			changeState = conn.prepareStatement(changeStateString);
    			changeState.setString(1, newState);
    			changeState.setInt(2, ID);
    			changeState.executeUpdate();
    			conn.close();
    			return true;
    		}
    		//conn.close();
    	} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Property getProperty(int ID) {
    	Connection conn = null;
    	PreparedStatement getProperty = null;
    	String getPropertyString = "SELECT * from Property WHERE ID = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getProperty = conn.prepareStatement(getPropertyString);
    			getProperty.setInt(1, ID);
    			ResultSet rs = getProperty.executeQuery();
    			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
				rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getInt(8),
				rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
    			conn.close();
    			return p;
    		}
    		//conn.close();
    	} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public ArrayList<Property> getAllProperties(){
        Connection conn = null;
        PreparedStatement getAllProperties = null;
        String getAllPropertiesString = "SELECT * from Property";
        ArrayList<Property> temp = new ArrayList<Property>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getAllProperties = conn.prepareStatement(getAllPropertiesString);
        		ResultSet rs = getAllProperties.executeQuery();
        		while(rs.next()) {
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getInt(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
					conn.close();
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	//conn.close();
        } catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Property> getProperties(Property searchCriteria){
        Connection conn = null;
        PreparedStatement getProperties = null;
        String getPropertiesString = "SELECT * from Property WHERE type = ? AND numOfBedrooms = ? AND numOfBathrooms = ? AND isFurnished = ? AND listingState = 'Active'";
        ArrayList<Property> temp = new ArrayList<Property>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getProperties = conn.prepareStatement(getPropertiesString);
        		getProperties.setString(1, searchCriteria.getType());
        		getProperties.setInt(2, searchCriteria.getBedRoom());
        		getProperties.setInt(3, searchCriteria.getBathroom());
        		getProperties.setBoolean(4, searchCriteria.getFurnish());
        		getProperties.setString(5, searchCriteria.getState());
        		ResultSet rs = getProperties.executeQuery();
        		while(rs.next()) {
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getInt(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
					conn.close();
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	//conn.close();
        } catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Property> getLandlordProperties(String name){
    	Connection conn = null;
        PreparedStatement getLandlordProperties = null;
        String getLandlordPropertiesString = "SELECT * from Property WHERE landlordName = ?";
        ArrayList<Property> temp = new ArrayList<Property>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getLandlordProperties = conn.prepareStatement(getLandlordPropertiesString);
        		getLandlordProperties.setString(1, name);
        		ResultSet rs = getLandlordProperties.executeQuery();
        		while(rs.next()) {
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getInt(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	//conn.close();
        } catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
	public boolean addLandlord(Landlord l){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addUser = null;
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setString(1, l.getName());
    			
    			addUser.executeUpdate();
    			System.out.println("Added User");
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }

	public boolean addManager(Manager m){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addUser = null;
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setString(1, m.getName());
    			
    			addUser.executeUpdate();
    			System.out.println("Added User");
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }
    
    public Report getReport(String start, String end) {
    	Connection conn = null;
    	PreparedStatement getReport = null;
    	String getListedString = "SELECT COUNT(*) AS NumberOfListed FROM Property WHERE \r\n" + 
    			"        strftime('%s', feePeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?)";
    	String getRentedString = "SELECT COUNT(*) AS NumberOfRented FROM Property WHERE \r\n" + 
    			"  		 strftime('%s', feePeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND listingState = 'Rented'";
    	String getActiveString = "SELECT COUNT(*) AS NumberOfActive FROM Property WHERE \r\n" + 
    			"  		 strftime('%s', feePeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND listingState = 'Active'";
    	ArrayList<Property> temp = new ArrayList<Property>();
    	String getAllRentedString = "SELECT * FROM Property WHERE \r\n" + 
    			"    	 strftime('%s', feePeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND listingState = 'Rented'";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getReport = conn.prepareStatement(getListedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rs = getReport.executeQuery();
    			getReport = conn.prepareStatement(getRentedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rs1 = getReport.executeQuery();
    			getReport = conn.prepareStatement(getActiveString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rs2 = getReport.executeQuery();
    			getReport = conn.prepareStatement(getAllRentedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rs3 = getReport.executeQuery();
    			while(rs3.next()) {
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getInt(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
					conn.close();
        			temp.add(p);
    			}
    			Report summaryReport = new Report();
    			conn.close();
    			return summaryReport;
    		}
    		conn.close();
    	} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
	public static void main(String[] args) {
		DatabaseManager db = new DatabaseManager();
		// Property Ahouse = new Property("10022", "Attached House", 2, 2, true, null, "Available", 1000, "november", "december", "Mike", "mike@ucalgary.ca");
		// //Property apartment = new Property("1004", "Apartment", 2, 1, false, "123 road", "Available", 1000, "november", "december", "Mike", "mike@ucalgary.ca");
		// db.addProperty(Ahouse);

		ArrayList<Property> disp =  db.getLandlordProperties("Mike");
		for (Property i : disp){
			System.out.println(i.getID());
		}
		
	}
}