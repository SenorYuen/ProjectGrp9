package Implementation;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoginSession {
    private ArrayList<String> movieNames;
    private boolean authenticated;
    private Person currentUser;

    public boolean getAuthenticationStatus() {
        return this.authenticated;
    }

    public ArrayList<String> getMovieNames() {
        return this.movieNames;
    }

    public void setEnteredUsername(String username) {
        this.currentUser.setEmail(username);
    }

    public void setEnteredPassword(String password) {
        this.currentUser.setPassword(password);
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    // If the user is registered, obtain card number.
    public String getCard() {
        if (currentUser instanceof RegisteredCustomer) {
            RegisteredCustomer currentReg = (RegisteredCustomer) this.currentUser;
            return currentReg.getCardNumber();
        }
        return "";
    }

    public LocalDate getAccountCreationDate() {
        if (currentUser instanceof RegisteredCustomer) {
            RegisteredCustomer currentReg = (RegisteredCustomer) this.currentUser;
            return currentReg.getLocalDate();
        }
        return LocalDate.now();
    }


    // Constructor
    public LoginSession(boolean auth, String username, String password, Theater theater){
        this.authenticated = auth;
        if (this.authenticated) {
            for (RegisteredCustomer user: theater.getUserStorage()) {
                if ((user.getEmail().equals(username)) && (user.getPassword().equals(password))) {
                    currentUser = user;
                }
            }
        }
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
