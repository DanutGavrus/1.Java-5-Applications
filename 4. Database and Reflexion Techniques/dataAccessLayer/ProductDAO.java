package dataAccessLayer;

import connection.ConnectionFactory;
import model.product;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.showMessageDialog;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (product_name, product_count, product_price)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where id_product = ?";
    private final static String deleteStatementString = "DELETE FROM product where id_product = ?";
    private final static String updateStatementString = "UPDATE product SET product_name = ?, product_count = ?, product_price = ? where id_product = ?";
    private final static String countStatementString = "SELECT COUNT(id_product) AS rowcount FROM product";
    private final static String decrementStatementString = "UPDATE product SET product_count = product_count - ? WHERE id_product = ?";

    public static product findById(int id_product) {
        product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id_product);
            rs = findStatement.executeQuery();
            rs.next();

            String product_name = rs.getString("product_name");
            int product_count = rs.getInt("product_count");
            int product_price = rs.getInt("product_price");
            toReturn = new product(id_product, product_name, product_count, product_price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"productDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static void deleteById(int id_product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setLong(1, id_product);
            deleteStatement.executeUpdate();
            showMessageDialog(null, "Product Deleted !");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"productDAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void updateByID(String product_name, int product_count, int product_price, int id_product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setLong(4, id_product);
            if (product_name.length() > 0) updateStatement.setString(1, product_name);
            else updateStatement.setString(1, findById(id_product).getProduct_name());
            if (product_count != -1) updateStatement.setLong(2, product_count);
            else updateStatement.setLong(2, findById(id_product).getProduct_count());
            if (product_price != -1) updateStatement.setLong(3, product_price);
            else updateStatement.setLong(3, findById(id_product).getProduct_price());
            updateStatement.executeUpdate();
            showMessageDialog(null, "Product Updated !");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"productDAO:updateById " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static int insert(product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getProduct_name());
            insertStatement.setInt(2, product.getProduct_count());
            insertStatement.setInt(3, product.getProduct_price());
            insertStatement.executeUpdate();
            showMessageDialog(null, "Product Inserted !");

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
            ConnectionFactory.close(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "productDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int getNrOfProducts() {
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
            LOGGER.log(Level.WARNING, "ProductDAO:getNrOfProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(countStatement);
            ConnectionFactory.close(dbConnection);
        }
        return countNumber;
    }

    public static int decrementStock(int id_product, int order_count) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement decrementStatement = null;
        try {
            decrementStatement = dbConnection.prepareStatement(decrementStatementString);
            decrementStatement.setInt(1, order_count);
            decrementStatement.setInt(2, id_product);
            decrementStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:decrementStock " + e.getMessage());
            return -1;
        } finally {
            ConnectionFactory.close(decrementStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
