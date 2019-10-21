package PresentationLayer;

import BussinessLayer.MenuItem;
import BussinessLayer.BaseProduct;
import BussinessLayer.CompositeProduct;

import BussinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AdministratorGraphicalInterface extends JFrame {
    private int newPrice;

    public AdministratorGraphicalInterface() {
        // Properties of the UI
        setLayout(null);
        setTitle("Administrator GUI");
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(42, 288, 896, 504);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel adminTitle = new JLabel("Restaurant`s Administrator Operations");
        adminTitle.setBounds(350, 60, 300, 20);
        add(adminTitle);

        // Buttons
        JButton viewMenuItems = new JButton("View Menu Items.");
        viewMenuItems.setBounds(60, 128, 150, 100);
        viewMenuItems.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                viewNewMenuItem();
            }
        });
        add(viewMenuItems);
        JButton addMenuItem = new JButton("Add a Menu Item.");
        addMenuItem.setBounds(270, 128, 150, 100);
        addMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addNewMenuItem();
            }
        });
        add(addMenuItem);
        JButton editMenuItem = new JButton("Edit a Menu Item.");
        editMenuItem.setBounds(480, 128, 150, 100);
        editMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editNewMenuItem();
            }
        });
        add(editMenuItem);
        JButton deleteMenuItem = new JButton("Delete a Menu Item.");
        deleteMenuItem.setBounds(690, 128, 150, 100);
        deleteMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deleteNewMenuItem();
            }
        });
        add(deleteMenuItem);
        JButton addCompositeItem = new JButton("Add a New Composite Menu Item.");
        addCompositeItem.setBounds(270, 256, 362, 100);
        addCompositeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addNewCompositeItem();
            }
        });
        add(addCompositeItem);
        JButton serializeMenu = new JButton("SERIALIZE MENU(files will be created in this folder)");
        serializeMenu.setBounds(270, 375, 362, 50);
        serializeMenu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RestaurantSerializator.serialize();
                showMessageDialog(getFrames()[0], "Done.");
            }
        });
        add(serializeMenu);

        repaint();
    }

    public void addNewMenuItem() {
        final JFrame addNewMenuItem = new JFrame("Add New Menu Item");
        addNewMenuItem.setVisible(true);
        addNewMenuItem.setBounds(250, 350, 500, 400);
        addNewMenuItem.setLayout(null);

        JLabel product_nameJL = new JLabel("Menu Item Name: ");
        final JTextField product_nameJT = new JTextField();
        JLabel product_descriptionJL = new JLabel("Menu Item Description: ");
        final JTextField product_descriptionJT = new JTextField();
        JLabel product_priceJL = new JLabel ("Menu Item Price: ");
        final JTextField product_priceJT = new JTextField();

        product_nameJL.setBounds(80, 80, 160, 20);
        product_nameJT.setBounds(220, 80, 200, 20);
        product_descriptionJL.setBounds(80, 110, 160, 20);
        product_descriptionJT.setBounds(220, 110, 200, 20);
        product_priceJL.setBounds(80, 140, 160, 20);
        product_priceJT.setBounds(220, 140, 200, 20);

        addNewMenuItem.add(product_nameJL);
        addNewMenuItem.add(product_nameJT);
        addNewMenuItem.add(product_descriptionJL);
        addNewMenuItem.add(product_descriptionJT);
        addNewMenuItem.add(product_priceJL);
        addNewMenuItem.add(product_priceJT);

        JButton addTheItem = new JButton("ADD.");
        addTheItem.setBounds(180, 220, 100, 100);
        addTheItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MenuItem toAdd = new BaseProduct(product_nameJT.getText(), product_descriptionJT.getText(), Integer.parseInt(product_priceJT.getText()));
                Restaurant.restaurant.createMenuItem(toAdd);
                showMessageDialog(addNewMenuItem, "Done.");
                addNewMenuItem.setVisible(false);
            }
        });
        addNewMenuItem.add(addTheItem);
    }

    public void editNewMenuItem() {
        final JFrame editNewMenuItem = new JFrame("Edit A Menu Item");
        editNewMenuItem.setVisible(true);
        editNewMenuItem.setBounds(250, 350, 500, 400);
        editNewMenuItem.setLayout(null);

        JLabel product_nameJL = new JLabel("Menu Item Name: ");
        final JTextField product_nameJT = new JTextField();
        JLabel product_newNameJL = new JLabel("New Menu Item Name: ");
        final JTextField product_newNameJT = new JTextField();
        JLabel product_newDescriptionJL = new JLabel("New Item Description: ");
        final JTextField product_newDescriptionJT = new JTextField();
        JLabel product_newPriceJL = new JLabel ("New Menu Item Price: ");
        final JTextField product_newPriceJT = new JTextField();

        product_nameJL.setBounds(80, 50, 160, 20);
        product_nameJT.setBounds(220, 50, 200, 20);
        product_newNameJL.setBounds(80, 80, 160, 20);
        product_newNameJT.setBounds(220, 80, 200, 20);
        product_newDescriptionJL.setBounds(80, 110, 160, 20);
        product_newDescriptionJT.setBounds(220, 110, 200, 20);
        product_newPriceJL.setBounds(80, 140, 160, 20);
        product_newPriceJT.setBounds(220, 140, 200, 20);

        editNewMenuItem.add(product_nameJL);
        editNewMenuItem.add(product_nameJT);
        editNewMenuItem.add(product_newNameJL);
        editNewMenuItem.add(product_newNameJT);
        editNewMenuItem.add(product_newDescriptionJL);
        editNewMenuItem.add(product_newDescriptionJT);
        editNewMenuItem.add(product_newPriceJL);
        editNewMenuItem.add(product_newPriceJT);

        JButton editTheItem = new JButton("EDIT.");
        editTheItem.setBounds(180, 220, 100, 100);
        editTheItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean found = false;
                for (int j = 0; j < Restaurant.menu.getMenuItems().size(); j++) {
                    if (Restaurant.menu.getMenuItems().get(j).getMenuItemName().equals(product_nameJT.getText())) {
                        found = true;
                        String newName = product_newNameJT.getText();
                        String newDescription = product_newDescriptionJT.getText();
                        int newPrice = Integer.parseInt(product_newPriceJT.getText());
                        Restaurant.restaurant.editMenuItem(Restaurant.menu.getMenuItems().get(j), newName, newDescription, newPrice);
                    }
                }
                if (!found) showMessageDialog(editNewMenuItem, "Menu item with this name DOES NOT EXIST!");
                else showMessageDialog(editNewMenuItem, "Done.");
                editNewMenuItem.setVisible(false);
            }
        });
        editNewMenuItem.add(editTheItem);
    }

    public void deleteNewMenuItem() {
        final JFrame deleteNewMenuItem = new JFrame("Delete A Menu Item");
        deleteNewMenuItem.setVisible(true);
        deleteNewMenuItem.setBounds(250, 350, 500, 400);
        deleteNewMenuItem.setLayout(null);

        JLabel product_nameJL = new JLabel("Menu Item Name: ");
        final JTextField product_nameJT = new JTextField();

        product_nameJL.setBounds(80, 50, 160, 20);
        product_nameJT.setBounds(220, 50, 200, 20);

        deleteNewMenuItem.add(product_nameJL);
        deleteNewMenuItem.add(product_nameJT);

        JButton deleteTheItem = new JButton("DELETE.");
        deleteTheItem.setBounds(180, 220, 100, 100);
        deleteTheItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean found = false;
                for (int j = 0; j < Restaurant.menu.getMenuItems().size(); j++) {
                    if (Restaurant.menu.getMenuItems().get(j).getMenuItemName().equals(product_nameJT.getText())) {
                        found = true;
                        Restaurant.restaurant.deleteMenuItem(Restaurant.menu.getMenuItems().get(j));
                    }
                }
                if (!found) showMessageDialog(deleteNewMenuItem, "Menu item with this name DOES NOT EXIST!");
                else showMessageDialog(deleteNewMenuItem, "Done.");
                deleteNewMenuItem.setVisible(false);
            }
        });
        deleteNewMenuItem.add(deleteTheItem);
    }

    public void viewNewMenuItem() {
        final JFrame viewNewMenuItem = new JFrame("All Menu Items");
        viewNewMenuItem.setVisible(true);
        viewNewMenuItem.setBounds(250, 350, 500, 400);
        viewNewMenuItem.setLayout(null);

        JTable allObjectsJT = Restaurant.restaurant.viewAllMenuItems();

        allObjectsJT.setBounds(40, 40, 400, 280);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(40,40,400,280);
        jScrollPane.getViewport().add(allObjectsJT, null);
        viewNewMenuItem.add(jScrollPane);
    }

    public void addNewCompositeItem() {
        newPrice = 0;

        final JFrame addNewCompositeItem = new JFrame("Add New Composite Item");
        addNewCompositeItem.setVisible(true);
        addNewCompositeItem.setBounds(250, 350, 500, 400);
        addNewCompositeItem.setLayout(null);

        JLabel product_nameJL = new JLabel("Menu Item Name: ");
        final JTextField product_nameJT = new JTextField();
        JLabel product_descriptionJL = new JLabel("Menu Item Description: ");
        final JTextField product_descriptionJT = new JTextField();
        JLabel product_numberJL = new JLabel ("Nr Of Products: ");
        final JTextField product_numberJT = new JTextField();

        product_nameJL.setBounds(80, 80, 160, 20);
        product_nameJT.setBounds(220, 80, 200, 20);
        product_descriptionJL.setBounds(80, 110, 160, 20);
        product_descriptionJT.setBounds(220, 110, 200, 20);
        product_numberJL.setBounds(80, 140, 160, 20);
        product_numberJT.setBounds(220, 140, 200, 20);

        addNewCompositeItem.add(product_nameJL);
        addNewCompositeItem.add(product_nameJT);
        addNewCompositeItem.add(product_descriptionJL);
        addNewCompositeItem.add(product_descriptionJT);
        addNewCompositeItem.add(product_numberJL);
        addNewCompositeItem.add(product_numberJT);

        JButton advance = new JButton("Advance.");
        advance.setBounds(180, 220, 100, 100);
        advance.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                final MenuItem toAdd = new CompositeProduct(product_nameJT.getText(), product_descriptionJT.getText(), 0);
                int count;
                count = Integer.parseInt(product_numberJT.getText());
                for (int i = 0; i < count; i++) {
                    final JFrame aux = new JFrame("Select A Product.");
                    aux.setVisible(true);
                    aux.setBounds(250, 350, 500, 400);
                    aux.setLayout(null);
                    // Create the choice
                    final Choice productCH = new Choice();
                    productCH.setBounds(160,110,160,20);
                    final JLabel procut_infoJL = new JLabel("Select a menu item: ");
                    procut_infoJL.setBounds(160,80,160,20);
                    aux.add(productCH);
                    aux.add(procut_infoJL);
                    for (int j = 0; j < Restaurant.menu.getMenuItems().size(); j++) {
                        productCH.add(Restaurant.menu.getMenuItems().get(j).getMenuItemName());
                    }
                    JButton auxButton = new JButton("Continue.");
                    auxButton.setBounds(140, 220, 200, 60);
                    auxButton.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            for (int j = 0; j < Restaurant.menu.getMenuItems().size(); j++) {
                                if (Restaurant.menu.getMenuItems().get(j).getMenuItemName().equals(productCH.getSelectedItem())) {
                                    toAdd.add(Restaurant.menu.getMenuItems().get(j));
                                    newPrice += Restaurant.menu.getMenuItems().get(j).getMenuItemPrice();
                                    showMessageDialog(aux, "Done. Continue adding.");
                                    aux.setVisible(false);
                                    toAdd.setMenuItemPrice(newPrice);
                                }
                            }
                        }
                    });
                    aux.add(auxButton);
                }
                Restaurant.restaurant.createMenuItem(toAdd);
                addNewCompositeItem.setVisible(false);
            }
        });
        addNewCompositeItem.add(advance);
    }
}
