package Implementation;

public class Receipt {
    private double amountPaid;
    private String issueDate;
    private String movieName;
    private String rowNumber;
    private String columnNumber;


    // Constructor
    public Receipt(double amountPaid, String issueDate, String movieName, String rowNumber, String columnNumber){
        this.amountPaid = amountPaid;
        this.issueDate = issueDate;
        this.movieName = movieName;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    // Getter and Setter for pricePaid
    public double getPricePaid() {
        return amountPaid;
    }

    public void setPricePaid(double amountPaid) {
        this.amountPaid = amountPaid;
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

    // Getter and Setter for rowNumber
    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    // Getter and Setter for columnNumber
    public String getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(String columnNumber) {
        this.columnNumber = columnNumber;
    }



}
