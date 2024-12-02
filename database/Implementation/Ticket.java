/*
* File Name: Ticket.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

package Implementation;

public class Ticket {
    private String seatNumber;
    private String movieName;
    private String theaterLocation;

    // Constructor
    public Ticket(String seatNumber, String movieName, String theaterLocation){
        this.seatNumber = seatNumber;
        this.movieName = movieName;
        this.theaterLocation = theaterLocation;
    }

    // GETTERS and SETTERS
    public String getSeatNumber() {
        return this.seatNumber;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTheaterLocation(){
        return this.theaterLocation;
    }



}
