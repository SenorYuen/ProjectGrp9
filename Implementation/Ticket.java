package Implementation;

public class Ticket {
    private Seat seat;
    private Movie movie;

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
}