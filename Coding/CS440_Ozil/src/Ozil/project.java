package Ozil;

import java.io.Serializable;
import java.util.ArrayList;

public class project  implements Serializable {
	
	private String ProjectTitle;
	private String ProjectID;
	private int AuthorID;
	
	private String ProjectDescription;
	
	private ArrayList<Integer> userIDs;
	private ArrayList<task> pendingTasks; //Should we add stories that contain tasks here? or just have projects->tasks
										  //or projects->stories->tasks let me know. 
	
	private ArrayList<task> AcceptedTasks;
	
	project(String projectTitle, String projectID, int authorID)
	{
		ProjectTitle = projectTitle;
		ProjectID = projectID;
		AuthorID = authorID;
	}
	
	//public functions
	
	public void reNameProject(String newProjectTitle)
	{
		ProjectTitle = newProjectTitle;
	}
	
	
	//if we add stories, just replace with addNewStory
	public void addNewTask(String taskTitle)
	{
		//task id is generated based on number of currently existing tasks :
			//-Problem: this means we might have multiple tasks with the same taskID, SOLVE. implement MAX task capacity?, add old task ids to a list or the db?
		pendingTasks.add(new task(taskTitle,pendingTasks.size()));
	}
	
	
	public void  approveTask(String taskName, int taskID, int pos)
	{
		AcceptedTasks.add(pendingTasks.get(pos));
		pendingTasks.remove(pos);
	}
	
	public void removeTask(String taskName, int taskID, int pos)
	{
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
