package store.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import store.cart.CartItem;
import store.orders.Order;
import store.orders.dto.OrderDTO;
import store.orders.dto.OrderItemDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SaveOrder class responsible for saving orders to a JSON file.
 * Converts Order objects to OrderDTOs and writes them to a file in JSON format.
 */
public class SaveOrder {

    private final String path = "src/resources/orders.json";

    public SaveOrder(){}

    /**
     * Converts an Order object to an OrderDTO.
     * @param order the Order object to convert
     * @return the corresponding OrderDTO
     */
    private OrderDTO fromOrderToDTO(Order order) {
        List<OrderItemDTO> items = new ArrayList<>();

        for (CartItem item : order.getItems()) {
            items.add(new OrderItemDTO(
                    item.getProduct().getName(),
                    item.getProduct().getCategory().name(),
                    item.getQuantity(),
                    item.getProduct().getPrice()
            ));
        }

        return new OrderDTO(
                order.getOrderID(),
                order.getStatus().name(),
                order.getTotalAmount(),
                items
        );
    }

    /**
     * Saves an Order object to a JSON file.
     * @param order the Order object to save
     * @throws IOException if an I/O error occurs
     */
    public void saveOrderToFile(Order order) throws IOException {
        OrderDTO orderDTO = fromOrderToDTO(order);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(orderDTO);

        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(json);
            writer.write(System.lineSeparator());
        }
    }
}
