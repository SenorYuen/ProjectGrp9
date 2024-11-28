//Showtime.java


package Implementation;


public class Date {

	private String day;
	private String month;
	private int year;
	private String time;
	
	// Constructor
	public Date(String day, String month, int year, String time) {
		/**
		 * I assume this will be saved to the database if not then 
		 * we need a way to ensure the following:
		 * MONTH is a valid month
		 * DAY is a valid day depending on the month
		 * maybe ensure YEAR is a 4 digit number
		 */
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
	}

	// GETTERS and SETTERS
	public String getDay() {
		return this.day;

	}
	public String getMonth() {
		return this.month;
	}
	public int getYear() {
		return this.year;
	}
	public String getTime() {
		return this.time;
	}

	public void setDay(String selectedDay) {
		this.day = selectedDay;
	}
	public void setMonth(String selectedMonth) {
		this.month = selectedMonth;
	}
	public void setYear(int selectedYear) {
		this.year = selectedYear;
	}
	public void setTime(String selectedTime) {
		this.time = selectedTime;
	}


}
