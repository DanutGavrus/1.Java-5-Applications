package PresentationLayer;

import DataLayer.RestaurantSerializator;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.getRootFrame;
import static javax.swing.JOptionPane.showMessageDialog;

public class ChefGraphicalUserInterface extends JFrame implements Observer {
    private static int spacing = 60;

    public ChefGraphicalUserInterface() {
        super();
        // Properties of the UI
        setLayout(null);
        setTitle("Chef GUI");
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(650, 40, 600, 240);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel chefTitle = new JLabel("Chef Kitchen(Displays a message every time a new composite menu item is ordered.)");
        chefTitle.setBounds(40, 20, 500, 20);
        add(chefTitle);

        // Check if the menu was deserialized
        if (RestaurantSerializator.menuDeserialized) showMessageDialog(getRootFrame(), "Menu was SUCCESSFULLY DESERIALIZED!");
        else showMessageDialog(getRootFrame(), "NO FILE to deserialize the menu exists, so a menu was generated for you. Serialize the menu for changes to be saved!");
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        JLabel cooking = new JLabel(arg1 + " I`ve started to cook it.");
        cooking.setBounds(20, spacing, 500, 20);
        spacing+= 40;
        add(cooking);
        repaint();
    }
}
