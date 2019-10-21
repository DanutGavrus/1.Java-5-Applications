package dataAccessLayer;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

import connection.ConnectionFactory;
import model.client;


public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (client_name, client_age)"
            + " VALUES (?,?)";
    private final static String findStatementString = "SELECT * FROM client where id_client = ?";
    private final static String deleteStatementString = "DELETE FROM client where id_client = ?";
    private final static String updateStatementString = "UPDATE client SET client_name = ?, client_age = ? where id_client = ?";
    private final static String countStatementString = "SELECT COUNT(id_client) AS rowcount FROM client";

    public static client findById(int id_client) {
        client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id_client);
            rs = findStatement.executeQuery();
            rs.next();

            String client_name = rs.getString("client_name");
            int client_age = rs.getInt("client_age");
            toReturn = new client(id_client, client_name, client_age);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static void deleteById(int id_client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        PreparedStatement alterStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setLong(1, id_client);
            deleteStatement.executeUpdate();
            showMessageDialog(null, "Client Deleted !");

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void updateByID(String client_name, int client_age, int id_client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setLong(3, id_client);
            if (client_name.length() > 0) updateStatement.setString(1, client_name);
            else updateStatement.setString(1, findById(id_client).getClient_name());
            if (client_age != -1) updateStatement.setLong(2, client_age);
            else updateStatement.setLong(2, findById(id_client).getClient_age());
            updateStatement.executeUpdate();
            showMessageDialog(null, "Client Updated !");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:updateById " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static int insert(client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getClient_name());
            insertStatement.setInt(2, client.getClient_age());
            insertStatement.executeUpdate();
            showMessageDialog(null, "Client added !");

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
            ConnectionFactory.close(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int getNrOfClients() {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement countStatement = null;
        int countNumber = -1;
        try {
            countStatement = dbConnection.prepareStatement(countStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = countStatement.executeQuery();
            if (rs.next()) {
                countNumber = rs.getInt("rowcount");
            }
            ConnectionFactory.close(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:getNrOfClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(countStatement);
            ConnectionFactory.close(dbConnection);
        }
        return countNumber;
    }

    public static void order(int id_client, int id_product, int order_count, int order_number) {
        if (ProductDAO.findById(id_product).getProduct_count() < order_count)
            showMessageDialog(null, "Understock !");
        else {
            if(ProductDAO.decrementStock(id_product, order_count) == 1) {
                int order_price = ProductDAO.findById(id_product).getProduct_price() * order_count;
                try {
                    File file = new File (System.getProperty("user.dir") + "/Order" + order_number + ".txt");
                    PrintWriter writer = new PrintWriter(file);
                    writer.println("Ordered by: " + findById(id_client).getClient_name());
                    writer.println("Product ordered: " + ProductDAO.findById(id_product).getProduct_name());
                    writer.println("Quantity ordered: " + order_count);
                    writer.println("Order price: " + order_price);
                    writer.close();
                    showMessageDialog(null, "Order added in table !");
                    OrderDAO.addOrder(id_client, id_product, order_price);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
