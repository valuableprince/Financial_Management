import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<BudgetCategory> categories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<BudgetCategory> getCategories() {
        return categories;
    }

    public void addCategory(BudgetCategory category) {
        categories.add(category);
        System.out.println("Категория \"" + category.getName() + "\" успешно добавлена.");
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void saveUserToFile() {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();  // Создаем папку "data", если её нет
            System.out.println("Папка 'data' была создана.");
        }

        String filePath = "data/" + username + ".ser";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(this);
            System.out.println("Данные пользователя сохранены в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных пользователя: " + e.getMessage());
        }
    }

    public static User loadUserFromFile(String username) {
        String filePath = "data/" + username + ".ser";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (User) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке данных пользователя: " + e.getMessage());
            return null;
        }
    }
}
