/*
* File Name: Ticket.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/

package Implementation;

public class Ticket {
    private Seat seat;
    private Movie movie;
    private Theater theater;

    // Constructor
    public Ticket(Seat seat, Movie movie, Theater theater){
        this.seat = seat;
        this.movie = movie;
        this.theater = theater;
    }

    // GETTERS and SETTERS
    public Seat getSeat() {
        return this.seat;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater(){
        return this.theater;
    }

    public void setTheater(Theater theater){
        this.theater = theater;
    }
    


}
