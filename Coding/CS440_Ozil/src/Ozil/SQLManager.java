
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
	public SQLManager(String DataBaseName, String initQuery, ArrayList<String> Tables){
		DataBaseTarget = DataBaseName; //Set the name of our database
		existingDB = false;  //initially false
		tables = Tables;
		
		try{
			
		getConnection(); //get a connection to our db, sets con value
		existingDB = checkForDB();
		initializeDB(initQuery);	
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
	public static boolean checkForDB() throws SQLException  
	{
		
		if(con != null)
		{
			
			ResultSet resultSet = con.getMetaData().getCatalogs();

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
		}
		return false;
	}
	
		
	public static void initializeDB(String initQuery) throws SQLException {
		if(!existingDB == true)
		{
			existingDB = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery(initQuery);
			
			if(!res.next() )
			{
				
				System.out.println("building new database now...");
				
				Statement state1 = con.createStatement();
				
				for(int i = 0; i<tables.size();i++)
				{
					res = state1.executeQuery(tables.get(i));
				}
				
				
				
				
			}
			else
			{
				System.out.println("These tables seem to exist. Canceling new Database creation");
			}
		
		
		
		
		}
	}
	
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:" + DataBaseTarget);
		//Add another line for project Database
		//initializeDB();
		
	}
	
	
	
	//This function is what we will use to execute most of our query requests.
	public static ResultSet executeQuery(String query) throws SQLException
	{
		ResultSet set;
		Statement state;
		
		state = con.createStatement();
		set = state.executeQuery(query);
		return set; 
	}

	public static Connection getCon() throws ClassNotFoundException, SQLException {
		if(con == null)
		{
			getConnection();
		}
		
		return con;
	}

	public static void setCon(Connection conn) {
		con = conn;
	}


	public static PreparedStatement preparedStatement(String string) throws SQLException {
		// TODO Auto-generated method stub
		
		return con.prepareStatement(string);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
