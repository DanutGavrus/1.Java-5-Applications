package BussinessLayer;

import DataLayer.FileWriter;
import DataLayer.RestaurantSerializator;
import PresentationLayer.AdministratorGraphicalInterface;
import PresentationLayer.ChefGraphicalUserInterface;
import PresentationLayer.WaiterGraphicalInterface;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant implements RestaurantProcessing {
    public static ChefGraphicalUserInterface chefGUI;
    public static HashMap<Order, ArrayList<MenuItem>> ordersHashMap;
    public static ArrayList<Order> orderList = new ArrayList<Order>();
    public static ArrayList<MenuItem> menus;
    public static MenuItem menu;
    public static Restaurant restaurant;

    public Restaurant() {
        try {
            this.menus = RestaurantSerializator.deserializeMenu();
            this.menu = menus.get(0);
            this.ordersHashMap = RestaurantSerializator.deserializeOrders();
        } catch (Exception e) {
            this.menus = new ArrayList<MenuItem>();
            this.menu = new CompositeProduct("Restaurant Menu", "constains all the products", 0);
            this.ordersHashMap = new HashMap<Order, ArrayList<MenuItem>>();
        }
    }

    public boolean isWellFormed() {
        for (int i = 0; i < menu.getMenuItems().size(); i++) {
            if (menu.getMenuItems().get(i).getMenuItemDescription() == null) return false;
        }
        return true;
    }

    public String createMenuItem(MenuItem product) {
        assert (isWellFormed()); // @inv
        assert (product != null); // @pre
        int initialSize = menu.getMenuItems().size();
        menu.add(product);
        assert (menu.getMenuItems().size() == initialSize + 1); // @post
        assert (isWellFormed()); // @inv
        return "Menu Item Added.";
    }

    public String deleteMenuItem(MenuItem product) {
        assert (isWellFormed()); // @inv
        assert (product != null); // @pre
        int initialSize = menu.getMenuItems().size();
        menu.remove(product);
        assert (menu.getMenuItems().size() == initialSize - 1); // @post
        assert (isWellFormed()); // @inv
        return "Menu Item Removed";
    }

    public String editMenuItem(MenuItem product, String newName, String newDescription, int newPrice) {
        assert (isWellFormed()); // @inv
        assert (product != null); // @pre
        product.setMenuItemName(newName);
        product.setMenuItemDescription(newDescription);
        product.setMenuItemPrice(newPrice);
        assert (isWellFormed()); // @inv
        return "Menu Item Edited";
    }

    public JTable viewAllMenuItems() {
        assert (isWellFormed()); // @inv
        assert (menu.getMenuItems().size() > 0); // @pre
        int nrOfRows = menu.getMenuItems().size();
        String[][] rowData = new String[nrOfRows][3];
        String[] columnNames = {"Name", "Description", "Price"};
        for (int i = 0; i < nrOfRows; i++) {
            rowData[i][0] = menu.getMenuItems().get(i).getMenuItemName();
            rowData[i][1] = menu.getMenuItems().get(i).getMenuItemDescription();
            Integer aux = menu.getMenuItems().get(i).getMenuItemPrice();
            rowData[i][2] = aux.toString();
        }
        JTable allObjectsJT = new JTable(rowData, columnNames);
        assert (isWellFormed()); // @inv
        return allObjectsJT;
    }

    public String createOrder(String[] productNames, int orderHour, int orderPrice) {
        assert (isWellFormed()); // @inv
        for (int i = 0; i < productNames.length; i++) {
            assert (productNames[i] != null); // @pre
        }
        Order aux = new Order(orderHour, orderPrice);
        for (int i = 0; i < productNames.length; i++) {
            for (int j = 0; j < menu.getMenuItems().size(); j++) {
                if (menu.getMenuItems().get(j).getMenuItemName().equals(productNames[i])) {
                    aux.addProductInOrder(menu.getMenuItems().get(j));
                }
            }
        }
        orderList.add(aux);
        ordersHashMap.put(aux, aux.getOrderProducts());
        aux.notifyTheChef();
        return "New Order Added.";
    }

    public String computeOrderBill(Order order) {
        assert (isWellFormed()); // @inv
        assert (order != null); // @pre
        FileWriter fileWriter = new FileWriter(order);
        assert (isWellFormed()); // @inv
        return "Bill Computed";
    }

    public JTable viewAllOrders() {
        assert (isWellFormed()); // @inv
        assert (ordersHashMap.size() > 0); // @pre
        int nrOfRows = orderList.size();
        String[][] rowData = new String[nrOfRows][4];
        String[] columnNames = {"Id", "Hour", "Price", "Nr of Products"};
        for (int i = 0; i < nrOfRows; i++) {
            Integer aux1 = orderList.get(i).getOrderId();
            rowData[i][0] = aux1.toString();
            Integer aux2 = orderList.get(i).getOrderHour();
            String aux22 = aux2/100 + ":" + aux2%100;
            rowData[i][1] = aux22;
            Integer aux3 = orderList.get(i).getOrderPrice();
            rowData[i][2] = aux3.toString();
            Integer aux4 = orderList.get(i).getOrderProducts().size();
            rowData[i][3] = aux4.toString();
        }
        JTable allObjectsJT = new JTable(rowData, columnNames);
        assert (isWellFormed()); // @inv
        return allObjectsJT;
    }

    public static void main(String[] args) {
        restaurant = new Restaurant();

        ordersHashMap = new HashMap<Order, ArrayList<MenuItem>>();

        if (!RestaurantSerializator.menuDeserialized) {
            MenuItem dailyMenu1 = new CompositeProduct("Daily Menu 1", "Chicken Wings & Fries", 12);
            MenuItem dailyMenu2 = new CompositeProduct("Daily Menu 2", "Beef & Mashed Potatoes", 14);
            MenuItem dailyMenu3 = new CompositeProduct("Daily Menu 3", "Pork & Rice", 16);

            MenuItem baseProduct1 = new BaseProduct("Chicken Wings", "are hot and spicy", 6);
            MenuItem baseProduct2 = new BaseProduct("Fries", "are salty", 6);
            MenuItem baseProduct3 = new BaseProduct("Beef", "is juicy", 7);
            MenuItem baseProduct4 = new BaseProduct("Mashed Potatoes", "are healthy",7 );
            MenuItem baseProduct5 = new BaseProduct("Pork", "is a little fatty",8 );
            MenuItem baseProduct6 = new BaseProduct("Rice", "is good for your healthy", 8);

            MenuItem baseProduct7 = new BaseProduct("Cola", "is sweet", 5);
            MenuItem baseProduct8 = new BaseProduct("Fanta", "is tasty", 5);
            MenuItem baseProduct9 = new BaseProduct("Sprite", "is juicy", 5);

            menu = new CompositeProduct("Restaurant`s Menu", "it contains all the products", 0);
            menus = new ArrayList<MenuItem>();

            dailyMenu1.add(baseProduct1);
            dailyMenu1.add(baseProduct2);

            dailyMenu2.add(baseProduct3);
            dailyMenu2.add(baseProduct4);

            dailyMenu3.add(baseProduct5);
            dailyMenu3.add(baseProduct6);

            menu.add(dailyMenu1);
            menu.add(dailyMenu2);
            menu.add(dailyMenu3);
            menu.add(baseProduct1);
            menu.add(baseProduct2);
            menu.add(baseProduct3);
            menu.add(baseProduct4);
            menu.add(baseProduct5);
            menu.add(baseProduct6);
            menu.add(baseProduct7);
            menu.add(baseProduct8);
            menu.add(baseProduct9);

            menus.add(menu);
        }

        AdministratorGraphicalInterface adminGUI = new AdministratorGraphicalInterface();
        WaiterGraphicalInterface waiterGUI = new WaiterGraphicalInterface();
        chefGUI = new ChefGraphicalUserInterface();
    }
}
