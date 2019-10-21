package BussinessLayer;

import javax.swing.*;

public interface RestaurantProcessing {
    /*
    // @invariant all products must have a description
     */

    /* Administrator Functions */
    // @pre the product item must not be null
    // @post number of menu items must be bigger than before
    String createMenuItem(MenuItem product);
    // @pre the menu item with the specified name must exist
    // @post number of menu items must be lower than before
    String deleteMenuItem(MenuItem product);
    // @pre the menu item with the specified name must exist
    String editMenuItem(MenuItem product, String newName, String newDescription, int newPrice);
    // @pre there must be at least one product
    JTable viewAllMenuItems();

    /* Waiter functions */
    // @pre the products with the specified names must exist
    String createOrder(String[] productNames, int orderHour, int orderPrice);
    // @pre the order must not be null
    String computeOrderBill(Order order);
    // @pre there must be at least one order
    JTable viewAllOrders();
}
