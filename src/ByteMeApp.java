import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ByteMeApp {
    private static String OrderSaveFile = "Orders.ser";
    private static String ItemSaveFile = "Items.ser";
    private static String UserSaveFile = "Users.ser";
    public static void main(String[] args) throws IOException {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        File file = new File(OrderSaveFile);
        if (file.exists())
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OrderSaveFile));
            try {
                OrderContainer.setOrders((ArrayList<Order>) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        }
        file = new File(ItemSaveFile);
        if (file.exists())
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ItemSaveFile));
            try {
                ItemContainer.setItemsFromList((ArrayList<Item>) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        }
        file = new File(UserSaveFile);
        if (file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UserSaveFile));
            try {
                userManager.setUsers((ArrayList<User>) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        }

        while (true) {
            System.out.println("\n=== Welcome to Byte Me! ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    System.out.print("Enter role (VIP/Customer): ");
                    String regRole = scanner.nextLine();
                    if (!regRole.equalsIgnoreCase("VIP") && !regRole.equalsIgnoreCase("Customer")) {
                        System.out.println("Invalid role. Please choose 'VIP' or 'Customer'.");
                    } else {
                        userManager.register(regUsername, regPassword, regRole);
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User user = userManager.login(loginUsername, loginPassword);

                    if (user != null) {
                        if (user.getRole().equals("Admin")) {
                            ((Admin) user).adminMenu();
                        } else {
                            ((Customer) user).customerMenu();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using Byte Me! Goodbye.");
                    System.out.println("Saving data...");
                    saveData();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void saveData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OrderSaveFile));
        oos.writeObject(OrderContainer.getOrders());
        oos.close();
        oos = new ObjectOutputStream(new FileOutputStream(ItemSaveFile));
        oos.writeObject(ItemContainer.getItemsAsList());
        oos.close();
        oos = new ObjectOutputStream(new FileOutputStream(UserSaveFile));
        oos.writeObject(UserManager.getUsers());
        oos.close();
    }
}