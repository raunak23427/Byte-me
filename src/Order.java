// src/Order.java
import java.io.Serializable;
import java.util.Map;

public class Order implements Serializable {
    private Map<Item, Integer> cart;
    private String username;
    private boolean vipStatus;
    private String status; // e.g., "Pending", "Processed", "Refunded"
    private String specialRequest;
    public Order(Map<Item, Integer> cart, String username, boolean vipStatus, String status, String specialRequest) {
        this.cart = cart;
        this.username = username;
        this.vipStatus = vipStatus;
        this.status = status;
        this.specialRequest = specialRequest;
    }

    public Map<Item, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Item, Integer> cart) {
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }
    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", username='" + username + '\'' +
                ", vipStatus=" + vipStatus +
                ", status='" + status + '\'' +
                ", specialRequest='" + specialRequest + '\'' +
                '}';
    }
}