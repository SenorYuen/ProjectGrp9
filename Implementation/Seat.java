package Implementation;

/*
    File Name: Seat.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Seat{
    private String row;
    private String column;
    private boolean taken;


    // Constructor
    public Seat(String row, String column, boolean taken){
        this.row = row;
        this.column = column;
        this.taken = taken;
    }



    // GETTERS and SETTERS
    public String getRow(){
        return row;
    }

    public void setRow(String newRow){
        this.row = newRow;
    }

    public String column(){
        return column;
    }

    public void setColumn(String newColumn){
        this.column = newColumn;
    }

    public boolean getTaken(){
        return taken;
    }

    public void setTaken(boolean newStatus){
        this.taken = newStatus;
    }

    

}