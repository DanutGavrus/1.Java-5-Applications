package DataLayer;

import BussinessLayer.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static BussinessLayer.Restaurant.orderList;

public class RestaurantSerializator {
    public static boolean menuDeserialized;
    static File menuSer = new File (System.getProperty("user.dir") + "/Menu.ser");
    static File ordersSer = new File (System.getProperty("user.dir") + "/Orders.ser");

    public RestaurantSerializator() {

    }

    public static void serialize() {
        try {
            assert (Restaurant.restaurant.isWellFormed());
            FileOutputStream fileOutMenu = new FileOutputStream(menuSer);
            ObjectOutputStream out1 = new ObjectOutputStream(fileOutMenu);
            out1.writeObject(Restaurant.menus);
            out1.close();
            fileOutMenu.close();
            FileOutputStream fileOutOrders = new FileOutputStream(ordersSer);
            ObjectOutputStream out2 = new ObjectOutputStream(fileOutOrders);
            out2.writeObject(Restaurant.ordersHashMap);
            out2.close();
            fileOutOrders.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<MenuItem> deserializeMenu() {
        try {
            FileInputStream fileIn = new FileInputStream(menuSer);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            @SuppressWarnings("unchecked")
            ArrayList<MenuItem> menuItems = (ArrayList<MenuItem>) in.readObject();
            if (menuItems == null) {
                menuItems = new ArrayList<MenuItem>();
            }
            in.close();
            fileIn.close();
            menuDeserialized = true;
            return menuItems;
        } catch (IOException i) {
            //i.printStackTrace();
            return new ArrayList<MenuItem>();
        } catch (ClassNotFoundException c) {
            System.out.println("MenuItem Class was not found.");
            c.printStackTrace();
            return new ArrayList<MenuItem>();
        }
    }

    public static HashMap<Order, ArrayList<MenuItem>> deserializeOrders() {
        try {
            FileInputStream fileIn = new FileInputStream(ordersSer);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            @SuppressWarnings("unchecked")
            HashMap<Order, ArrayList<MenuItem>> orders = (HashMap<Order, ArrayList<MenuItem>>) in.readObject();
            if (orders == null)
                orders = new HashMap<Order, ArrayList<MenuItem>>();
            in.close();
            fileIn.close();
            orderList.clear();
            Order.auxStatic = orderList.size() + 1;
            for (Order o : orders.keySet()) {
                orderList.add(o);
            }
            return orders;
        } catch (IOException i) {
            i.printStackTrace();
            return new HashMap<Order, ArrayList<MenuItem>>();
        } catch (ClassNotFoundException c) {
            System.out.println("Order Class was not found.");
            c.printStackTrace();
            return new HashMap<Order, ArrayList<MenuItem>>();
        }
    }
}
