package Implementation;

public class Receipt {
    private double totalAmount;
    private String issueDate;

    // Constructor
    public Receipt(String issueDate, double totalAmount){
        this.totalAmount = totalAmount;
        this.issueDate = issueDate;
    }
}
