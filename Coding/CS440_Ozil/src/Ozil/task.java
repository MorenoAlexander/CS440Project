package Ozil;

public class task {
	
	
	private String TaskName;
	private String TaskDescription;
	private int TaskID;
	private boolean TaskScheduled;
	
	
	
	task(String taskName,  int taskID){
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
