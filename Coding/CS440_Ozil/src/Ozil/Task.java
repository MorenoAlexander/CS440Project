package Ozil;

public class Task implements Serializable{
	
	
	private String TaskName;
	private String TaskDescription;
	private int TaskID;
	private boolean TaskScheduled;
	
	
	
	public Task(String taskName,  int taskID){
		TaskName = taskName;
		TaskID = taskID;
		TaskScheduled = false; //default
		
	}
	


	public int getTaskIdentifier()
	{
		return TaskID;
		
	}
	
	
	
	public void renameTask(String newName)
	{
		TaskName = newName;
	}
	
	public void setTaskScheduled(boolean flag)
	{
		TaskScheduled = flag;
	}
	
	public boolean getTaskScheduled()
	{
		return TaskScheduled;
	}
}
