/*
 * /******
Name: Gabriel Cristo
Assignment: Lab 5
Date: 3/27
******/
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.function.BiFunction;
public class Main {
	public static void main(String[] args) {
		ToDoList tdl = new ToDoList();
		// addTask(int id, String description, Date date, Boolean completed, Priority
		// priority)
		tdl.addTask(101, "example 1", new Date(1, 4, 2029), false, Priority.HIGH);
		tdl.addTask(102, "example 2", new Date(2, 1, 2024), false, Priority.MEDIUM);
		tdl.addTask(103, "example 3", new Date(3, 4, 2000), false, Priority.LOW);
		tdl.addTask(104, "example 4", new Date(3, 4, 2025), false, Priority.LOW);
		tdl.addTask(105, "example 5", new Date(3, 4, 2000), false, Priority.LOW);
		tdl.addTask(106, "example 5", new Date(3, 4, 2000), false, Priority.LOW);

		System.out.println("TEST: COMPARE TASK METHOD");
		System.out.println(tdl.getTask(102).compareTo(tdl.getTask(101)));

		Date date1 = new Date(3, 4, 2000);
		Date date2 = new Date(3, 4, 2000);
		System.out.println("TEST: EQUALS DATE METHOD: " + date1.equals(date2));

		System.out.println("TEST: EQUALS TASK METHOD");
		System.out.println(tdl.getTask(105).equals(tdl.getTask(106)));

		System.out.println("TEST: PRINT MAP");
		Map<Integer, Task> map = tdl.getMap();
		System.out.println(map);

		System.out.println("TEST: PRINT ALL TASKS");
		tdl.printAllTasks();
		System.out.println();

		System.out.println("TEST: REMOVE TASK #103");
		tdl.removeTask(103);
		tdl.printAllTasks();
		System.out.println();

		System.out.println("TEST: Mark Tasks As Complete");
		tdl.getTask(102).setCompleted(true);
		tdl.getTask(105).setCompleted(true);

		// Print out the result of countCompletedTasks
		System.out.println("TEST: COUNT COMPLETED TASKS");
		System.out.println(tdl.countCompletedTasks());

		// countExpiredTasks
		System.out.println("TEST: COUNT EXPIRED TASKS");
		System.out.println(tdl.countExpiredTasks());

		// countAllTasks
		System.out.println("TEST: COUNT ALLTASKS");
		System.out.println(tdl.countAllTasks());

		// remove expired
		System.out.println("TEST: REMOVE EXPIRED");
		tdl.removeExpired();
		tdl.printAllTasks();

		// remove completed
		System.out.println("TEST: REMOVE COMPLETED");
		tdl.removeCompleted();
		tdl.printAllTasks();

		// printPriorityTasks
		System.out.println("TEST: PRINTING");
		tdl.printPriorityTasks(Priority.HIGH);
		tdl.printExpiredTasks();
		tdl.printAllTasks();

		// Change task #105's date
		System.out.println("TEST: CHANGE DATE");
		Date date = new Date(2, 1, 2023);
		tdl.changeDate(104, date);
		tdl.printAllTasks();

		System.out.println("TEST: CSV");
		readCsv("task.csv", tdl); // fix
		tdl.printAllTasks();

		System.out.println("TEST: MAP");
		TaskFunction priority = (t) -> {
			Task modifiedTask = new Task(t.getId(), t.getDescription(), t.getDate(), t.getCompleted(), Priority.LOW);
			return modifiedTask;
		};
		tdl.map(priority);
		tdl.printAllTasks();

		System.out.println("TEST: FILTER");
		TaskFilter lowPriorityFilter = task -> task.getPriority() == Priority.LOW;
		LinkedList<Task> lowPriorityTasks = tdl.filterTasks(lowPriorityFilter);
		System.out.println("Low Priority Tasks:");
		for (Task task : lowPriorityTasks) {
			System.out.println(task);
		}
		
		System.out.println("TEST: FOLD");
		BiFunction<Integer, Task, Integer> taskCounter = (count, task) -> count + 1;
		int numberOfTasks = tdl.fold(0, taskCounter);
		System.out.println("Number of tasks: " + numberOfTasks);
	}



	public static void readCsv(String filePath, ToDoList tdl) {

		try {

			Scanner scnr = new Scanner(new File(filePath));

			while (scnr.hasNext()) {

				String line = scnr.nextLine(); 
				String[] fields = line.split(",");

				Task t = new Task(Integer.parseInt(fields[0]), fields[1],
						new Date(Integer.parseInt(fields[2].split("/")[0]), Integer.parseInt(fields[2].split("/")[1]),
								Integer.parseInt(fields[2].split("/")[2])),
						Boolean.valueOf(fields[3]), Priority.valueOf(fields[4]));
				tdl.addTask(t);
			} 
			scnr.close();
		} catch (FileNotFoundException e) {

			System.out.println("an error ocurred");
		}

	}

}