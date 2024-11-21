//Showtime.java


package Implementation;


public class Showtime {

	private String day;
	private String month;
	private int year;
	private String time;


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

	public Showtime(String day, String month, int year, String time) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;


	}

}
