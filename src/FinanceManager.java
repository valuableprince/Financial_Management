import java.util.Scanner;

public class FinanceManager {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Добро пожаловать в Сервис управления личными финансами!");
            System.out.println("Для продолжения работы введите команду: register, login, exit: ");
            String command = scanner.nextLine();

            switch (command) {
                case "register":
                    registerUser();
                    break;
                case "login":
                    loginUser();
                    break;
                case "exit":
                    System.out.println("Выход из программы...");
                    if (currentUser != null) {
                        currentUser.saveUserToFile();
                    }
                    return;
                default:
                    System.out.println("Неизвестная команда.");
                    break;
            }
        }
    }

    private static void registerUser() {
        System.out.println("Введите логин:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (UserManager.register(username, password)) {
            currentUser = DataStore.getUser(username);
            userMenu();
        }
    }

    private static void loginUser() {
        System.out.println("Введите логин:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        currentUser = User.loadUserFromFile(username);

        if (currentUser != null && currentUser.getPassword().equals(password)) {
            System.out.println("Пользователь " + username + " успешно авторизован.");
            userMenu();
        } else {
            System.out.println("Неверный логин или пароль.");
            currentUser = null;
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("""
                \nВведите команду:
                - add_income [сумма] - добавить доход;
                - add_expense [сумма] [категория] - добавить расход;
                - add_category [категория] [бюджет] - добавить категорию с бюджетом;
                - show_balance - показать баланс;
                - show_categories - показать категории;
                - exit - выход
                """);
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            switch (parts[0]) {
                case "add_income":
                    if (parts.length == 2) {
                        double amount = Double.parseDouble(parts[1]);
                        currentUser.addTransaction(new Transaction(amount, "Income"));
                        System.out.println("Доход в размере " + amount + " добавлен.");
                    }
                    break;

                case "add_expense":
                    if (parts.length == 3) {
                        double amount = Double.parseDouble(parts[1]);
                        String category = parts[2];
                        currentUser.addTransaction(new Transaction(amount, category));
                        currentUser.getCategories().forEach(c -> {
                            if (c.getName().equals(category)) {
                                c.addExpense(amount);
                            }
                        });
                        System.out.println("Расход в размере " + amount + " добавлен в категорию " + category);
                    }
                    break;

                case "add_category":
                    if (parts.length == 3) {
                        String categoryName = parts[1];
                        double budget = Double.parseDouble(parts[2]);
                        BudgetCategory category = new BudgetCategory(categoryName, budget);
                        currentUser.addCategory(category);
                    }
                    break;

                case "show_balance":
                    double totalBalance = currentUser.getTransactions().stream()
                            .filter(t -> t.getCategory().equals("Income"))
                            .mapToDouble(Transaction::getAmount)
                            .sum();
                    System.out.println("Текущий баланс: " + totalBalance);
                    break;

                case "show_categories":
                    currentUser.getCategories().forEach(System.out::println);
                    break;

                case "exit":
                    currentUser.saveUserToFile();
                    System.out.println("Выход из программы...");
                    return;

                default:
                    System.out.println("Неизвестная команда.");
                    break;
            }
        }
    }
}
