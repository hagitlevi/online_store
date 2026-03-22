/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import store.orders.Order;
import store.orders.OrderStatus;
import store.orders.dto.OrderDTO;
import store.orders.dto.OrderItemDTO;
import store.cart.CartItem;
import store.products.Category;
import store.products.Product;
import store.engine.StoreEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LoadOrders class responsible for loading orders from a JSON file.
 * Reads the file line by line, converts each line to an OrderDTO,
 * and then transforms it into an Order object.
 */
public class LoadOrders {

    private final String path = "src/resources/orders.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public LoadOrders() {}

    /**
     * Loads orders from the specified JSON file.
     * @return a list of Order objects loaded from the file
     */
    public List<Order> loadOrdersFromFile() {
        List<Order> orders = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            return orders;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                OrderDTO dto = mapper.readValue(line, OrderDTO.class);
                Order order = fromDTOToOrder(dto);
                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Converts an OrderDTO to an Order object.
     * @param orderDTO the OrderDTO to convert
     * @return the corresponding Order object
     */
    private Order fromDTOToOrder(OrderDTO orderDTO) {
        StoreEngine engine = StoreEngine.getInstance();

        List<CartItem> items = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {

            if(engine.findProductByNameAndCategory(itemDTO.getProductName(), Category.valueOf(itemDTO.getCategory())) == null){
                continue;
            }
            Product product = engine.findProductByNameAndCategory(
                    itemDTO.getProductName(),
                    Category.valueOf(itemDTO.getCategory())
            );

            if (product != null) {
                items.add(new CartItem(product, itemDTO.getQuantity()));
            }
        }

        Order order = new Order(items, orderDTO.getTotalAmount());
        order.setOrderID(orderDTO.getOrderId());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));

        return order;
    }
}
