package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Andres Caicedo <a 
 * href="mailto: acaicedo@ucalgary.ca</a> 
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

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
    	String addPropertyString = "INSERT INTO Property values (?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addProperty = conn.prepareStatement(addPropertyString);
    			addProperty.setString(1, p.getID());
				addProperty.setString(2, p.getType());
    			addProperty.setInt(3, p.getBedRoom());
				addProperty.setInt(4, p.getBathroom());
    			addProperty.setBoolean(5, p.getFurnish());
				addProperty.setString(6, p.getQuadrant());
    			addProperty.setString(7, p.getState());
    			addProperty.setString(8, p.getFeePeriodStart());
    			addProperty.setString(9, p.getFeePeriodEnd());
    			addProperty.setString(10, p.getLandlordName());
    			addProperty.setString(11, p.getLandlordEmail());
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
    	String changeStateString = "UPDATE Property SET State = ? WHERE ID = ?";
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

	public boolean changePeriod(String newPS, String newPE, int ID) {
    	Connection conn = null;
    	PreparedStatement changePeriod = null;
    	String changePeriodString = "UPDATE Property SET PeriodStart = ?, PeriodEnd = ? WHERE ID = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			changePeriod = conn.prepareStatement(changePeriodString);
    			changePeriod.setString(1, newPS);
				changePeriod.setString(2, newPE);
    			changePeriod.setInt(3, ID);
    			changePeriod.executeUpdate();
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
				rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),
				rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
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
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
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
    
    public ArrayList<Property> SearchProperties(Property search){
        Connection conn = null;
        PreparedStatement getProperties = null;
        String getPropertiesString = "SELECT * from Property WHERE type = ? AND Bedrooms = ? AND Bathrooms = ? AND Furnished = ? AND CityQuadrant = ? AND State = 'Active'";
        ArrayList<Property> temp = new ArrayList<Property>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getProperties = conn.prepareStatement(getPropertiesString);
        		getProperties.setString(1, search.getType());
        		getProperties.setInt(2, search.getBedRoom());
        		getProperties.setInt(3, search.getBathroom());
        		getProperties.setBoolean(4, search.getFurnish());
        		getProperties.setString(5, search.getQuadrant());
        		ResultSet rs = getProperties.executeQuery();
        		while(rs.next()) {
					System.out.println("DEBUG");
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
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
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
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
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setString(1, l.getUserName());
				addUser.setString(2, l.getUsertype());
				addUser.setString(3, l.getFname());
				addUser.setString(4, l.getLname());
				addUser.setString(5, l.getEmail());
				addUser.setString(6, l.getPassword());    			
    			addUser.executeUpdate();
    			System.out.println("Added Lanlord");
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }

	public boolean removeLandlord(Landlord R){
    	Connection conn = null;
    	PreparedStatement deleteUser = null;
    	String deleteUserString = "DELETE FROM User WHERE userName = ? AND type = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			deleteUser = conn.prepareStatement(deleteUserString);
    			deleteUser.setString(1, R.getUserName());
				deleteUser.setString(2, "Landlord");
    			deleteUser.executeUpdate();
    			conn.close();
				System.out.println("Removed Landlord");
    			return true;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }

	public Landlord getLandlord(String username) {
    	Connection conn = null;
    	PreparedStatement getUser = null;
    	String getUserString = "SELECT * FROM User WHERE userName = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getUser = conn.prepareStatement(getUserString);
    			getUser.setString(1, username);
    			ResultSet rs = getUser.executeQuery();
    			Landlord u = new Landlord(rs.getString(1), rs.getString(2),
				rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    			conn.close();
				System.out.println("got Landlord");
    			return u;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }

	public boolean addManager(Manager m){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addUser = null;
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setString(1, m.getUserName());
				addUser.setString(2, m.getUsertype());
				addUser.setString(3, m.getFname());
				addUser.setString(4, m.getLname());
				addUser.setString(5, m.getEmail());
				addUser.setString(6, m.getPassword());  
    			
    			addUser.executeUpdate();
    			System.out.println("Added Manager");
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }

	public boolean removeManager(Manager R){
    	Connection conn = null;
    	PreparedStatement deleteUser = null;
    	String deleteUserString = "DELETE FROM User WHERE userName = ? AND type = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			deleteUser = conn.prepareStatement(deleteUserString);
    			deleteUser.setString(1, R.getUserName());
				deleteUser.setString(2, "Manager");
    			deleteUser.executeUpdate();
    			conn.close();
				System.out.println("Removed Manager");
    			return true;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }

	public Manager getManager(String username) {
    	Connection conn = null;
    	PreparedStatement getUser = null;
    	String getUserString = "SELECT * FROM User WHERE userName = ? AND type = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getUser = conn.prepareStatement(getUserString);
    			getUser.setString(1, username);
				getUser.setString(2, "Manager");
    			ResultSet rs = getUser.executeQuery();
    			Manager u = new Manager(rs.getString(1), rs.getString(2),
				rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    			conn.close();
				System.out.println("got Manager");
    			return u;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }

	public boolean addRegRenter(RegisteredRenter r){
    	boolean success = true;
    	Connection conn = null;
    	PreparedStatement addUser = null;
    	String addUserString = "INSERT INTO User values (?, ?, ?, ? ,? ,?)";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			addUser = conn.prepareStatement(addUserString);
    			addUser.setString(1, r.getUserName());
				addUser.setString(2, r.getUsertype());
				addUser.setString(3, r.getFname());
				addUser.setString(4, r.getLname());
				addUser.setString(5, r.getEmail());
				addUser.setString(6, r.getPassword());  
    			
    			addUser.executeUpdate();
    			System.out.println("Added Registered Renter");
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return success;
    }

	public boolean removeRegRenter(RegisteredRenter R){
    	Connection conn = null;
    	PreparedStatement deleteUser = null;
    	String deleteUserString = "DELETE FROM User WHERE userName = ?";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			deleteUser = conn.prepareStatement(deleteUserString);
    			deleteUser.setString(1, R.getUserName());
    			deleteUser.executeUpdate();
    			conn.close();
				System.out.println("Removed Registered Renter");
    			return true;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }

	public RegisteredRenter getRegRenter(String username) {
    	Connection conn = null;
    	PreparedStatement getUser = null;
    	String getUserString = "SELECT * from User WHERE userName = ?";
    	try{
    		conn = getConn();
    		if(conn != null) {
    			getUser = conn.prepareStatement(getUserString);
    			getUser.setString(1, username);
    			ResultSet rs = getUser.executeQuery();
    			RegisteredRenter u = new RegisteredRenter(rs.getString(1), rs.getString(2),
				rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    			conn.close();
				System.out.println("got Registered Renter");
    			return u;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    	
	public String checkAccount(String username){
		Connection conn = null;
    	PreparedStatement check = null;
    	String checkUserString = "SELECT * FROM User WHERE userName = ?";
		try{
			conn = getConn();
			if(conn != null){
				check = conn.prepareStatement(checkUserString);
				check.setString(1, username);
				ResultSet rs = check.executeQuery();
				if (rs.next() == false){
					return "NE";
				}
				String account = rs.getString(2);
				conn.close();
				return account;
			}

		}catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
		}
		
		return "Connection failed";
	}


	public Report getReport(String start, String end) {
    	Connection conn = null;
    	PreparedStatement getReport = null;
		String getListedString = "SELECT COUNT(*) AS NumberOfListed FROM Property WHERE \r\n" + 
		"        strftime('%s', PeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?)";
    	String getRentedString = "SELECT COUNT(*) AS NumberOfRented FROM Property WHERE \r\n" + 
    			"  		 strftime('%s', PeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND State = 'Rented'";
    	String getActiveString = "SELECT COUNT(*) AS NumberOfActive FROM Property WHERE \r\n" + 
    			"  		 strftime('%s', PeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND State = 'Active'";
    	ArrayList<Property> temp = new ArrayList<Property>();
    	String getAllRentedString = "SELECT * FROM Property WHERE \r\n" + 
    			"    	 strftime('%s', PeriodStart) BETWEEN strftime('%s', ?) AND strftime('%s', ?) AND State = 'Rented'";
    	try {
    		conn = getConn();
    		if(conn != null) {
    			getReport = conn.prepareStatement(getListedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet all = getReport.executeQuery();
    			getReport = conn.prepareStatement(getRentedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rented = getReport.executeQuery();
    			getReport = conn.prepareStatement(getActiveString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet active = getReport.executeQuery();
    			getReport = conn.prepareStatement(getAllRentedString);
    			getReport.setString(1, start);
    			getReport.setString(2, end);
    			ResultSet rs = getReport.executeQuery();
    			while(rs.next()) {
        			Property p = new Property(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),
					rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
        			temp.add(p);
    			}
    			Report Report = new Report(start, end, all.getInt(1), rented.getInt(1), active.getInt(1), temp);

    			return Report;
    		}
    		//conn.close();
    	} catch(ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

	public ArrayList<String> getAllUsers(){
		Connection conn = null;
        PreparedStatement getAllUsers = null;
        String getAllUsersString = "SELECT * from User";
        ArrayList<String> temp = new ArrayList<String>();
        try {
        	conn = getConn();
        	if(conn != null) {
        		getAllUsers = conn.prepareStatement(getAllUsersString);
        		ResultSet rs = getAllUsers.executeQuery();
        		while(rs.next()) {
        			temp.add(rs.getString(1)+ "\t" + rs.getString(2) + "\t" +  rs.getString(3) 
					+ "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" +  rs.getString(6));
        		}
        		conn.close();
        		return temp;
        	}
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
        return null;

	}

	public static void main(String[] args) {
		DatabaseManager db = new DatabaseManager();
		Property apartment1 = new Property("123", "Apartment", 2, 1, true, "NW", "Active", "2014-10-07", "2017-10-07", "Andres", "a@ucalgary.ca");
		Property apartment2 = new Property("124", "Apartment", 2, 2, false, "NE", "Active", "2015-10-07", "2019-10-07", "Kaitlin", "k@ucalgary.ca");
		Property house1 = new Property("125", "Attached House", 2, 1, false, "SE", "Active", "2014-10-07", "2017-10-07", "Kaitlin", "k@ucalgary.ca");
		Property house2 = new Property("126", "Detached House", 3, 3, false, "NW", "Cancelled", "2014-10-07", "2017-10-07", "Zheng", "z@ucalgary.ca");
		Property townhouse1 = new Property("127", "Townhouse", 2, 2, false, "SW", "Suspended", "2014-10-07", "2018-10-07", "Brian", "b@ucalgary.ca");
		Property townhouse2 = new Property("128", "Townhouse", 3, 2, false, "NW", "Rented", "2014-10-07", "2017-10-07", "Zheng", "z@ucalgary.ca");
		// db.addProperty(apartment1);
		// db.addProperty(apartment2);
		// db.addProperty(house1);
		// db.addProperty(house2);
		// db.addProperty(townhouse1);
		// db.addProperty(townhouse2);

		Report r = db.getReport("2014-09-07", "2016-11-07");
		System.out.println(r.Display());
	}	
}