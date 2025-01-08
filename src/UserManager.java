public class UserManager {
    public static boolean register(String username, String password) {
        if (DataStore.getUser(username) != null) {
            System.out.println("Пользователь с таким логином уже существует.");
            return false;
        }
        User user = new User(username, password);
        DataStore.addUser(user);
        System.out.println("Пользователь " + username + " зарегистрирован.");
        return true;
    }

    public static User login(String username, String password) {
        User user = DataStore.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Пользователь " + username + " успешно авторизован.");
            return user;
        } else {
            System.out.println("Неверный логин или пароль.");
            return null;
        }
    }
}
