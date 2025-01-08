import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements Serializable {
    private List<Transaction> income;
    private List<Transaction> expenses;

    public Wallet() {
        income = new ArrayList<>();
        expenses = new ArrayList<>();
    }

    public List<Transaction> getIncome() {
        return income;
    }

    public List<Transaction> getExpenses() {
        return expenses;
    }
}
