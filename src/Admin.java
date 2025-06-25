import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    private ItemContainer itemContainer;
    private OrderContainer orderContainer;

    public Admin(String username, String password) {
        super(username, password, "Admin");
        this.itemContainer = new ItemContainer();
        this.orderContainer = new OrderContainer();
    }

    public void displayLoginConfirmation() {
        System.out.println("Login successful! Welcome, Admin " + getUsername() + ".");
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Show All Items");
            System.out.println("5. View Pending Orders");
            System.out.println("6. Update Order Status");
            System.out.println("7. Process Refund");
            System.out.println("8. Handle Special Request");
            System.out.println("9. Display Orders by Priority");
            System.out.println("10. Generate Sales Report");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    int price = scanner.nextInt();
                    System.out.print("Enter item quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter item availability: ");
                    String available = scanner.nextLine();
                    System.out.print("Enter item category: ");
                    String category = scanner.nextLine();
                    Item newItem = new Item(name, price, qty, available, category);
                    itemContainer.addItem(newItem);
                    System.out.println("Item added successfully.");
                    break;

                case 2:
                    System.out.print("Enter item name to update: ");
                    String updateName = scanner.nextLine();
                    Item itemToUpdate = itemContainer.findItemByName(updateName);
                    if (itemToUpdate != null) {
                        System.out.print("Enter new price: ");
                        itemToUpdate.setPrice(scanner.nextInt());
                        System.out.print("Enter new quantity: ");
                        itemToUpdate.setQty(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter new availability: ");
                        String newAvailable = scanner.nextLine();
                        itemToUpdate.setAvailable(newAvailable);
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();
                        itemToUpdate.setCategories(newCategory);
                        System.out.println("Item updated successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter item name to remove: ");
                    ItemContainer.displayItemsSTatic();
                    String removeName = scanner.nextLine();
                    itemContainer.removeItem(removeName);
                    System.out.println("Item removed successfully.");
                    break;

                case 4:
                    itemContainer.displayItems();
                    itemContainer.displayItemsSTatic();
                    break;

                case 5:
                    List<Order> pendingOrders = orderContainer.getPendingOrders();
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    if (pendingOrders.isEmpty()) {
                        System.out.println("No pending orders.");
                    } else {
                        for (Order order : pendingOrders) {
                            System.out.println(order);
                            JLabel label = new JLabel(order.toString());
                            panel.add(label);
                        }
                    }
                    JOptionPane.showMessageDialog(null, panel, "Pending Orders", JOptionPane.PLAIN_MESSAGE);
                    break;

                case 6:
                    System.out.print("Enter order username to update status: ");
                    String username = scanner.nextLine();
                    List<Order> ordersToUpdate = orderContainer.findOrdersByUsername(username);
                    if (ordersToUpdate.isEmpty()) {
                        System.out.println("No orders found for this username.");
                    } else {
                        System.out.print("Enter new status: ");
                        String status = scanner.nextLine();
                        for (Order order : ordersToUpdate) {
                            orderContainer.updateOrderStatus(order, status);
                        }
                        System.out.println("Order status updated.");
                    }
                    break;

                case 7:
                    System.out.print("Enter order username to process refund: ");
                    String refundUsername = scanner.nextLine();
                    List<Order> ordersToRefund = orderContainer.findOrdersByUsername(refundUsername);
                    if (ordersToRefund.isEmpty()) {
                        System.out.println("No orders found for this username.");
                    } else {
                        for (Order order : ordersToRefund) {
                            orderContainer.processRefund(order);
                        }
                        System.out.println("Refund processed.");
                    }
                    break;

                case 8:
                    System.out.print("Enter order username to handle special request: ");
                    String requestUsername = scanner.nextLine();
                    List<Order> ordersToHandle = orderContainer.findOrdersByUsername(requestUsername);
                    if (ordersToHandle.isEmpty()) {
                        System.out.println("No orders found for this username.");
                    } else {
                        System.out.print("Enter special request: ");
                        String request = scanner.nextLine();
                        for (Order order : ordersToHandle) {
                            orderContainer.handleSpecialRequest(order, request);
                        }
                        System.out.println("Special request handled.");
                    }
                    break;

                case 9:
                    orderContainer.displayOrdersByPriority();
                    break;
                case 10:
                    orderContainer.generateSalesReport();
                    break;
                case 11:
                    System.out.println("Exiting Admin Menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}