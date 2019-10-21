package presentation;

import businessLayer.ProductBLL;
import dataAccessLayer.AbstractDAO;
import dataAccessLayer.ProductDAO;
import model.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductWindow extends JFrame{

    public ProductWindow() {
        // Title
        JLabel productTitle = new JLabel("Product Operations");
        productTitle.setBounds(400, 60, 200, 20);
        productTitle.setBackground(Color.red);
        add(productTitle);

        // Buttons
        JButton addProduct = new JButton("Add New Product.");
        addProduct.setBounds(60, 284, 150, 100);
        addProduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addNewProduct();
            }
        });
        add(addProduct);
        JButton editProduct = new JButton("Edit A Product.");
        editProduct.setBounds(270, 284, 150, 100);
        editProduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editNewproduct();
            }
        });
        add(editProduct);
        JButton deleteProduct = new JButton("Delete A Product.");
        deleteProduct.setBounds(480, 284, 150, 100);
        deleteProduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deleteNewproduct();
            }
        });
        add(deleteProduct);
        JButton viewProducts = new JButton("View All Products.");
        viewProducts.setBounds(690, 284, 150, 100);
        viewProducts.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                viewNewProduct();
            }
        });
        add(viewProducts);

        // Properties of the UI
        setLayout(null);
        setTitle("productWindow");
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(980, 288, 896, 504);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addNewProduct() {
        final JFrame addNewProduct = new JFrame("addNewProduct");
        addNewProduct.setVisible(true);
        addNewProduct.setBounds(1190, 350, 500, 400);
        addNewProduct.setLayout(null);

        JLabel product_nameJL = new JLabel("product_name: ");
        final JTextField product_nameJT = new JTextField();
        JLabel product_priceJL = new JLabel ("product_price: ");
        final JTextField product_priceJT = new JTextField();
        JLabel product_countJL = new JLabel ("product_count: ");
        final JTextField product_countJT = new JTextField();

        product_nameJL.setBounds(80, 80, 100, 20);
        product_nameJT.setBounds(180, 80, 100, 20);
        product_priceJL.setBounds(80, 140, 100, 20);
        product_priceJT.setBounds(180, 140, 100, 20);
        product_countJL.setBounds(80, 200, 100, 20);
        product_countJT.setBounds(180, 200, 100, 20);

        addNewProduct.add(product_nameJL);
        addNewProduct.add(product_nameJT);
        addNewProduct.add(product_priceJL);
        addNewProduct.add(product_priceJT);
        addNewProduct.add(product_countJL);
        addNewProduct.add(product_countJT);

        JButton addTheproduct = new JButton("ADD.");
        addTheproduct.setBounds(180, 220, 100, 100);
        addTheproduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                product toAdd = new product(product_nameJT.getText(), Integer.parseInt(product_countJT.getText()), Integer.parseInt(product_priceJT.getText()));
                ProductBLL productBLL = new ProductBLL();
                productBLL.insertProduct(toAdd);
                addNewProduct.setVisible(false);
            }
        });
        addNewProduct.add(addTheproduct);
    }

    public void editNewproduct() {
        final JFrame editNewproduct = new JFrame("editNewproduct");
        editNewproduct.setVisible(true);
        editNewproduct.setBounds(1190, 350, 500, 400);
        editNewproduct.setLayout(null);

        JLabel message = new JLabel("Only enter the fields that you want to edit!");
        final JLabel id_productJL = new JLabel("id_product: ");
        final JTextField id_productJT = new JTextField();
        JLabel product_nameJL = new JLabel("product_name: ");
        final JTextField product_nameJT = new JTextField();
        JLabel product_priceJL = new JLabel ("product_price: ");
        final JTextField product_priceJT = new JTextField();
        JLabel product_countJL = new JLabel ("product_count: ");
        final JTextField product_countJT = new JTextField();
        product_priceJT.setText("-1");
        product_countJT.setText("-1");

        message.setBounds(80,20,300,20);
        id_productJL.setBounds(80,40,100,20);
        id_productJT.setBounds(180,40,100,20);
        product_nameJL.setBounds(80, 80, 100, 20);
        product_nameJT.setBounds(180, 80, 100, 20);
        product_priceJL.setBounds(80, 140, 100, 20);
        product_priceJT.setBounds(180, 140, 100, 20);
        product_countJL.setBounds(80, 200, 100, 20);
        product_countJT.setBounds(180, 200, 100, 20);

        editNewproduct.add(message);
        editNewproduct.add(id_productJL);
        editNewproduct.add(id_productJT);
        editNewproduct.add(product_nameJL);
        editNewproduct.add(product_nameJT);
        editNewproduct.add(product_priceJL);
        editNewproduct.add(product_priceJT);
        editNewproduct.add(product_countJL);
        editNewproduct.add(product_countJT);

        JButton editTheproduct = new JButton("EDIT.");
        editTheproduct.setBounds(180, 220, 100, 100);
        editTheproduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ProductDAO.updateByID(product_nameJT.getText(), Integer.parseInt(product_countJT.getText()), Integer.parseInt(product_priceJT.getText()), Integer.parseInt(id_productJT.getText()));
                editNewproduct.setVisible(false);
            }
        });
        editNewproduct.add(editTheproduct);
    }

    public void deleteNewproduct() {
        final JFrame deleteNewproduct = new JFrame("deleteNewproduct");
        deleteNewproduct.setVisible(true);
        deleteNewproduct.setBounds(1190, 350, 500, 400);
        deleteNewproduct.setLayout(null);

        JLabel message = new JLabel("Enter the id of the product that you want to delete!");
        final JLabel id_productJL = new JLabel("id_product: ");
        final JTextField id_productJT = new JTextField();

        message.setBounds(80,20,300,20);
        id_productJL.setBounds(80,40,100,20);
        id_productJT.setBounds(180,40,100,20);

        deleteNewproduct.add(message);
        deleteNewproduct.add(id_productJL);
        deleteNewproduct.add(id_productJT);

        JButton deleteTheproduct = new JButton("DELETE.");
        deleteTheproduct.setBounds(180, 220, 100, 100);
        deleteTheproduct.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ProductDAO.deleteById(Integer.parseInt(id_productJT.getText()));
                deleteNewproduct.setVisible(false);
            }
        });
        deleteNewproduct.add(deleteTheproduct);
    }

    public void viewNewProduct() {
        final JFrame viewNewProduct = new JFrame("viewNewProduct");
        viewNewProduct.setVisible(true);
        viewNewProduct.setBounds(1190, 350, 500, 400);
        viewNewProduct.setLayout(null);

        int nrOfProducts = ProductDAO.getNrOfProducts();
        Object[] products = new Object[nrOfProducts + 1];
        int j = 1;
        for (int i = 1; i <= nrOfProducts; i++)
        {
            while (ProductDAO.findById(j) == null) j++;
            if (ProductDAO.findById(j) != null) products[i] = ProductDAO.findById(j);
            j++;
        }
        AbstractDAO.viewAllObjects(products, viewNewProduct, nrOfProducts);
        repaint();
    }
}
