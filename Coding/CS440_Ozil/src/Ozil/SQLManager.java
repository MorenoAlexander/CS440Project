package Ozil;
import java.sql.*;

public class SQLManager {
	private static Connection con;
	private static boolean existingDB;
	
	
	
	SQLManager(String dbFileName, String URL)
	{ 
		existingDB = false;
	}
	
	
	
	
	public ResultSet checkForDB()  {
		
		if(con == null)
		{
			//Check for our databases, could be remade for remote connection to a database later, for now we will use a two local databases
			try {
				getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				
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
			res = state.executeQuery("SELECT fname, lname FROM users");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:OzilUsersDB.db");
		//Add another line for project Database
		initalize();
		
	}
	
	
	
	
	
	private static void initalize() throws SQLException {
		if(!existingDB == true)
		{
			existingDB = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");
			
			if(!res.next() )
			{
				System.out.println("No Pre-Existing user database found, building new database now...");
				
				Statement state1 = con.createStatement();
				state1.executeQuery("CREATE TABLE users(id integer,"
						+ "fName varchar(60),"
						+ " lName varchar(60),"
						+ " email varchar(60),"
						+ " primary key(id));");
				state1.executeQuery("CREATE TABLE projects(id integer,"
						+ "projectTitle varchar(60), ");
				
				
			}
		}
	}
}
