//Showtime.java


package Implementation;


public class Date {

	private int day;
	private int month;
	private int year;
	private String time;

	// Constructor
	public Date(int day, int month, int year, String time) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
	}

	// GETTERS and SETTERS
	public int getDay() {
		return this.day;

	}
	public int getMonth() {
		return this.month;
	}
	public int getYear() {
		return this.year;
	}
	public String getTime() {
		return this.time;
	}

	public void setDay(int selectedDay) {
		this.day = selectedDay;
	}
	public void setMonth(int selectedMonth) {
		this.month = selectedMonth;
	}
	public void setYear(int selectedYear) {
		this.year = selectedYear;
	}
	public void setTime(String selectedTime) {
		this.time = selectedTime;
	}

	@Override
	public String toString(){
		return (String.valueOf(year) + '/' + String.valueOf(month) + '/' + String.valueOf(day) + " - " + time);
	}


}
