package Implementation;

/*
    File Name: Movie.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Movie {
    private String name;
    private int length;
    private String start_showing;
    private String end_showing;

    // Constructor
    public Movie(String name, int length, String start_showing, String end_String){
        this.name = name;
        this.length = length;
        this.start_showing = start_showing;
        this.end_showing = end_String;
    }


    // GETTERS and SETTERS
    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int newLength){
        this.length = newLength;
    }

    public String getStartShowing(){
        return start_showing;
    }

    public void setStartShowing(String newStartShowing){
        this.start_showing = newStartShowing;
    }

    public String getEndShowing(){
        return end_showing;
    }

    public void setEndShowing(String newEndShowing){
        this.end_showing = newEndShowing;
    }


}
