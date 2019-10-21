package BussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeProduct extends MenuItem implements Serializable {
    private String compositeName;
    private String compositeDescription;
    private int compositePrice;
    private ArrayList<MenuItem> menuItems = new ArrayList();
    private static final long serialVersionUID = 7L;

    public CompositeProduct(String newCompositeName, String newCompositeDescription, int newCompositePrice) {
        compositeName = newCompositeName;
        compositeDescription = newCompositeDescription;
        compositePrice = newCompositePrice;
    }

    public void add(MenuItem newMenuItem) {
        menuItems.add(newMenuItem);
    }

    public void remove(MenuItem newMenuItem) {
        menuItems.remove(newMenuItem);
    }

    public String getMenuItemName() {
        return compositeName;
    }

    public void setMenuItemName(String newName)  {
        this.compositeName = newName;
    }

    public String getMenuItemDescription() {
        return compositeDescription;
    }

    public void setMenuItemDescription(String newDescription)  {
        this.compositeDescription = newDescription;
    }

    public int getMenuItemPrice() {
        return compositePrice;
    }

    public void setMenuItemPrice(int newPrice) {
        compositePrice = newPrice;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
}
