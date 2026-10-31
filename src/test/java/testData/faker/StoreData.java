
package testData.faker;

import com.github.javafaker.Faker;
import payload.Order;

public class StoreData {

    private static final Faker faker = FakerUtility.getFaker();

    public static Order getOrderData() {

        Order order = new Order();

        order.setId(faker.number().numberBetween(1, 100000));
        order.setPetId(faker.number().numberBetween(1,4) );
        order.setQuantity(faker.number().numberBetween(1, 5));
        order.setShipDate("2026-10-18T12:00:00.000Z");
        order.setStatus("placed");
        order.setComplete(true);

        return order;
    }
}