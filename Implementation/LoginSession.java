package Implementation;

import java.util.ArrayList;

public class LoginSession {
    private Theater theater;
    private ArrayList<String> mainCatalog;
    private String enteredUsername;
    private String enteredPassword;
    private boolean authenticated;

    public Theater getTheater(){
        return theater;
    }

    public boolean getAuthenticationStatus() {
        return this.authenticated;
    }

    // Constructor
    public LoginSession(boolean auth, String username, String password){
        this.enteredUsername = username;
        this.enteredPassword = password;
        this.authenticated = auth;
        mainCatalog = new ArrayList<String>();
        // for(Movie movie: theater.getCatalog()){
        //     mainCatalog.add(movie.getName());
        // }
        
        // User is registered -> they get both catalogs
        // if(authenticated == true){
        //     for(Movie movie: theater.getUnreleasedCatalog()){
        //         mainCatalog.add(movie.getName());
        //     }
        // }

    }

    
}
