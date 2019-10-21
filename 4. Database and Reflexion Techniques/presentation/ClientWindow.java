package presentation;

import dataAccessLayer.AbstractDAO;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.ProductDAO;
import model.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame {
    private int order_number = 0;

    public ClientWindow() {
        // Title
        JLabel clientTitle = new JLabel("Client Operations");
        clientTitle.setBounds(400, 60, 200, 20);
        clientTitle.setBackground(Color.red);
        add(clientTitle);

        // Buttons
        JButton addClient = new JButton("Add New Client.");
        addClient.setBounds(60, 284, 150, 100);
        addClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addNewClient();
            }
        });
        add(addClient);
        JButton editClient = new JButton("Edit A Client.");
        editClient.setBounds(270, 284, 150, 100);
        editClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editNewClient();
            }
        });
        add(editClient);
        JButton deleteClient = new JButton("Delete A Client.");
        deleteClient.setBounds(480, 284, 150, 100);
        deleteClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deleteNewClient();
            }
        });
        add(deleteClient);
        JButton viewClients = new JButton("View All Clients.");
        viewClients.setBounds(690, 284, 150, 100);
        viewClients.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                viewNewClient();
            }
        });
        add(viewClients);
        JButton placeOrder = new JButton("Place a new Order for a client.");
        placeOrder.setBounds(270, 124, 362, 100);
        placeOrder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                orderNewClient();
            }
        });
        add(placeOrder);

        // Properties of the UI
        setLayout(null);
        setTitle("clientWindow");
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(42, 288, 896, 504);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addNewClient() {
        final JFrame addNewClient = new JFrame("addNewClient");
        addNewClient.setVisible(true);
        addNewClient.setBounds(250, 350, 500, 400);
        addNewClient.setLayout(null);

        JLabel client_nameJL = new JLabel("client_name: ");
        final JTextField client_nameJT = new JTextField();
        JLabel client_ageJL = new JLabel ("client_age: ");
        final JTextField client_ageJT = new JTextField();

        client_nameJL.setBounds(80, 80, 100, 20);
        client_nameJT.setBounds(180, 80, 100, 20);
        client_ageJL.setBounds(80, 140, 100, 20);
        client_ageJT.setBounds(180, 140, 100, 20);

        addNewClient.add(client_nameJL);
        addNewClient.add(client_nameJT);
        addNewClient.add(client_ageJL);
        addNewClient.add(client_ageJT);

        JButton addTheClient = new JButton("ADD.");
        addTheClient.setBounds(180, 220, 100, 100);
        addTheClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                client toAdd = new client(client_nameJT.getText(), Integer.parseInt(client_ageJT.getText()));
                ClientDAO.insert(toAdd);
                addNewClient.setVisible(false);
            }
        });
        addNewClient.add(addTheClient);
    }

    public void editNewClient() {
        final JFrame editNewClient = new JFrame("editNewClient");
        editNewClient.setVisible(true);
        editNewClient.setBounds(250, 350, 500, 400);
        editNewClient.setLayout(null);

        JLabel message = new JLabel("Only enter the fields that you want to edit!");
        final JLabel id_clientJL = new JLabel("id_client: ");
        final JTextField id_clientJT = new JTextField();
        JLabel client_nameJL = new JLabel("client_name: ");
        final JTextField client_nameJT = new JTextField();
        JLabel client_ageJL = new JLabel ("client_age: ");
        final JTextField client_ageJT = new JTextField();
        client_ageJT.setText("-1");

        message.setBounds(80,20,300,20);
        id_clientJL.setBounds(80,40,100,20);
        id_clientJT.setBounds(180,40,100,20);
        client_nameJL.setBounds(80, 80, 100, 20);
        client_nameJT.setBounds(180, 80, 100, 20);
        client_ageJL.setBounds(80, 140, 100, 20);
        client_ageJT.setBounds(180, 140, 100, 20);

        editNewClient.add(message);
        editNewClient.add(id_clientJL);
        editNewClient.add(id_clientJT);
        editNewClient.add(client_nameJL);
        editNewClient.add(client_nameJT);
        editNewClient.add(client_ageJL);
        editNewClient.add(client_ageJT);

        JButton editTheClient = new JButton("EDIT.");
        editTheClient.setBounds(180, 220, 100, 100);
        editTheClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientDAO.updateByID(client_nameJT.getText(), Integer.parseInt(client_ageJT.getText()), Integer.parseInt(id_clientJT.getText()));
                editNewClient.setVisible(false);
            }
        });
        editNewClient.add(editTheClient);
    }

    public void deleteNewClient() {
        final JFrame deleteNewClient = new JFrame("deleteNewClient");
        deleteNewClient.setVisible(true);
        deleteNewClient.setBounds(250, 350, 500, 400);
        deleteNewClient.setLayout(null);

        JLabel message = new JLabel("Enter the id of the client that you want to delete!");
        final JLabel id_clientJL = new JLabel("id_client: ");
        final JTextField id_clientJT = new JTextField();

        message.setBounds(80,20,300,20);
        id_clientJL.setBounds(80,40,100,20);
        id_clientJT.setBounds(180,40,100,20);

        deleteNewClient.add(message);
        deleteNewClient.add(id_clientJL);
        deleteNewClient.add(id_clientJT);

        JButton deleteTheClient = new JButton("DELETE.");
        deleteTheClient.setBounds(180, 220, 100, 100);
        deleteTheClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientDAO.deleteById(Integer.parseInt(id_clientJT.getText()));
                deleteNewClient.setVisible(false);
            }
        });
        deleteNewClient.add(deleteTheClient);
    }

    public void orderNewClient() {
        final JFrame orderNewClient = new JFrame("orderNewClient");
        orderNewClient.setVisible(true);
        orderNewClient.setBounds(250, 350, 500, 400);
        orderNewClient.setLayout(null);

        JLabel message = new JLabel("Select the client, the product and enter the quantity!");
        JLabel id_clientJL = new JLabel("clients: ");
        final Choice id_clientCH = new Choice();
        JLabel id_productJL = new JLabel("products: ");
        final Choice id_productCH = new Choice();
        JLabel product_countJL = new JLabel ("order_count: ");
        final JTextField product_countJT = new JTextField();

        message.setBounds(80,20,300,20);
        id_clientJL.setBounds(80,40,100,20);
        id_clientCH.setBounds(180,40,100,20);
        id_productJL.setBounds(80, 80, 100, 20);
        id_productCH.setBounds(180, 80, 100, 20);
        product_countJL.setBounds(80, 140, 100, 20);
        product_countJT.setBounds(180, 140, 100, 20);

        orderNewClient.add(message);
        orderNewClient.add(id_clientJL);
        orderNewClient.add(id_clientCH);
        orderNewClient.add(id_productJL);
        orderNewClient.add(id_productCH);
        orderNewClient.add(product_countJL);
        orderNewClient.add(product_countJT);

        int nrOfClients = ClientDAO.getNrOfClients();
        int j = 1;
        for (int i = 1; i <= nrOfClients; i++)
        {
            while (ClientDAO.findById(j) == null) j++;
            if (ClientDAO.findById(j) != null) id_clientCH.add("" + j + ") " + ClientDAO.findById(j).getClient_name());
            j++;
        }

        int nrOfProducts = ProductDAO.getNrOfProducts();
        j = 1;
        for (int i = 1; i <= nrOfProducts; i++)
        {
            while (ProductDAO.findById(j) == null) j++;
            if (ProductDAO.findById(j) != null) id_productCH.add("" + j + ") " + ProductDAO.findById(j).getProduct_name());
            j++;
        }

        JButton orderTheClient = new JButton("ORDER.");
        orderTheClient.setBounds(180, 220, 100, 100);
        orderTheClient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_client, id_product, order_count;
                order_number++;
                id_client = Character.getNumericValue(id_clientCH.getSelectedItem().charAt(0));
                id_product = Character.getNumericValue(id_productCH.getSelectedItem().charAt(0));
                order_count = Integer.parseInt(product_countJT.getText());
                ClientDAO.order(id_client, id_product, order_count, order_number);
                orderNewClient.setVisible(false);
            }
        });
        orderNewClient.add(orderTheClient);
    }

    public void viewNewClient() {
        final JFrame viewNewClient = new JFrame("viewNewClient");
        viewNewClient.setVisible(true);
        viewNewClient.setBounds(250, 350, 500, 400);
        viewNewClient.setLayout(null);

        int nrOfClients = ClientDAO.getNrOfClients();
        Object[] clients = new Object[nrOfClients + 1];
        int j = 1;
        for (int i = 1; i <= nrOfClients; i++)
        {
            while (ClientDAO.findById(j) == null) j++;
            if (ClientDAO.findById(j) != null) clients[i] = ClientDAO.findById(j);
            j++;
        }
        AbstractDAO.viewAllObjects(clients, viewNewClient, nrOfClients);
        repaint();
    }
}
