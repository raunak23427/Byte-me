import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderContainer implements Serializable {
    private static final long serialVersionUID = 170045791434480430L;
    private static List<Order> orders;

    public OrderContainer() {
        this.orders = new ArrayList<>();
    }

    public static void setOrders(ArrayList<Order> orders) {
        OrderContainer.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public List<Order> findOrdersByUsername(String username) {
        List<Order> matchingOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUsername().equals(username)) {
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    }

    public void cancelOrder(Order order) {
        if ("Pending".equals(order.getStatus())) {
            order.setStatus("Cancelled");
        }
    }

    public void displayOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void displayOrdersStatic() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Order order : orders) {
            System.out.println(order);
            JLabel label = new JLabel(order.toString());
            panel.add(label);
        }
        JOptionPane.showMessageDialog(null, panel, "Orders", JOptionPane.PLAIN_MESSAGE);
    }

    public void updateOrderStatus(Order order, String status) {
        order.setStatus(status);
    }

    public void processRefund(Order order) {
        order.setStatus("Refunded");
    }

    public void handleSpecialRequest(Order order, String request) {
        order.setSpecialRequest(request);
    }

    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orders) {
            if ("Pending".equals(order.getStatus())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public void displayOrdersByPriority() {
        orders.sort((o1, o2) -> Boolean.compare(o2.isVipStatus(), o1.isVipStatus()));
        displayOrders();
        displayOrdersStatic();
    }

    public void generateSalesReport() {
        double totalSales = 0;
        for (Order order : orders) {
            for (Item item : order.getCart().keySet()) {
                totalSales += item.getPrice() * order.getCart().get(item);
            }
        }
        System.out.println("Total sales: $" + totalSales);
    }
}