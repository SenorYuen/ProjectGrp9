package Implementation;

public class Receipt {
    private double amountPaid;
    private String issueDate;
    private String movieName;
    private int seatNumber;
    private int id;

    static private int idCounter = 1000;

    // Constructor
    public Receipt(double amountPaid, String issueDate, String movieName, int seatNumber){
        this.amountPaid = amountPaid;
        this.issueDate = issueDate;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.id = ++idCounter;
    }

    // Getter and Setter for pricePaid
    public double getPricePaid() {
        return amountPaid;
    }

    public void setPricePaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    // Getter for id
    public int getId(){
        return id;
    }

    // Getter and Setter for issueDate
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    // Getter and Setter for movieName
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSeatNumber(){
        return seatNumber;
    }

}
