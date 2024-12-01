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
    private ArrayList<Receipt> receiptStorage = new ArrayList<Receipt>(1);
    private ArrayList<RegisteredCustomer> userStorage = new ArrayList<RegisteredCustomer>(1);


    // Constructor
    public Theater(ArrayList<Movie> catalog, ArrayList<Movie> unreleasedCatalog, String location, ArrayList<RegisteredCustomer> userStorage){
        this.catalog = catalog;
        this.unreleasedCatalog = unreleasedCatalog;
        this.location = location;
        this.userStorage = userStorage;
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

    public ArrayList<RegisteredCustomer> getUserStorage() {
        return this.userStorage;
    }

    public ArrayList<Receipt> getReceiptStorage() {
        return receiptStorage;
    }

    // End of GETTERS and SETTERS

}