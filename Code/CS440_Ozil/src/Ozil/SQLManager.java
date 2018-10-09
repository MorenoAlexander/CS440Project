
package Ozil;

import java.sql.*;




public class SQLManager
{
	private static Connection con;
	private static boolean existingDB;
	private static String DataBaseTarget;
	private static  String PathToDataBase;
	
	//Path where we want 
	public SQLManager(String DataBaseName, String PATH){
		DataBaseTarget = DataBaseName;
		PathToDataBase = PATH;
		existingDB = false;
		
		
	}
	
	/*
	 * "SELECT fname, lname FROM users"
	 * 
	 * change from ResultSet to boolean
	 */
	public ResultSet checkForDB(String query)  {
		
		if(con == null)
		{
			//Check for our databases, could be remade for remote connection to a database later, for now we will use a two local databases
			try {
				getConnection();
				
			} catch (ClassNotFoundException | SQLException e) {
				existingDB = false;
				e.printStackTrace();
			}
		}
		
		Statement state = null;
		try {
			state = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet res = null;
		try {
			res = state.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void initializeDB() throws SQLException {
		if(!existingDB == true)
		{
			existingDB = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");
			
			if(!res.next() )
			{
				System.out.println("No Pre-Existing user database found, building new database now...");
				
				Statement state1 = con.createStatement();
				res = state1.executeQuery("CREATE TABLE users(userId integer,"
						+ "fName varchar(60),"
						+ "lName varchar(60),"
						+ "email varchar(60),"
						+ "password varchar(64),"
						+ "primary key(userId))"
						+ ";");
				res = state1.executeQuery("CREATE TABLE projects(projectId INTEGER,"
						+ " projectTitle VARCHAR(60),data BLOB, PRIMARY KEY(projectId); ");
				
				
				
				res = state1.executeQuery("CREATE TABLE user_projects("
						+ "index INTEGER,"
						+ "userId INTEGER NOT NULL,"
						+ "projectId INTEGER NOT NULL"
						+ "PRIMARY KEY(index),"
						+ "FOREIGN KEY(userId) REFERENCES users(userId),"
						+ "FOREIGN KEY(projectId) REFERENCES projects(projectID));");
			}
		}
		
		
		
		
	}
	
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:OzilUsersDB.db");
		//Add another line for project Database
		initializeDB();
		
	}
	
	
	
	
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

