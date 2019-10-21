package model;

public class product {
    private int id_product;
    private String product_name;
    private int product_count;
    private int product_price;

    public product(int id_product, String product_name, int product_count, int product_price) {
        super();
        this.id_product = id_product;
        this.product_name = product_name;
        this.product_count = product_count;
        this.product_price = product_price;
    }

    public product(String product_name, int product_count, int product_price) {
        super();
        this.product_name = product_name;
        this.product_count = product_count;
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_count() {
        return product_count;
    }

    public int getProduct_price() {
        return product_price;
    }
}
