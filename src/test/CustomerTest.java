import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private ItemContainer itemContainer;
    private OrderContainer orderContainer;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        itemContainer = new ItemContainer();
        orderContainer = new OrderContainer();
        customer = new Customer("testUser", "password", "Customer");
    }

    @Test
    public void testOrderingOutOfStockItems() {
        Item outOfStockItem = new Item("OutOfStockItem", 100, 0, "no", "Category1");
        itemContainer.addItem(outOfStockItem);
        customer.addItemToCart(outOfStockItem, 1);
        assertFalse(customer.getCart().containsKey(outOfStockItem));
    }

    @Test
    public void testInvalidLoginAttempts() {
        User invalidUser = new User("invalidUser", "wrongPassword", "Customer");
        UserManager userManager = new UserManager();
        userManager.register("invalidUser", "wrongPassword", "Customer");
        assertTrue(invalidUser.login("invalidUser", "wrongPassword"));
        assertFalse(invalidUser.login("testUser", "wrongPassword"));
        assertFalse(invalidUser.login("invalidUser", "password"));
    }
}
