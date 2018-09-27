import Ozil.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
	
	private static Connection con;
	private static boolean existingDB =  false;
	
	
	
	
	
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
	
	private void addUser(String firstName, String lastName, String email) throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?, ?, ?, ?);");
		prep.setString(2, firstName);
		prep.setString(3, lastName);
		prep.setString(4, email);
		prep.execute();
	}
	



	public static void main(String[] args) {
		//TODO set up command line loop
		
		//
		System.out.println("Welcome to the Ozil Project Management software!");
		//connect to database
		if(con == null)
		{
			try {
				getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		
		
		System.exit(0);
	}

}
