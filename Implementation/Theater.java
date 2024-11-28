package Implementation;

import java.util.ArrayList;

/*
    File Name: Theater.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Theater{
    private ArrayList<Movie> catalog;
    private ArrayList<Movie> unreleasedCatalog;
    private String location;
    private ArrayList<Seat> seatMap;


    // Constructor
    public Theater(ArrayList<Movie> catalog, ArrayList<Movie> unreleasedCatalog, String location, ArrayList<Seat> seatMap){
        this.catalog = new ArrayList<Movie>();
        this.unreleasedCatalog = new ArrayList<Movie>();
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

    public ArrayList<Movie> getUnreleasedCatalog(){
        return unreleasedCatalog;
    }

    public void setUnreleasedCatalog(ArrayList<Movie> unreleasedCatalog) {
        this.unreleasedCatalog = unreleasedCatalog;
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

}