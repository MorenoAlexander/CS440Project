
package Ozil;

import java.sql.*;
import java.util.ArrayList;




public class SQLManager
{
	private static Connection con;
	private static boolean existingDB;
	private static String DataBaseTarget;
	private static String PathToDataBase;
	private static ArrayList<String> tables;
	
	//Path where we want 
	public SQLManager(String DataBaseName, String PATH, ArrayList<String> Tables){
		DataBaseTarget = DataBaseName; //Set the name of our database
		PathToDataBase = PATH; //its path
		existingDB = false;  //initially false
		ArrayList<String> tables = Tables;
		
		try{
			
		getConnection() //get a connection to our db, sets con value
		existingDB = checkForDB()
		initializeDB();	
		}
		catch(SQLException e)
		{	//call init
			//e.printStackTrace(e)
		}
		catch(ClassNotFoundException c)
		{
			
		}
		
		
		
	}
	
		   
	public boolean dbExists()
	{
		return existingDB;
	}
	
	
	
	
	/*
	 * "SELECT fname, lname FROM users"
	 * 
	 * change from ResultSet to boolean
	 */
	public boolean checkForDB()  
	{
		
		if(con != null)
		{
			
			ResultSet resultSet = connection.getMetaData().getCatalogs();

			//iterate each catalog in the ResultSet
			while (resultSet.next()) 
			{
  				// Get the database name, which is at position 1
  				String dbName = resultSet.getString(1);
				if(dbName == DataBaseTarget)
				{
					return true;
				}
				
		}
			return false;
			resultSet.close();
	}
	
		
	public static void initializeDB(String initQuery, String[] ) throws SQLException {
		if(!existingDB == true)
		{
			existingDB = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");
			
			if(!res.next() )
			{
				
				System.out.println("building new database now...");
				
				Statement state1 = con.createStatement();
				res = state1.executeQuery(
				
				
				
			}
			else
			{
				System.out.println("These tables seem to exist. Canceling new Database creation");
			}
		
		
		
		
	}
	
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:" + DataBaseTarget);
		//Add another line for project Database
		//initializeDB();
		
	}
	
	
	
	//This function is what we will use to execute most of our query requests.
	public ResultSet executeQuery(String query) throws SQLException
	{
		ResultSet set;
		Statement state;
		
		state = con.createStatement();
		set = state.executeQuery(query);
		return set; 
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

