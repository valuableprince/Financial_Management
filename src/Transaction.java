import java.io.Serial;
import java.io.Serializable;

public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double amount;
    private String category;

    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Transaction{amount=" + amount + ", category='" + category + "'}";
    }
}
