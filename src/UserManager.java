import java.io.Serializable;
import java.util.ArrayList;

public class UserManager implements Serializable {
    private static ArrayList<User> users = new ArrayList<>();

    public UserManager() {
        User defaultAdmin = new Admin("admin", "admin");
        users.add(defaultAdmin);
    }

    public void register(String username, String password, String role) {
        User user;
        if (role.equalsIgnoreCase("Customer") || role.equalsIgnoreCase("VIP")) {
            user = new Customer(username, password, role);
        } else {
            user = new User(username, password, role);
        }
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public static void setUsers(ArrayList<User> u) {
        users = u;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}