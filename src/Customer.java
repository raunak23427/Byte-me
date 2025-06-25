import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    private ItemContainer itemContainer;
    private Map<Item, Integer> cart;
    private boolean vipStatus;
    private OrderContainer orderContainer;

    public Customer(String username, String password, String role) {
        super(username, password, role);
        this.itemContainer = new ItemContainer();
        this.cart = new HashMap<>();
        this.vipStatus = role.equalsIgnoreCase("VIP");
        this.orderContainer = new OrderContainer();
    }

    public void displayLoginConfirmation() {
        System.out.println("Login successful! Welcome, " + getRole() + " " + getUsername() + ".");
    }

    public void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Customer Menu ===");
            System.out.println("1. Show All Items");
            System.out.println("2. Browse Items by Name");
            System.out.println("3. Show Items by Category");
            System.out.println("4. Show Items by Price (Ascending/Descending)");
            System.out.println("5. Add Item to Cart");
            System.out.println("6. Remove Item from Cart");
            System.out.println("7. Update Item Quantity in Cart");
            System.out.println("8. View Cart");
            System.out.println("9. Checkout");
            System.out.println("10. View Order Status");
            System.out.println("11. Cancel Order");
            System.out.println("12. View Order History");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    itemContainer.displayItems();
                    break;

                case 2:
                    System.out.print("Enter item name to search: ");
                    String name = scanner.nextLine();
                    List<Item> matchingItems = itemContainer.searchItemsByName(name);
                    if (matchingItems.isEmpty()) {
                        System.out.println("No items found.");
                    } else {
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        for (Item item : matchingItems) {
                            System.out.println(item);
                            panel.add(new JLabel(item.toString()));
                        }
                        JOptionPane.showMessageDialog(null, panel, "Matching Items", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case 3:
                    System.out.print("Enter category to search: ");
                    String category = scanner.nextLine();
                    List<Item> categoryItems = itemContainer.searchItemsByCategory(category);
                    if (categoryItems.isEmpty()) {
                        System.out.println("No items found.");
                    } else {
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        for (Item item : categoryItems) {
                            System.out.println(item);
                            panel.add(new JLabel(item.toString()));
                        }
                        JOptionPane.showMessageDialog(null, panel, "Category Items", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case 4:
                    System.out.print("Enter order (asc/desc): ");
                    String order = scanner.nextLine();
                    itemContainer.showItemsByPrice(order.equalsIgnoreCase("asc"));
                    break;

                case 5:
                    System.out.print("Enter item name to add to cart: ");
                    String addItemName = scanner.nextLine();
                    Item addItem = itemContainer.findItemByName(addItemName);
                    if (addItem != null) {
                        if ("yes".equalsIgnoreCase(addItem.getavailable())) {
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            addItemToCart(addItem, quantity);
                            System.out.println("Item added to cart.");
                        } else {
                            System.out.println("Item is not available.");
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter item name to remove from cart: ");
                    String removeItemName = scanner.nextLine();
                    Item removeItem = itemContainer.findItemByName(removeItemName);
                    if (removeItem != null) {
                        removeItemFromCart(removeItem);
                        System.out.println("Item removed from cart.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter item name to update quantity: ");
                    String updateItemName = scanner.nextLine();
                    Item updateItem = itemContainer.findItemByName(updateItemName);
                    if (updateItem != null) {
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt();
                        updateItemQuantityInCart(updateItem, newQuantity);
                        System.out.println("Item quantity updated.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 8:
                    displayCart();
                    break;

                case 9:
                    Order order1 = checkout();
                    orderContainer.addOrder(order1);
                    System.out.println("Order created: " + order1);
                    break;

                case 10:
                    viewOrderStatus();
                    break;

                case 11:
                    cancelOrder();
                    break;

                case 12:
                    viewOrderHistory();
                    break;

                case 13:
                    System.out.println("Exiting Customer Menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addItemToCart(Item item, int quantity) {
        if ("yes".equalsIgnoreCase(item.getavailable())) {
            cart.put(item, cart.getOrDefault(item, 0) + quantity);
        } else {
            System.out.println("Item is not available.");
        }
    }

    public void removeItemFromCart(Item item) {
        cart.remove(item);
    }

    public void updateItemQuantityInCart(Item item, int quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, quantity);
        }
    }

    public void displayCart() {
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
        }
    }

    public Order checkout() {
        Order order = new Order(new HashMap<>(cart), getUsername(), vipStatus, "Pending", "");
        cart.clear();
        return order;
    }

    public void viewOrderStatus() {
        List<Order> orders = orderContainer.findOrdersByUsername(getUsername());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void cancelOrder() {
        List<Order> orders = orderContainer.findOrdersByUsername(getUsername());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                orderContainer.cancelOrder(order);
            }
            System.out.println("Orders cancelled.");
        }
    }

    public void viewOrderHistory() {
        List<Order> orders = orderContainer.findOrdersByUsername(getUsername());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public Map<Item, Integer> getCart() {
        return cart;
    }
}
