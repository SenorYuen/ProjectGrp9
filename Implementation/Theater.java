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


    // Constructor
    public Theater(ArrayList<Movie> catalog, String location){
        this.catalog = new ArrayList<Movie>();
        this.location = location;
    }

    // GETTERS and SETTERS
    public ArrayList<Movie> getCatalog(){
        return catalog;
    }

    


}