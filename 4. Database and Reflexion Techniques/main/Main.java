package main;

import connection.ConnectionFactory;
import presentation.ClientWindow;
import presentation.ProductWindow;
import java.util.logging.Logger;

public class Main {

    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // UI
        ClientWindow clientWindow = new ClientWindow();
        ProductWindow productWindow = new ProductWindow();
    }
}
