

package Ozil;



public class User {
	private String FirstName;
	private String LastName;
	private String EmailID; //should be alphanumeric
	private int userID; //init when connecting to database
	
	
	
	
	
	
	public User(String firstName,String lastName,String emailID)
	{
		FirstName = firstName;
		LastName = lastName;
		EmailID = emailID;
	}
	/*
	 * set the name of the user and
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
	
	
	
}
