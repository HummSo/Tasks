package main.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private boolean complete;
	
	public Task(String name) {
		this.name = name;
	}
	
	public Task(String name, boolean complete) {
		this.name = name;
		this.complete = complete;
	}
	
	public Task () {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	@Override
	public String toString() {
		return this.id+"_"+this.name+": "+(complete ? "done" : "WIP");
	}

}
