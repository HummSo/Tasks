package main.obj;

import java.util.ArrayList;
import java.util.List;

public class TaskWrap {
	
	private List<Task> tasks;
	
	public TaskWrap(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public TaskWrap() {
		this.tasks = new ArrayList<Task>();
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
