package Server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import PRMS.Report;
import Property.Property;
import PRMS.Report;
import Users.*;

public class DatabaseManager{
    public Connection getConn() throws ClassNotFoundException, SQLException{
        Connection c = DriverManager.getConnection("jdbc:mysql:C:/Users/andca/Documents/GitHub/ENSF480_Project/src/Server/Database.mwb");
        return c;
    }

    public boolean addProperty(Property p){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addProperty = null;
    	String addPropertyString = "INSERT INTO Property values (?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?, ?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addProperty = conn.prepareStatement(addPropertyString);
    			addProperty.setInt(1, p.getID());
    			addProperty.setString(2, p.getAddress());
    			addProperty.setString(3, p.getType());
    			addProperty.setInt(4, p.getBedrooms());
    			addProperty.setInt(5, p.getBathrooms());
    			addProperty.setBoolean(6, p.isFurnished());
    			addProperty.setString(8, p.getListingState());
    			addProperty.setDouble(9, p.getFee());
    			addProperty.setInt(10, p.getFee());
    			addProperty.setInt(11, p.getFee());
    			addProperty.setString(12, p.getLandlordName());
    			addProperty.setString(13, p.getLandlordEmail());
    			addProperty.executeUpdate();
    			System.out.println("Added property");
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
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
    			deleteProperty.setInt(1, p.getID());
    			deleteProperty.executeUpdate();
    			conn.close();
    			return true;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    // Manager and Landlord
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
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
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
    			Property p = new Property(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
        					rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), 
        					rs.getString(12), rs.getString(13));
    			conn.close();
    			return p;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
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
        			Property p = new Property(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
        					rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), 
        					rs.getString(12), rs.getString(13));
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	conn.close();
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Property> getProperties(Property searchCriteria){
        Connection conn = null;
        PreparedStatement getProperties = null;
        String getPropertiesString = "SELECT * from Property WHERE type = ? AND numOfBedrooms = ? AND numOfBathrooms = ? AND isFurnished = ? AND cityQuadrant = ? AND listingState = 'Active'";
        ArrayList<Property> temp = new ArrayList<Property>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getProperties = conn.prepareStatement(getPropertiesString);
        		getProperties.setString(1, searchCriteria.getType());
        		getProperties.setInt(2, searchCriteria.getBedrooms());
        		getProperties.setInt(3, searchCriteria.getBathrooms());
        		getProperties.setBoolean(4, searchCriteria.isFurnished());
        		getProperties.setString(5, searchCriteria.getCityQuadrant());
        		ResultSet rs = getProperties.executeQuery();
        		while(rs.next()) {
        			Property p = new Property(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
        					rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(12), rs.getString(13));
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	conn.close();
        } catch(ClassNotFoundException | SQLException e) {
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
        			Property p = new Property(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
        					rs.getBoolean(6), rs.getString(7), rs.getString(8), new PropertyFee(rs.getString(9), rs.getString(10)), 
        					rs.getString(12), rs.getString(13));
        			temp.add(p);
        		}
        		conn.close();
        		return temp;
        	}
        	conn.close();
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public boolean addUser(User u){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addUser = null;
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setInt(1, u.getID());
    			addUser.setString(2, u.getType());
    			addUser.setString(3, u.getUserName());
    			addUser.setString(4, u.getFirstName());
    			addUser.setString(5, u.getLastName());
    			addUser.setString(6, u.getEmail());
    			addUser.setString(7, u.getPassword());
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

    public boolean removeUser(User u){
    	Connection conn = null;
    	PreparedStatement deleteUser = null;
    	String deleteUserString = "DELETE FROM User WHERE ID = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			deleteUser = conn.prepareStatement(deleteUserString);
    			deleteUser.setInt(1, u.getID());
    			deleteUser.executeUpdate();
    			conn.close();
    			return true;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public User getUser(String username) {
    	Connection conn = null;
    	PreparedStatement getUser = null;
    	String getUserString = "SELECT * from User WHERE userName = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getUser = conn.prepareStatement(getUserString);
    			getUser.setString(1, username);
    			ResultSet rs = getUser.executeQuery();
    			User u = new User(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
        					rs.getString(6), rs.getString(7), rs.getString(2));
    			conn.close();
    			return u;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public ArrayList<User> getUsers(){
        Connection conn = null;
        PreparedStatement getAllUsers = null;
        String getAllUsersString = "SELECT * from User";
        ArrayList<User> temp = new ArrayList<User>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getAllUsers = conn.prepareStatement(getAllUsersString);
        		ResultSet rs = getAllUsers.executeQuery();
        		while(rs.next()) {
        			User u = new User(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
        					rs.getString(6), rs.getString(7), rs.getString(2));
        			temp.add(u);
        		}
        		conn.close();
        		return temp;
        	}
        	conn.close();
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    // Manager
    public boolean updateFee(int newFee) {
    	Connection conn = null;
    	PreparedStatement updateFee = null;
    	String updateFeeString = "UPDATE Property SET fee = ?, feePeriodStart = ?, feePeriodEnd = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			updateFee = conn.prepareStatement(updateFeeString);
    			updateFee.setDouble(1, newFee.getAmount());
    			updateFee.setString(2, newFee.getFeePeriodStart().toString());
    			updateFee.setString(3, newFee.getFeePeriodEnd().toString());
    			updateFee.executeUpdate();
    			conn.close();
    			return true;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
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
        			Property p = new Property(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getInt(4), rs3.getInt(5),
        					rs3.getBoolean(6), rs3.getString(7), rs3.getString(8), new PropertyFee(rs3.getString(9), rs3.getString(10)), 
        					rs3.getString(12), rs3.getString(13));
        			temp.add(p);
    			}
    			Report summaryReport = new Report(start, end, rs.getInt(1), rs1.getInt(1), rs2.getInt(1), temp);
    			conn.close();
    			return summaryReport;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public User login(String username, String password, String userType) {
    	Connection conn = null;
    	PreparedStatement login = null;
    	String loginString = "SELECT * from User WHERE userName = ? AND password = ? AND type = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			login = conn.prepareStatement(loginString);
    			login.setString(1, username);
    			login.setString(2, password);
    			login.setString(3, userType);
    			ResultSet rs = login.executeQuery();
    			User u = null;
    			if(rs.next()) {
    				u = new User(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
        					rs.getString(6), rs.getString(7), rs.getString(2));
    			}
    			conn.close();
    			return u;
    		}
    		conn.close();
    	} catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    	
    }
}