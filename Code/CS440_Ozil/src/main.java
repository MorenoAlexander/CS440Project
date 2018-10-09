import Ozil.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
	
	private static Connection con;
	private static boolean existingDB =  false;
	//private static SQLManager dbInterface = new SQLManager("jdbc:sqlite:OzilUsersDB.db")
	
	

	
	
	


	
	
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


	/*
	 * Initialize an empty data base
	 */
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
				state1.executeQuery("CREATE TABLE users(userId integer,"
						+ "fName varchar(60),"
						+ "lName varchar(60),"
						+ "email varchar(60),"
						+ "password varchar(64),"
						+ "primary key(userId))"
						+ ";");
				state1.executeQuery("CREATE TABLE projects(projectId INTEGER,"
						+ " projectTitle VARCHAR(60),data BLOB, PRIMARY KEY(projectId); ");
				
				
				
				state1.executeQuery("CREATE TABLE user_projects("
						+ "index INTEGER,"
						+ "userId INTEGER NOT NULL,"
						+ "projectId INTEGER NOT NULL"
						+ "PRIMARY KEY(index),"
						+ "FOREIGN KEY(userId) REFERENCES users(userId),"
						+ "FOREIGN KEY(projectId) REFERENCES projects(projectID));");
			}
		}
		
		
		
		
	}
	
	
	/**
	 * Addes a new user to the database
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static int  addUser(String firstName, String lastName, String email,String passwrd) throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO users values(?, ?, ?, ?, ?);");
		prep.setString(2, firstName);
		prep.setString(3, lastName);
		prep.setString(4, email);
		prep.setString(5, passwrd);
		prep.execute();
		
		
		ResultSet id = null;
		Statement st = con.createStatement();
		id = st.executeQuery("SELECT userId FROM users WHERE fName ='"+firstName + "'AND lName = '"+lastName+"' AND email='"+email+"';");
		//sql query to return user ID
		
		return id.getInt(1);
	}
	
	
	
	
	
	/***
	 * Check for valid email format
	 */
	public static boolean validateEmail(String emailstr)
	{
		//email regex
		Pattern pat = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$");
		
		
		if(emailstr == null)
		{
			return false;
		}
		
		
		return pat.matcher(emailstr).matches();
	}
	
	
	
	
	/**
	 * 
	 * 
	 * Returns: a user object for the new user, or null for exit
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private static User registerCurrentUser(Scanner input) throws ClassNotFoundException, SQLException
	{
		User newCurrentUser =  null;
		String UserInput;
		int UserInputNumerical=0;
		String userEmail = "user@address.com";
		boolean validEmail = false;
		String userPassword;
		
		while (newCurrentUser == null){
			System.out.println("Sign in or sign up:");
			System.out.println("1. Sign in\n2. sign up\n3. exit");
			UserInput = input.nextLine();
			
			
			try {
				UserInputNumerical = Integer.parseInt(UserInput);
			}
			catch(NumberFormatException e)
			{
				//don't do anything
			}
			
			if (UserInput.toLowerCase().equals("signin") || UserInputNumerical == 1)
			{

				//get username and password
				//if corresponding username and password exist in database, create a user object for that

				
				//Enter email verification
				do {
					System.out.print("Enter your email: ");
					userEmail = input.nextLine();
					validEmail = validateEmail(userEmail);
					
					if(!validEmail)
					{
						System.out.println("Please enter a valid email address: ");
					}
				}
				while(validEmail == false);
				
				
				System.out.print("Enter password: ");
				userPassword = input.nextLine();
				
				//Do sql query 
				
				
				//sqlmanager.executeQuery();
				
				ResultSet set = null;
				Statement statement  = con.createStatement();
				set = statement.executeQuery("SELECT password,fName,lName,userId FROM users WHERE email= '"+ userEmail + "'; ");
				if(set.getString(1).isEmpty() == true)
				{
					System.out.println("No user with such email.");
					
				}
				else if(set.getString(1).equals(userPassword))
				{
					newCurrentUser = new User(set.getString(2),set.getString(3),userEmail);
					newCurrentUser.setUserID(Integer.parseInt(set.getString(4)));
				}
				else
				{
					System.out.println("Entered the wrong password.");
				}
				
				
				
				
				
				
				
				return newCurrentUser;
				
			}
			else if(UserInput.toLowerCase().equals("signup")|| UserInputNumerical == 2)
			{
				//Get user information
				System.out.print("Enter your first name: ");
				String firstName = input.nextLine();
				System.out.print("Enter your last name: ");
				String lastName = input.nextLine();
				
				
				//email validation
				do {
					System.out.print("Enter your email: ");
					userEmail = input.nextLine();
					validEmail = validateEmail(userEmail);
					
					if(!validEmail)
					{
						System.out.println("Please enter a valid email address: ");
					}
				}
				while(validEmail == false);
				
				
				
				System.out.print("Enter your intended password : ");
				String passwrd = input.nextLine();
				
				
				
				int userid = addUser(firstName,lastName,userEmail,passwrd); //add the user to the database
				newCurrentUser = new User(firstName,lastName,userEmail);
				newCurrentUser.setUserID(userid);
				//add them to sql database
				
				
				
				return newCurrentUser;
				
				
			}
			else if(UserInput.toLowerCase().equals("exit") || UserInputNumerical == 3)
			{
				
				return null;
			}
			
			//end of while loop
			}
		
		
		return newCurrentUser;
	}

	
	static public String[] getCommand(Scanner input)
	{
		String command = "";
		System.out.print(">>\n");
		command = input.nextLine();
		String[] cargsv = command.split(" ");
		return cargsv;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//TODO set up command line loop
		Scanner userInputStream = new Scanner(System.in);
		User currentUser =  null;
		String UserInput;
		int UserInputNumerical;
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
		
		
		//Signin/signup
		currentUser = registerCurrentUser(userInputStream);
		System.out.println("Welcome" + currentUser.getFullName() + ".");
		
		
		String[] cargsv = getCommand(userInputStream);
		while(!cargsv[0].toLowerCase().equals("exit"))
		{
			switch(cargsv[0].toLowerCase())
			{
				
			}
		}
		
		
		System.out.print("Exiting Ozil Application...");
		userInputStream.close();
		System.exit(0);
	}





}



















