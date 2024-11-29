package Implementation;

import java.util.ArrayList;

public class LoginSession {
    private Theater theater;
    private ArrayList<String> mainCatalog;

    public Theater getTheater(){
        return theater;
    }

    // Constructor
    public LoginSession(boolean auth){
        mainCatalog = new ArrayList<String>();
        for(Movie movie: theater.getCatalog()){
            mainCatalog.add(movie.getName());
        }
        
        // User is registered -> they get both catalogs
        if(auth == true){
            for(Movie movie: theater.getUnreleasedCatalog()){
                mainCatalog.add(movie.getName());
            }
        }

    }

    
}
