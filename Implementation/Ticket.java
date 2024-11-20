package Implementation;

public class Ticket {
    private Seat seat;
    private Movie movie;

    public void getSeat() {
        return this.seat;
    }

    public void getMovie() {
        return this.movie;
    }

    public Seat setSeat(Seat seat) {
        this.seat = seat;
    }

    public Movie setMovie(Movie movie) {
        this.movie = movie;
    }
}
