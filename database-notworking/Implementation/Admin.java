/*
* File Name: Admin.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/
package Implementation;

import java.util.ArrayList;

public class Admin {
    private String name;
    private String email;
    private String password;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String desiredName) {
        this.name = desiredName;
    }

    public void setEmail(String desiredEmail) {
        this.email = desiredEmail;
    }

    public void setPassword(String desiredPassword) {
        this.password = desiredPassword;
    }

    // I dunno if we should add a check if the movie isn't already added
    public void addMovie(Movie movie, Theater theater){
        /* this function is designed so that the new movie object is already
            made which i dont know if that's how we want to design it like that 
        */ 
        ArrayList<Movie> catalog = theater.getCatalog();

        catalog.add(movie);
    }

    /**
     * 
     * @param movieRemoved the movie the admin wants to remove
     * @param theater
     */
    public void removeMovie(Movie movieRemoved, Theater theater){
        ArrayList<Movie> catalog = theater.getCatalog();
        for(Movie movie : catalog){
            if(movie.getName() == movieRemoved.getName()){
                catalog.remove(movie);
            }
        }
    }


    // Constructor
    // TODO: ensure there's one copy only --> Singleton?
    Admin() {
        this.name = "Default";
        this.email = "Default";
        this.password = "Default";
    }
}

