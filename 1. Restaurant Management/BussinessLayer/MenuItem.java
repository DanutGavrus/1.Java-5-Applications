package BussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {
    public void add(MenuItem newMenuItem) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuItem newMenuItem) {
        throw new UnsupportedOperationException();
    }

    public String getMenuItemName() {
        throw new UnsupportedOperationException();
    }

    public void setMenuItemName(String newName)  {
        throw new UnsupportedOperationException();
    }

    public String getMenuItemDescription() {
        throw new UnsupportedOperationException();
    }

    public void setMenuItemDescription(String newDescription)  {
        throw new UnsupportedOperationException();
    }

    public int getMenuItemPrice() {
        throw new UnsupportedOperationException();
    }

    public void setMenuItemPrice(int newPrice) { throw new UnsupportedOperationException(); }

    public ArrayList<MenuItem> getMenuItems() {
        throw new UnsupportedOperationException();
    }
}
