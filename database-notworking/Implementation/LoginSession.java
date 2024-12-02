package Implementation;

import java.util.ArrayList;

public class LoginSession {
    private Theater theater;
    private ArrayList<String> movieNames;
    private String enteredUsername;
    private String enteredPassword;
    private boolean authenticated;

    public Theater getTheater(){
        return theater;
    }

    public boolean getAuthenticationStatus() {
        return this.authenticated;
    }

    public ArrayList<String> getMovieNames() {
        return this.movieNames;
    }

    public void setEnteredUsername(String username) {
        this.enteredUsername = username;
    }

    public void setEnteredPassword(String password) {
        this.enteredPassword = password;
    }

    // Constructor
    public LoginSession(boolean auth, String username, String password){
        this.enteredUsername = username;
        this.enteredPassword = password;
        this.authenticated = auth;
        movieNames = new ArrayList<String>();
        theater = TheaterPopulator.populateTheater();
        for(Movie movie: theater.getCatalog()){
            movieNames.add(movie.getName());
        }

        // User is registered -> they get both catalogs
        if(authenticated == true){
            for(Movie movie: theater.getUnreleasedCatalog()){
                movieNames.add(movie.getName());
            }
        }
    }
}
