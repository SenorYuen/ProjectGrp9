//Showtime.java


package Implementation;


public class Showtime {

	private String day;
	private String month;
	private int year;
	private String time;

		
	public String currentDay() {
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
	
	public Showtime(String day, String month, int year, String time) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		
		
	}
	
}
