/*
 * /******
Name: Gabriel Cristo
Assignment: Lab 5
Date: 3/27
******/

public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		if (!validDay(day, month, year)) {
			throw new IllegalArgumentException("invalid date");
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int newValue) {
		if (!validDay(newValue, this.month, this.year)) {
			throw new IllegalArgumentException("invalid date");
		}
		this.day = newValue;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int newValue) {
		if (!validDay(this.day, month, this.year)) {
			throw new IllegalArgumentException("invalid month");
		}
		this.month = newValue;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int newValue) {
		if (!validDay(this.day, this.month, newValue)) {
			throw new IllegalArgumentException("invalid year");
		}
		this.year = newValue;
	}

	public void printDate() {
		System.out.println("Date:" + this.month + "," + this.day + "," + this.year);
	}

	private static boolean validDay(int day, int month, int year) {
		if (day <= 0)
			return false;
		if (month <= 0 || month > 12)
			return false;
		if (year < 0)
			return false;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return day <= 31;
		case 4:
		case 6:
		case 9:
			return day <= 30;
		case 2:
			return day <= 28;
		}
		return true;
	}

	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}




@Override
public boolean equals(Object obj){
  if(obj instanceof Date){
  	Date other = (Date) obj;
  
  if(this.day == other.day && this.month == other.month
  && this.year == other.year) 
    	return true;
}
	return false;
}
}
