

package Ozil;



public class User {
	protected String FirstName;
	protected String LastName;
	protected String EmailID; //should be alphanumeric
	protected int userID; //init when connecting to database
	
	
	
	
	
	
	public User(String firstName,String lastName,String emailID)
	{
		FirstName = firstName;
		LastName = lastName;
		EmailID = emailID;
	}
	/*
	 * set the name of the user
	 */
	public void Name(String firstName, String lastName)
	{
		FirstName = firstName;
		LastName = lastName;
	}
	
	
	/*
	 * 
	 */
	public boolean Registered(boolean Flag)
	{
		return Flag;
	}
	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("User name : "+FirstName+" "+LastName );
		System.out.println("User email: "+EmailID);
		System.out.println("User ID : "+userID);
		
		System.out.println("Your projects: ");
		
		System.out.println("\tFIXME: Replace with projects from sql");
	}
	
	public void setUserID(int newID)
	{
		userID = newID;
	}
	
	public int getID()
	{
		return userID;
	}
	
	
	public String getFullName()
	{
		return (this.FirstName + " " + this.LastName);
	}
	
	public String getFirstName()
	{
		return this.FirstName;
	}
	
	public String getLastName()
	{
		return this.LastName;
	}
	
	
}
