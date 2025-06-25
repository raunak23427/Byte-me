import javax.swing.*;
import java.io.Serializable;
import java.util.*;

public class ItemContainer implements Serializable {
    private static HashMap<String, Item> items = new HashMap<>();

    public ItemContainer() {
        items.put("Item1", new Item("Item1", 100, 10, "Yes", "Category1"));
        items.put("Item2", new Item("Item2", 200, 20, "Yes", "Category2"));
        items.put("Item3", new Item("Item3", 300, 30, "No", "Category1"));
        items.put("Item4", new Item("Item4", 400, 40, "Yes", "Category3"));
        items.put("Item5", new Item("Item5", 500, 50, "No", "Category2"));
        items.put("Item6", new Item("Item6", 600, 60, "Yes", "Category3"));
        items.put("Item7", new Item("Item7", 700, 70, "No", "Category1"));
        items.put("Item8", new Item("Item8", 800, 80, "Yes", "Category2"));
        items.put("Item9", new Item("Item9", 900, 90, "No", "Category3"));
        items.put("Item10", new Item("Item10", 1000, 100, "Yes", "Category1"));
    }

    public static void setItems(ArrayList<Item> items) {
        for (Item item : items) {
            ItemContainer.items.put(item.getName(), item);
        }
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public void removeItem(String itemName) {
        items.remove(itemName);
    }

    public static HashMap<String, Item> getItems() {
        return items;
    }

    public static List<Item> getItemsAsList() {
        return new ArrayList<>(items.values());
    }

    public static void setItemsFromList(List<Item> itemList) {
        items.clear();
        for (Item item : itemList) {
            items.put(item.getName(), item);
        }
    }

    public Item findItemByName(String name) {
        return items.get(name);
    }

    public void displayItems() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Item item : items.values()) {
            System.out.println(item);
            JLabel label = new JLabel(item.toString());
            panel.add(label);
        }
        JOptionPane.showMessageDialog(null, panel, "Items", JOptionPane.PLAIN_MESSAGE);
    }

    public List<Item> searchItemsByName(String name) {
        List<Item> matchingItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getName().contains(name)) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    public List<Item> searchItemsByCategory(String category) {
        List<Item> matchingItems = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getCategories().contains(category)) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    public void showItemsByPrice(boolean ascending) {
        List<Item> itemList = new ArrayList<>(items.values());
        itemList.sort(Comparator.comparingInt(Item::getPrice));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        if (!ascending) {
            Collections.reverse(itemList);
        }
        for (Item item : itemList) {
            System.out.println(item);
            JLabel label = new JLabel(item.toString());
            panel.add(label);
        }
        JOptionPane.showMessageDialog(null, panel, "Items by Price", JOptionPane.PLAIN_MESSAGE);
    }

    public static void displayItemsSTatic() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Item item : items.values()) {
            System.out.println(item);
            JLabel label = new JLabel(item.toString());
            panel.add(label);
        }
        JOptionPane.showMessageDialog(null, panel, "Items", JOptionPane.PLAIN_MESSAGE);
    }
}