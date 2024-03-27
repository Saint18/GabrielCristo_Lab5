/*
 * /******
Name: Gabriel Cristo
Assignment: Lab 5
Date: 3/27
******/
import java.time.LocalDate;

public class Task implements Comparable<Task>{

	int id;
	String description;
	Date date;
	Boolean completed;
	Priority priority;

	public Task(int id, String description, Date date, Boolean completed, Priority priority) {
		this.id = id;
		this.description = description;
		this.date = date;
		this.completed = completed;
		this.priority = priority;

	}

	@Override
	public String toString() {
		String result = "";
		result += "Task ID:" + this.id + "\n";
		result += "Task Description:" + this.description + "\n";
		result += "Task Date:" + this.date + "\n";
		result += "Task Completion:" + this.completed + "\n";
		result += "Task Priority Level:" + this.priority + "\n";
		result += "---------------------";
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int newValue) {
		this.id = newValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String newValue) {
		this.description = newValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date newValue) {
		this.date = newValue;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean newValue) {
		this.completed = newValue;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority newValue) {
		this.priority = newValue;
	}

	public Boolean expired() {
		if (LocalDate.now().getYear() < this.date.getYear()) {
			return false;
		}
		if (LocalDate.now().getMonthValue() < this.date.getMonth()) {
			return false;
		}
		if (LocalDate.now().getDayOfMonth() < this.date.getDay()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Task) {

			Task other = (Task) obj;
			if (this.description.equals(other.description) 
					&& this.date.equals(other.date)
					&& this.completed == other.completed 
					&& this.priority == other.priority)
				return true;

		}
		return false;
	}
  
	public int compareTo(Task other){
	      
	      if(this.priority.getValue() < other.priority.getValue()){
	        		
	        return 1;
	      }
	      else if(this.priority.getValue() > other.priority.getValue()){
	        return -1;
	      }
	      else{
	        return 0;
	        
	      }
	      
	    }

  
}
