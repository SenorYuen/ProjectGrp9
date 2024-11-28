package Implementation;

import java.util.ArrayList;

/*
    File Name: Movie.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Movie {
    private String name;
    private int length;
    private ArrayList<Date> showtimes;

    // Constructor
    public Movie(String name, int length, ArrayList<Date> showtimes) {
        this.name = name;
        this.length = length;
        this.showtimes = showtimes;
    }

    // GETTERS and SETTERS
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int newLength) {
        this.length = newLength;
    }

    public ArrayList<Date> getShowtimes(){
        return this.showtimes;
    }

    public void setShowtimes(ArrayList<Date> newShowtimes){
        this.showtimes = newShowtimes;
    }

    // end of GETTERS and SETTERS

}
