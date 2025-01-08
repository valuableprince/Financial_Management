import java.io.Serial;
import java.io.Serializable;

public class BudgetCategory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private double budgetLimit;
    private double expenses;

    public BudgetCategory(String name, double budgetLimit) {
        this.name = name;
        this.budgetLimit = budgetLimit;
        this.expenses = 0.0;
    }

    public String getName() {
        return name;
    }

    public void addExpense(double amount) {
        expenses += amount;
    }

    @Override
    public String toString() {
        return "Category{name='" + name + "', budgetLimit=" + budgetLimit + ", expenses=" + expenses + "}";
    }
}
