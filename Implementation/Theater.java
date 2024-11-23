package Implementation;

import java.util.ArrayList;

/*
    File Name: Theater.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Theater{
    private ArrayList<Movie> catalog;
    private String location;
    private ArrayList<Seat> seatMap;


    // Constructor
    public Theater(ArrayList<Movie> catalog, String location, ArrayList<Seat> seatMap){
        this.catalog = new ArrayList<Movie>();
        this.location = location;
        this.seatMap = new ArrayList<Seat>(); // I can already seeing this being a problem for the "admin" to add in but thats a later issue
    }

    // GETTERS and SETTERS
    public ArrayList<Movie> getCatalog(){
        return catalog;
    }

    public void setCatalog(ArrayList<Movie> selectedCatalog) {
        this.catalog = selectedCatalog;
    }

    public String getLocation() {
        return location;
    }
    
    public void setLocation (String selectedLocation) {
        this.location = selectedLocation;
    }

    public ArrayList<Seat> getSeatMap(){
        return this.seatMap;
    }

    public void setSeatMap(ArrayList<Seat> seatMap){
        this.seatMap = seatMap;
    }

    // End of GETTERS and SETTERS


    public void addMovie(Movie movie){
        catalog.add(movie);
    }

    /**
     * This might have issues working if it does; to fix it we need to override 
     * equals() and hashCode() in Movie. If it works fine off the rip then disregard this comment
     */
    public void removeMovie(Movie movie){
        catalog.remove(movie);
    }
    

}