import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private int price;
    private int qty;
    private String Available;
    private String categories;


    public Item(String name, int price, int qty, String available, String categories) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.Available = available;
        this.categories = categories;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public String getavailable() { return Available; }
    public void setAvailable(String available) { this.Available = available; }

    public  String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", available=" + Available +
                ", categories=" + categories
                +
                '}';
    }
}