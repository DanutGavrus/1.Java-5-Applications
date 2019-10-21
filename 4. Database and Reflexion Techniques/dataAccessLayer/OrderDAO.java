package dataAccessLayer;

import connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final static String insertStatementString = "INSERT INTO orders (order_client, order_product, order_price)"
            + " VALUES (?,?,?)";

    public static void addOrder (int id_client, int id_product, int order_price) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString);
            insertStatement.setString(1, ClientDAO.findById(id_client).getClient_name());
            insertStatement.setString(2, ProductDAO.findById(id_product).getProduct_name());
            insertStatement.setInt(3, order_price);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "orderDAO:addOrder " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
