package DataLayer;

import BussinessLayer.Order;

import java.io.File;
import java.io.PrintWriter;

public class FileWriter {
    public FileWriter(Order order) {
        try {
            File file = new File (System.getProperty("user.dir") + "/Order" + order.getOrderId() + ".txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println("Order id: " + order.getOrderId());
            writer.println("Order hour: " + order.getOrderHour() / 100 + ":" + order.getOrderHour() % 100);
            writer.println("Order price: " + order.getOrderPrice());
            writer.println();
            writer.println("The order contains the following products: ");
            for (int i = 0; i < order.getOrderProducts().size(); i++) {
                writer.println("#" + (i + 1) + ".Menu Item Name: " + order.getOrderProducts().get(i).getMenuItemName());
                writer.println("#" + (i + 1) + ".Menu Item Description: " + order.getOrderProducts().get(i).getMenuItemDescription());
                writer.println("#" + (i + 1) + ".Menu Item Price: " + order.getOrderProducts().get(i).getMenuItemPrice());
                writer.println();
            }
            writer.println();
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
