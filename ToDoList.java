/*
 * /******
Name: Gabriel Cristo
Assignment: Lab 5
Date: 3/27
******/
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.BiFunction;
public class ToDoList {
    private Node first;

    // constructor
    public ToDoList() {
        this.first = null;
    }
    
    public void map(TaskFunction taskFunction) {
        Node currNode = this.first;
        while (currNode != null) {
            Task oldTask = currNode.getTask();
            Task newTask = taskFunction.process(oldTask); 
            currNode.setTask(newTask); 
            currNode = currNode.getNext(); 
        }
    }


    public HashMap<Integer, Task> getMap() {
        HashMap<Integer, Task> map = new HashMap<>();

        Node currNode = first;
        while (currNode != null) {
            Task task = currNode.getTask(); 	// get task from node
            map.put(task.getId(), task);		// add task to map
            currNode = currNode.getNext();      // traverse to next node
        }

        return map;
    }

    // add a task
    private void addNodeToEnd(Node list, Node nodeToAdd) {
        if (list.getNext() == null) {
            list.setNext(nodeToAdd);
        } else {
            addNodeToEnd(list.getNext(), nodeToAdd);
        }
    }

    public void addTask(Task newTask){
    	   Node newNode = new Node(newTask);

    	        if (first == null) {
    	            first = newNode;
    	        } else {
    	            addNodeToEnd(first, newNode);
    	        }
    	    }
    	  
    	  

    	public void addTask(int id, String description, Date date, Boolean completed, Priority priority) {
    	        Task newTask = new Task(id, description, date, completed, priority);
    	        addTask(newTask);

    	        
    	    }

    // remove
    public void removeTask(Node prev, Node curr, int id) {
        if (curr == null) {
            return;
        } else if (curr.getTask().getId() == id) {
            if (prev == null) {
                first = first.getNext();
            } else {
                prev.setNext(curr.getNext());
            }
            return;
        }

        removeTask(curr, curr.getNext(), id);
    }

    // remove
    public void removeTask(int id) {
        removeTask(null, first, id);
    }

    // count all
    public int countCompletedTasks() {
        return this.countCompletedTasks(first, 0); // calling recursive helper
    }

    public int countCompletedTasks(Node node, int completedTasks) {

        if(node == null){
            return completedTasks;
        }

        if(node.getTask().getCompleted()){
            ++completedTasks;
        }

        return countCompletedTasks(node.getNext(), completedTasks);
    }

    public int countExpiredTasks() {
        return this.countExpiredTasks(first, 0); // calling recursive helper

    }

    public int countExpiredTasks(Node node, int expiredTasks) {
        if(node == null){
            return expiredTasks;
        }

        if(node.getTask().expired()){
            ++expiredTasks;
        }

        return countExpiredTasks(node.getNext(), expiredTasks);
    }

    // count all
    public int countAllTasks() {
        return this.countAllTasks(first, 0); // calling recursive helper
    }

    public int countAllTasks(Node node, int allTasks) {
        if(node == null){
            return allTasks;
        }

        ++allTasks;

        return countAllTasks(node.getNext(), allTasks);
    }

    public void changeDate(int id, Date newDate){
        changeDate(first, id, newDate);
    }

    // change date
    public void changeDate(Node currNode, int id, Date newDate) {
        if (currNode != null) {
            if (currNode.getTask().getId() == id) {
                currNode.getTask().setDate(newDate);
                return;
            }
            changeDate(currNode.getNext(), id, newDate);

        }
    }

    public void removeAll() {
        this.first = null;
    }

    // remove all, expired, completed

    public void removeExpired() {
        Node currNode = this.first;
        Node prevNode = null;
        while (currNode != null) {
            if (currNode.getTask().expired() == true) {
                prevNode.setNext(currNode.getNext());
                currNode = currNode.getNext();
            } else {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }
    }

    public void removeCompleted() {
        Node currNode = this.first;
        Node prevNode = null;
        while (currNode != null) {
            if (currNode.getTask().getCompleted() == true) {
                prevNode.setNext(currNode.getNext());
                currNode = currNode.getNext();
            } else {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }
    }

    public void printAllTasks() {
        printAllTasks(first);
    }

    public void printAllTasks(Node currNode) {
        if (currNode != null) {
            System.out.println(currNode.getTask());
            printAllTasks(currNode.getNext());
        }
    }

    public void printPriorityTasks(Priority priority) {
        printPriorityTasks(first, priority);
    }

    public void printPriorityTasks(Node currNode, Priority priority) {
        if (currNode != null) {
            if (currNode.getTask().getPriority() == priority) {
                System.out.println(currNode.getTask());
            }
            printPriorityTasks(currNode.getNext(), priority);
        }
    }

    public void printExpiredTasks() {
        printExpiredTasks(first);
    }

    public void printExpiredTasks(Node currNode) {
        if (currNode != null) {
            if (currNode.getTask().expired() == true) {
                System.out.println(currNode.getTask());
            }
            printExpiredTasks(currNode.getNext());
        }
    }

    private Task getTask(Node beginFrom, int id) {
        if(beginFrom == null){
            return null;
        }

        if(beginFrom.getTask().getId() == id){
            return beginFrom.getTask();
        }

        return getTask(beginFrom.getNext(), id);
    }

    public Task getTask(int id) {
        return getTask(first, id);
    }
    public LinkedList<Task> filterTasks(TaskFilter filter) {
        LinkedList<Task> filteredTasks = new LinkedList<>();
        Node currNode = first;
        while (currNode != null) {
            Task task = currNode.getTask();
            if (filter.filter(task)) {
                filteredTasks.add(task);
            }
            currNode = currNode.getNext();
        }
        return filteredTasks;
    }
    public <U> U fold(U initial, BiFunction<U, Task, U> folder) {
        Node currNode = first;
        U accumulator = initial;
        while (currNode != null) {
            accumulator = folder.apply(accumulator, currNode.getTask());
            currNode = currNode.getNext();
        }
        return accumulator;
    }
}
