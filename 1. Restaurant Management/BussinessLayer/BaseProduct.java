package BussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseProduct extends MenuItem implements Serializable {
    private String baseProductName;
    private String baseProductDescription;
    private int baseProductPrice;
    private static final long serialVersionUID = 7L;

    public BaseProduct(String newBaseProductName, String newBaseProductDescription, int newBaseProductPrice) {
        baseProductName = newBaseProductName;
        baseProductDescription = newBaseProductDescription;
        baseProductPrice = newBaseProductPrice;
    }

    public String getMenuItemName() {
        return baseProductName;
    }

    public void setMenuItemName(String newName)  {
        this.baseProductName = newName;
    }

    public String getMenuItemDescription() {
        return baseProductDescription;
    }

    public void setMenuItemDescription(String newDescription)  {
        this.baseProductDescription = newDescription;
    }

    public int getMenuItemPrice() {
        return baseProductPrice;
    }

    public void setMenuItemPrice(int newPrice) {
        baseProductPrice = newPrice;
    }

    public ArrayList<MenuItem> getMenuItems() {
        ArrayList<MenuItem> aux = new ArrayList();
        aux.add(this);
        return aux;
    }
}
