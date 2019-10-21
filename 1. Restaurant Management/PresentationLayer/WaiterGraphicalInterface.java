package PresentationLayer;

import BussinessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class WaiterGraphicalInterface extends JFrame {
    private String[] productNames;
    private int newPrice, i, count, auxPos;

    public WaiterGraphicalInterface() {
        // Properties of the UI
        setLayout(null);
        setTitle("Waiter GUI");
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(980, 288, 896, 504);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel waiterTitle = new JLabel("Waiter Operations");
        waiterTitle.setBounds(400, 60, 200, 20);
        add(waiterTitle);

        // Buttons
        JButton addNewOrder = new JButton("Place An Order.");
        addNewOrder.setBounds(90, 210, 180, 80);
        addNewOrder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addNewOrder();
            }
        });
        add(addNewOrder);
        JButton viewAllOrders = new JButton("View All Orders.");
        viewAllOrders.setBounds(360, 210, 180, 80);
        viewAllOrders.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                viewAllOrders();
            }
        });
        add(viewAllOrders);
        JButton computeBill = new JButton("Compute A Bill.");
        computeBill.setBounds(630, 210, 180, 80);
        computeBill.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                computeBill();
            }
        });
        add(computeBill);

        repaint();
    }

    public void addNewOrder() {
        productNames = null;
        newPrice = 0;

        final JFrame placeNewOrder = new JFrame("Place New Order");
        placeNewOrder.setVisible(true);
        placeNewOrder.setBounds(1200, 350, 500, 400);
        placeNewOrder.setLayout(null);

        JLabel order_numberJL = new JLabel("Nr Of Products: ");
        final JTextField order_numberJT = new JTextField();
        JLabel order_hourJL = new JLabel("Order Hour: ");
        final JTextField order_hourJT = new JTextField();
        JLabel order_hour_exJL = new JLabel("(Please insert 19:25 as 1925 and so on.)");

        order_numberJL.setBounds(80, 80, 160, 20);
        order_numberJT.setBounds(220, 80, 200, 20);
        order_hourJL.setBounds(80, 110, 160, 20);
        order_hourJT.setBounds(220, 110, 200, 20);
        order_hour_exJL.setBounds(80, 140, 250, 20);

        placeNewOrder.add(order_numberJL);
        placeNewOrder.add(order_numberJT);
        placeNewOrder.add(order_hourJL);
        placeNewOrder.add(order_hourJT);
        placeNewOrder.add(order_hour_exJL);

        JButton orderTheItems = new JButton("ORDER.");
        orderTheItems.setBounds(250, 220, 100, 100);
        orderTheItems.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auxPos = 0;
                count = Integer.parseInt(order_numberJT.getText());
                productNames = new String[count];
                for (i = 0; i < count; i++) {
                    final JFrame aux = new JFrame("Select A Product.");
                    aux.setVisible(true);
                    aux.setBounds(1200, 350, 500, 400);
                    aux.setLayout(null);
                    // Create the choice
                    final Choice productCH = new Choice();
                    productCH.setBounds(160,110,160,20);
                    final JLabel choiceText = new JLabel("Select a menu item: ");
                    choiceText.setBounds(160,80,160,20);
                    aux.add(productCH);
                    aux.add(choiceText);
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
                                    productNames[auxPos] = productCH.getSelectedItem();
                                    auxPos++;
                                    newPrice += Restaurant.menu.getMenuItems().get(j).getMenuItemPrice();
                                    showMessageDialog(aux, "Done. Continue ordering.");
                                    if (auxPos == count) Restaurant.restaurant.createOrder(productNames, Integer.parseInt(order_hourJT.getText()), newPrice);
                                    aux.setVisible(false);
                                }
                            }
                        }
                    });
                    aux.add(auxButton);
                }
                placeNewOrder.setVisible(false);
            }
        });
        placeNewOrder.add(orderTheItems);
    }

    public void computeBill() {
        final JFrame computeNewBill = new JFrame("Compute New Bill");
        computeNewBill.setVisible(true);
        computeNewBill.setBounds(1200, 350, 500, 400);
        computeNewBill.setLayout(null);

        JLabel order_numberJL = new JLabel("Introduce the order's id of which you want the bill: ");
        final JTextField order_numberJT = new JTextField();
        JLabel order_infoJL = new JLabel("The bill will be created as a .txt file in the main folder.");

        order_numberJL.setBounds(80, 70, 300, 20);
        order_numberJT.setBounds(80, 100, 300, 20);
        order_infoJL.setBounds(80, 130, 300, 20);

        computeNewBill.add(order_numberJL);
        computeNewBill.add(order_numberJT);
        computeNewBill.add(order_infoJL);

        JButton computeBill = new JButton("BILL.");
        computeBill.setBounds(140, 180, 180, 60);
        computeBill.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean found = false;
                for (int i = 0; i < Restaurant.orderList.size(); i++) {
                    if (Restaurant.orderList.get(i).getOrderId() == Integer.parseInt(order_numberJT.getText())) {
                        found = true;
                        Restaurant.restaurant.computeOrderBill(Restaurant.orderList.get(i));
                    }
                }
                if (!found) showMessageDialog(computeNewBill, "Order with the specified ID DOES NOT EXIST!");
                else {
                    showMessageDialog(computeNewBill, "Done.");
                    computeNewBill.setVisible(false);
                }
            }
        });
        computeNewBill.add(computeBill);
    }

    public void viewAllOrders() {
        final JFrame viewAllOrders = new JFrame("All Orders");
        viewAllOrders.setVisible(true);
        viewAllOrders.setBounds(1200, 350, 500, 400);
        viewAllOrders.setLayout(null);

        JTable allOrdersJT = Restaurant.restaurant.viewAllOrders();

        allOrdersJT.setBounds(40, 40, 400, 280);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(40,40,400,280);
        jScrollPane.getViewport().add(allOrdersJT, null);
        viewAllOrders.add(jScrollPane);
    }
}
