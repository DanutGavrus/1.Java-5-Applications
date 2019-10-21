package BussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Order extends Observable implements Serializable {
    public static int auxStatic = 0;
    private int orderId, orderHour, orderPrice;
    private ArrayList<MenuItem> orderProducts = new ArrayList();
    private static final long serialVersionUID = 7L;

    public Order(int newOrderHour, int newOrderPrice) {
        orderId = ++auxStatic;
        orderHour = newOrderHour;
        orderPrice = newOrderPrice;
    }

    public void addProductInOrder(MenuItem product) {
        orderProducts.add(product);
    }

    public void notifyTheChef() {
        boolean containsComposite = false;
        for (int i = 0; i < orderProducts.size(); i++) {
            if (orderProducts.get(i).getClass().toString().equals("class BussinessLayer.CompositeProduct")) {
                containsComposite = true;
            }
        }
        if (containsComposite) {
            addObserver(Restaurant.chefGUI);
            setChanged();
            notifyObservers("A command containing a CompositeProduct was ordered.");
        }
    }

    public ArrayList<MenuItem> getOrderProducts() {
        return orderProducts;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderHour() {
        return orderHour;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
