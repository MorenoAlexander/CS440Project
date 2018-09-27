

package Ozil;



public class user {
	private String FirstName;
	private String LastName;
	private String EmailID; //should be alphanumeric
	
	
	
	
	
	
	user(String firstName,String lastName,String emailID)
	{
		
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
	
	
	
}
