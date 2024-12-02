package Implementation;

/*
    File Name: Seat.java
    Completed by: Fahmi Sardar
    Development Date: November 19, 2024
*/

public class Seat{
    private int seatNumber;
    private boolean taken;
    private double price;

    // Constructor
    public Seat(int seatNumber, boolean taken, double price){
        this.seatNumber = seatNumber;
        this.taken = taken;
        this.price = price;
    }

    public Seat(Seat other){
        this.seatNumber = other.getSeatNumber();
        this.taken = other.getTaken();
        this.price = other.getPrice();
    }


    // GETTERS and SETTERS
    public int getSeatNumber(){
        return this.seatNumber;
    }

    public void setSeatNumber(int newSeatNumber){
        this.seatNumber = newSeatNumber;
    }

    public boolean getTaken(){
        return taken;
    }

    public void setTaken(boolean newStatus){
        this.taken = newStatus;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }
}