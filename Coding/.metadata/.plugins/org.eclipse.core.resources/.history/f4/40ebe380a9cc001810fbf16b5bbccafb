package Ozil;


import java.io.Serializable;
import java.util.ArrayList;

public class project  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 103686074430709125L;
	private String ProjectTitle;
	private String ProjectID;
	private int AuthorID;
	
	private String ProjectDescription;
	
	private ArrayList<Integer> userIDs;
	private ArrayList<Task> pendingTasks; //Should we add stories that contain tasks here? or just have projects->tasks
										  //or projects->stories->tasks let me know. 
	
	private ArrayList<Task> AcceptedTasks;
	
	project(String projectTitle, String projectID, int authorID)
	{
		ProjectTitle = projectTitle;
		setProjectID(projectID);
		setAuthorID(authorID);
	}
	
	//public functions
	
	public void reNameProject(String newProjectTitle)
	{
		ProjectTitle = newProjectTitle;
	}
	
	
	public String GetProjectName()
	{
		return ProjectTitle;
	}
	
	
	//if we add stories, just replace with addNewStory
	public void addNewTask(String taskTitle)
	{
		//task id is generated based on number of currently existing tasks :
			//-Problem: this means we might have multiple tasks with the same taskID, SOLVE. implement MAX task capacity?, add old task ids to a list or the db?
		pendingTasks.add(new Task(taskTitle,pendingTasks.size()));
	}
	
	public void approveTask(String taskTitle, int taskID)
	{
		for(int i=0;i<pendingTasks.size();i++)
		{
			if(pendingTasks.get(i).getTaskIdentifier() == taskID)
			{
				AcceptedTasks.add(pendingTasks.get(i));
				pendingTasks.remove(i);
			}
		}
	}
	
	
	
	
	public void  approveTask(String taskName, int taskID, int pos)
	{
		AcceptedTasks.add(pendingTasks.get(pos));
		pendingTasks.remove(pos);
	}
	
	
	/*
	 * remove task from the project entirely, regardless if on pending or accepted list
	 * 
	 *  
	 */
	public void removeTask(String taskName, int taskID)
	{
		Task removedTask  = null;
		int min = Math.min(AcceptedTasks.size(),pendingTasks.size());
		int max = Math.min(AcceptedTasks.size(),pendingTasks.size());
		for(int i = 0; i < max;i++)
		{
			if(AcceptedTasks.get(i).getTaskIdentifier() == taskID)
			{
				removedTask = AcceptedTasks.remove(i);
			}
			else if(pendingTasks.get(i).getTaskIdentifier() == taskID)
			{
				removedTask = pendingTasks.remove(i);
			}
		}
		
	}

	public String getProjectID() {
		return ProjectID;
	}

	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}

	public int getAuthorID() {
		return AuthorID;
	}

	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}

	public String getProjectDescription() {
		return ProjectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		ProjectDescription = projectDescription;
	}

	public ArrayList<Integer> getUserIDs() {
		return userIDs;
	}

	public void setUserIDs(ArrayList<Integer> userIDs) {
		this.userIDs = userIDs;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
