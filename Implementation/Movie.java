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
    private Date showtime;
    private ArrayList<Seat> seatMap;

    // Constructor
    /**
     *
     * @param name name of the movie
     * @param length length of the movie in minutes
     * @param showtime date the movie is coming to theaters
     * @param seatMap map of the seat
     */
    public Movie(String name, int length, Date showtime, ArrayList<Seat> seatMap) {
        this.name = name;
        this.length = length;
        this.showtime = showtime;
        this.seatMap = new ArrayList<Seat>();

        for(Seat seat: seatMap){
            this.seatMap.add(new Seat(seat));
        }

    }

    // GETTERS and SETTERS
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public ArrayList<Seat> getSeatMap(){
        return seatMap;
    }

    public void setSeatMap(ArrayList<Seat> newSeatMap){
        this.seatMap = newSeatMap;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int newLength) {
        this.length = newLength;
    }

    public Date getShowtime(){
        return this.showtime;
    }

    public void setShowtime(Date showtime){
        this.showtime = showtime;
    }

    // end of GETTERS and SETTERS

}
