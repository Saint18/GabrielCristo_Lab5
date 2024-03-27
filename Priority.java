/*
 * /******
Name: Gabriel Cristo
Assignment: Lab 5
Date: 3/27
******/ 
enum Priority {

	HIGH (3), 
    MEDIUM (2), 
    LOW (1);

    public final int priority;
 
    Priority(int priority){
      this.priority = priority;
    }
    public int getValue() {
        return priority;
    }
 }