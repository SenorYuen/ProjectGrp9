package Implementation;

import java.util.ArrayList;

public class LoginSession {
    private Theater theater;
    private ArrayList<String> mainCatalog;
    private String enteredUsername;
    private String enteredPassword;

    public Theater getTheater(){
        return theater;
    }

    // Constructor
    public LoginSession(boolean auth, String username, String password){
        this.enteredUsername = username;
        this.enteredPassword = password;
        mainCatalog = new ArrayList<String>();
        // for(Movie movie: theater.getCatalog()){
        //     mainCatalog.add(movie.getName());
        // }
        
        // User is registered -> they get both catalogs
        if(auth == true){
            for(Movie movie: theater.getUnreleasedCatalog()){
                mainCatalog.add(movie.getName());
            }
        }

    }

    
}
